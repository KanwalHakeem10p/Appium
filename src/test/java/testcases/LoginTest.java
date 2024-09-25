package testcases;
import base.AppFactory;
import pages.loginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends AppFactory{
    loginPage loginPage;

    @BeforeMethod
    public void setup(){
        loginPage = new loginPage();
    }

    @Test
    public void verifyValidUserLogin() {
        System.out.println("This test is used to login with valid username and password");
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
        System.out.println("Login Successful");
    }

    @AfterMethod
    public void tearDown(){
        AppFactory.quitDriver();
    }
}
