package example.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class SelenideConfig {

    @Value("${tests.base-url}")
    private String baseUrl;
    @Value("${selenoid.gridOn:false}")
    private boolean gridOn;
    @Value("${selenod.gridUrl:}")
    private String gridUrl;
    @Value("${browser.webDriverOff:false}")
    private boolean webDriverOff;
    @Value("${selenide.driver.download:false}")
    private boolean isSelenideDriverDownloadedFromWeb;

    private WebDriver currentWebDriver;

    @PostConstruct
    public void init() {

        if (!webDriverOff) {
            Configuration.baseUrl = baseUrl;
            Configuration.timeout = TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS);
            Configuration.driverManagerEnabled = isSelenideDriverDownloadedFromWeb;

            SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
            ChromeOptions options = createChromeOptions();
            if (gridOn) {
                try {
                    RemoteWebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);
                    WebDriverRunner.setWebDriver(driver);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Unable to create remote driver", e);
                }
            } else {
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                options.setExperimentalOption("prefs", chromePrefs);
                WebDriverRunner.setWebDriver(new ChromeDriver(options));
            }
            currentWebDriver = WebDriverRunner.getWebDriver();
        }
    }

    @PreDestroy
    public void tearDown() {
        currentWebDriver.close();
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-xss-auditor");
        options.addArguments("--incognito");
        options.setCapability("enableVNC", true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
        options.addArguments("--window-size=1920,1080");
        return options;
    }
}
