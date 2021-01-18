import static java.lang.Thread.sleep;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class EditLocationTest {
    
    WebDriver driver;
    String signIn;
    String username;
    String password;
    String newLocation;
    String locationList;
    String location;
    
    @Before
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
                options.addArguments("headless");
                options.addArguments("window-size=1200x600");
        driver = new FirefoxDriver(options);
        signIn = "https://localhost:8181/faces/common/signIn.xhtml";
        locationList = "https://localhost:8181/faces/location/listLocations.xhtml";
        username = "JDoe";
        password = "P@ssw0rd";
    }
    
    @Test
    public void editLocationTest() throws InterruptedException{
        //sign in as warehouse
        driver.get(signIn);
        WebElement login = driver.findElement(By.name("j_username"));
        login.clear();
        login.sendKeys(username);
        
        WebElement pass = driver.findElement(By.name("j_password"));
        pass.clear();
        pass.sendKeys(password);

        driver.findElement(By.cssSelector(".content > form:nth-child(1) > p:nth-child(2) > input:nth-child(2)")).click();
        
        Assert.assertFalse(driver.getPageSource().contains("Incorrect login or password") || driver.getPageSource().contains("Niepoprawny login lub hasło "));
        
        //display list
        driver.get(locationList);
        
        //gut current location type
        WebElement locationType = driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)"));
        String oldType = locationType.getText();

        //go to edition
        WebElement editFirstLocationBtn = driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(5) > input:nth-child(1)"));
        editFirstLocationBtn.click();
        
        //pick a new type
        WebElement newTypePicker = driver.findElement(By.name("EditLocationForm:locationType"));
        newTypePicker.click();
        
        //get two different types
        WebElement newType1 = driver.findElement(By.cssSelector("#EditLocationForm\\:locationType > option:nth-child(4)"));
        WebElement newType2 = driver.findElement(By.cssSelector("#EditLocationForm\\:locationType > option:nth-child(3)"));
        String type1 = newType1.getText();
        String type2 = newType2.getText();

        //assign a new type
        if(!oldType.equals(type1)){
            newType1.click();
            driver.findElement(By.name("EditLocationForm:j_idt31")).click();
            WebElement editedType = driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)"));
            Assert.assertFalse(editedType.getText().equals(oldType));
            Assert.assertEquals(editedType.getText(), type1);
        }
        else{
            newType2.click();
            driver.findElement(By.name("EditLocationForm:j_idt31")).click();
            WebElement editedType = driver.findElement(By.cssSelector(".table > tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(2)"));  
            Assert.assertFalse(editedType.getText().equals(oldType));
            Assert.assertEquals(editedType.getText(), type2);
        }  
    }
    
    @After
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }     
    }
    
}
