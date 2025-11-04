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
import webpage_ui_locators.WebPage_UI_Element_Locators.Entitlement_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.LoginPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.Sidemenu_Modules;
import webpage_ui_locators.WebPage_UI_Element_Locators.ToastMessage;

public class Admin_Adding_Newuser_LeaveEntitlementDays {

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
	public void adminAdding_LeaveEntitlementDays() throws InterruptedException {

		// === Admin User Login Credentials ===
		final String Username = "Admin";
		final String Password = "admin123";
		
		LoginPage lpage = new LoginPage(driver);
		DashboardPage dshboard = new DashboardPage(driver);
		Sidemenu_Modules smenu = new Sidemenu_Modules(driver);
		AddEmployee_Menu addempMenu = new AddEmployee_Menu(driver);
		ToastMessage tmsg = new ToastMessage(driver);
		EmployeeList_Menu emplist = new EmployeeList_Menu(driver);
		Entitlement_Menu entitle_menu = new Entitlement_Menu(driver);
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
		addempMenu.addemp_first_name.sendKeys("Tim");
		addempMenu.addemp_last_name.sendKeys("Cook");
		
		String addempid = addempMenu.addemp_emp_id.getAttribute("value");
		System.out.println("New EmployeeID: "+addempid);
		
		addempMenu.addemp_cld_toggle_btn.click();
		addempMenu.addemp_cld_unamefield.sendKeys("tim_cook");
		
		// === By Default Status Radio Button In Enable ===
		boolean radiostatus = addempMenu.addemp_cld_status_radio_btn.isSelected();
		assertTrue(radiostatus, "Status Radio Button Not Selected On Enable");
		
		addempMenu.addemp_cld_password_field.sendKeys("tim_cook123");
		addempMenu.addemp_cld_confirm_password_field.sendKeys("tim_cook123");
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
		 
		emplist.emplist_empname.sendKeys("Tim");
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
		
//--------------------------------------------------------------------------------------------------------------------------------------------------------		
		// === New Employee Login ===
		lpage.login_username.sendKeys("tim_cook");
		lpage.login_password.sendKeys("tim_cook123");
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt1 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt1, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing New Employee UserName ===
		String username_txt1 = dshboard.user_name_txt.getText();
		System.out.println("\nNew Employee Name: "+username_txt1);
		
		// === New Employee My Entitlement Screen ===
		smenu.leave_module.click();
		
		entitle_menu.entitlement_dd_menu.click();
		for (WebElement entitle_dd_list : entitle_menu.entitlement_dd_menu_list) {
			String entitle_dd = entitle_dd_list.getText();
				if (entitle_dd.equals("My Entitlements")) {
					entitle_dd_list.click();
					break;
				}
		}
		
		String myentitle_txt = entitle_menu.my_leave_entitlement_label.getText();	
		assertEquals(myentitle_txt, "My Leave Entitlements", "My Leave Entitlements Screen Not Viewed:");
		
		String total_leave_txt = entitle_menu.my_entitle_totalday_label.getText();
		if (total_leave_txt.equals("Total 0.00 Day(s)")) {
			System.out.println("No Leave Entitlements Days Added:");
		} else {
			System.out.println("Leave Entitlements Days Added:");
		}
		
		// === New Employee Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();

		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");	
		
//--------------------------------------------------------------------------------------------------------------------------------------------------------				
		// === Admin Login ===
		lpage.login_username.sendKeys(Username);
		lpage.login_password.sendKeys(Password);
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt2 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt2, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing Admin User Name ===
		String username_txt2 = dshboard.user_name_txt.getText();
		System.out.println("\nCurrent Admin Name: "+username_txt2);
		
		smenu.leave_module.click();
		
		entitle_menu.entitlement_dd_menu.click();
		for (WebElement entitle_dd_list : entitle_menu.entitlement_dd_menu_list) {
			String entitle_dd = entitle_dd_list.getText();
				if (entitle_dd.equals("Add Entitlements")) {
					entitle_dd_list.click();
					break;
				}
		}
		
		String add_leaventitle_txt = entitle_menu.add_leave_entitlement_label.getText();
		assertEquals(add_leaventitle_txt, "Add Leave Entitlement", "Add Leave Entitlement Screen Not Viewed:");
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait1.until(ExpectedConditions.elementToBeClickable(entitle_menu.add_leave_entitle_empname));
		entitle_menu.add_leave_entitle_empname.sendKeys("Tim");
			for (WebElement addleave_empname_list : entitle_menu.add_leave_entitle_empname_list) {
				String addleave_empname_dd = addleave_empname_list.getText();
					if (addleave_empname_dd.equals("Tim Cook")) {
						addleave_empname_list.click();
						break;
					}
			}
		
		entitle_menu.add_leave_entitle_leavetype.click();	
			for (WebElement leavetype_list : entitle_menu.add_leave_entitle_leavetype_list) {
				String leavetype_dd = leavetype_list.getText();
					if (leavetype_dd.equals("CAN - Personal")) {
						leavetype_list.click();
						break;
					}
			}
		
		entitle_menu.add_leave_entitle_field.sendKeys("12");
		entitle_menu.add_leave_entitle_savebtn.click();
		
		String popup = entitle_menu.add_leave_entitle_dialogpoup.getText();
		System.out.println(popup);
		
		entitle_menu.add_leave_entitle_dialogsavebtn.click();
		String toastmsg1 =tmsg.toast_msg.getText();
		System.out.println("Leave Entitlement Days Added: "+toastmsg1);
		
		// === Admin Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();

		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");	

//--------------------------------------------------------------------------------------------------------------------------------------------------------				
		/* New Employee Logged In, Verifying The Leave Entitlement Days Added By Admin */
		// === New Employee Login ===
		lpage.login_username.sendKeys("tim_cook");
		lpage.login_password.sendKeys("tim_cook123");
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt3 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt3, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing New Employee UserName ===
		String username_txt3 = dshboard.user_name_txt.getText();
		System.out.println("\nNew Employee Name: "+username_txt3);
		
		// === New Employee My Entitlement Screen ===
		smenu.leave_module.click();
		
		entitle_menu.entitlement_dd_menu.click();
		for (WebElement entitle_dd_list : entitle_menu.entitlement_dd_menu_list) {
			String entitle_dd = entitle_dd_list.getText();
				if (entitle_dd.equals("My Entitlements")) {
					entitle_dd_list.click();
					break;
				}
		}
		
		String myentitle_txt1 = entitle_menu.my_leave_entitlement_label.getText();	
		assertEquals(myentitle_txt1, "My Leave Entitlements", "My Leave Entitlements Screen Not Viewed:");
		
		String total_leave_txt1 = entitle_menu.my_entitle_totalday_label.getText();
		if (total_leave_txt1.equals("Total 0.00 Day(s)")) {
			System.out.println("No Leave Entitlements Days Added:");
		} else {
			System.out.println("Leave Entitlements Days Added:");
		}
				
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
