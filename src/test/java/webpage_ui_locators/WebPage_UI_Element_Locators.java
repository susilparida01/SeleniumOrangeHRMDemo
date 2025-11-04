package webpage_ui_locators;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebPage_UI_Element_Locators {
	
	/*Login Page Locators*/
	public static class LoginPage {
		
		@FindBy(name = "username")
		public WebElement login_username;
		
		@FindBy(name = "password")
		public WebElement login_password;
		
		@FindBy(xpath = "//button[normalize-space()='Login']")
		public WebElement login_button;
		
		@FindBy(xpath = "//div[@role='alert']/div[1]/p")
		public WebElement login_invalid_credentials_label;
		
		public LoginPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//-------------------------------------------------------------------------------------------------------------------------	
	/*Dashboard Page Locators*/
	public static class DashboardPage {
		
		//Dashboard Label Text:
		@FindBy(xpath = "//h6[normalize-space()='Dashboard']")
		public WebElement dashboard_label;
		
		//User Account Menu Text:
		@FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']/ul/li/span/p")
		public WebElement user_name_txt;
		
		//User Account Menu:
		@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
		public WebElement user_account_menu;
		
		//User Account Logout DropDown:
		@FindBy(xpath = "//li/a[normalize-space()='Logout']")
		public WebElement user_logout_dd;
		
		public DashboardPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//-------------------------------------------------------------------------------------------------------------------------		
	/*Successful Saved Toast Message*/
	public static class ToastMessage{
		
		//Successful Save Toast Message
		@FindBy(xpath = "//div[@id='oxd-toaster_1']/div/div/div/p[2]")
		public WebElement toast_msg;
		
		public ToastMessage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//----------------------------------------------------------------------------------------------------------------------------				
	/*SideBar Navigation Menu Locators*/
	public static class Sidemenu_Modules{
		
		//PIM Module:
		@FindBy(xpath = "//span[text()='PIM']")
		public WebElement pim_module;
		
		//Admin Module:
		@FindBy(xpath = "//div[@class='oxd-sidepanel-body']/ul/li/a/span[text()='Admin']")
		public WebElement admin_module;
		
		//Leave Module:			
		@FindBy(xpath = "//div[@class='oxd-sidepanel-body']/ul[@class='oxd-main-menu']/li/a/span[text()='Leave']")
		public WebElement leave_module;
		
		//My Info Module:			
		@FindBy(xpath = "//span[text()='My Info']")	
		public WebElement my_info_module;
		
		//SideBar Navigation Menus Lists
		@FindBy(xpath = "//div[@class='oxd-sidepanel-body']/ul/li/a/span")
		public List<WebElement> sidebar_menulists;
		
		public Sidemenu_Modules(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//----------------------------------------------------------------------------------------------------------------------------					
	/*ADD Employee TabBar View Menu Locators ->[PIM Module]*/
	public static class AddEmployee_Menu{
		
		//AddEmployee Menu
		@FindBy(xpath = "//a[text()='Add Employee']")
		public WebElement add_employee_menu;
		
		//AddEmployee Screen Name Label
		@FindBy(xpath = "//h6[text()='Add Employee']")
		public WebElement addemp_namelabel;
				
		//AddEmployee FirstName FieldBox
		@FindBy(name = "firstName")
		public WebElement addemp_first_name;
			
		//AddEmployee LastName FieldBox
		@FindBy(name = "lastName")
		public WebElement addemp_last_name;
			
		//AddEmployee Employee_ID FieldBox
		@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
		public WebElement addemp_emp_id;
		
		//AddEmployee Create Login Toggle Button
		@FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
		public WebElement addemp_cld_toggle_btn;
				
		//AddEmployee CLD Username FieldBox
		@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[3]")
		public WebElement addemp_cld_unamefield;	
		
		//AddEmployee CLD Status Radio Button
		@FindBy(xpath = "//label[normalize-space()='Enabled']/input")
		public WebElement addemp_cld_status_radio_btn;

		//AddEmployee CLD Password Field
		@FindBy(xpath = "(//input[@type='password'])[1]")
		public WebElement addemp_cld_password_field;
		
		//AddEmployee CLD Confirm Password Field
		@FindBy(xpath = "(//input[@type='password'])[2]")
		public WebElement addemp_cld_confirm_password_field;
		
		//AddEmployee Save Button
		@FindBy(xpath = "//button[normalize-space()='Save']")
		public WebElement addemp_cld_save_btn;
		
		public AddEmployee_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//----------------------------------------------------------------------------------------------------------------------------					
	/*Employee List TabBar View Menu Locators - [PIM Module]*/
	public static class EmployeeList_Menu{
		
		//Employee List Menu
		@FindBy(xpath = "//a[text()='Employee List']")			
		public WebElement emp_list_menu;
						
		//Employee List Label
		@FindBy(xpath = "//div[@class='oxd-table-filter']/div[1]/div/h5")
		public WebElement emp_information_label;
			
		//Employee List Employee Name Field
		@FindBy(xpath = "(//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input)[1]")		
		public WebElement emplist_empname;
				
		//Employee List Employee ID Field
		@FindBy(xpath = "//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
		public WebElement emplist_empid;
				
		//Employee List Search Button		
		@FindBy(xpath = "//button[normalize-space()='Search']")
		public WebElement emplist_search_btn;
				
		//Employee List Record Label Text
		@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span'])[1]")
		public WebElement emplist_record_table_label;
		
		//Employee List Records Tabel
		@FindBy(xpath = "//div[@class='oxd-table-card']/div/div[3]")
		public WebElement emplist_tabel_record;
		
		//Employee List Record Tabel EmpID Field
		@FindBy(xpath = "//div[@class='oxd-table-card']/div/div[2]")
		public WebElement emplist_record_table_empid;
		
		//Employee List Record Tabel EmpName Field
		@FindBy(xpath = "//div[@class='oxd-table-card']/div/div[3]")
		public WebElement emplist_record_tabel_empname;
		
		//Employee List Record Tabel EmpLastName Field
		@FindBy(xpath = "//div[@class='oxd-table-card']/div/div[4]")
		public WebElement emplist_record_tabel_emplastname;
		
		//Employee List Personal Details Screen Nationality DropDown
		@FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active']/div[@class='oxd-select-text-input'])[1]")
		public WebElement emplis_personal_national_dd;

		//Employee List Personal Details Screen Nationality DropDown List
		@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div/span")
		public List<WebElement> emplist_personal_national_dd_lists;
						
		//Employee List Personal Details Screen Marital Status DropDown
		@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[2]")
		public WebElement emplist_personal_marital_dd;
		
		//Employee List Personal Details Screen Marital Status DropDown List
		@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div/span")
		public List<WebElement> emplist_personal_marital_dd_list;
				
		//Employee List Personal Details Screen Gender Male Radio Button
		@FindBy(xpath = "//input[@type='radio' and @value='1']")
		public WebElement emplist_personal_gender_male_btn;
			
		//Employee List Personal Details Screen Gender Male
		@FindBy(xpath = "(//div[@class='oxd-radio-wrapper']/label/span)[1]")
		public WebElement emplist_personal_gender_male;
						
		//Employee List Personal Details Screen Gender Female Radio Button
		@FindBy(xpath = "//input[@type='radio' and @value='2']")
		public WebElement emplist_personal_gender_female_btn;
		
		//Employee List Personal Details Screen Female
		@FindBy(xpath = "(//div[@class='oxd-radio-wrapper']/label/span)[2]")
		public WebElement emplist_personal_gender_female;
				
		//Employee List Personal Details Screen Personal Details Save Button		
		@FindBy(xpath = "(//div[@class='oxd-form-actions']/button)[1]")
		public WebElement emplist_personal_savebtn;
				
		//Employee List Personal Details Screen Blood Type DropDown
		@FindBy(xpath = "(//div[@class='oxd-select-text-input'])[3]")
		public WebElement emplist_personal_bloodtype_dd;
		
		//Employee List Personal Details Screen Blood Type DropDown Lists
		@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div/span")
		public List<WebElement> emplist_Personal_bloodtype_dd_list;
		
		//Employee List Personal Details Screen Custom Field Save Button
		@FindBy(xpath = "(//div[@class='oxd-form-actions']/button)[2]")
		public WebElement emplist_personal_customfield_savebtn;
		
		public EmployeeList_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}	
//----------------------------------------------------------------------------------------------------------------------------										
	/*Apply Leave TabBar View Menu - [Leave Module]*/
	public static class ApplyLeave_Menu{
		
		//Apply Leave
		@FindBy(xpath = "(//div[@class='oxd-topbar-body']/nav/ul/li)[1]/a")
		public WebElement apply_leave_menu;	
				
		//Apply Leave Label:
		@FindBy(xpath = "//div[@class='orangehrm-card-container']/h6")
		public WebElement apply_leave_label;				

		//Leave Balance Label:
		@FindBy(xpath = "(//div[@class='oxd-grid-item oxd-grid-item--gutters'])[2]/div/div[2]/p")
		public WebElement leavebalance_label;	
			
		//LeaveType DropDown Field:
		@FindBy(xpath = "//div[@class='oxd-select-wrapper']/div/div[@class='oxd-select-text-input']")
		public WebElement leavetype_dd;

		//LeaveType DropDown List:
		@FindBy(xpath = "//div[@class='oxd-select-dropdown --positon-bottom']/div")
		public List<WebElement> leavetype_dd_list;
					
		/*Calendar Interactions*/
		//From Date Field
		@FindBy(xpath = "(//div[@class='oxd-date-input']/i)[1]")
		public WebElement from_date_field;

		//From Date Select
		@FindBy(xpath = "//div[@class='oxd-calendar-wrapper']/div[@class='oxd-calendar-dates-grid']/div/div[text()='13']")
		public WebElement fromdate_select;			

		//Comment Box Field	
		@FindBy(xpath = "//textarea")	
		public WebElement comment_box;
						
		//Apply Button
		@FindBy(xpath = "//div[@class='oxd-form-actions']/button")
		public WebElement apply_btn;
		
		public ApplyLeave_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}	
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	/*Entitlement TabBar View Menu - [Leave Module]*/
	public static class Entitlement_Menu{
		
		//Entitlement TabBar View Menu
		@FindBy(xpath = "(//nav[@class='oxd-topbar-body-nav']/ul/li)[3]")
		public WebElement entitlement_dd_menu;	
				
		//Entitlement DropDown Menu List:
		@FindBy(xpath = "//ul[@class='oxd-dropdown-menu']/li/a")
		public List<WebElement> entitlement_dd_menu_list;
		
		//My Leave Entitlement:
		@FindBy(xpath = "//h5[@class='oxd-text oxd-text--h5 oxd-table-filter-title']")
		public WebElement my_leave_entitlement_label;
					
		//My Leave Entitlement-[Total Days Label:]
		@FindBy(xpath = "//div[@class='orangehrm-header-container']/span")
		public WebElement my_entitle_totalday_label;
				
		/*Add Leave Entitlement DropDown Menu:*/		
		//Add Leave Entitlement Leave Employee Name:
		@FindBy(xpath = "//input[@placeholder='Type for hints...']")
		public WebElement add_leave_entitle_empname;
		
		//Add Leave Entitlement Leave Employee Name AutoList:
		@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span")
		public List<WebElement> add_leave_entitle_empname_list;
			
		//Add Leave Entitlement Label:
		@FindBy(xpath = "//div[@class='orangehrm-card-container']/p")
		public WebElement add_leave_entitlement_label;
			
		//Add Leave Entitlement Leave Type:		
		@FindBy(xpath = "(//div[@class='oxd-select-text oxd-select-text--active']/div[@class='oxd-select-text-input'])[1]")		
		public WebElement add_leave_entitle_leavetype;
		
		//Add Leave Entitlement Leave Leave Type AutoList:
		@FindBy(xpath = "//div[@class='oxd-select-wrapper']/div[@role='listbox']/div/span")
		public List<WebElement> add_leave_entitle_leavetype_list;
					
		//Add Leave Entitlement Field:
		@FindBy(xpath = "(//div[@class='oxd-grid-3 orangehrm-full-width-grid'])[3]/div[3]/div/div[2]/input")	
		public WebElement add_leave_entitle_field;
					
		//Add Leave Entitlement Save Button:
		@FindBy(xpath = "(//div[@class='oxd-form-actions']/button)[2]")
		public WebElement add_leave_entitle_savebtn;
			
		//Add Leave Entitlement Dialog Pop-Up Message:
		@FindBy(xpath = "(//div[@role='document']/div/p)[1]")
		public WebElement add_leave_entitle_dialogpoup;
			
		//Add Leave Entitlement Dialog Pop-Up Save Button:
		@FindBy(xpath = "(//div[@role='document']/div[@class='orangehrm-modal-footer']/button)[2]")
		public WebElement add_leave_entitle_dialogsavebtn;
		
		public Entitlement_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------					
	/*My Leave TabBar View Menu - [Leave Module]*/
	public static class MyLeave_Menu{
		
		//My Leave Menu
		@FindBy(xpath = "(//div[@class='oxd-topbar-body']/nav[@role='navigation']/ul/li)[2]")
		public WebElement my_leave_menu;
			
		//My Leave Records Table Label:
		@FindBy(xpath = "//div[@class='orangehrm-paper-container']/div/span")
		public WebElement myleave_record_table_label;
			
		//Record Table Date:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[2]/div")
		public WebElement myleave_record_table_date;
			
		//Record Table Employee Name:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[3]/div")
		public WebElement myleave_record_table_empname;
				
		//Record Table Leave Type:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[4]/div")
		public WebElement myleave_record_table_leavetype;
			
		//Record Table Leave Balance Days:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[5]/div")
		public WebElement myleave_record_table_leavebalance_days;
				
		//Record Table Number Of Days:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[6]/div")
		public WebElement myleave_record_table_nofdays;
				
		//Record Table Status:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[7]/div")
		public WebElement myleave_record_table_status;
		
		public MyLeave_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	/*Leave List TabBar Menu - [Leave Module]*/
	public static class LeaveList_Menu{
		
		//Leave List Label Text:
		@FindBy(xpath = "//div[@class='oxd-table-filter-header']/div/h5")
		public WebElement leavelist_label;
				
		//Leave List Records Table EmployeeName:
		@FindBy(xpath = "(//div[@class='oxd-table-card']/div[@role='row']/div/div)[3]")
		public WebElement leavelist_record_table_empname;
				
		//Leave List Record Table Status
		@FindBy(xpath = "(//div[@class='oxd-table-card']/div[@role='row']/div/div)[7]")
		public WebElement leavelist_record_table_status;
				
		//Leave List Record Table Approval Button:
		@FindBy(xpath = "(//div[@class='oxd-table-card']/div[@role='row']/div/div)[9]/button[1]")
		public WebElement leavelist_record_table_approvalbtn;
				
		//Leave Table Status
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[7]/div")
		public WebElement leavelist__table_status;
		
		public LeaveList_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}
//-----------------------------------------------------------------------------------------------------------------------------------------------------			
	/*System User Screen Locators - [Admin Module]*/
	public static class SystemUser_Screen{
		
		//System User Label:
		@FindBy(xpath = "//div[@class='oxd-table-filter-header']/div/h5")
		public WebElement system_user_label;
				
		//System User Employee Name Field:
		@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']/div/input")
		public WebElement system_user_name_field;
				
		//System User Employee Name Auto List Field:
		@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span")
		public List<WebElement> system_user_name_autolist;
				
		//System User Search Button:
		@FindBy(xpath = "(//div[@class='oxd-form-actions']/button)[2]")
		public WebElement system_user_search_btn;
				
		//System User Record Label:
		@FindBy(xpath = "//div[@class='orangehrm-paper-container']/div[2]/div/span")
		public WebElement system_user_record_label;
				
		//System User Record Table Employee Name:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[4]/div")
		public WebElement system_record_table_empname;
				
		//System User Record Table Edit Button:
		@FindBy(xpath = "//div[@class='oxd-table-card']/div[@role='row']/div[6]/div/button[2]")
		public WebElement system_record_table_editbutton;

		//Edit User Label:
		@FindBy(xpath = "//div[@class='oxd-layout-context']/div/div/h6")
		public WebElement edit_user_label;

		//Edit User Employee Name Field:
		@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']/div/input")
		public WebElement edit_user_empname;

		//Edit User Employee Name Field AutoList:
		@FindBy(xpath = "//div[@class='oxd-autocomplete-wrapper']/div[@role='listbox']/div/span")
		public List<WebElement> edit_user_empname_autolist;

		//Change Password CheckBox:
		@FindBy(xpath = "//div[@class='oxd-checkbox-wrapper']/label/span")
		public WebElement change_password_chkbox;
				
		//Change Password Input Field:
		@FindBy(xpath = "(//div[@class='oxd-input-group oxd-input-field-bottom-space']/div)[12]/input")
		public WebElement change_password_input;
				
		//Change Confirmation Password Input Field:
		@FindBy(xpath = "(//div[@class='oxd-input-group oxd-input-field-bottom-space']/div)[14]/input")
		public WebElement change_confirm_passwInput;
				
		//Edit User Save Button:
		@FindBy(xpath = "//div[@class='oxd-form-actions']/button[2]")
		public WebElement edituser_save_btn;
		
		public SystemUser_Screen(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------			
	/*Configuration TabBar View Menu - [Admin Module]*/
	public static class Config_Menu{
		
		@FindBy(xpath = "(//nav[@class='oxd-topbar-body-nav']/ul/li)[7]")		
		public WebElement admin_config_menu;	
		
		//Configuration TabBar DropDown Lists
		@FindBy(xpath = "//ul[@class='oxd-dropdown-menu']/li/a")
		public List<WebElement> admin_config_dd_lists;
		
		//Module Configuration Screen Label
		@FindBy(xpath = "//div[@class='orangehrm-card-container']/h6")
		public WebElement admin_module_config_label;
		
		//Admin Module Toggle Button
		@FindBy(xpath = "//div[@class='orangehrm-card-container']/h6")
		public WebElement admin_module_toggle_btn;
		
		//PIM Module Toggle Button
		@FindBy(xpath =  "(//div[@class='oxd-form-row']/div/div/div/label/span)[2]")
		public WebElement pim_module_toggle_btn;
		
		//Leave Module Toggle Button
		@FindBy(xpath =  "(//div[@class='oxd-form-row']/div/div/div/label/span)[3]")
		public WebElement leave_module_toggle_btn;
		
		//Time Module Toggle Button
		@FindBy(xpath =  "(//div[@class='oxd-form-row']/div/div/div/label/span)[4]")
		public WebElement time_module_toggle_btn;
		
		//Recruitment Module Toggle Button
		@FindBy(xpath =  "(//div[@class='oxd-form-row']/div/div/div/label/span)[5]")
		public WebElement recruitment_module_toggle_btn;
		
		//Performance Module Toggle Button
		@FindBy(xpath = "(//div[@class='oxd-form-row']/div/div/div/label/span)[6]")
		public WebElement performance_module_toggle_btn;
		
		//Directory Module Toggle Button
		@FindBy(xpath = "(//div[@class='oxd-form-row']/div/div/div/label/span)[7]")
		public WebElement directory_module_toggle_btn;
		
		//Mainteance Module Toggle Button
		@FindBy(xpath = "(//div[@class='oxd-form-row']/div/div/div/label/span)[8]")
		public WebElement mainteaance_module_toggle_btn;
		
		//Mobile Module Toggle Button
		@FindBy(xpath = "(//div[@class='oxd-form-row']/div/div/div/label/span)[9]")
		public WebElement mobilemodule_toggle_btn;
		
		//Claim Module Toggle Button
		@FindBy(xpath = "(//div[@class='oxd-form-row']/div/div/div/label/span)[10]")
		public WebElement claim_module_toggle_btn;
		
		//Buzz Module Toggle Button
		@FindBy(xpath = "(//div[@class='oxd-form-row']/div/div/div/label/span)[11]")
		public WebElement buzz_module_toggle_btn;
		
		//Configuration Module Save Button
		@FindBy(xpath = "//div[@class='oxd-form-actions']/button")
		public WebElement config_module_save_btn;
		
		public Config_Menu(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
	}	
	
}
