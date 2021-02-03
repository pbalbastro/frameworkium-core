package com.frameworkium.core.ui.pages.pageobjects;

import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.pages.BasePage;
import com.frameworkium.core.ui.pages.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AmazonPage extends BasePage<AmazonPage> {

    @Name("Search Box")
    @FindBy(css = "#twotabsearchtextbox")
    private WebElement searchBox;

    @Name("Search Button")
    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

    @Name("Amazon Product Link")
    @FindBy(partialLinkText = "iPhone 11,")
    private WebElement ProductLink;

    @Name("Amazon Product Name")
    @FindBy(css = "#productTitle")
    private WebElement productName;

    @Name("Amazon Product Price")
    @FindBy(css = "#priceblock_ourprice")
    private WebElement productPrice;

    @Step("Navigate to Amazon Home Page")
    public static AmazonPage open() {
        UITestLifecycle.get().getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return PageFactory.newInstance(AmazonPage.class, "http://www.amazon.com");
    }

    @Step("Set value to searchbox")
    public AmazonPage setSearch(String value) {
        searchBox.clear();
        searchBox.sendKeys(value);
        searchButton.click();
        return this;
    }

    @Step("Open new tab")
    public EbayPage openEbay() {
        ((JavascriptExecutor) UITestLifecycle.get().getWebDriver()).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(UITestLifecycle.get().getWebDriver().getWindowHandles());
        UITestLifecycle.get().getWebDriver().switchTo().window(tabs.get(1));//switch to new tab
        return PageFactory.newInstance(EbayPage.class, "http://www.ebay.com");
    }

    @Step("Get Amazon Website Name")
    public String getWebSiteName(){
        String amazonWebName = UITestLifecycle.get().getWebDriver().getTitle();
        return amazonWebName.substring(0, amazonWebName.indexOf(":"));
    }

    @Step("Click iPhone Product")
    public AmazonPage clickProductLink() {
        ProductLink.click();
        return this;
    }

    @Step("Get Product Name")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Get Product Price")
    public String getProductPrice() {
        String price = productPrice.getText();
        return price.substring(price.lastIndexOf("$") + 1);
    }

    @Step("Get  Product Link")
    public String getProductLink() {
        return driver.getCurrentUrl();
    }
}
