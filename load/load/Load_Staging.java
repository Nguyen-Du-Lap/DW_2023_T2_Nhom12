package load;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class Load_Staging {
	String db_name = "";
	String server = "";
	String port = "";
	String user = "";
	String pass = "";
	String save_log = "";
	ConnectDB connect;

	public void load(String id) throws Exception {
//		1. . Load file config init  có tên config.properties
		Properties properties = new Properties();
		properties = new Properties();
	    try (InputStream inputStream = Load_Staging.class.getClassLoader().getResourceAsStream("config.properties")) {
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
		try(Connection connection = connect.getConnection(db_name, server, port, user, pass)) {
//		Trường hợp yes
//			 4. Load các trường dữ liệu trong table config có flag = 1 và id= id từ database control
			String queryString = "call load_config(?)";
			try(CallableStatement callable = connection.prepareCall(queryString)){
				callable.setString(1, id);
				ResultSet res = callable.executeQuery();
				while (res.next()) {
//					 5. Lấy ra 1 dòng dữ liệu
					String status = res.getString("status");
					String id_config = res.getString("id");
					String data_staging = res.getString("database_name_staging");
					String server_staging = res.getString("server_name");
					String port_staging = res.getString("port");
					String user_staging = res.getString("username");
					String pass_staging = res.getString("password");
					String location  = res.getString("location");
					String format = res.getString("format");
					String seperator  = res.getString("separator");
				
//					6. Kiểm tra status= Default || PE || CE || PL  
					if (status.equals("Default") || status.equals("PE") || status.equals("CE") || status.equals("PL")) {
//					  7. Kiểm tra status= CE || PL 
					
						if (status.equals("CE") || status.equals("PL")) {
//							8. Cập nhật status = PL
						
							update_status(connection, id_config, "PL");
						
//							9. Kết nối database staging			
							Connection connect_staging = connect.getConnection(data_staging, server_staging, port_staging,
									user_staging, pass_staging);
						
//						10. Kiểm tra kết nối có thành công hay không?
							if (connect.isConnect(data_staging, server_staging, port_staging, user_staging, pass_staging)) {
//				Trường hợp yes
//			      11. Dựa vào dữ liệu config để load dữ liệu từ file excel vào table exchange_rate_temp trong data_staging
					   	String load_data_excel= "load data infile ? into table exchange_rate_temp CHARACTER SET utf8 fields terminated by ?  lines terminated by '\n' ignore 1 rows (currency,name,exchange_rate,date);";			   			
					   	try(PreparedStatement preLoad = connect_staging.prepareStatement(load_data_excel)) {
					   	String fullLocate = location + "." + format;
					
					   		preLoad.setString(1, fullLocate);
					   		preLoad.setString(2, seperator);
					   		preLoad.executeUpdate();
					   	
//					   		12. Kiểm tra có load thành công hay không?	//Trường hợp yes
//					   				13. Cleaning dữ liệu
					   				String clean_dt = "call clean_data()";
					   				try(CallableStatement call_clean = connect_staging.prepareCall(clean_dt)){
					   					int res_clean = call_clean.executeUpdate();
					   				
//					   					14. Chuyển dữ liệu từ  staging.exchange_rate_temp vào staging.exchange_rate
					   					String transfer = "call transfer_data(?,?)";
					   			
						   				try(CallableStatement call_transfer = connect_staging.prepareCall(transfer)){							   				
						   			        LocalDate currentDate = LocalDate.now();
						   			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd");
						   			        String formattedDate = currentDate.format(formatter);
						   					call_transfer.setString(1, id);
						   					call_transfer.setString(2, formattedDate);
						   					int res_transfer = call_transfer.executeUpdate();						   					
//						   					15. Kiểm tra có thành công hay không? = Trường hợp yes
//						   					16. Cập nhật status = CL 
											  update_status(connection, id_config, "CL");
//													17. Ghi log vào table log trong database control
											  insert_Log(connection, id_config, "Đã load thành công", res_transfer, "CL", "Load thành công");
//											  28. Xóa file  D://datawarehouse/data.xlsx
												  File fileExcelDelete = new File(fullLocate);
												
												  if(fileExcelDelete.exists()) {
													  fileExcelDelete.delete();
												  }
//											  18. Đóng kết nối
													connection.close();
													connect_staging.close();
													return;
						   				}
						   			}

						
					   			} catch (Exception e) {
					   				// Trường hợp no của bước 12
//					   				19. Cập nhật status = FL
					   				update_status(connection, id_config, "FL");
//					   				20. Ghi log vào table log trong database control
					   				insert_Log(connection,id_config,"Load không thành công",0,"FL",e.getMessage());
//									21. Đóng kết nối
					   				
					   				connection.close();
					   				connect_staging.close();
					   				return;
					   			}
//						Trường hợp no của bước 10
							}else{
//							22. Cập nhật status = FL 
							update_status(connection, id_config, "FL");
//							23. Ghi log vào table log trong database control
							insert_Log(connection, id_config, "Load không thành công", 0, "FL", "Không load thành công do không kết nối database staging không được");
//						    24. Đóng kết nối
							
							connection.close();
							return;
						}
					} else {
//						27. Cập nhật status = FL 
						update_status(connection, id_config, "FL");
//						20. Ghi log vào table log trong database control 
						insert_Log(connection, id_config, "Load không thành công", 0, "FL", "Không load thành công do chưa extract thành công.");
						connection.close();
						return;
					}
				}
//					Trường hợp no của bước 6
					else {
						
//						25. Đóng kết nối
						
					connection.close();
					return;
				}
					
			}
				
				}
		} catch (Exception e2) {
			// TODO: handle exception
//	    Trường hợp no của bước 3
//			26. Ghi log ra file lưu ở D://datawarehouse/log
			// Lấy ra ngày giờ hiện tại

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.SSSSSSSSS");
			String currentDateTime = LocalDateTime.now().format(formatter);
			Path logDirectory = Paths.get("D:\\datawarehouse\\log\\");
			Files.createDirectories(logDirectory);
			createFileWithIncrementedName(save_log, "LogError" + currentDateTime+ ".txt", "Module : Load /n Error : " + e2.getMessage());
//			 logError("Connection to the database failed");
		}
	}

	public void insert_Log(Connection connection, String id, String name, int row_count, String status, String message) throws Exception {
	    String insert = "call insert_log(?, ?, ?, ?, ?, ?, ?)";
	    try (CallableStatement call_insert = connection.prepareCall(insert)) {
	        call_insert.setString(1, id);
	        call_insert.setString(2, name);
	        call_insert.setInt(3, row_count);
	        call_insert.setString(4, status);
	        call_insert.setString(5, "file Excel"); // Replace with actual value
	        call_insert.setString(6, "Staging");   // Replace with actual value
	        call_insert.setString(7, message);

	        call_insert.executeUpdate();
	    }
	}
	public void update_status(Connection connection, String id, String status)throws Exception {
		String update = "call update_config(?,?)";
		try(CallableStatement call_update = connection.prepareCall(update)){
			call_update.setString(1, id);
			call_update.setString(2, status);
			call_update.executeUpdate();
		}
	}


	private static void createFileWithIncrementedName(String filePath, String fileName, String data) {
		Path path = Paths.get(filePath+"/"+fileName);
  
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))) {
			// Ghi dữ liệu vào file
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static void main(String[] args) throws Exception {
		Load_Staging load = new Load_Staging();
		String id_source = args.length > 0 ? args[0] : "1";
		load.load("1");
	}

}
