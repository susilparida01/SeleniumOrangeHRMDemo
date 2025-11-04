package test_cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webpage_ui_locators.WebPage_UI_Element_Locators.DashboardPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.LoginPage;

public class Login_AdminUser {

	WebDriver driver;

	@BeforeMethod
	public void openBrowser() {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	@Test
	public void adminLogin() {
		// === Admin User Login Credentials ===
		final String Username = "Admin";
		final String Password = "admin123";
		
		LoginPage lpage = new LoginPage(driver);
		DashboardPage dshboard = new DashboardPage(driver);
//------------------------------------------------------------------------------------------------------------------------		
		// === Admin Login ===
		lpage.login_username.sendKeys(Username);
		lpage.login_password.sendKeys(Password);
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing Admin User Name ===
		String username_txt = dshboard.user_name_txt.getText();
		System.out.println("Current Admin Name: "+username_txt);
		
		// === Admin Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();
		
		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");		
	}

	@AfterMethod
	public void quitBrowser() {
		
		driver.quit();
	}
	
}
