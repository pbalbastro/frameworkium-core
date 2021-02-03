package com.frameworkium.core.ui;

import com.frameworkium.core.ui.pages.pageobjects.AmazonPage;
import com.frameworkium.core.ui.pages.pageobjects.EbayPage;
import com.frameworkium.core.ui.pages.pageobjects.CombineResults;
import com.frameworkium.core.ui.tests.BaseUITest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EcommerceSiteSearchTest extends BaseUITest {

    @Test
    public void search_iphone11_sort_by_price() {
        String searchTerm = "iPhone 11";

        UITestLifecycle.get().getWebDriver().manage().window().maximize();

        AmazonPage amazonPage = AmazonPage.open()
                .setSearch(searchTerm)
                .clickProductLink();

        String amazonWebname = amazonPage.getWebSiteName();
        String amazonProductName = amazonPage.getProductName();
        String amazonProductPrice = amazonPage.getProductPrice();
        String amazonProductLink = amazonPage.getProductLink();

        EbayPage ebayPage = amazonPage.openEbay()
                .setSearch(searchTerm)
                .clickProductLink();

        String ebayWebName = ebayPage.getWebSiteName();
        String ebayProductName = ebayPage.getProductName();
        String ebayProductPrice = ebayPage.getProductPrice();
        String ebayProductLink = ebayPage.getProductLink();

        ArrayList<CombineResults> results = new ArrayList<CombineResults>();

        results.add(new CombineResults(amazonWebname, amazonProductName,
                amazonProductPrice, amazonProductLink));

        results.add(new CombineResults(ebayWebName, ebayProductName,
                ebayProductPrice, ebayProductLink));

        Collections.sort(results, new Comparator<CombineResults>() {
            @Override
            public int compare(CombineResults o1, CombineResults o2) {
                return o1.getProductPrice().compareTo(o2.getProductPrice());
            }
        });

        for (int count = 0; count < results.size(); count++) {
            System.out.println(results.get(count). getWebsiteName()+"\n"
                    +results.get(count).getProductName() +"\n"
                    +results.get(count).getProductPrice() + "\n"
                    +results.get(count).getProductLink() + "\n");
        }
    }
}
