package model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

@XmlRootElement
public class PassengerReg {
	
	
	public PassengerReg(String passengerId, String username, String password, 
			String email, String name, String surname, int age, String key) {
		this.passengerId = passengerId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
	public String getPassengerId() {
		return this.passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getKey() {
		return this.key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	static public PassengerReg decodePassengerReg(Document d) {
		String passengerId = d.getString("passengerId");
		String username = d.getString("username");
		String name = d.getString("name");
		String surname = d.getString("surname");
		String email = d.getString("email");
		String password = d.getString("password");
		int age = d.getInteger("age");
		String key = d.getString("key");
		return new PassengerReg(passengerId, username, password, email, name, surname, age, key);
	}
	
	static public Document encodePassengerReg(PassengerReg p) {
		Document pass = new Document();
		pass.append("passengerId", p.getPassengerId());
		pass.append("username", p.getUsername());
		pass.append("password", p.getPassword());
		pass.append("email",p.getEmail());
		pass.append("name", p.getName());
		pass.append("surname", p.getSurname());
		pass.append("age", p.getAge());
		pass.append("key", p.getKey());
		return pass;
	}
	
	private String passengerId;
	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private int age;
	private String key;
	
}
