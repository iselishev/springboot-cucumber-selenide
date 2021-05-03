package example.saucedemo.cucumber;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import example.config.CredentialsConfig;
import example.saucedemo.pages.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class LoginSteps {

    @Value("${tests.base-url}")
    private String baseUrl;
    @Value("${user.profile:default}")
    private String userProfile;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private CredentialsConfig credentialsConfig;


    @Given("the user passes to the SourceDemo Home page")
    public void theUserPassesToTheSourceDemoHopePage() {
        Configuration.baseUrl = baseUrl;
        loginPage.openPage().getsLoaded();
    }

    @Given("User logs into SourceDemo successfully")
    public void userLogsIntoSourceDemoSuccessfully() {
        theUserPassesToTheSourceDemoHopePage();
        loginPage.openPage().getsLoaded();
        userFillsInUsernameAndPassword(credentialsConfig.username(userProfile), credentialsConfig.password(userProfile));
        userClicksLogin();

    }

    @Given("User fills in username '{}' and password '{}'")
    public void userFillsInUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username).enterPassword(password);
    }

    @When("User clicks 'Login'")
    public void userClicksLogin() {
        loginPage.clickLoginBtn();
    }

    @Then("Error message is displayed '{}'")
    public void errorMessageIsDisplayedMessage(String errorMessage) {
        loginPage.errorMessageDiv.shouldBe(Condition.visible);
        loginPage.errorMessageDiv.shouldHave(Condition.text(errorMessage));
    }
}
