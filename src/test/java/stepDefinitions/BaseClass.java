package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pageObject.AddCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomer;

public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;

    public AddCustomerPage addCust;
    public SearchCustomer searchCust;

    //Created for generating random string for unique email
    public static String randomestring(){
        String generatedString1 = RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }
}
