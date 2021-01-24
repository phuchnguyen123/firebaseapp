package stepDefinitions;

import cucumber.api.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.FireBasePage;
import pageObjects.GithubPage;
import Helper.Helper;

import java.util.concurrent.TimeUnit;

public class firebasesteps {
    WebDriver driver;
    Helper helper;
    FireBasePage fireBasePage;
    String winHandleBefore;
    GithubPage githubPage;
    @Given("User launch Chrome browser")
    public void userLaunchChrome() {
        System.setProperty("webdriver.chrome.driver", "src//test//java//Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        fireBasePage = new FireBasePage(driver);
        githubPage  = new GithubPage(driver);
    }

    @When("Go to URL {string}")
    public void goToURL(String string) {
        driver.get(string);
        winHandleBefore = driver.getWindowHandle();
    }

    @And("Click 'Sign In with github'")
    public void clickSignInWithGithub() {
        helper.isPageReady(driver);
        fireBasePage.signInByGithub();
    }

    @And("Sign in by Github account with Username {string} and Password {string}")
    public void signInGithubAccount(String Username, String Password) {
        helper.isPageReady(driver);
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
        githubPage.enterUsername(Username);
        githubPage.enterPassword(Password);
        githubPage.clickSignIn();
        for(String winHandle : driver.getWindowHandles()){
            if(!(winHandle.equals(winHandleBefore))){
                driver.switchTo().window(winHandleBefore);
            }
        }
    }

    @And("Create 10 to do list with random strings")
    public void createToDoListWithRandomStrings() {
        helper.isPageReady(driver);
        int randomNumber;
        int count = fireBasePage.countTotalToDoList();
        if(count != 0){
            deleteUserListFrom(1,count);
        }
        for(int i = 0; i <= 10; i++){
            randomNumber = Helper.ranDomNumber(11111111, 99999999);
            fireBasePage.fillAList(randomNumber + "A");
            fireBasePage.clickAddList();
        }
    }

    @And("Sign out")
    public void signOut() {
        fireBasePage.signOut();
    }

    @And("Delete User list from {int}-{int}")
    public void deleteUserListFrom(int arg0, int arg1) {
        helper.isPageReady(driver);
        int i = arg1;
        do{
            fireBasePage.clickDeleteList(i);
            i--;
        }while(i>=arg0);
    }

    @Then("Verify total to do list is {int}")
    public void verifyTotalToDoListIs(int arg0) {
        driver.navigate().refresh();
        fireBasePage.waitPageLoadSuccess();
        int count = fireBasePage.countTotalToDoList();
        Assert.assertTrue(arg0 == count);
    }

    @Then("Quit browser")
    public void quitBrowser() {
        driver.quit();
    }
}
