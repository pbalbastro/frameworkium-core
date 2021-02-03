package com.frameworkium.core.ui.pages.pageobjects;

public class CombineResults {
    private String websiteName;
    private String productName;
    private String productPrice;
    private String productLink;

    public CombineResults(String websiteName, String productName, String productPrice, String productLink) {
        this.websiteName = websiteName;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productLink = productLink;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductLink() {
        return productLink;
    }
}
