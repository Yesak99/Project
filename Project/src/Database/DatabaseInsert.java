package Database;
import java.sql.*;
import java.util.ArrayList;
/* 
import account from logic package
import flights from logic package
 */


public class DatabaseInsert {
	
	// // Login to database

	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String databaseurl = "jdbc:mysql://localhost/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static final String databaseUsername = "root";
	static final String databasePassword = "javaflights123";
	

	public static boolean access;
	public ArrayList<Object> returnList;
	


	// Insert a flight into the flight table in the database

	public static void flightInsert(/*Flight Class*/ flight) {
		
		access = false;
		try {
			Class.forName(driver);

			System.out.println("Connection Success!");

			Connection connection = DriverManager.getConnection(databaseurl, databaseUsername, databasePassword);

			String query = "INSERT INTO flights(flight_number,departure_city,destination_city,depart_time,"
					+ "arrive_time,flight_date,number_seats,return_day)" + " VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setInt(1, flight.getFlightNumber());
			preparedStatement.setString(2, flight.getDepartureCity());
			preparedStatement.setString(3, flight.getDestinationCity());
			preparedStatement.setString(4, flight.getDepartTime());
			preparedStatement.setString(5, flight.getArriveTime());
			preparedStatement.setString(6, flight.getFlightDate());
			preparedStatement.setInt(7, flight.getNumberOfSeats());
			preparedStatement.setString(8, flight.getReturnFlight());
			
			preparedStatement.executeUpdate();
			
			connection.close();
				access = true;
			
		} catch (Exception e) {
			
		System.out.println("an error has occurred. :-(");
		e.printStackTrace();
		access = false;
	
		}
	}
	
	
	// Insert new booking details into the database 
	
	public static void bookingInsert(/* Booking Class */ booking) {
		try {

			Class.forName(driver);

			System.out.println("Connection Success!");

			Connection connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);

			String query = "INSERT INTO bookedflights(ticket_number,flight_number,account_id,flight_date,"
					+ "flight_time,departCity,destinationCity,passenger_username,return_flight_Date)" 
			+ " VALUES(?,?,?,?,?,?,?,?,?)";
			
			String sql ="UPDATE flights SET number_seats = number_seats -1 WHERE flight_number=" + "'" + booking.getFlight_number() + "'";
			
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
			preparedStatement2 .executeUpdate();
			
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, booking.getTicketNumber());
			preparedStatement.setInt(2, booking.getFlight_number());
			preparedStatement.setInt(3, booking.getAccount_id());
			preparedStatement.setString(4, booking.getFlight_date());
			preparedStatement.setString(5, booking.getFlight_time());
			preparedStatement.setString(6, booking.getDeparteCity());
			preparedStatement.setString(7, booking.getDestinationCity());
			preparedStatement.setString(8, booking.getPassenger_userName());
			preparedStatement.setString(9, booking.getReturnFlight());
			
			preparedStatement.executeUpdate();
		
			connection.close();
			
			AlertBox.display("We appreciate you for flying with us", "Your flight reservation is for: " + booking.getFlight_number()
			+ "\nTicket number is: " + booking.getTicketNumber());
			

		}catch (SQLIntegrityConstraintViolationException e1) {

				AlertBox.display("Duplicate Booking Alert!", "You already have a reservation booked for this flight" +
				"\nCheck that you entered the correct flight number");
				e1.printStackTrace();
				
			}catch(SQLException e2) {
				System.out.println("Syntax Error in SQL");
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}


	

}
	
	}
	
