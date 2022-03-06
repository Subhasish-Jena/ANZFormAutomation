package pages;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class HomeLoanPage {

    public WebDriver driver;
    public List<WebElement> applicationType;

    public HomeLoanPage(WebDriver driver) {
     this.driver = driver;
    }

    public void goToPage() throws InterruptedException {
        driver.get(Constants.homeLoanPageURL);
    }

    public void selectApplicationType(String option){
        applicationType = driver.findElements(By.className("application_type"));
        if (option.equals("Single")){
            applicationType.get(0).click();
        }else {
            applicationType.get(1).click();
        }
    }

    public  void selectDependents(String number){
        Select noOfDependents = new Select(driver.findElement(By.cssSelector("[title=\"Number of dependants\"]")));
        noOfDependents.selectByIndex(Integer.parseInt(number));

    }


    public void selectPropertyType(String type){
        WebElement propertyType = null;
        if (type.equals("Home")){
            propertyType = driver.findElement(By.xpath(Constants.propertyTypeXpath));
        }
        propertyType.click();
    }

    public String getTheLimit() throws InterruptedException {
        WebElement limitBox = (new WebDriverWait(driver, 40)).until(
                new ExpectedCondition<WebElement>() {
                    @NullableDecl
                    @Override
                    public WebElement apply(@NullableDecl WebDriver d) {
                        return d.findElement(By.id("borrowResultTextAmount"));
                    }
                }
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", limitBox);
        Thread.sleep(5000);
        return limitBox.getText();
    }

    public void checkTheLimit(String limit) throws InterruptedException {
        assertTrue("Limit is not what was expected, it came to" + limit, getTheLimit().equals(limit));
    }

    public void startOver() throws InterruptedException {
        driver.findElement(By.className("start-over")).click();
    }

    public void checkStartingLimit(String limit) throws InterruptedException {
        assertTrue("Amount was not reset. Start over did not work!!", getTheLimit().equals(limit));
    }

    public void enterAnnualIncome(String amount){
        WebElement annualIncome = driver.findElement(By.xpath(Constants.annualIncomeXpath));
        annualIncome.sendKeys(amount);
    }

    public void enterOtherIncome (String amount){
        WebElement otherIncome = driver.findElement(By.xpath(Constants.otherIncomeXpath));
        otherIncome.sendKeys(amount);

    }

    public void enterExpenses(String amount){
        driver.findElement(By.id("expenses")).sendKeys(amount);
    }

    public void enterOtherLoans(String amount){
        driver.findElement(By.id("otherloans")).sendKeys(amount);
    }

    public void enterCreditLimit(String amount){
        driver.findElement(By.id("credit")).sendKeys(amount);
    }

    public void findBorrowingLimit(){
        driver.findElement(By.id("btnBorrowCalculater")).click();
    }

    public void checkHelpLinePromptMessage(){
        WebElement errorMessage = (new WebDriverWait(driver, 40)).until(
                new ExpectedCondition<WebElement>() {
                    @NullableDecl
                    @Override
                    public WebElement apply(@NullableDecl WebDriver d) {
                        return d.findElement(By.className("borrow__error__text"));
                    }
                }
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", errorMessage);
        assertTrue("Helpline contact text did not match!!",
                errorMessage.getText().equals(Constants.contactHelpDeskText));
    }

    public void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }


}
