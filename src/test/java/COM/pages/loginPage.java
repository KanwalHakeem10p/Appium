package COM.pages;
import COM.base.AppDriver;
import COM.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

    public class loginPage extends AppFactory {
        public loginPage (){
            PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
        }

        @AndroidFindBy(accessibility = "test-Username")
        public WebElement userNameTextBox;

        @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
        public WebElement passwordTextBox;

        @AndroidFindBy(accessibility = "test-LOGIN")
        public WebElement loginBtn;

        By swagslabHeader = By.xpath("//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]");

        @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
        public WebElement errorMessage;

        @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"LOGOUT\"]")
        public WebElement logoutBtn;

        public void enterUserName(String userName){
            new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(swagslabHeader));
            userNameTextBox.sendKeys(userName);
        }

        public void enterPassword(String password){
            passwordTextBox.sendKeys(password);
        }

        public productPage clickLoginBtn(){
            clickElement(loginBtn, "Clicking Login Button");
            return new productPage();
        }

        public String getErrorMessage() {
            return getAttribute(errorMessage, "text");
        }

        public void clickLogout(){
            clickElement(logoutBtn, "Clicking Logout button");
        }

        public boolean IsUserNameDisplayed(){
            return userNameTextBox.isDisplayed();
        }
}
