package com.example.selenium_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginPageTest {

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
        webDriver.get("http://localhost:4200/login");
    }


    /**
     * Test case 1
     * Controlla che la pagina sia stata caricata correttamente
     */
    @Test
    public void isLoaded() {
        Assertions.assertTrue(webDriver.getPageSource().contains("Login"));
    }


    /**
     * Test case 2
     * Controlla che l'accesso venga eseguito correttamente
     * con l'utente già memorizzato nel form
     */
    @Test
    public void login_success(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("password")).sendKeys("Alessio");
        webDriver.findElement(By.id("connect")).click();
        webDriver.findElement(By.id("login")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Accesso eseguito"));
    }

    /**
     * Test case 3
     * Controllo che l'accesso non venga eseguito
     * poichè l'utente passato non esiste
     */
    @Test
    public void login_wrong(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        webDriver.findElement(By.id("password")).sendKeys("pluto8905");
        webDriver.findElement(By.id("connect")).click();
        webDriver.findElement(By.id("login")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Username o password"));
    }


    /**
     * Test case 4
     * Controllo che il bottone della login sia disabilitato
     * poichè manca il campo username
     */
    @Test
    public void login_no_username(){
        webDriver.findElement(By.id("password")).sendKeys("99191919191");
        webDriver.findElement(By.id("connect")).click();
        webDriver.findElement(By.id("login")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("login")).isEnabled());

    }

    /**
     * Test case 5
     * Controlla che il bottone sia disabilitato
     * perchè il campo username non è corretto, dev'essere min 4 caratteri
     */
    @Test
    public void login_username_min(){
        webDriver.findElement(By.id("username")).sendKeys("pi");
        webDriver.findElement(By.id("password")).sendKeys("seeeeeeeee");
        Assertions.assertFalse(webDriver.findElement(By.id("login")).isEnabled());
        Assertions.assertTrue(webDriver.getPageSource().contains("must be 4 characters"));
    }

    /**
     * Test case 6
     * Controllo che saltando il campo della password
     * il bottone login sia disabilitato
     */
    @Test
    public void login_no_psswd(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        Assertions.assertFalse(webDriver.findElement(By.id("login")).isEnabled());
    }

    /**
     * Test case 7
     * Controllo che mettendo una password di 2 caratteri dia errore
     */
    @Test
    public void login_password_min(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        webDriver.findElement(By.id("password")).sendKeys("pl");
        webDriver.findElement(By.id("login")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("min 6"));
    }


    /**
     * Test case 8
     * Controllo che mettendo un username ma non una password
     * il bottone sia disabilitato e che esca una stringa di errore 'required'
     */
    @Test
    public void login_password_blank(){
        webDriver.findElement(By.id("username")).sendKeys("Pippo");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.id("login")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("login")).isEnabled());
        Assertions.assertTrue(webDriver.getPageSource().contains("required"));
    }


    /**
     * Test case 9
     * Controllo che non completando il form di login
     * il bottone non sia abilitato e che escano gli errori required dei campi
     */
    @Test
    public void login_username_psswd_blank(){
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.id("login")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("login")).isEnabled());
        Assertions.assertTrue(webDriver.getPageSource().contains("Username is required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Password is required"));
    }


}
