package com.spqr.jdbc.test;

import com.spqr.jdbc.person.Person;
import com.spqr.jdbc.person.PersonJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JdbcTest {
    @Test
    public void testCRUD() throws SQLException, ClassNotFoundException {
        String host = System.getProperty("pgHost"),
                port = System.getProperty("pgPort"),
                db = System.getProperty("pgDb");

        String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, db);
        //user default
        String user = System.getProperty("pgUser");
        //your password. 12345678 is default
        String password = System.getProperty("pgPassword");
        PersonJDBC pjdbc = new PersonJDBC(url, user, password);

        pjdbc.createRelation();

        Person person = new Person();
        person.setName("Chloe");
        person.setIdentity("ZAA21");
        person.setBirthday("10/10/1980");
        pjdbc.addPerson(person);

        ArrayList<Person> array = pjdbc.getAllPersons();

        assertEquals(1, array.size());
        assertEquals("Chloe", array.get(0).getName());
        assertEquals("ZAA21", array.get(0).getIdentity());
        assertEquals("10/10/1980", array.get(0).getBirthday());

        pjdbc.removePerson(person);

        array = pjdbc.getAllPersons();

        assertEquals(0, array.size());
    }
}
