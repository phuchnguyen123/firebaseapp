package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Helper.Helper;
import java.util.List;

public class FireBasePage extends Helper{
    WebDriver driver;

    public FireBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = " //*[contains(@class,'btn-github')]")
    private WebElement loginByGithubBtn;

    @FindBy(xpath = " //input[contains(@class, 'ng-empty')]")
    private WebElement addListTxt;

    @FindBy(xpath = "//*[contains(@class, 'btn-success')]")
    private WebElement addListBtn;

    @FindBy(xpath = "//*[contains(@class, 'btn-default')]")
    private WebElement signOutBtn;

    private final String  toDoListItem = "//*[contains(@class, 'list-group')]//li[%s]//descendant::button[contains(@class, 'remove')]";

    private final String toDoList = "//*[contains(@class, 'list-group')]//li";

    public void signInByGithub(){
        waitForVisibilityOfElement(driver,loginByGithubBtn);
        loginByGithubBtn.click();
    }

    public void fillAList(String number){
        waitForVisibilityOfElement(driver,addListTxt);
        addListTxt.sendKeys(number);
    }
    public void clickAddList(){
        waitForVisibilityOfElement(driver,addListBtn);
        addListBtn.click();
    }

    public void signOut(){
        waitForVisibilityOfElement(driver,signOutBtn);
        signOutBtn.click();
    }

    public WebElement deleteAList(int number) {
        return driver.findElement(By.xpath(String.format(toDoListItem, number)));
    }

    public void clickDeleteList(int number){
        deleteAList(number).click();
    }

    public int countTotalToDoList(){
        List<WebElement> ToDoList = driver.findElements(By.xpath(toDoList));
        int ToDoListCount = ToDoList.size();
        return ToDoListCount;
    }
    public void waitPageLoadSuccess(){
        waitForVisibilityOfElement(driver,addListBtn);
    }
}
