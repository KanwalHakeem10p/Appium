package COM.pages;
import COM.base.AppDriver;
import COM.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class productPage  extends AppFactory{
    public productPage() {
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")

    @AndroidFindBy(xpath ="//android.widget.TextView[@text=\"Sauce Labs Backpack\"]")

    public WebElement productHeader;
    public WebElement productDetail;


    public String getTitle(){
        return getAttribute(productHeader, "text");
    }
    public void clickProduct(){
        clickElement(productDetail, "Clicking Product Image");
    }
}
