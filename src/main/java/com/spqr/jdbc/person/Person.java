package com.spqr.jdbc.person;

public class Person {
	
	/*
	 * id of database
	 */
	private int id;
	
	/*
	 * person name
	 */
	private String name;
	
	/*
	 * person identity
	 */
	private String identity;
	
	/*
	 * person birthday
	 */
	private String birthday;
	
	//constructor
	public Person() {
		super();
	}

	public Person(int id, String name, String identity, String birthday) {
		super();
		this.id = id;
		this.name = name;
		this.identity = identity;
		this.birthday = birthday;
	}

	//Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Person other = (Person) obj;
		if (this.id != other.getId()) {
			return false;
		}

        if ((this.name == null) ? (other.getName() != null) : !this.name.equals(other.getName())) {
            return false;
        }

        if ((this.birthday == null) ? (other.getBirthday() != null) : !this.birthday.equals(other.getBirthday())) {
            return false;
        }

		if ((this.identity == null) ? (other.getIdentity() != null) : !this.identity.equals(other.getIdentity())) {
            return false;
        }

        return true;
    }
}
