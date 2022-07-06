package org.akhil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FindIfLocatorExistsInPage {

    static WebDriver driver;
    static Scanner scanner = new Scanner(System.in);
    static String locator;


    private void getURL(String url){
        driver.get(url);
    }
    
    private boolean findAllTypesOfElements(String loc){
        boolean flag = false;
        try{
            List<WebElement> list = driver.findElements(By.xpath(loc));
            if (list.size() > 0){
                System.out.println("FOUND: " + list.size() + " element(s) by 'XPATH'.");
                flag = true;
            }
        }
        catch (Exception ignored){}
        
        try{
            List<WebElement> list = driver.findElements(By.id(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'ID'.");
                flag = true;
            }
        }
        catch (Exception ignored){}
        
        try{
            List<WebElement> list = driver.findElements(By.name(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'NAME'.");
                flag = true;
            }
        }
        catch (Exception ignored){}

        try{
            List<WebElement> list = driver.findElements(By.cssSelector(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'CSS Selector'.");
                flag = true;
            }
        }
        catch (Exception ignored){}

        try{
            List<WebElement> list = driver.findElements(By.linkText(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'Link Text'.");
                flag = true;
            }
        }
        catch (Exception ignored){}

        try{
            List<WebElement> list = driver.findElements(By.partialLinkText(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'Partial Link Text'.");
                flag = true;
            }
        }
        catch (Exception ex){}

        try{
            List<WebElement> list = driver.findElements(By.tagName(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'TagName'.");
                flag = true;
            }
        }
        catch (Exception ignored){}

        try{
            List<WebElement> list = driver.findElements(By.className(loc));
            if (list.size() > 0) {
                System.out.println("FOUND: " + list.size() + " element(s) by 'Class Name'.");
                flag = true;
            }
        }
        catch (Exception ignored){}

        if (!flag) {
            System.out.println("No element(s) found!");
            return false;
        }
        return true;
    }

    public void runner(){
        System.out.println("Enter the URL you want to open:");
        String url = scanner.nextLine();
        getURL(url);
        while (true){
            System.out.println("Enter locator: ");
            locator = scanner.nextLine();
            if (locator.equalsIgnoreCase("exit")) {
                System.out.println("Quitting..");
                break;
            }
            else if(locator.equalsIgnoreCase("m")) {
                System.out.println("Restarting..");
                break;
            }
            else{
                findAllTypesOfElements(locator);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FindIfLocatorExistsInPage findIfLocatorExistsInPage = new FindIfLocatorExistsInPage();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        while(true){
            System.out.println("#######################################################");
            System.out.println("                Welcome to LocatorFinder!               ");
            System.out.println("#######################################################");
            System.out.println("At any point in time, type 'exit' to quit and 'm' to return to main menu.");
            findIfLocatorExistsInPage.runner();
            if (locator.equalsIgnoreCase("q")){
                break;
            }
        }
        driver.close();

    }

}
