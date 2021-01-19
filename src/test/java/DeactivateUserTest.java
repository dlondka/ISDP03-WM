import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DeactivateUserTest {
    private WebDriver webDriver;
    private String url;
    private String username;
    private String password;
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "/home/student/geckodriver");
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        webDriver = new FirefoxDriver(options);
        url = "https://localhost:8181/faces/common/signIn.xhtml";
        username = "DMitchell";
        password = "P@ssw0rd";        
    }
    
    @Test
    public void deactivateUser() {
        login();
        webDriver.get("https://localhost:8181/faces/account/listAuthorizedAccounts.xhtml");
        
        webDriver.findElement(By.name("j_idt26:j_idt27:2:j_idt43")).click();
        Assert.assertEquals("true", webDriver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[3]/td[5]/input[5]")).getAttribute("disabled"));
        
        webDriver.findElement(By.name("j_idt26:j_idt27:2:j_idt42")).click();
        Assert.assertEquals("true", webDriver.findElement(By.xpath("/html/body/div/div[3]/div/form/table/tbody/tr[3]/td[5]/input[4]")).getAttribute("disabled"));
    }
    
    
    private void login() {
        webDriver.get(url);
        WebElement loginName = webDriver.findElement(By.name("j_username"));
        loginName.clear();
        loginName.sendKeys(username);
        
        WebElement passwordName = webDriver.findElement(By.name("j_password"));
        passwordName.clear();
        passwordName.sendKeys(password);
        
        webDriver.findElement(By.xpath("/html/body/div/div[3]/div/form/p/input")).click();
    }
    
    @After
    public void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();    
        }
    }
    
}