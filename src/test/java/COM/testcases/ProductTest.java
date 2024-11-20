package COM.testcases;
import COM.base.AppFactory;
import COM.pages.loginPage;
import COM.pages.productPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

public class ProductTest extends AppFactory {

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
    public void verifyProductName() {
        utilities.log().info("This test is used to login with valid Username and valid Password and go to product detail page");
        LoginPage.enterUserName(loginUser.getJSONObject("validUserAndPassword").getString("userName"));
        LoginPage.enterPassword(loginUser.getJSONObject("validUserAndPassword").getString("password"));
        productPage = LoginPage.clickLoginBtn();
        String expectedProductListTitle = stringHashMap.get("product_title");
        String actualProductListTitle = productPage.getTitle();
        utilities.log().info("Actual Product Title is " + actualProductListTitle + "\nExpected Product Title is " + expectedProductListTitle);
        Assert.assertEquals(actualProductListTitle, expectedProductListTitle);
        productPage.clickProduct();
        String expectedProductDetailTitle = stringHashMap.get("productDetail_title");
        String actualProductDetailTitle = productPage.getProductTitle();
        utilities.log().info("Actual Product Detail Title is " + actualProductDetailTitle + "\nExpected Product Detail Title is " + expectedProductDetailTitle);
        Assert.assertEquals(actualProductDetailTitle, expectedProductDetailTitle);
        productPage.clickLeftNavigationBar();
        LoginPage.clickLogout();
    }
}