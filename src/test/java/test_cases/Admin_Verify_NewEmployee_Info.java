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
import webpage_ui_locators.WebPage_UI_Element_Locators.ToastMessage;

public class Admin_Verify_NewEmployee_Info {
	
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
	public void adminVerify_EmployeeInfo() throws InterruptedException {
		
		// === Admin User Login Credentials ===
		final String Username = "Admin";
		final String Password = "admin123";
		
		// === New Employee Login Details ===
		final String fname = "Kalyan";
		final String lname = "Krishnamurthy";
		final String newempUsername = "kalyan_krishna";
		final String newempPassword = "kalyan_krish123";
		
		LoginPage lpage = new LoginPage(driver);
		DashboardPage dshboard = new DashboardPage(driver);
		Sidemenu_Modules smenu = new Sidemenu_Modules(driver);
		AddEmployee_Menu addempMenu = new AddEmployee_Menu(driver);
		ToastMessage tmsg = new ToastMessage(driver);
		EmployeeList_Menu emplist = new EmployeeList_Menu(driver);
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
		
		emplist.emplist_tabel_record.click();
		
		/* Admin User Verifying Newly Added Employee Personal Details - Before change */
		String national_dd = emplist.emplis_personal_national_dd.getText();
		String marital_dd = emplist.emplist_personal_marital_dd.getText();
	 	String bloodty_dd = emplist.emplist_personal_bloodtype_dd.getText();
		
	 	System.out.println("Before New Employee Nationality: "+national_dd);
	 	System.out.println("Before New Employee Marital Status: "+marital_dd);
	 	
		boolean malebtn = emplist.emplist_personal_gender_male_btn.isSelected();
		boolean femalebtn = emplist.emplist_personal_gender_female_btn.isSelected();
			if (malebtn!=true && femalebtn!=true) {
				System.out.println("Both Male & Female Radio Button's Not Selected:");
			} else {
				if (malebtn==true) {
					System.out.println("Male Button Is Selected:");
					}
				if (femalebtn==true) {
					System.out.println("Female Button Is Selected:");
					}
			}
		
		System.out.println("Before New Employee BloodType: "+bloodty_dd);

		// === Admin Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();
		
		// === Verify logout success ===
		assertTrue(lpage.login_button.isDisplayed(), "Logout failed: login button not visible.");	

//----------------------------------------------------------------------------------------------------------------------------
		/* New Employee Login On Application */
		lpage.login_username.sendKeys(newempUsername);
		lpage.login_password.sendKeys(newempPassword);
		lpage.login_button.click();

		// === Dashboard Verification ===
		String dshboardtxt1 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt1, "Dashboard", "Dashboard Screen Not Viewed:");
		
		String useraccount_txt1 = dshboard.user_name_txt.getText();
		System.out.println("\nNew EmployeeName: "+useraccount_txt1);
		
		smenu.my_info_module.click();
		
		emplist.emplis_personal_national_dd.click();
			for (WebElement national_dd_list : emplist.emplist_personal_national_dd_lists) {
				String national_name = national_dd_list.getText();
					if (national_name.equals("Indian")) {
						national_dd_list.click();
						break;
					}
			}
		String nationaldd_txt = emplist.emplis_personal_national_dd.getText();
		System.out.println("New User Selected National: "+nationaldd_txt);
			
		emplist.emplist_personal_marital_dd.click();
			for (WebElement marital_dd_list : emplist.emplist_personal_marital_dd_list) {
				String marital_name = marital_dd_list.getText();
					if (marital_name.equals("Single")) {
						marital_dd_list.click();
						break;
					}
			}
		String maritaldd_txt = emplist.emplist_personal_marital_dd.getText();	
		System.out.println("New User Selected Marital Status: "+maritaldd_txt);
		
		emplist.emplist_personal_gender_male.click();
		malebtn = emplist.emplist_personal_gender_male_btn.isSelected();
		femalebtn = emplist.emplist_personal_gender_female_btn.isSelected();
			if (malebtn!=true && femalebtn!=true) {
				System.out.println("Both Male & Female Radio Button's Not Selected:");
			} else {
				if (malebtn==true) {
					System.out.println("New User Selected Male");
					}
				if (femalebtn==true) {
					System.out.println("New User Selected Female");
					}
			}

			emplist.emplist_personal_savebtn.click();	
			
			emplist.emplist_personal_bloodtype_dd.click();	
			for (WebElement bloodtype_dd_list : emplist.emplist_Personal_bloodtype_dd_list) {
				String bloodty_list = bloodtype_dd_list.getText();
					if (bloodty_list.equals("B+")) {
						bloodtype_dd_list.click();
						break;
					}
			}
		String bloodtype_txt = emplist.emplist_personal_bloodtype_dd.getText();
		System.out.println("New User Selected BloodType: "+bloodtype_txt);
			
		emplist.emplist_personal_customfield_savebtn.click();
		
		// === New Employee Logout ===
		dshboard.user_account_menu.click();
		dshboard.user_logout_dd.click();
		
//----------------------------------------------------------------------------------------------------------------------------		
	/*After New User Update Personal Details, Now Admin User Verify The Employee Deatils From There Login*/
		// === Admin Login ===
		lpage.login_username.sendKeys(Username);
		lpage.login_password.sendKeys(Password);
		lpage.login_button.click();
		
		// === Dashboard Verification ===
		String dshboardtxt2 = dshboard.dashboard_label.getText();
		assertEquals(dshboardtxt2, "Dashboard", "Dashboard Screen Not Viewed:");
		
		// === Printing Admin User Name ===
		String username_txt1 = dshboard.user_name_txt.getText();
		System.out.println("\nCurrent Admin Name: "+username_txt1);
		
		// === Admin Navigates To Add Employee Menu ===
		smenu.pim_module.click();

		emplist.emplist_empname.sendKeys(fname);
		emplist.emplist_empid.sendKeys(addempid);
		emplist.emplist_search_btn.click();
		emplist.emplist_tabel_record.click();
		
		Thread.sleep(2000);
		national_dd = emplist.emplis_personal_national_dd.getText();
		marital_dd = emplist.emplist_personal_marital_dd.getText();
	 	bloodty_dd = emplist.emplist_personal_bloodtype_dd.getText();
		
	 	System.out.println("After New Employee Updated Nationality: "+national_dd);
	 	System.out.println("After New Employee Updated Marital Status: "+marital_dd);
	 	
		malebtn = emplist.emplist_personal_gender_male_btn.isSelected();
		femalebtn = emplist.emplist_personal_gender_female_btn.isSelected();
			if (malebtn!=true && femalebtn!=true) {
				System.out.println("Both Male & Female Radio Button's Not Selected:");
			} else {
				if (malebtn==true) {
					System.out.println("After New Employee Updated: Male Gender");
					}
				if (femalebtn==true) {
					System.out.println("After New Employee Updated: Female Gender");
					}
			}
		
		System.out.println("After New Employee Updated BloodType: "+bloodty_dd);

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
