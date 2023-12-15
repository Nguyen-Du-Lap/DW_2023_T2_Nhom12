package loadAggregate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Load_Aggregate {
	String db_name = "";
	String server = "";
	String port = "";
	String user = "";
	String pass = "";
	String save_log = "";
	ConnectDB connect;

	public void loadAggregate(String id) throws Exception {
//		1. . Load file config init  có tên config.properties
		Properties properties = new Properties();
		properties = new Properties();
		try (InputStream inputStream = Load_Aggregate.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (inputStream == null) {
				throw new FileNotFoundException("config.properties not found in the classpath");
			}
			properties.load(inputStream);
			server = properties.getProperty("server_name");
			port = properties.getProperty("port");
			user = properties.getProperty("username");
			pass = properties.getProperty("password");
			db_name = properties.getProperty("database_name");
			save_log = properties.getProperty("save_log");
		}
//		2. Kết nối database	control
		connect = new ConnectDB();
//		3. Kiểm tra kết nối có thành công hay không?
		try (Connection connection = connect.getConnection(db_name, server, port, user, pass)) {
//		Trường hợp yes
//			 4. Load các trường dữ liệu trong table config có flag = 1 và id= id từ database control
			String queryString = "call load_config(?)";
			try (CallableStatement callable = connection.prepareCall(queryString)) {
				callable.setString(1, id);
				ResultSet res = callable.executeQuery();
				while (res.next()) {
//					 5. Lấy ra 1 dòng dữ liệu
					String status = res.getString("status");
					String id_config = res.getString("id");
					String data_warehouse = res.getString("database_name_warehouse");
					String server_warehouse = res.getString("server_name");
					String port_warehouse = res.getString("port");
					String user_warehouse = res.getString("username");
					String pass_warehouse = res.getString("password");
					String location = res.getString("location");
					String format = res.getString("format");
					String seperator = res.getString("separator");

//					6. Kiểm tra status = Default || PE || CE || PL || CL || PT| CT || PA 
					if (status.equals("Default") || status.equals("PE") || status.equals("CE") || status.equals("PL")
							|| status.equals("CL") || status.equals("PT") || status.equals("CT")
							|| status.equals("PA")) {
//					  Trường hợp yes
//						7. Kiểm tra status= CT || PA
						if (status.equals("CT") || status.equals("PA")) {
//							Trường hợp yes
//							8. Cập nhật status = PA
							update_status(connection, id_config, "PA");

//							9. Kết nối database warehouse 	
							Connection connect_warehouse = connect.getConnection(data_warehouse, server_warehouse,
									port_warehouse, user_warehouse, pass_warehouse);
//						10. Kiểm tra kết nối có thành công hay không?
							if (connect.isConnect(data_warehouse, server_warehouse, port_warehouse, user_warehouse,
									pass_warehouse)) {
//				Trường hợp yes
//			      11. Dựa vào dữ liệu từ bảng fact và dim để load dữ liệu vào bảng aggregate trong database warehouse 
								String load_aggregate = "call InsertAggregateData()";
								try (PreparedStatement preLoad = connect_warehouse.prepareStatement(load_aggregate)) {
									int row_load = preLoad.executeUpdate();

//					   		12. Kiểm tra có load thành công hay không?	//Trường hợp yes
//									13. Cập nhật status = CA
									update_status(connection, id_config, "CA");
//					   		14. Ghi log vào table log trong database control
									insert_Log(connection, id_config, "Load aggregate thành công", row_load, "CA",
											"Load aggregate thành công");
//									15. Đóng kết nối
									connection.close();
									connect_warehouse.close();
									return;
								} catch (Exception e) {
									// Trường hợp no của bước 12
//					   				19. Cập nhật status = FA
									update_status(connection, id_config, "FA");
//					   				20. Ghi log vào table log trong database control
									insert_Log(connection, id_config, "Load aggregate không thành công", 0, "FA",
											e.getMessage());
//									21. Đóng kết nối
									connection.close();
									connect_warehouse.close();
									return;
								}
//						Trường hợp no của bước 10
							} else {
//							16. Cập nhật status = FA 
								update_status(connection, id_config, "FA");
//							17. Ghi log vào table log trong database control
								insert_Log(connection, id_config, "Load aggregate không thành công", 0, "FA",
										"Không load aggregate thành công do không kết nối database warehouse không được");
//						    18. Đóng kết nối

								connection.close();
								return;
							}
						}
						// Trường hợp no của bước 7
						else {
//						22. Cập nhật status = FA
							update_status(connection, id_config, "FA");
//						20. Ghi log vào table log trong database control 
							insert_Log(connection, id_config, "Load aggregate không thành công", 0, "FA",
									"Không load aggregate thành công do chưa transform thành công.");
//						21. Đóng kết nối
							connection.close();
							return;
						}
					}
//					Trường hợp no của bước 6
					else {
//						23. Đóng kết nối
						connection.close();
						return;
					}

				}

			}
		} catch (Exception e2) {
			
//	    Trường hợp no của bước 3
//			24. Ghi log ra file lưu ở D://datawarehouse/log
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.SSSSSSSSS");
			String currentDateTime = LocalDateTime.now().format(formatter);
			Path logDirectory = Paths.get("D:\\datawarehouse\\log\\");
			Files.createDirectories(logDirectory);
			createFileWithIncrementedName(save_log, "LogError" + currentDateTime + ".txt",
					"Module : Load /n Error : " + e2.getMessage());
		}
	}

	public void insert_Log(Connection connection, String id, String name, int row_count, String status, String message)
			throws Exception {
		String insert = "call insert_log(?, ?, ?, ?, ?, ?, ?)";
		try (CallableStatement call_insert = connection.prepareCall(insert)) {
			call_insert.setString(1, id);
			call_insert.setString(2, name);
			call_insert.setInt(3, row_count);
			call_insert.setString(4, status);
			call_insert.setString(5, "warehouse"); // Replace with actual value
			call_insert.setString(6, "aggregate"); // Replace with actual value
			call_insert.setString(7, message);

			call_insert.executeUpdate();
		}
	}

	public void update_status(Connection connection, String id, String status) throws Exception {
		String update = "call update_config(?,?)";
		try (CallableStatement call_update = connection.prepareCall(update)) {
			call_update.setString(1, id);
			call_update.setString(2, status);
			call_update.executeUpdate();
		}
	}
// viết dữ liệu vào file
	private static void createFileWithIncrementedName(String filePath, String fileName, String data) {
		Path path = Paths.get(filePath + "/" + fileName);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
			// Ghi dữ liệu vào file
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Load_Aggregate load = new Load_Aggregate();
		String id_source = args.length > 0 ? args[0] : "1";
		load.loadAggregate(id_source);
	}

}
