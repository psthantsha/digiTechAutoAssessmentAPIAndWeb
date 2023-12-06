package webTesting;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserTablePage extends BasePage{
    static WebDriver driver;

    BasePage BasePage;

    //Constructor to initialize the driver
    public UserTablePage(WebDriver driver) {
        super();
        this.driver = driver;
        BasePage = new BasePage();
    }

    @Given("User launch webtable browser {string}")
    public static void userLaunchWebtableBrowser(String browser){

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver.exe");
            driver = new ChromeDriver();

        }else if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/firefox/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.getWindowHandles();
    }
    @Then("Validate you are on the user list table")
    public void validateYouAreOnTheUserListTable(){
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("webtables"));
        System.out.println(currentUrl);
    }
    @And("Click add user button")
    public void clickAddUserButton(){
        if(isElementPresent(By.xpath(properties.getProperty("addButton")))){
            driver.findElement(By.xpath(properties.getProperty("addButton"))).click();
            Assert.assertTrue(driver.findElement(By.xpath(properties.getProperty("addUserHeader"))).isDisplayed(),"Add user header is not displayed");}
    }
    @And("Add users into the table {string} company {string} role")
    public void addUsersIntoTheTable(DataTable regData, String company, String role) {
        List<List<String>> userDetails = Collections.singletonList(regData.row(1));

        if (isElementPresent(By.xpath(properties.getProperty("firstName")))) {
            driver.findElement(By.xpath(properties.getProperty("firstName"))).sendKeys(userDetails.get(0).get(1));
            driver.findElement(By.xpath(properties.getProperty("lastName"))).sendKeys(userDetails.get(0).get(2));
            driver.findElement(By.xpath(properties.getProperty("userName"))).sendKeys(userDetails.get(0).get(3));
            driver.findElement(By.xpath(properties.getProperty("password"))).sendKeys(userDetails.get(0).get(4));
            switch (company) {
                case "Company AAA":
                    driver.findElement(By.xpath(properties.getProperty("companyAAA"))).click();
                    break;
                case "Company BBB":
                    driver.findElement(By.xpath(properties.getProperty("companyBBB"))).click();
            }
            driver.findElement(By.xpath(properties.getProperty("role"))).click();
            waitElementToBeClickable(properties.getProperty("salesTeam"));
            switch (role) {
                case "Sales Team":
                    driver.findElement(By.xpath(properties.getProperty("salesTeam"))).click();
                    break;
                case "Customer":
                    driver.findElement(By.xpath(properties.getProperty("customer"))).click();
                    break;
                case "Admin":
                    driver.findElement(By.xpath(properties.getProperty("admin"))).click();
            }
            driver.findElement(By.xpath(properties.getProperty("email"))).sendKeys(userDetails.get(0).get(5));
            driver.findElement(By.xpath(properties.getProperty("mobilePhone"))).sendKeys(userDetails.get(0).get(6));
            driver.findElement(By.xpath(properties.getProperty("saveButton"))).click();
            Assert.assertTrue(driver.findElement(By.xpath(properties.getProperty("locked"))).isDisplayed(),"Webtable with saved users is not displayed");
        }
    }
        @And ("Confirm a user is added to the list")
        public void validateAUserIsAddedToTheList(){
            if (isElementPresent(By.xpath(properties.getProperty("locked")))) {
                Assert.assertTrue(driver.findElement(By.xpath(properties.getProperty("addedFirstName"))).isDisplayed(), "User is not added");
            }
            else {
                Assert.fail("User is not added");
        }
    }
}
