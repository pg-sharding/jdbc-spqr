import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		if (args.length != 5) {
			System.err.println("Incorrect arguments, please provide host, port, database, user and password");
			System.exit(1);
		}

		String host = args[0],
				port = args[1],
				db = args[2];

		//you need create database with this name 'github-example-jdbc'
		String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, db);
		//user default
		String user = args[3];
		//your password. root is default
		String password = args[4];
		PersonJDBC pjdbc = new PersonJDBC(url, user, password);

		pjdbc.createRelation();
		
		Person person = new Person();
		person.setName("Chloe");
		person.setIdentity("ZAA21");
		person.setBirthday("10/10/1980");
		pjdbc.addPerson(person);
		
		
		ArrayList<Person> array = pjdbc.getAllPersons();
		
		for (Person i : array) {
			System.out.println(i.getName()+ ", your id is "+ i.getId()+ 
					", "+ i.getBirthday());
		}
		
		System.out.println(pjdbc.getPerson("Chloe").getName());
		pjdbc.removePerson(person);
	}

}
