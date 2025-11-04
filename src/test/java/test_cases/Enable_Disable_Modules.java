package test_cases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webpage_ui_locators.WebPage_UI_Element_Locators.Config_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.DashboardPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.LoginPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.Sidemenu_Modules;
import webpage_ui_locators.WebPage_UI_Element_Locators.ToastMessage;

public class Enable_Disable_Modules {
	
	WebDriver driver;
	
	@BeforeMethod
	public void openBrowser() {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--lang=en");
		driver = new ChromeDriver(option);
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
	
	@Test
	public void adminEnable_Disable_Modules() {
		
		// === Admin User Login Credentials ===
		final String Username = "Admin";
		final String Password = "admin123";
		
		LoginPage lpage = new LoginPage(driver);
		DashboardPage dshboard = new DashboardPage(driver);
		Sidemenu_Modules smenu = new Sidemenu_Modules(driver);
		Config_Menu configmenu = new Config_Menu(driver);
		ToastMessage tmsg = new ToastMessage(driver);
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
		
		//Printing Enabled SideBar Menu Modules, Before Disable Modules
		for (WebElement sidebarmenus : smenu.sidebar_menulists) {
			String sidebar_menulists = sidebarmenus.getText();
			System.out.println(sidebar_menulists);
		}

		smenu.admin_module.click();
		configmenu.admin_config_menu.click();
			for (WebElement admin_config_dd : configmenu.admin_config_dd_lists) {
				String config_ddlists = admin_config_dd.getText();
					if (config_ddlists.equals("Modules")) {
						admin_config_dd.click();
						break;
					}
			}

		String module_config = configmenu.admin_module_config_label.getText();
		assertEquals(module_config, "Module Configuration", "Module Configuration Screen Not Viewed:");

		configmenu.admin_module_toggle_btn.click();
		configmenu.pim_module_toggle_btn.click();
		configmenu.leave_module_toggle_btn.click();
		configmenu.time_module_toggle_btn.click();
		configmenu.recruitment_module_toggle_btn.click();
		configmenu.performance_module_toggle_btn.click();
		configmenu.directory_module_toggle_btn.click();
		configmenu.mainteaance_module_toggle_btn.click();
		configmenu.mobilemodule_toggle_btn.click();
		configmenu.claim_module_toggle_btn.click();
		configmenu.buzz_module_toggle_btn.click();
		configmenu.config_module_save_btn.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(tmsg.toast_msg));
		String toastlabel = tmsg.toast_msg.getText();
		System.out.println("Modules "+toastlabel);

		driver.navigate().refresh();
		
		//Printing SideBar Menu Modules, After Disable Modules
		System.out.println();
		for (WebElement sidebarmenus : smenu.sidebar_menulists) {
			String sidebar_menulists = sidebarmenus.getText();
			System.out.println(sidebar_menulists);
		}

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
