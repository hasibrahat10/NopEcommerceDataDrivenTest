package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObject.AddCustomerPage;
import pageObject.LoginPage;
import pageObject.SearchCustomer;

import java.util.Properties;


public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;

    public AddCustomerPage addCust;
    public SearchCustomer searchCust;

    public static Logger logger;
public Properties configProp;
    //Created for generating random string for unique email
    public static String randomestring(){
        String generatedString1 = RandomStringUtils.randomAlphabetic(5);
        return (generatedString1);
    }
}
