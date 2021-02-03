package com.frameworkium.core.ui.pages.pageobjects;

import com.frameworkium.core.ui.UITestLifecycle;
import com.frameworkium.core.ui.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class EbayPage extends BasePage<EbayPage> {

    @Name("Search Box")
    @FindBy(css = "input[class='gh-tb ui-autocomplete-input']")
    private WebElement searchBox;

    @Name("Search Button")
    @FindBy(css = "input[class='btn btn-prim gh-spr']")
    private WebElement searchButton;

    @Name("Product Link")
    @FindBy(partialLinkText = "iPhone 11 -")
    private WebElement ProductLink;

    @Name("Product Name")
    @FindBy(css = "#itemTitle")
    private WebElement productName;

    @Name("Product Price")
    @FindBy(css = "#prcIsum_bidPrice")
    private WebElement productPrice;

    @Step("Set value to searchbox")
    public EbayPage setSearch(String value) {
        searchBox.clear();
        searchBox.sendKeys(value);
        searchButton.click();
        return this;
    }

    @Step("Get Website Name")
    public String getWebSiteName(){
        String eBayWebName = UITestLifecycle.get().getWebDriver().getTitle();
        return eBayWebName.substring(eBayWebName.lastIndexOf("|") + 2);
    }

    @Step("Click iPhone Product")
    public EbayPage clickProductLink() {
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
