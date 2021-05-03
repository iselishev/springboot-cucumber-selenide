package example.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.baseUrl;

@Component
public class LoginPage implements BasePage {

    private SelenideElement usernameField = $(By.id("user-name"));
    private SelenideElement passwordField = $(By.id("password"));
    private SelenideElement loginBtn = $(By.id("login-button"));

    public SelenideElement errorMessageDiv = $x("//div[@class='error-message-container error']");
    public SelenideElement errorMessageBtnX = $x("//button[@class='error-button']");


    public LoginPage openPage(){
        open(baseUrl);
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameField.click();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordField.click();
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginBtn() {
        loginBtn.click();
        return this;
    }

    public boolean errorMessageIsDisplayed() {
        return errorMessageDiv.isDisplayed();
    }

    public LoginPage attemptToCloseErrorMessage() {
        errorMessageBtnX.click();
        return this;
    }

    public void getsLoaded() {
        loginBtn.should(Condition.exist);
    }
}
