package example.saucedemo.cucumber;

import example.saucedemo.pages.model.Product;
import example.saucedemo.pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    @Autowired
    private CartPage cartPage;

    @Autowired
    private SharedContent shared;


    @And("User is at YOUR CART page")
    public void userIsAtYOURCARTPage() {
        cartPage.getsLoaded();
    }

    @Then("products in cart match the ADDED_PRODUCTS")
    public void productsInCartMatchTheADDED_PRODUCTS() {
        List<Product> inCartProducts = new ArrayList<>();
        cartPage.getAllInventoryItems().forEach(x -> inCartProducts.add(x.getProductInfo()));
        compareListsThroughToString(shared.getAddedProducts(), inCartProducts);
    }

    private void compareListsThroughToString(List a, List b) {
        assertEquals(a.size(), b.size(), "Amount of items does not match expectation");
        List<String> aString = new ArrayList<>();
        List<String> bString = new ArrayList<>();
        a.forEach(x -> aString.add(x.toString()));
        b.forEach(x -> bString.add(x.toString()));
        aString.forEach(x -> assertTrue(bString.contains(x)));
    }

    @When("User clicks at 'Checkout'")
    public void userClicksAtCheckout() {
        cartPage.checkoutBtn.click();
    }

}
