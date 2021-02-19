package testsSel;


import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class descriptionEdit {
    WebDriver browser;


    @BeforeSuite
    public void start() {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        browser = new ChromeDriver();
        browser.get("https://jira.ithillel.com/browse/AQ-38");
        browser.manage().window().maximize();
        browser.manage().deleteAllCookies();
        WebDriverWait wait = new WebDriverWait(browser, 10);

        browser.findElement(By.id("login-form-username")).sendKeys("donor1mozga");
        browser.findElement(By.id("login-form-password")).sendKeys("Wx5u4wWNQqP8Lkp");
        browser.findElement(By.id("login-form-submit")).click();
    }

    @Test
    public void description() throws InterruptedException {


        Actions actions = new Actions(browser);

        WebElement clickDescription = browser.findElement(By.id("description-val"));
        actions.moveToElement(clickDescription).perform();

        clickDescription.click();
        Thread.sleep(3000);
        WebElement ide = browser.findElement(By.cssSelector("[title=\"Rich Text Area. Press ALT-F9 for menu. Press ALT-F10 for toolbar. Press ALT-0 for help\"]"));
        Thread.sleep(3000);
        browser.switchTo().frame(ide);
        Thread.sleep(3000);
        WebElement ids = browser.findElement(By.id("tinymce"));

        ids.sendKeys("Test AQA");

        browser.switchTo().defaultContent();
        browser.findElement(By.cssSelector("[class=\"aui-button aui-button-primary submit\"]")).click();
        Thread.sleep(3000);
        String description = browser.findElement(By.cssSelector("[class=\"user-content-block\"]")).getText();
        Assert.assertEquals(description, "Test AQA");
        System.out.println(description);
    }

    @Test
    public void Issues() {

        WebElement issue = browser.findElement(By.cssSelector("[href=\"/projects/AQ/issues\"]"));
        Actions newTab = new Actions(browser);
        newTab
                .keyDown(Keys.CONTROL)
                .click(issue)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
        ArrayList<String> tabs = new ArrayList<String>(browser.getWindowHandles());
        browser.switchTo().window(tabs.get(1));

        List<WebElement> listIssue = browser.findElements(By.cssSelector("[class=\"list-content\"]"));
        List<String>allIssue = new ArrayList<>();
        for (int i = 0; i < listIssue.size(); i++){
            allIssue.add(listIssue.get(i).getText());
        }

        for (int i = 0; i < allIssue.size(); i++) {
            System.out.println(allIssue.get(i));
        }

//        Select select = new Select(listIssue);
//        List<WebElement> options = select.getOptions();
//
//        for (WebElement option : options) {
//            System.out.println(option.getText());
//        }
    }

}
