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
import webpage_ui_locators.WebPage_UI_Element_Locators.AddEmployee_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.DashboardPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.EmployeeList_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.LoginPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.Sidemenu_Modules;
import webpage_ui_locators.WebPage_UI_Element_Locators.SystemUser_Screen;
import webpage_ui_locators.WebPage_UI_Element_Locators.ToastMessage;

public class Admin_ChangePassword_Newuser {

	WebDriver driver;
	
	@BeforeMethod
	public void openBrowser() {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--lang=en");
		driver = new ChromeDriver(option);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	 
	@Test
	public void adminAdding_NewUser() {
		
		// === Admin User Login Credentials ===
		final String Username = "Admin";
		final String Password = "admin123";
		
		// === New Employee Login Details ===
		final String fname = "Satya";
		final String lname = "Nadella";
		final String newempUsername = "satya_nadella";
		final String newempPassword = "satya_nadella123";
		
		LoginPage lpage = new LoginPage(driver);
		DashboardPage dshboard = new DashboardPage(driver);
		Sidemenu_Modules smenu = new Sidemenu_Modules(driver);
		AddEmployee_Menu addempMenu = new AddEmployee_Menu(driver);
		ToastMessage tmsg = new ToastMessage(driver);
		EmployeeList_Menu emplist = new EmployeeList_Menu(driver);
		SystemUser_Screen sysuserscr = new SystemUser_Screen(driver);
//-------------------------------------------------------------------------------------------------------		
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
		
		// === Admin Navigates To Add Employee Menu ===
		smenu.pim_module.click();
		addempMenu.add_employee_menu.click();
		
		// === Add Employee Screen Verification ===
		String addempLabel = addempMenu.addemp_namelabel.getText();
		assertEquals(addempLabel, "Add Employee", "Add Employee Screen Not Viewed:");
		
		// === Admin Adding New User On Add Employee Screen ===
		addempMenu.addemp_first_name.sendKeys(fname);
		addempMenu.addemp_last_name.sendKeys(lname);
		
		String addempid = addempMenu.addemp_emp_id.getAttribute("value");
		System.out.println("New EmployeeID: "+addempid);
		
		addempMenu.addemp_cld_toggle_btn.click();
		addempMenu.addemp_cld_unamefield.sendKeys(newempUsername);
		
		// === By Default Status Radio Button In Enable ===
		boolean radiostatus = addempMenu.addemp_cld_status_radio_btn.isSelected();
		assertTrue(radiostatus, "Status Radio Button Not Selected On Enable");
		
		addempMenu.addemp_cld_password_field.sendKeys(newempPassword);
		addempMenu.addemp_cld_confirm_password_field.sendKeys(newempPassword);
		addempMenu.addemp_cld_save_btn.click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(tmsg.toast_msg));
		String toastlabel = tmsg.toast_msg.getText();
		System.out.println("New Employee: "+toastlabel);
		
		/*Admin Verify Newly Added Employee Record Shows On Employee List Record Table*/
		// === Admin Navigates To Employee List Menu ===
		emplist.emp_list_menu.click();
		
		// === Employee List Screen Verification ===
		String emplist_label = emplist.emp_information_label.getText();
		assertEquals(emplist_label, "Employee Information", "Employee Information Screen Not Visible");
		 
		emplist.emplist_empname.sendKeys(fname);
		emplist.emplist_empid.sendKeys(addempid);
		emplist.emplist_search_btn.click();
		
		String emplist_record_txt = emplist.emplist_record_table_label.getText();
		assertEquals(emplist_record_txt, "(1) Record Found", "No Records Found");
		
		String recordid = emplist.emplist_record_table_empid.getText();
		String recordfname = emplist.emplist_record_tabel_empname.getText();
		String recordlname = emplist.emplist_record_tabel_emplastname.getText();
		System.out.println("Employee_Id: "+recordid+" Employee_FirstName: "+recordfname+" Employee_LastName: "+recordlname);
		
		// === Admin Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();
		
		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");	

//----------------------------------------------------------------------------------------------------------------------------------
		/* New Employee Logged In, Verifying The Leave Entitlement Days Added By Admin */
		// === New Employee Login ===
		lpage.login_username.sendKeys(newempUsername);
		lpage.login_password.sendKeys(newempPassword);
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt3 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt3, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing New Employee UserName ===
		String username_txt3 = dshboard.user_name_txt.getText();
		System.out.println("\nNew Employee Name: "+username_txt3);
		
		// === New Employee Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();

		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");		
//-----------------------------------------------------------------------------------------------------------------------------		
		/*New Employee Forgot Login Password, entering wrong password*/
		// === New Employee Login ===
		lpage.login_username.sendKeys(newempUsername);
		lpage.login_password.sendKeys("satya_nadella12");
		lpage.login_button.click();
		
		String invalid_txt = lpage.login_invalid_credentials_label.getText();
		assertEquals(invalid_txt, "Invalid credentials", "Invalid credentials Label Not Viewed:");
//-------------------------------------------------------------------------------------------------------		
	/*Admin user change password for new employee*/	
		// === Admin Login ===
		lpage.login_username.sendKeys(Username);
		lpage.login_password.sendKeys(Password);
		lpage.login_button.click();
				
		// === Dashboard Verification ===
		String dshboardtxt1 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt1, "Dashboard", "Dashboard Screen Not Viewed:");
				
		// === Printing Admin User Name ===
		String username_txt1 = dshboard.user_name_txt.getText();
		System.out.println("\nCurrent Admin Name: "+username_txt1);

		smenu.admin_module.click();
		
		String system_user_txt = sysuserscr.system_user_label.getText();
		assertEquals(system_user_txt, "System Users", "System Users Screen Not Viewed:");
		
		sysuserscr.system_user_name_field.sendKeys("Satya");
		for (WebElement username_list : sysuserscr.system_user_name_autolist) {
			String username_dd = username_list.getText();
				if (username_dd.equals("Satya Nadella")) {
					username_list.click();
					break;
				}
		}

		sysuserscr.system_user_search_btn.click();
		
		String sysrecord_txt = sysuserscr.system_user_record_label.getText();
		assertEquals(sysrecord_txt, "(1) Record Found","No Record Found");
		
		String Empnametxt = sysuserscr.system_record_table_empname.getText();
			if (Empnametxt.equals("Satya Nadella")) {
				sysuserscr.system_record_table_editbutton.click();
				
				String edituser_txt = sysuserscr.edit_user_label.getText();
				assertEquals(edituser_txt, "Edit User", "Edit User Screen Not Visible");
				
				sysuserscr.edit_user_empname.click();
					for (WebElement edit_empname_list : sysuserscr.edit_user_empname_autolist) {
						String edit_empname_dd = edit_empname_list.getText();
							if (edit_empname_dd.equals("Satya Nadella")) {
								edit_empname_list.click();
								break;
							}
					}
				
					sysuserscr.change_password_chkbox.click();
					sysuserscr.change_password_input.sendKeys("satya_nadella1234");
					sysuserscr.change_confirm_passwInput.sendKeys("satya_nadella1234");
					sysuserscr.edituser_save_btn.click();
			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait1.until(ExpectedConditions.visibilityOf(tmsg.toast_msg));
			String toastlabel1 = tmsg.toast_msg.getText();
			System.out.println("Change Password: "+toastlabel1);				
			}else {
				System.out.println("Satya Nadella Employee Name Not Found:");
			}

			// === Admin Logout ===
			dshboard.user_account_menu.click();
			dshboard.user_logout_dd.click();
			
			// === Verify logout success ===
			assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");	

//------------------------------------------------------------------------------------------------------------------------------
			/*New Employee Logged In, with new password*/
			// === New Employee Login ===
			lpage.login_username.sendKeys(newempUsername);
			lpage.login_password.sendKeys("satya_nadella1234");
			lpage.login_button.click();
			
			// === Dashboard Verification ===
			String dshboardtxt4 = dshboard.dashboard_label.getText();
			assertEquals(dshboardtxt4, "Dashboard", "Dashboard Screen Not Viewed:");
			
			// === Printing New Employee UserName ===
			String username_txt4 = dshboard.user_name_txt.getText();
			System.out.println("\nNew Employee Name: "+username_txt4);
			
			// === New Employee Logout ===
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
