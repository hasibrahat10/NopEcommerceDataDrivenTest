package stepDefinitions;

import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pageObject.AddCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Steps extends BaseClass {
@Before
public void setup() throws IOException {
    logger = Logger.getLogger("nopCommerce"); //Added loger
    PropertyConfigurator.configure("Log4j.properties");//added logger

        // Reading Properties
            configProp = new Properties();

            FileInputStream configPropFile = new FileInputStream("config.properties");
            configProp.load(configPropFile);

            //System.setProperty("webdriver.chrome.driver","C://Drivers/chromedriver.exe");
            //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//Drivers/chromedriver.exe");

            String br= configProp.getProperty("browser");
            if(br.equals("chrome")){
                System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
                driver = new ChromeDriver();
            }
            else if(br.equals("firefox")){
                System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
                driver = new FirefoxDriver();
            }else if(br.equals("ie")){
                System.setProperty("webdriver.ie.driver",configProp.getProperty("iexplorepath"));
                driver = new InternetExplorerDriver();
            }


           logger.info("********Launching Browser ************");
}

    @Given("User Launch Chrome browser")
    public void user_Launch_Chrome_browser() {
//        logger = Logger.getLogger("nopCommerce"); //Added loger
//        PropertyConfigurator.configure("Log4j.properties");
//
//        System.setProperty("webdriver.chrome.driver","C://Drivers//chromedriver_win32/chromedriver.exe");
//        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ "//Drivers//chromedriver_win32/chromedriver.exe");
//        driver = new ChromeDriver();
//        logger.info("********Launching Browser ************");
        lp = new LoginPage(driver);
    }

    @When("User open URL {string}")
    public void user_open_URL(String url) {
        logger.info("******** Opening Url ************");
        driver.get(url);
        driver.manage().window().maximize();
    }

    // =================== Login With Valid username and password =====================

    @When("User enter Email as {string} and Password as {string}")
    public void user_enter_Email_as_and_Password_as(String email, String password) {
        logger.info("******** Log in Details ************");

        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_Login() {
        logger.info("******** Started Login Process ************");

        lp.clickLogin();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String title) {
        if(driver.getPageSource().contains("Login was unsuccessful.")){
            driver.close();
            logger.info("******** Login Passed ************");

            Assert.assertTrue(false);
        }else {
            logger.info("******** Login Failed ************");

            Assert.assertEquals(title, driver.getTitle());
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("User click on Log out link")
    public void user_click_on_Log_out_link() {
        logger.info("******** Logout  click************");

        lp.clickLogout();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //========================= Add new Customer step definitions =====================

    @Then("User can view Dashboard")
    public void user_can_view_Dashboard() {
        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle() );
    }

    @When("User click on customers Menu")
    public void user_click_on_customers_Menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomersMenu();
     }

    @When("click on customers Menu Item")
    public void click_on_customers_Menu_Item() throws InterruptedException{
        Thread.sleep(3000);
        addCust.clickOnCustomersMenuItem();
    }

    @When("click on Add new button")
    public void click_on_Add_new_button() {
        logger.info("******** Add new button click************");

        addCust.clickOnAddnew();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("User can view Add new customer page")
    public void user_can_view_Add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {

        logger.info("******** Adding new customer ************");
        logger.info("********providing details ************");

        String email= randomestring()+"@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        //Registered Default
        // The customer can not be in both 'guest' and 'registered' roles
        // add the customer to 'guests' or 'registered role'
        //addCust.setCustomerRoles("Guests");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Hasib");
        addCust.setLastName("Rahat");
        addCust.setDob("1/22/2018"); //Format: D/MM/YYYY
        addCust.setCompanyName("testQA");
        addCust.setAdminContent("Testing purpose");

    }

    @When("click on Save button")
    public void click_on_Save_button() {
        logger.info("******** Saving customer data ************");

        addCust.clickOnSave();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String confirmMessage) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }

    //================= Steps for customer search by email id ===============

    @When("Enter customer Email")
    public void enter_customer_Email() {
        logger.info("********Search by email ************");

        searchCust = new SearchCustomer(driver);
        searchCust.setEmail("james_pan@nopCommerce.com");
    }


    @When("Click on search button")
    public void click_on_search_button() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);
    }


    @Then("User should found Email in the Search table")
    public void user_should_found_Email_in_the_Search_table() {
        boolean status = searchCust.searchCustomerByEmail("james_pan@nopCommerce.com");
        Assert.assertEquals(true, status);
    }


    // ==================== Search customer By Name ===================

    @When("Enter customer First Name")
    public void enter_customer_First_Name() {
        logger.info("******* Searching by name ************");

        searchCust = new SearchCustomer(driver);
        searchCust.setFirstName("Victoria");
    }

    @When("Enter customer Last Name")
    public void enter_customer_Last_Name() {
        searchCust = new SearchCustomer(driver);
        searchCust.setLastName("Terces");
    }

    @Then("User should found Name in the Search table")
    public void user_should_found_Name_in_the_Search_table() {
        Assert.assertEquals(true, searchCust.searchCustomerByName("Victoria Terces"));
    }


    @Then("close browser")
    public void close_browser() {
        logger.info("******** Closing browser  ************");

        driver.quit();
    }
}
