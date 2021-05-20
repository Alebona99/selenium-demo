package com.example.selenium_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FormCPageTest {

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
        webDriver.get("http://localhost:4200/formC");
    }


    /**
     * Test case 1
     * Controllo che la pagina sia stata caricata correttamente
     * verificando che abbia la stringa Registration
     */
    @Test
    public void isLoaded(){
        Assertions.assertTrue(webDriver.getPageSource().contains("Registration"));
    }


    /**
     * Test case 2
     * Controllo che completando il form correttamente venga visualizzata la stringa di registrazione avvenuta
     */
    @Test
    public void formC_succes(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertTrue(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Registrazione avvenuta con successo!!"));
    }


    /**
     * Test case 3
     * Controllo che completando il form venga visualizzato correttamente il summary
     */
    @Test
    public void formC_summary(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertTrue(webDriver.findElement(By.id("summary")).isEnabled());
        webDriver.findElement(By.id("summary")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("This dialog show your Data Registration"));
    }


    /**
     * Test case 4
     * Controllo che completando il form correttamente
     * venga visualizzato il summary e la registrazione avvenuta con successo
     */
    @Test
    public void formC_summary_submit(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("terms")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Accept Terms and Condition"));
        webDriver.findElement(By.id("close")).click();

        webDriver.findElement(By.id("policy")).click();

        Assertions.assertTrue(webDriver.findElement(By.id("summary")).isEnabled());
        webDriver.findElement(By.id("summary")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("This dialog show your Data Registration"));
        webDriver.findElement(By.id("close")).click();

        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Registrazione avvenuta con successo!!"));
    }


    /**
     * Test case 5
     * Controllo che completando il form senza accettare le condizioni
     * il bottone login sia disabilitato
     */
    @Test
    public void formC_no_policy(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");

        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());

    }


    /**
     * Test case 6
     * Controllo che completando il form senza username
     * mi esca una stringa di errore
     */
    @Test
    public void formC_no_username(){
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Username Required"));
    }


    /**
     * Test case 7
     * Controllo che completando il form con un username minore della lunghezza consentita
     * mi esca una stringa di errore
     */
    @Test
    public void formC_username_min(){
        webDriver.findElement(By.id("username")).sendKeys("Ale");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Username min 4 characters"));
    }


    /**
     * Test case 8
     * Controllo che completando il form senza email
     * mi esca una stringa di errore
     */
    @Test
    public void formC_no_email(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Email Required"));
    }


    /**
     * Test case 9
     * Controllo che completando il form con un email di formato sbagliato
     * mi esca una stringa di errore
     */
    @Test
    public void formC_email_wrong(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Invalid Email"));
    }


    /**
     * Test case 10
     * Controllo che comlpetando il form senza password
     * mi dia una stringa di errore
     */
    @Test
    public void formC_no_password(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.id("repPass")).sendKeys("Alessio99");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Password Required"));
    }


    /**
     * Test case 11
     * Controllo che completando il form senza password e repeat password
     * mi dia una stringa di errore
     */
    @Test
    public void formC_no_password_repPass(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.id("repPass")).sendKeys("");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Password Required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Repeat Password Required"));
    }


    /**
     * Test case 12
     * Controllo che non inserendo la repeat password
     * mi dia una stringa di errore
     */
    @Test
    public void formC_no_repPass(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Repeat Password Required"));
    }


    /**
     * Test case 13
     * Controllo che inserendo una password minore della lunghezza consentita
     * mi dia una stringa di errore
     */
    @Test
    public void formC_password_min(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Ale");
        webDriver.findElement(By.id("repPass")).sendKeys("");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Password min 6 characters"));
    }


    /**
     * Test case 14
     * Controllo che completando il form ma con 2 password differenti
     * mi dia una stringa di errore
     */
    @Test
    public void formC_password_no_match(){
        webDriver.findElement(By.id("username")).sendKeys("Alessio");
        webDriver.findElement(By.id("email")).sendKeys("alexiobona99@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("Alessio99");
        webDriver.findElement(By.id("repPass")).sendKeys("Asdrty6");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Password doesn't match"));
    }


    /**
     * Test case 15
     * Controllo che non immettendo nessun valore nei campi dei form
     * mi dia delle stringhe di errore
     */
    @Test
    public void formC_all_wrong(){
        webDriver.findElement(By.id("username")).sendKeys("");
        webDriver.findElement(By.id("email")).sendKeys("");
        webDriver.findElement(By.id("password")).sendKeys("");
        webDriver.findElement(By.id("repPass")).sendKeys("");
        webDriver.findElement(By.id("policy")).click();
        Assertions.assertFalse(webDriver.findElement(By.id("submit")).isEnabled());
        webDriver.findElement(By.id("submit")).click();
        Assertions.assertTrue(webDriver.getPageSource().contains("Username Required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Email Required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Password Required"));
        Assertions.assertTrue(webDriver.getPageSource().contains("Repeat Password Required"));
    }

}
