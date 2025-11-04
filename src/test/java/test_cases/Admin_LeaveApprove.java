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
import webpage_ui_locators.WebPage_UI_Element_Locators.ApplyLeave_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.DashboardPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.EmployeeList_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.Entitlement_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.LeaveList_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.LoginPage;
import webpage_ui_locators.WebPage_UI_Element_Locators.MyLeave_Menu;
import webpage_ui_locators.WebPage_UI_Element_Locators.Sidemenu_Modules;
import webpage_ui_locators.WebPage_UI_Element_Locators.ToastMessage;

public class Admin_LeaveApprove {
	
	WebDriver driver;
	
	@BeforeMethod
	public void openBrowser() {
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void adminLeave_Approve() throws InterruptedException {
		
		// === Admin User Login Credentials ===
		final String Username = "Admin";
		final String Password = "admin123";
		
		// === New Employee Login Details ===
		final String fname = "Roy";
		final String lname = "Jakobs";
		final String newempUsername = "roy_jakobs";
		final String newempPassword = "roy_jakobs123";
		
		LoginPage lpage = new LoginPage(driver);
		DashboardPage dshboard = new DashboardPage(driver);
		Sidemenu_Modules smenu = new Sidemenu_Modules(driver);
		AddEmployee_Menu addempMenu = new AddEmployee_Menu(driver);
		ToastMessage tmsg = new ToastMessage(driver);
		EmployeeList_Menu emplist = new EmployeeList_Menu(driver);
		Entitlement_Menu entitle_menu = new Entitlement_Menu(driver);
		ApplyLeave_Menu applymenu = new ApplyLeave_Menu(driver);
		MyLeave_Menu myleavemenu = new MyLeave_Menu(driver);
		LeaveList_Menu leavelistmenu = new LeaveList_Menu(driver);
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
		
		/*Admin Adding New User On Add Employee Screen*/
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
		entitle_menu.add_leave_entitle_empname.sendKeys("Roy");
			for (WebElement addleave_empname_list : entitle_menu.add_leave_entitle_empname_list) {
				String addleave_empname_dd = addleave_empname_list.getText();
					if (addleave_empname_dd.equals("Roy Jakobs")) {
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

//----------------------------------------------------------------------------------------------------------------------------		
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

		// New Employee Applying Leave
		applymenu.apply_leave_menu.click();
		
		String apply_leave_txt = applymenu.apply_leave_label.getText();
		assertEquals(apply_leave_txt, "Apply Leave", "Apply Leave Screen Not Visible");
		
		applymenu.leavetype_dd.click();
			for (WebElement leavetype_list : applymenu.leavetype_dd_list) {
				String leavetype_list_txt = leavetype_list.getText();
					if (leavetype_list_txt.equals("CAN - Personal")) {
						leavetype_list.click();
						break;
					}
			}
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait2.until(driver -> applymenu.leavebalance_label.getText().contains("12.00"));
		String leaveBalance_txt1 = applymenu.leavebalance_label.getText();
		System.out.println("Leave Balance - Before Leave Apply: " + leaveBalance_txt1);

		applymenu.from_date_field.click();
		applymenu.fromdate_select.click();
		applymenu.comment_box.sendKeys("Testing Purpose");
		applymenu.apply_btn.click();
		
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait3.until(ExpectedConditions.visibilityOf(tmsg.toast_msg));
		String toastlabel1 = tmsg.toast_msg.getText();
		System.out.println("Leave Applied "+toastlabel1);
		
		applymenu.leavetype_dd.click();
		for (WebElement leavetype_list : applymenu.leavetype_dd_list) {
			String leavetype_list_txt = leavetype_list.getText();
				if (leavetype_list_txt.equals("CAN - Personal")) {
					leavetype_list.click();
					break;
				}
		}
		
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait4.until(driver -> !applymenu.leavebalance_label.getText().equals(leaveBalance_txt1));
		String leaveBalance_txt2 = applymenu.leavebalance_label.getText();
		System.out.println("Leave Balance - After Leave Apply: " + leaveBalance_txt2);
		
		/* After Applying Leave From Apply Leave, Need To Verify Applied Leave Record Appears On My Leave List */
		myleavemenu.my_leave_menu.click();
		
		WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait5.until(driver -> myleavemenu.myleave_record_table_label.getText().contains("Record Found"));

		String recordtable_label = myleavemenu.myleave_record_table_label.getText();
		assertEquals(recordtable_label, "(1) Record Found", "No Leave Apply Records Found");
		
		String date = myleavemenu.myleave_record_table_date.getText();
		String empName = myleavemenu.myleave_record_table_empname.getText();
		String leaveType = myleavemenu.myleave_record_table_leavetype.getText();
		String leaveBalance = myleavemenu.myleave_record_table_leavebalance_days.getText();
		String numofdays = myleavemenu.myleave_record_table_nofdays.getText();
		String status = myleavemenu.myleave_record_table_status.getText();
		
		System.out.println("Date: "+date+
						   "\tEmployee Name: "+empName+
						   "\tLeave Type: "+leaveType+
						   "\tLeave Balance (Days): "+leaveBalance+
						   "\tNumber of Days: "+numofdays+
						   "\tStatus: "+status);
		
		// === New Employee Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();

		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");		
		
//-------------------------------------------------------------------------------------------------------		
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
				
		// === Admin Navigates To Leave List Menu ===
		smenu.leave_module.click();
		
		String leavelist_txt = leavelistmenu.leavelist_label.getText();
		assertEquals(leavelist_txt, "Leave List", "Leave List Screen Not Visible");
		
		String leavelist_empname = leavelistmenu.leavelist_record_table_empname.getText();
		if (leavelist_empname.equals("Roy Jakobs")) {
		 	String Approval_status = leavelistmenu.leavelist_record_table_status.getText();
				if (Approval_status.contains("Pending Approval")) {
					leavelistmenu.leavelist_record_table_approvalbtn.click();
				} else {
					System.out.println("Unable To Approve Leave:");
				}
 		} else {
 			System.out.println("NO Record Found:");
		}

		// === Admin Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();
		
		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");		

//-----------------------------------------------------------------------------------------------------------------		
		/*New Employee Logged In, Verify The Applied Leave Approve By Admin*/
		// === New Employee Login ===
		lpage.login_username.sendKeys(newempUsername);
		lpage.login_password.sendKeys(newempPassword);
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt4 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt4, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing New Employee UserName ===
		String username_txt4 = dshboard.user_name_txt.getText();
		System.out.println("\nNew Employee Name: "+username_txt4);
		
		// === New Employee My Entitlement Screen ===
		smenu.leave_module.click();

		String newuser_leave_status = leavelistmenu.leavelist_record_table_status.getText();
		if (newuser_leave_status.contains("Scheduled")) {
			System.out.println("Leave Approved By Admin:");
		} else {
			System.out.println("Leave Not Approved By Admin:");
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
