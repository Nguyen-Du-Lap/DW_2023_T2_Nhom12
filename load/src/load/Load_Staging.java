package load;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Load_Staging {

	ConnectDB connect;

	public void load(String id) throws Exception {
//		1. . Load file config init  có tên config.properties
		Properties properties = new Properties();
		FileInputStream inputFile = new FileInputStream("config.properties");
		properties.load(inputFile);
		String db_name = properties.getProperty("database_name");
		String server = properties.getProperty("server_name");
		String port = properties.getProperty("port");
		String user = properties.getProperty("username");
		String pass = properties.getProperty("password");
//		2. Kết nối database	control
		connect = new ConnectDB();
		Connection connection = connect.getConnection(db_name, server, port, user, pass);
//		Kiểm tra kết nối có thành công hay không?
		if (connect.isConnect(db_name, server, port, user, pass)) {
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
//					6. Kiểm tra status= Default || PE || CE || PL  
					if (status.equals("Default") || status.equals("PE") || status.equals("CE") || status.equals("PL")) {
//					  7. Kiểm tra status=CE 
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
					   			String a= "";
					   			try {
//								   	12. Kiểm tra có thành công hay không?
//						   			Trường hợp yes
//					   				13. Cleaning dữ liệu
					   				String clean_dt = "call clean_data()";
					   				try(CallableStatement call_clean = connect_staging.prepareCall(clean_dt)){
					   					ResultSet res_clean = call_clean.executeQuery();
//					   					Chuyển dữ liệu từ  staging.exchange_rate_temp vào staging.exchange_rate
					   					String transfer = "call transfer_data()";
						   				try(CallableStatement call_transfer = connect_staging.prepareCall(transfer)){
						   					ResultSet res_transfer = call_transfer.executeQuery();
						   				}catch (Exception e) {
											// TODO: handle exception
										}
						   			}catch (Exception e) {
										// TODO: handle exception
									}

						
					   			} catch (Exception e) {
									// TODO: handle exception
								}
//						Trường hợp no
							}else{
//							Cập nhật status = FL 
							update_status(connection, id_config, "FL");
//							Ghi log vào table log trong database control
							insert_Log(connection, id_config);
//						Đóng kết nối
							connection.close();
							return;
						}
					} else {
						connection.close();
					}
				}
			}
			
				}

		} else {
//	    Trường hợp no
//			 logError("Connection to the database failed");
		}
	}

	public void insert_Log(Connection connection, String id) throws Exception {
//		Ghi log vào table log trong database control
		String insert_log = "insert into log(config_id, name, row_count, status, data_range_from, data_range_to, message, create_at) "
				+ "values(" + id + ",'Load không thành công', '0', 'FL', 'file excel', 'Database_Staging', 'Không load thành công', now())";
		PreparedStatement pre_insertLog = connection.prepareStatement(insert_log);
		pre_insertLog.executeUpdate();
	}
	public void update_status(Connection connection, String id, String status)throws Exception {
		String update = "call update_config(?,?)";
		try(CallableStatement call_update = connection.prepareCall(update)){
			call_update.setString(1, id);
			call_update.setString(2, status);
			call_update.executeUpdate();
		}
	}
	public static void main(String[] args) throws Exception {
		Load_Staging load = new Load_Staging();
		load.load();
	}

}
