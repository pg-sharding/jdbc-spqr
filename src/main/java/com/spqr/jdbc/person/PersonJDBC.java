package com.spqr.jdbc.person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PersonJDBC implements PersonDAO{
	
	private Connection connection;
	
	public PersonJDBC(String url, String user, String password) throws ClassNotFoundException, SQLException {
		//load driver communication of postgresql.
		Class.forName("org.postgresql.Driver");
		//open the connection
		this.connection = DriverManager.getConnection(url, user, password);
	}

	public void executeSQL(String query) throws SQLException {
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(query);
		stmt.close();
	}

	public void createRelation() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS person (id INTEGER PRIMARY KEY, name TEXT, identity TEXT, birthday TEXT)";
		Statement stmt = this.connection.createStatement();
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public void addPerson(Person person) throws SQLException {
		//query of postgresql
		String sql = "insert into person(id, name, identity, birthday)"
				+ "values (?,?,?,?)";
		
		PreparedStatement ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		
		ps.setInt(1, person.getId());
		ps.setString(2, person.getName());
		ps.setString(3, person.getIdentity());
		ps.setString(4, person.getBirthday());
		
		//use execute update when the database return nothing
		ps.executeUpdate();
	}

	public void removePerson(Person person) throws SQLException {
		String sql = "delete from person where id = ?";
		PreparedStatement ps = this.connection.prepareStatement(sql);
		ps.setInt(1, person.getId());
		ps.execute();
	}

	public Person getPerson(int id) throws SQLException {
		PreparedStatement stmt = this.connection.prepareStatement("select * from person where id = ?");
		stmt.setInt(1, id);
		ResultSet result = stmt.executeQuery();
		if (result.next()) {
			Person person = new Person();
			person.setName(result.getString("name"));
			person.setId(result.getInt("id"));
			person.setIdentity(result.getString("identity"));
			person.setBirthday(result.getString("birthday"));
			return person;
		}
		return null;
	}
}
