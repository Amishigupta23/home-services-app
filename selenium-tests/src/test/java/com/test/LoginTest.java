package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Maximize browser (important for CI stability)
            driver.manage().window().maximize();

            // Wait object (explicit wait is better than sleep)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Safety delay for Docker/React startup
            Thread.sleep(5000);

            // Open login page
            driver.get("http://localhost:5173/login");
            System.out.println("✅ Login page opened");

            // Wait for email field
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[placeholder='Enter email']")));

            // Enter email
            driver.findElement(By.cssSelector("input[placeholder='Enter email']"))
                    .sendKeys("test@gmail.com");
            System.out.println("✅ Email entered");

            // Enter password
            driver.findElement(By.cssSelector("input[placeholder='Enter password']"))
                    .sendKeys("test12345");
            System.out.println("✅ Password entered");

            // Click login button
            driver.findElement(By.xpath("//button[text()='Login']")).click();
            System.out.println("✅ Login clicked");

            // Wait for navigation / UI update
            Thread.sleep(3000);

            // Validate result
            String currentUrl = driver.getCurrentUrl();

            if (!currentUrl.contains("login")) {
                System.out.println("🎉 TEST PASSED - Login successful");
                System.out.println("Redirected to: " + currentUrl);
            } else {
                System.out.println("❌ TEST FAILED - Still on login page");
            }

        } catch (Exception e) {
            System.out.println("❌ Test Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }
}