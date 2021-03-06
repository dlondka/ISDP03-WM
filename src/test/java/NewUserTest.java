

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class NewUserTest {
    private WebDriver driver;
    private String loginUrl;
    private String createUserUrl;
    private String newUsersUrl;
    private String userName;
    private String userPass;
    // New user information
    private String newUserName;
    private String newUserSurname;
    private String newUserMail;
    private String newUserLogin;
    private String newUserPass;
    private String newUserQuestion;
    private String newUserAnswer;
    
    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        loginUrl = "https://localhost:8181/faces/common/signIn.xhtml";
        createUserUrl = "https://localhost:8181/faces/common/registerAccount.xhtml";
        newUsersUrl = "https://localhost:8181/faces/account/listNewAccounts.xhtml";
        userName = "DMitchell";
        userPass = "P@ssw0rd";       
        newUserName = "Daniel";
        newUserSurname = "Londka";
        newUserMail = "isdp.zespol3@gmail.com";
        newUserLogin = "DLondka";
        newUserPass = "P@ssw0rd";
        newUserQuestion = "What is my purpose?";
        newUserAnswer = "You pass butter";
    }
    
    @Test
    public void testAddNewUser() throws Exception {
        // Create a new instance of the Firefox driver

        driver.get(loginUrl);
        
        WebElement login = driver.findElement(By.name("j_username"));
        login.clear();
        login.sendKeys(userName);
        
        WebElement password = driver.findElement(By.name("j_password"));
        password.clear();
        password.sendKeys(userPass);
        
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input")).click();
       
        driver.get(createUserUrl);
        
        WebElement nName = driver.findElement(By.name("RegisterForm:name"));
        nName.clear();
        nName.sendKeys(newUserName);
        
        WebElement nSurname = driver.findElement(By.name("RegisterForm:surname"));
        nSurname.clear();
        nSurname.sendKeys(newUserSurname);

        WebElement nEmail = driver.findElement(By.name("RegisterForm:email"));
        nEmail.clear();
        nEmail.sendKeys(newUserMail);

        WebElement nLogin = driver.findElement(By.name("RegisterForm:login"));
        nLogin.clear();
        nLogin.sendKeys(newUserLogin);

        WebElement nPassword = driver.findElement(By.name("RegisterForm:password"));
        nPassword.clear();
        nPassword.sendKeys(newUserPass);

        WebElement nConfirmPassword = driver.findElement(By.name("RegisterForm:passwordRepeat"));
        nConfirmPassword.clear();
        nConfirmPassword.sendKeys(newUserPass);

        WebElement nQuestion = driver.findElement(By.name("RegisterForm:question"));
        nQuestion.clear();
        nQuestion.sendKeys(newUserQuestion);

        WebElement nAnswer = driver.findElement(By.name("RegisterForm:answer"));
        nAnswer.clear();
        nAnswer.sendKeys(newUserAnswer);
        
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();
        
        driver.get(newUsersUrl);
        
        Assert.assertTrue(driver.getPageSource().contains(newUserLogin) &&
                driver.getPageSource().contains(newUserName) &&
                driver.getPageSource().contains(newUserSurname) &&
                driver.getPageSource().contains(newUserMail));
     
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[4]/td[5]/input[2]")).click();
        
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/input[2]")).click();
        
        Assert.assertFalse(driver.getPageSource().contains(newUserLogin) ||
                driver.getPageSource().contains(newUserMail));
    }
    
    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();    
        }
    }
}
