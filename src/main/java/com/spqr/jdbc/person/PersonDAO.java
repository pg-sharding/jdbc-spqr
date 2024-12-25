package com.spqr.jdbc.person;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDAO {
	
	/*
	 * add person in database
	 */
	public void addPerson(Person person) throws SQLException;
	/*
	 * remove person of database
	 */
	public void removePerson(Person person) throws SQLException;
	/*
	 * get one person by their id
	 */
	public Person getPerson(int id) throws SQLException;
}
