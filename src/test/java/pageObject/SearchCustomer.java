package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomer {
    public WebDriver ldriver;
    public   SearchCustomer (WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

}
