package databaseTesting.databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test2indatabase {
	
	WebDriver driver = new ChromeDriver();
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	int employeeNumberInDataBase;
	String FirstNameInDataBase;
	String LastNameInDataBase;
	String Email;
	String Password;
	String THEWIBESITE = "http://smartbuy-me.com/account/register";
	
	@BeforeTest
	public void mySetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234");
		driver.get(THEWIBESITE);
		driver.manage().window().maximize();
	}
	@Test(priority =1,enabled=true)
	public void AddNewEmployee() throws SQLException {
		String query = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, jobTitle) VALUES (2002, 'Mohammad', 'Nour', 'x123', 'nour181@classicmodelcars.com', '1', 'Customer Service')";
		stmt = con.createStatement();
		int Insert = stmt.executeUpdate(query);
		System.out.println(Insert);
	}
	@Test(priority =2,enabled=true)
	public void UpdateEmployee() throws SQLException {
		String query = "UPDATE employees SET jobTitle = 'Senior Sales Rep' WHERE employeeNumber = 2002";
		stmt = con.createStatement();
		int update = stmt.executeUpdate(query);
		System.out.println(update);
	}
	@Test(priority =3,enabled=true)
	public void ReadTheUpdateEmployee() throws SQLException {
		String query = "SELECT * FROM employees WHERE employeeNumber = 2002";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next()) {
			 employeeNumberInDataBase = rs.getInt("employeeNumber");
			 FirstNameInDataBase = rs.getString("firstName");
			 LastNameInDataBase = rs.getString("lastName");
			 Email = rs.getString("email");
			 Password="1234asdert-57";
		}
		driver.findElement(By.id("customer[first_name]")).sendKeys(FirstNameInDataBase);
		driver.findElement(By.id("customer[last_name]")).sendKeys(LastNameInDataBase);
		driver.findElement(By.id("customer[email]")).sendKeys(Email);
		driver.findElement(By.id("customer[password]")).sendKeys(Password);
	}
	@Test(priority =4,enabled=true)
	public void DeleteEmployee() throws SQLException {
		String query = "DELETE FROM employees WHERE employeeNumber = 2002";
		stmt = con.createStatement();
		int delete = stmt.executeUpdate(query);
		System.out.println(delete);
	}

}
