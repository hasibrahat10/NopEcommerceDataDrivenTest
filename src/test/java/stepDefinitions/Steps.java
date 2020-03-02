package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.AddCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomer;

public class Steps extends BaseClass {

    @Given("User Launch Chrome browser")
    public void user_Launch_Chrome_browser() {
        System.setProperty("webdriver.chrome.driver","C://Drivers//chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        lp = new LoginPage(driver);
    }

    @When("User open URL {string}")
    public void user_open_URL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    // =================== Login With Valid username and password =====================

    @When("User enter Email as {string} and Password as {string}")
    public void user_enter_Email_as_and_Password_as(String email, String password) {
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_Login() {
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
            Assert.assertTrue(false);
        }else {
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
        driver.quit();
    }
}
