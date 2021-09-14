package org.example;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ActionWtBrowser {

    public static WebDriver driver;

    ActionWtBrowser (WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public static void createNewTab (boolean switchToNewTab) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        if (switchToNewTab) driver.switchTo().window(tabs.get(1));
    }

    public static void createNewTab2 (boolean switchToNewTab) {
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .keyDown(Keys.SHIFT)
                .click()
                .keyUp(Keys.CONTROL)
                .keyUp(Keys.SHIFT)
                .perform();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (switchToNewTab) driver.switchTo().window(tabs.get(1));
    }

}
