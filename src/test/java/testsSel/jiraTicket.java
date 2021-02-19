package testsSel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class jiraTicket {
    WebDriver browser;


    @BeforeSuite
    public void start() {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        browser = new ChromeDriver();
        browser.get("https://jira.ithillel.com/browse/AQ-38");
        browser.manage().window().maximize();

    }


    @Test
    public void assignMe() {

        WebDriverWait wait = new WebDriverWait(browser, 10);

        browser.findElement(By.id("login-form-username")).sendKeys("donor1mozga");
        browser.findElement(By.id("login-form-password")).sendKeys("Wx5u4wWNQqP8Lkp");
        browser.findElement(By.id("login-form-submit")).click();

        WebElement labels = browser.findElement(By.cssSelector("[id=\"labels-16503-value\"]"));
        System.out.println(labels.getText());

        Actions actions = new Actions(browser);
        WebElement clickToEdit = browser.findElement(By.cssSelector("[class=\"labels-wrap value editable-field inactive\"]"));
        actions.moveToElement(clickToEdit).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/section/di" +
                "v[2]/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div[2]/ul/li[5]/div/div/span"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id=\"labels-textarea\"]")))
                .sendKeys("TestAQA");
//        wait.until(ExpectedConditions.elementToBeClickable(By.className("aui-button submit"))).click();
//        browser.findElement(By.xpath("//*[class=\"aui-button submit\"]")).click();

        WebElement priority = browser.findElement(By.cssSelector("[class=\"item new\"]"));
        actions.moveToElement(priority).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/section/div[2]/div/div/div/div/div[2]" +
                "/div/div/div/div[1]/div[1]/div[2]/ul/li[3]/div/span/span"))).click();
        WebElement labelsPriority = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("priority")));
        Select select = new Select(labelsPriority);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            System.out.println(option.getAttribute("textContent").trim());
        }

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.cssSelector("[class=\"issueaction-assign-to-me\"]")));
        browser.findElement(By.cssSelector("[class=\"issueaction-assign-to-me\"]"))
        .click();


        WebElement assignTicket = browser.findElement(By.cssSelector("[class=\"user-hover\"]"));
        String assignToMe = assignTicket.getText();
        Assert.assertEquals(assignToMe, "Sasha Shaposhnyk");


    }

    @AfterSuite
    public void quit() {
        browser.quit();
    }


}
