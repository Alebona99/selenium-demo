package com.example.selenium_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormDPageTest {

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
        webDriver.get("http://localhost:4200/formD");
    }


    /**
     * Test case 1
     * Controllo che la pagina sia stata caricata correttamente
     * verificando abbia la stringa Recupera Password
     */
    @Test
    public void isLoaded(){
        Assertions.assertTrue(webDriver.getPageSource().contains("Recupera Password"));
    }


    /**
     * Test case 2
     * Controllo che completando correttamente il form
     * venga visulizzato il dialog con la password dimenticata
     */
    @Test
    public void formD_success(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Recupero Password"));
    }


    /**
     * Test case 3
     * Controllo che completando il form con un utente sbagliato
     * mi dia un messaggio di errore
     */
    @Test
    public void formD_wrong(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        webDriver.findElement(By.id("email")).sendKeys("pippo@pluto.com");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Username o email sbagliati"));
    }


    /**
     * Test case 4
     * Controllo che non mettendo un username
     * mi dia un stringa di errore
     */
    @Test
    public void formD_no_username(){
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Username required"));
    }


    /**
     * Test case 5
     * Controllo che mettendo un username con lunghezza inferiore a quella consentita
     * mi dia una stringa di errore
     */
    @Test
    public void formD_username_min(){
        webDriver.findElement(By.id("username")).sendKeys("Pi");
        webDriver.findElement(By.id("email")).sendKeys("pippo@pluto.com");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Username min 4 characters"));
    }


    /**
     * Test case 6
     * Controllo che non inserendo la mail
     * mi dia una stringa di errore
     */
    @Test
    public void formD_no_email(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        webDriver.findElement(By.id("email")).sendKeys("");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Email required"));
    }


    /**
     * Test case 7
     * Controllo che inserendo una mail sbagliata
     * mi dia una stringa di errore
     */
    @Test
    public void formD_email_invalid(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        webDriver.findElement(By.id("email")).sendKeys("pippo.com");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Email is invalid"));
    }


    /**
     * Test case 8
     * Controllo che non inserendo niente nei campi
     * mi escano delle stringhe di errore
     */
    @Test
    public void formD_all_wrong(){
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("email")).sendKeys("");
        webDriver.findElement(By.id("recpass")).click();

        Assertions.assertTrue(webDriver.getPageSource().contains("Username required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Email required"));
    }

}
