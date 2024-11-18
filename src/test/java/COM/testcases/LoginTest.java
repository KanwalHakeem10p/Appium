package COM.testcases;
import COM.QA.base.AppFactory;
import COM.pages.loginPage;
import COM.pages.productPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import COM.reports.ExtentReport;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

public class LoginTest extends AppFactory{
    loginPage LoginPage;
    productPage productPage;
    InputStream inputStream;
    JSONObject loginUser;

    @BeforeMethod
    public void setup(Method method) {
        LoginPage = new loginPage();
        utilities.log().info("\n\n********** " + method.getName() + " **********\n\n");
    }
    @BeforeClass
    public void setupDataStream() throws IOException {
        try {
            String dataFileName = "data/loginUser.json";
            inputStream = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener jsonTokener = new JSONTokener(Objects.requireNonNull(inputStream));
            loginUser = new JSONObject(jsonTokener);

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void verifyInvalidUsername() {
        utilities.log().info("This test is used to login with invalid Username and valid Password");
        LoginPage.enterUserName(loginUser.getJSONObject("invalidUser").getString("userName"));
        LoginPage.enterPassword(loginUser.getJSONObject("invalidUser").getString("password"));
        LoginPage.clickLoginBtn();

        String expectedErrorMessage = stringHashMap.get("error_invalid_username_and_password");
        String actualErrorMessage = LoginPage.getErrorMessage();
        utilities.log().info("Actual Error Message is " + actualErrorMessage + "\nExpected Error Message is " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void verifyInvalidPassword() {
        utilities.log().info("This test is used to login with valid Username and invalid Password");
        LoginPage.enterUserName(loginUser.getJSONObject("invalidPassword").getString("userName"));
        LoginPage.enterPassword(loginUser.getJSONObject("invalidPassword").getString("password"));
        LoginPage.clickLoginBtn();

        String expectedErrorMessage = stringHashMap.get("error_invalid_username_and_password");
        String actualErrorMessage = LoginPage.getErrorMessage();

        utilities.log().info("Actual Error Message is " + actualErrorMessage + "\nExpected Error Message is " + expectedErrorMessage);
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
    @Test
    public void verifyValidLogin() {
        utilities.log().info("This test is used to login with valid Username and valid Password");
        LoginPage.enterUserName(loginUser.getJSONObject("validUserAndPassword").getString("userName"));
        LoginPage.enterPassword(loginUser.getJSONObject("validUserAndPassword").getString("password"));
        productPage = LoginPage.clickLoginBtn();
        String expectedTitle = stringHashMap.get("product_title");
        String actualTitle = productPage.getTitle();
        utilities.log().info("Actual Product Title is " + actualTitle + "\nExpected Product Title is " + expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterTest
    public void tearDown(){
        AppFactory.quitDriver();
    }
}
