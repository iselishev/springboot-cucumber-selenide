package example.saucedemo.cucumber;

import com.codeborne.selenide.Condition;
import example.saucedemo.pages.model.Product;
import example.saucedemo.pages.CheckoutInfoPage;
import example.saucedemo.pages.CheckoutOverviewPage;
import example.saucedemo.pages.CheckoutPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutSteps {

    @Autowired
    private CheckoutInfoPage checkoutInfoPage;
    @Autowired
    private CheckoutOverviewPage checkoutOverviewPage;
    @Autowired
    private CheckoutPage checkoutPage;

    @Autowired
    private SharedContent shared;


    @And("User fills in checkout info")
    public void userFillsInCheckoutInfo(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        for (List<String> columns : rows) {
            checkoutInfoPage.firstName.sendKeys(columns.get(0));
            checkoutInfoPage.lastName.sendKeys(columns.get(1));
            checkoutInfoPage.postalCode.sendKeys(columns.get(2));
        }
    }

    @When("User clicks at 'Continue'")
    public void userClicksAtContinue() {
        checkoutInfoPage.continueLink.click();
    }

    @Then("total products price is displayed in the 'Item total'")
    public void totalProductsPriceIsDisplayedInTheItemTotal() {
        String expectedTotal = "Item total: $" + calculateTotalItemsAmount(shared.getAddedProducts());
        checkoutOverviewPage.itemTotalAmountLabel.shouldHave(Condition.text(expectedTotal));
    }

    private Float calculateTotalItemsAmount(List<Product> products) {
        List<Float> prices = new ArrayList<>();
        products.forEach(x -> prices.add(Float.valueOf(x.getPrice().substring(1))));
        Float sum = 0.00f;
        for (Float price : prices) {
            sum += price;
        }
        return sum;
    }

    @When("User clicks at 'Finish'")
    public void userClicksAtFinish() {
        checkoutOverviewPage.finishLink.click();
    }

    @Then("User is at CHECKOUT: COMPLETE page")
    public void userIsAtCHECKOUTCOMPLETEPage() {
        checkoutPage.getsLoaded();
    }

    @And("Checkout message {string} is displayed")
    public void checkoutMessageIsDisplayed(String message) {
        checkoutPage.thankYouNote.shouldHave(Condition.text(message));
    }

}
