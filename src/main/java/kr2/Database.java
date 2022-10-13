package kr2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
	private Connection conn;
	private Statement statement;
	
	public Database(String address, String username, String pass) throws SQLException {
		conn = DriverManager.getConnection(address, username, pass);
		statement = conn.createStatement();
	}
	
	public void close() throws SQLException {
		statement.close();
		conn.close();
	}
	
	public ArrayList<UserAddress> select() throws SQLException {
		ResultSet data = statement.executeQuery("SELECT id, second_name, first_name, address, phone FROM User_address");
		ArrayList<UserAddress> list = new ArrayList<UserAddress>();
		
		while(data.next()) {
			list.add(new UserAddress(data.getInt(1), data.getString(2), data.getString(3), data.getString(4), data.getString(5)));
		}
		
		return list;
	}
	
	public void post(UserAddress ua) throws SQLException {
		statement.execute("INSERT INTO User_address (second_name, first_name, address, phone) VALUES (" + ua.prepareInsert() + ")");
	}
	
	public void update(UserAddress ua) throws SQLException {
		statement.execute("UPDATE User_address SET " + ua.prepareUpdate());
	}
	
	public void delete(int id) throws SQLException {
		statement.execute("DELETE FROM User_address WHERE id = " + id);
	}
}
