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
    public WebElement productHeader;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text=\"Sauce Labs Backpack\"]")
    public WebElement productImageClick;

    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"Sauce Labs Backpack\"]")
    public WebElement productDetail;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    public WebElement LeftNavigation;

    public String getTitle(){
        return getAttribute(productHeader, "text");
    }
    public void clickProduct(){
        clickElement(productImageClick, "Clicking Product Image");
    }
    public String getProductTitle(){
        return getAttribute(productDetail, "text");
    }
    public void clickLeftNavigationBar(){
        clickElement(LeftNavigation, "Clicking SideNavigation Button");
    }
}
