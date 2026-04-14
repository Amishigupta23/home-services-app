package com;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginTest {

    @Test
    public void loginTest() throws Exception {

        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Thread.sleep(5000);

            driver.get("http://localhost:5173/login");
            System.out.println("✅ Login page opened");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder='Enter email']")));

            driver.findElement(By.cssSelector("input[placeholder='Enter email']"))
                    .sendKeys("test@gmail.com");

            driver.findElement(By.cssSelector("input[placeholder='Enter password']"))
                    .sendKeys("test12345");

            driver.findElement(By.xpath("//button[text()='Login']")).click();

            Thread.sleep(3000);

            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("login")) {
                throw new RuntimeException("❌ TEST FAILED");
            } else {
                System.out.println("🎉 TEST PASSED");
            }

        } finally {
            driver.quit();
        }
    }
}