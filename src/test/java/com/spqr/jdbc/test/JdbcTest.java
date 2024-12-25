package com.spqr.jdbc.test;

import com.spqr.jdbc.person.Person;
import com.spqr.jdbc.person.PersonJDBC;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class JdbcTest {
    // @Before
    // public void SetupSharding() throws SQLException, ClassNotFoundException {
    //     String host = System.getProperty("pgHost"),
    //            port = System.getProperty("pgPort"),
    //            user = "spqr-console",
    //            db = "spqr-console";
    //     String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, db);
    //     PersonJDBC pjdbc = new PersonJDBC(url, user, "");
    //     String query = "CREATE DISTRIBUTION ds1 COLUMN TYPES INTEGER";
    //     pjdbc.executeSQL(query);
    // }

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

        // TODO: create relation

        // insert into first shard
        Person person = new Person(1, "Chloe", "ZAA21", "10/10/1980");
        pjdbc.addPerson(person);

        Person dbPerson = pjdbc.getPerson(1);
        assertEquals(person, dbPerson);

        // insert into second shard
        Person other = new Person(100, "Jim", "NI4BB", "10/10/1980");
        pjdbc.addPerson(other);

        dbPerson = pjdbc.getPerson(100);
        assertEquals(other, dbPerson);

        pjdbc.removePerson(person);

        dbPerson = pjdbc.getPerson(1);

        assertEquals(null, dbPerson);
    }
}
