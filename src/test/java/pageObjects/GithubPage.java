package pageObjects;

import Helper.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GithubPage extends Helper{
    WebDriver driver;
    public GithubPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id= 'login_field']")
    private WebElement usernametxt;

    @FindBy(xpath = "//input[@id= 'password']")
    private WebElement passwordtxt;

    @FindBy(xpath = "//input[contains(@class,'btn-primary')]")
    private WebElement signinBtn;

    public void enterUsername(String username){
        waitForVisibilityOfElement(driver,usernametxt);
        usernametxt.sendKeys(username);
    }

    public void enterPassword(String password){
        waitForVisibilityOfElement(driver,passwordtxt);
        passwordtxt.sendKeys(password);
    }

    public void clickSignIn(){
        waitForVisibilityOfElement(driver,signinBtn);
        signinBtn.click();
    }
}
