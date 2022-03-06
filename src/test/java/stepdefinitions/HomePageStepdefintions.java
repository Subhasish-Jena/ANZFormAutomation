package stepdefinitions;


import pages.HomeLoanPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageStepdefintions {
    private WebDriver driver;
    private HomeLoanPage homeLoanPage;

    @Given("^I navigate to the ANZ Home Loan Page$")
    public void iNavigateToANZHomeLoanPage() throws InterruptedException {
        setUpDriver();
        homeLoanPage.goToPage();
    }

    @When("^I set details as$")
    public void iSetDetails(DataTable details){
        List<List<String>> rows = details.asLists(String.class);

        for (List<String> row: rows) {
            switch (row.get(0)){
                case "Application Type" :
                {
                 homeLoanPage.selectApplicationType(row.get(1));
                 break;
                }
                case "Dependents" :
                {
                    homeLoanPage.selectDependents(row.get(1));
                    break;
                }
                case "Property type" :
                {
                    homeLoanPage.selectPropertyType(row.get(1));
                    break;
                }
            }
        }
    }

    @And("^I set earnings as$")
    public void iSetEarnings(DataTable earnings){
        List<List<String>> rows = earnings.asLists(String.class);
        for (List<String> row: rows) {
            switch (row.get(0)){
                case "Annual Income" :
                {
                    homeLoanPage.enterAnnualIncome(row.get(1));
                    break;
                }
                case "OtherIncome" :
                {
                    homeLoanPage.enterOtherIncome(row.get(1));
                    break;
                }
            }
        }
    }

    @And("^I set expenses as$")
    public void iSetExpenses(DataTable expenses){
        List<List<String>> rows = expenses.asLists(String.class);
        for (List<String> row: rows) {
            switch (row.get(0)){
                case "Living Expenses" :
                {
                    homeLoanPage.enterExpenses(row.get(1));
                    break;
                }
                case "Other Loan repayment" :
                {
                    homeLoanPage.enterOtherLoans(row.get(1));
                    break;
                }
                case "Credit Card Limits" :
                {
                    homeLoanPage.enterCreditLimit(row.get(1));
                    break;
                }
            }
        }
    }

    @Then("^I should get the borrowing estimate as \"(.*)\"$")
    public void iGetTheResultsAndCheck(String limit) throws InterruptedException {
        homeLoanPage.findBorrowingLimit();
        homeLoanPage.checkTheLimit(limit);
        homeLoanPage.closeDriver();
    }

    @When("^I use the Start Over option$")
    public void iUseTheStartOverOption() throws InterruptedException {
        homeLoanPage.startOver();
    }

    @Then("^The borrowing limit should be reset to \"(.*)\"$")
    public void iCheckBorrowingLimitHasBeenReset(String resetAmount) throws InterruptedException {
        homeLoanPage.checkStartingLimit(resetAmount);
        homeLoanPage.closeDriver();
    }

    @Then("^I verify the prompt to contact helpline$")
    public void iVerifyThePromptToContactHelpline() throws InterruptedException {
        homeLoanPage.findBorrowingLimit();
        homeLoanPage.checkHelpLinePromptMessage();
        homeLoanPage.closeDriver();
    }

    public void setUpDriver(){
        System.out.println("here");
        System.setProperty("webdriver.chrome.driver", "/Users/subhasishjena/Projects/ANZFormAutomation/chromedriver");
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homeLoanPage = new HomeLoanPage(driver);
    }
}
