package example.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public interface PageHeader {
    SelenideElement menuButton = $("#react-burger-menu-btn");
    SelenideElement shoppingCartIcon = $x("//a[@class='shopping_cart_link']");
    SelenideElement shoppingCartTotalBadge  = shoppingCartIcon.$x("./span");
    SelenideElement pageTitle = $x("//span[@class='title']");
}
