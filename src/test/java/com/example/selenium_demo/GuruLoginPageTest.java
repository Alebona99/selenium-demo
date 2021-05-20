package com.example.selenium_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GuruLoginPageTest {

  MainPage mainPage = new MainPage();
  static WebDriver webDriver= null;

  @BeforeAll
  public static void setUpAll() {
    System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--headless");
    chromeOptions.addArguments("--no-sandbox");
    webDriver = new ChromeDriver(chromeOptions);

  }

  @BeforeEach
  public void setUp() {
    webDriver.get("http://demo.guru99.com/test/login.html");
  }

  @Test
  public void isLoaded() {
    if (webDriver.getPageSource().contains("Demo Sites"))
      Assertions.assertTrue(true);
    else
      Assertions.fail("Non Trovato");
  }

  @Test
  public void Login() {
    webDriver.findElement(By.id("email")).sendKeys("mngr326691");
    webDriver.findElement(By.id("passwd")).sendKeys("Pippo");
    webDriver.findElement(By.id("SubmitLogin")).click();
    Assertions.assertTrue(webDriver.getPageSource().contains("Successfully Logged in..."));
  }

}
