package Database;

    
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	
	
	/* 
	import account from logic package
	import bookings from logic package
	import flights from logic package
	 */

		// Login to database
		public class GetDatabase {
		static final String driver = "com.mysql.cj.jdbc.Driver";
		static final String databaseurl = "jdbc:mysql://localhost/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		static final String databaseUsername = "root";
		static final String databasePassword = "javaflights123";

		//Accesses an account in the database
		
		public static /*Enter Account class*/ GetAccount(String usern) {
			
			/*Account*/ useraccount = new /*Account*/();

			try {
				Class.forName(driver);

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
			return useraccount;
		}
	
		// Access all flights in the database
		public static ObservableList/*<Flight Class>*/getFlights() {
			
			 ObservableList/*<Flight Class>*/flights = FXCollections.observableArrayList();

			
			try {

				Class.forName(driver);

				Connection connection = DriverManager.getConnection(databaseurl, databaseUsername, databasePassword);

				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM flights");

				ResultSet flightresults = preparedStatement.executeQuery();
				
				

				while (flightresults.next()) {
					
					/* INSERT FLIGHT CLASS */
					
					// Flight flight = new Flight(); 

//					flight.setflightnum(flightresults.getInt("flight_number"));
//					flight.setDepartureCity(flightresults.getString("departure_city"));
//					flight.setDestinationCity(flightresults.getString("destination_city"));
//					flight.setDepartTime(flightresults.getString("depart_time"));
//					flight.setArriveTime(flightresults.getString("arrive_time"));
//					flight.setFlightDate(flightresults.getString("flight_date"));
//					flight.setReturnFlight(flightresults.getString("return_day"));
//					flight.setNumberOfSeats(flightresults.getInt("number_seats"));
//					
//					flights.add(flight);
				}

				connection.close();

			} catch (Exception e1) {
				e1.printStackTrace();

			}
			return flights;
			
		}
		
		// Get flight bookings 
		public static ObservableList/*<Booking class>*/retrieveBookings(int account_id) {

			 ObservableList/*<Booking class>*/ bookings = FXCollections.observableArrayList();

			
			try {

				Class.forName(driver);

				Connection connection = DriverManager.getConnection(databaseurl, databaseUsername, databasePassword);

				PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM bookedflights WHERE account_id=" + "'" + account_id + "'");

				ResultSet bookingresults = preparedStatement.executeQuery();

				while (bookingresults.next()) {
					
					/*INSERT BOOKING CLASS */
					
					/*Booking class */ booked = new /*Booking class */ 
//													(bookingresults.getInt("ticket_number"),
////												(bookingresults.getInt("flight_number")),
////												(bookingresults.getString("flight_date")),
////												(bookingresults.getString("flight_time")),
////												(bookingresults.getString("departCity")),
////												(bookingresults.getString("destinationCity")),
////												(bookingresults.getString("return_flight_date")));
////							
//					bookings.add(booked);

				}

				connection.close();

			} catch (Exception e1) {
				e1.printStackTrace();

			}
		
		return bookings;

		}
	
	

}
