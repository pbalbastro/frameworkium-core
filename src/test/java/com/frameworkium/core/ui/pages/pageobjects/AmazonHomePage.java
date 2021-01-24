package com.frameworkium.core.ui.pages.pageobjects;

import com.frameworkium.core.ui.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;

public class AmazonHomePage extends BasePage<AmazonHomePage> {

    @Name("Search Box")
    @FindBy(css = "#twotabsearchtextbox")
    private WebElement SearchBox;

    @Name("Search Button")
    @FindBy(css = "#nav-search-submit-button")
    private WebElement searchButton;

}
