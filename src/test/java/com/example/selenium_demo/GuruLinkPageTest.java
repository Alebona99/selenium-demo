package com.example.selenium_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GuruLinkPageTest {

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
        webDriver.get("http://demo.guru99.com/test/link.html");
    }


    /**
     * Test case 1
     * Controllo che sia stata caricata correttamente la pagina
     */
    @Test
    public void isLoaded(){
        Assertions.assertTrue(webDriver.getPageSource().contains("click here"));
    }


    /**
     * Test case 2
     * Controllo che cliccando sul primo link esca la pagina google
     * e che sia visualizzata
     */
    @Test
    public void link_google(){
        webDriver.findElement(By.xpath("/html/body/a[1]")).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //WebElement google = webDriver.findElement(By.xpath("//input[@name='btnK']"));
        WebElement cerca = webDriver.findElement(By.name("btnK"));
        Assertions.assertTrue(cerca.getAttribute("value").contains("Google"));

    }


    /**
     * Test case 3
     * Controllo che cliccando sul secondo link esca la pagina Facebook
     * e che sia visualizzata
     */
    @Test
    public void link_fb(){
        webDriver.findElement(By.xpath("/html/body/a[2]")).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement email = webDriver.findElement(By.id("email"));
        WebElement logo = webDriver.findElement(By.className("fb_logo"));

        Assertions.assertTrue(email.getAttribute("placeholder").contains("E-mail o numero di telefono"));
        Assertions.assertTrue(logo.isDisplayed());
    }


    /**
     * Test case 4
     * Controllo che cliccando sui link si visualizzino le pagine
     * e che la navigazione funzioni bene
     */
    @Test
    public void link(){
        webDriver.findElement(By.xpath("/html/body/a[1]")).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement cerca = webDriver.findElement(By.name("btnK"));
        WebElement google = webDriver.findElement(By.className("lnXdpd"));

        Assertions.assertTrue(cerca.getAttribute("value").contains("Google"));
        Assertions.assertTrue(google.getAttribute("alt").contains("Google"));

        webDriver.navigate().back();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.findElement(By.xpath("/html/body/a[2]")).click();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement email = webDriver.findElement(By.id("email"));
        WebElement logo = webDriver.findElement(By.className("fb_logo"));

        Assertions.assertTrue(email.getAttribute("placeholder").contains("E-mail o numero di telefono"));
        Assertions.assertTrue(logo.isDisplayed());

        webDriver.navigate().back();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Assertions.assertTrue(webDriver.getPageSource().contains("click here"));
    }
}
