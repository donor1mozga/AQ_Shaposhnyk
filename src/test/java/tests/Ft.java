package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ft {

    public static void main(String[] args) throws InterruptedException {
        final String binPath = String.format("%s/bin/chromedriver.exe", System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", binPath);
        final WebDriver browser = new ChromeDriver();

        browser.get("https://jira.ithillel.com/browse/AQ-38");
        browser.manage().window().maximize();
//        WebElement inputField = browser.findElement(By.xpath("//div[@id='searchform']//input[@name='q']"));
//        inputField.sendKeys("Hillel");
//        WebElement button = browser.findElements(By.cssSelector("[id='searchform'] [name='btnK']")).get(1);
        WebElement language = browser.findElement(By.xpath("//*[@id=\"SIvCob\"]/a"));
//        Actions browserAct = new Actions(browser);
//        browserAct
//                .keyDown(Keys.CONTROL)
//                .click(language)
//                .keyUp(Keys.CONTROL)
//                .build()
//                .perform();

//        browser.findElement(By.cssSelector("[alt='Google']")).click();
        Thread.sleep(5000);
//        button.click();
//        Thread.sleep(5000);
        browser.quit();



    }
    }

