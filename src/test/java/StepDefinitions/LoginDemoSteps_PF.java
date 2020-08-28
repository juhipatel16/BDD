package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageFactory.HomePage_PF;
import pageFactory.LoginPage_PF;

public class LoginDemoSteps_PF {

	WebDriver driver = null;
	
	LoginPage_PF login;
	HomePage_PF home;
	
	@Given("browser is open")
	public void browser_is_open() {
	
		System.out.println("=== I am insid LoginDemoSteps_PF class ===");
		System.out.println("Inside Step: browser is open");
	    
	    String projectPath = System.getProperty("user.dir");
	    System.out.println("Project path is:" +projectPath);
	   
	    System.setProperty("webdriver.gecko.driver",projectPath+"/src/test/resources/Drivers/geckodriver.exe");
	    
	    driver = new FirefoxDriver();      
	    driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	    driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}

	@And("user is on login page")
	public void user_is_on_login_page() {
	
		driver.navigate().to("https://example.testproject.io/web/");
	}

	@When("^user enters (.*) and (.*)$")
	public void user_enters_username_and_password(String username, String password) {
		
		login = new LoginPage_PF(driver);
		
		login.enterUsername(username);
		login.enterPassword(password);
		
	//	driver.findElement(By.id("name")).sendKeys(username);
	//	driver.findElement(By.id("password")).sendKeys(password);
	}
	
	@And("user clicks on login")
	public void hits_enter() {
		 
		login.clickOnLogin();
	//	 driver.findElement(By.id("login")).click();
	}

	@Then("user is navigated to the home page")
	public void user_is_navigated_to_the_home_page() {
		
		home = new HomePage_PF(driver);
	
		home.checkLogoutIsDisabled();	
	//	driver.findElement(By.id("logout")).isDisplayed();
		
		driver.close();
		driver.quit();
	}
}
