package apponteotomasyon;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {
    public AppiumDriver driver;
    public WebDriverWait wait;
    By consultantButton = By.id("mobi.appcent.apponte:id/btnInstitutional");
    By loginButton = By.xpath("//android.widget.Button[@resource-id=\"mobi.appcent.apponte:id/ibSignIn\"]");
    By numberText=By.id("mobi.appcent.apponte:id/etPhoneNumber");
    By sendCodeButton= By.id("mobi.appcent.apponte:id/btnSendCode");
    @BeforeTest
    public void beforeTest() {
        try {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability("platformName", "Android");
            cap.setCapability("appium:platformVersion", "10.0");
            cap.setCapability("appium:deviceName", "Android SDK built for arm64");
            cap.setCapability("appium:udid", "emulator-5554");
            cap.setCapability("appium:appPackage", "mobi.appcent.apponte");
            cap.setCapability("appium:appActivity", "mobi.appcent.apponte.ui.activity.login.LoginActivity");
            cap.setCapability("appium:skipUnlock", true);
            cap.setCapability("appium:noReset", false);

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        } catch (MalformedURLException mx) {
            System.out.println("Hatalı oluşturulmuş URL istisnası");
        }
    }

    @Test
    public void test() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Kurumsal butonuna tıklama
        WebElement consultantSelect = driver.findElement(consultantButton);
        consultantSelect.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Giriş yap butonunu seç
        WebElement loginButtonSelect = driver.findElement(loginButton);
        loginButtonSelect.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //OTP Phone number
        WebElement phoneNumberSelect=driver.findElement(numberText);
        phoneNumberSelect.sendKeys("5399393358");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement sendCodeSelect=driver.findElement(sendCodeButton);
        sendCodeSelect.click();
        Thread.sleep(10000);

        

    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
