package com.zetatronics.tutorial;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MercuryTours_AutomatedTest {
	
	private WebDriver driver;
	By registerLinkLocator = By.linkText("REGISTER");
	By registerPageLocator = By.xpath("//img[@src='/images/masts/mast_register.gif']");
	By userNameLocator = By.id("email"); 
	By passwordLocator = By.name("password");
	By confirmPasswordLocator = By.cssSelector("input[name='confirmPassword']");
	By userLocator = By.name("userName");
	By passLocator = By.name("password");
	By signInbtnLocator = By.name("login");
	By homePageLocator = By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']");
	
	
	By registerBtnLocator = By.name("register");

	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
	}

	@Test
	public void registerUser() throws InterruptedException {
		
		driver.findElement(registerLinkLocator).click();
		Thread.sleep(2000);
		
		if(driver.findElement(registerPageLocator).isDisplayed()) {
			
			driver.findElement(userNameLocator).sendKeys("GabTest1");
			driver.findElement(passwordLocator).sendKeys("123456");
			driver.findElement(confirmPasswordLocator).sendKeys("123456");	
			
			driver.findElement(registerBtnLocator).click();	
		} else {
			
			JOptionPane.showMessageDialog(null, "Page was not found");	
		}
		
		// como la pagina despues del regitso muestra un mensaje el mismo esta en fonts dentro de 6 elementos, por eso la lista
		
		List <WebElement> fonts = driver.findElements(By.tagName("font"));
		
		assertEquals("Note: Your user name is GabTest1.", fonts.get(5).getText());
		
	}

	// el proximo paso es ir a la pagina y loguearse con el usuario realizado
	
	@Test
	
	public void signIn() throws InterruptedException {
		
		if(driver.findElement(userLocator).isDisplayed()) {
			
			driver.findElement(userLocator).sendKeys("GabTest1");
			driver.findElement(passLocator).sendKeys("123456");;
			driver.findElement(signInbtnLocator).click();
			Thread.sleep(2000);
			assertTrue(driver.findElement(homePageLocator).isDisplayed());
			}
		
		else {
				
				JOptionPane.showMessageDialog(null, "Username textBox was not present");
			}			
	}
	
}//fin de la clase
