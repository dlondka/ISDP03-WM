package newUser;

import static java.lang.Thread.sleep;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChooseUsersAccessLevel {
    private WebDriver webDriver;
    private String url;
    private String username;
    private String password;
    
    @Before
    public void setUp() throws InterruptedException {
        sleep(3000);
        webDriver = new FirefoxDriver();
        url = "https://localhost:8181/faces/common/signIn.xhtml";
        username = "DMitchell";
        password = "P@ssw0rd";        
    }
    
@Test
    public void chooseUsersAccessLevel() {
        login();
        
    }
    
    
    private void login() {
        webDriver.get(url);
        WebElement loginName = webDriver.findElement(By.name("j_username"));
        loginName.clear();
        loginName.sendKeys(username);
        
        WebElement passwordName = webDriver.findElement(By.name("j_password"));
        passwordName.clear();
        passwordName.sendKeys(password);
        
        webDriver.findElement(By.xpath("//input[@value='Sign in']")).click();
        
    }
    
}

/// k, dziena