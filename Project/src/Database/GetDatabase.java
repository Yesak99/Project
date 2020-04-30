package Database;

    
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import javafx.collections.ObservableList;
	
	/* 
	import businessLogicLayer.Account;
	import businessLogicLayer.Booking;
	import businessLogicLayer.Flight;
	import businessLogicLayer.Flight.*;
	
	 */

		// Login to database
		public class GetDatabase {
		static final String databaseURL = "com.mysql.cj.jdbc.Driver";
		static final String databaseUsername = "root";
		static final String databasePassword = "javaflights123";

		//Accesses an account in the database
		public static /*Enter Account class*/ GetAccount(String usern) {
			
			/*Account*/ account = new /*Account*/();

			try {

				Class.forName("java.sql.Driver");

				Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM account WHERE username=" + "'" + usern + "'");

				ResultSet res = preparedStatement.executeQuery();

				while (res.next()) {

					account.setaccount_id(res.getInt("account_id"));
					account.setUserName(res.getString("username"));
					account.setPassword(res.getString("password"));
					account.setFirstName(res.getString("firstname"));
					account.setLastName(res.getString("lastname"));
					account.setAddress(res.getString("address"));
					account.setState(res.getString("state"));
					account.setEmail(res.getString("email"));
					account.setZipCode(res.getInt("zipcode"));
					account.setSsn(res.getInt("ssn"));
					account.setSecurityQuestion(res.getString("security_q"));
					account.setSecurityAnswer(res.getString("security_a"));
		
				}
				connection.close();

			} catch (Exception e) {
				e.printStackTrace();

			}
			return account;
		}
	
	// Access all flights in the database
		public static ObservableList<Flight> getFlights() {
			
			 Observable<Flight> flights = FXCollections.observableArrayList();

			
			try {

				Class.forName("java.sql.Driver");

				Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM flights");

				ResultSet rs = preparedStatement.executeQuery();
				
				

				while (rs.next()) {
					
					Flight flight = new Flight();

					flight.setflightnum(rs.getInt("flight_number"));
					flight.setDepartureCity(rs.getString("departure_city"));
					flight.setDestinationCity(rs.getString("destination_city"));
					flight.setDepartTime(rs.getString("depart_time"));
					flight.setArriveTime(rs.getString("arrive_time"));
					flight.setFlightDate(rs.getString("flight_date"));
					flight.setReturnFlight(rs.getString("return_day"));
					flight.setNumberOfSeats(rs.getInt("num_seats"));
					
					flights.add(flight);
				}

				connection.close();

			} catch (Exception e1) {
				e1.printStackTrace();

			}
			return flights;
			
		}
		public static Connection getConnection() throws Exception {
			
			try {
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; // local host can be changed to your ip address of your server if yyou're not using local
				String username = "root";
				String password = "javaflights123";
				Class.forName(driver);
				
				Connection conn = DriverManager.getConnection(url,username,password);
				System.out.println("Connected");
				return conn;
			} catch(Exception e){System.out.println(e);}
				
			return null; 
		}
	

}
