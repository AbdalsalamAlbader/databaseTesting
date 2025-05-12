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

public class AppTest {
	
	WebDriver driver = new ChromeDriver();
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	int customerNumberInDataBase;
	String CustomerFirstNameInDataBase;
	String CustomerLastNameInDataBase;
	String email;
	String password;
	String website = "http://smartbuy-me.com/account/register";
	
	@BeforeTest
	public void mtSetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234");
		driver.get(website);
		driver.manage().window().maximize();
		
	}
	@Test(priority =1,enabled =true)
	public void AddNewCustomer() throws SQLException {
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (9997, 'Abc company', 'Ali', 'Ahmad', '962795034344', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00)";
		stmt = con.createStatement();
		int rowInserted = stmt.executeUpdate(query);
		System.out.println(rowInserted);
		
	}
	@Test(priority =2,enabled =true)
	public void UpdateCustomerInfo() throws SQLException {
		String query = "UPDATE customers SET contactLastName = 'Mohammad' WHERE customerNumber = 9997";
		stmt = con.createStatement();
		int rowInserted = stmt.executeUpdate(query);
		System.out.println(rowInserted);
		
	}
	@Test(priority =3,enabled = true)
	public void ReadTheUpdatedData() throws SQLException {
		String query = "select * from customers where customerNumber =9997";
		
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		while(rs.next()) {
			 customerNumberInDataBase  = rs.getInt("customerNumber");
			
			 CustomerFirstNameInDataBase = rs.getString("contactFirstName").toString().trim();
			
			 CustomerLastNameInDataBase = rs.getString("contactLastName") ;
			
			 email =CustomerFirstNameInDataBase+CustomerLastNameInDataBase+"@gmail.com" ;
			
			 password = "1233456" ;
			
			
			
			System.out.println(customerNumberInDataBase);
			System.out.println(CustomerFirstNameInDataBase);
			System.out.println(CustomerLastNameInDataBase);
			System.out.println(email);
			System.out.println(password);
		}
		driver.findElement(By.id("customer[first_name]")).sendKeys(CustomerFirstNameInDataBase);
		driver.findElement(By.id("customer[last_name]")).sendKeys(CustomerLastNameInDataBase);
		driver.findElement(By.id("customer[email]")).sendKeys(email);
		driver.findElement(By.id("customer[password]")).sendKeys(password);
	}
	@Test(priority =4,enabled =true)
	public void DeleteCustomer() throws SQLException {
		String query = "delete from customers where customerNumber =9997";
		stmt = con.createStatement();
		int rowInserted = stmt.executeUpdate(query);
		System.out.println(rowInserted);
	}

	
}
