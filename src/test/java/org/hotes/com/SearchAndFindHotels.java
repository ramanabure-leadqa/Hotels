package org.hotes.com;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchAndFindHotels {
	public static String strTestUrl = "https://hotels.com/";

	// TestData
	public static String strGoingToDestination = "Orlando";
	public static String strDatesTravelling = "8 April-10 April";
	public static int intAdultsToChoose = 2;
	public static int intChilderensToChoose = 1;
	public static String strChildAge = "10";
	public static String strSortByOption = "Guest rating + our choices";
	public static String strPriceGreterThanDollorAmount = "500";

	// Element Locators
	public static String strGoingToMenuTrigger = "//button[@data-stid='destination_form_field-menu-trigger']";
	public static String strGoingToMenuInput = "//input[@data-stid='destination_form_field-menu-input']";
	public static String strGoinToCity = "(//button[contains(@aria-label,'" + strGoingToDestination + "')])[1]";

	public static String strDates = "//button[@data-testid='uitk-date-selector-input1-default']";
	public static String strChooseDates = "//div[@class='uitk-day-aria-label' and contains(@aria-label,'date-to-select')]";
	public static String strChooseDateButtons = "//div[@class='uitk-day-aria-label' and contains(@aria-label,'date-to-select')]/parent::div";
	public static String strDateSelectDoneBtn = "//button[@data-stid='apply-date-selector']";

	public static String strTravelersAndRoom = "//button[@data-stid='open-room-picker']";
	public static String strIncreaseAdults = "//*[@aria-label='Increase the number of adults in room 1']";
	public static String strDecreaseAdults = "//*[@aria-label='Decrease the number of adults in room 1']";
	public static String strIncreaseChildren = "//*[@aria-label='Increase the number of children in room 1']";
	public static String strDecreaseChildren = "//*[@aria-label='Decrease the number of children in room 1']";
	public static String strChildrenAgeSelection = "//select[@name='child-traveler_selector_children_age_selector-0-0']";
	public static String strTravelersDoneBtn = "//button[@id='traveler_selector_done_button']";

	public static String strSearchBtn = "//button[@id='search_button']";
	public static String strSortBySelection = "//select[@id='sort-filter-dropdown-sort']";

	public static String strPriceMinimumDollorInput = "//input[@id='price-min']";

	public static String strResultsHeaderMessage = "//div[@data-stid='results-header-message']";

	public static void main(String[] args) throws InterruptedException {

		// Launching AUT URL with Chrome Browser
		WebDriver driver = new ChromeDriver();
		driver.get(strTestUrl);

		// Configuring browser options
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));

		// Step to click on the Going to field
		WebElement elmGoingToMenu = driver.findElement(By.xpath(strGoingToMenuTrigger));
		elmGoingToMenu.click();

		// Step to enter value in GoingToMenu field
		WebElement elmGointToMenuInput = driver.findElement(By.xpath(strGoingToMenuInput));
		elmGointToMenuInput.sendKeys(strGoingToDestination);

		// Step to select the suggestive option based on the search in Going to field
		WebElement elmGoingToCity = driver.findElement(By.xpath(strGoinToCity));
		elmGoingToCity.click();
		System.out.println("Going to Selected as: " + strGoingToDestination);

		// Step to click on the Dates field
		WebElement elmChooseDates = driver.findElement(By.xpath(strDates));
		elmChooseDates.click();
		// Thread.sleep(10);

		// Step to get Start Date and End Date from the test data
		String strStartDate = strDatesTravelling.split("-")[0].trim();
		String strEndDate = strDatesTravelling.split("-")[1].trim();

		// Step to choose Start Date
		if (chooseDate(driver, strStartDate)) {
			System.out.println("Start Date : " + strStartDate + " selected");
		} else {
			System.out.println("Failed to select From Date : " + strStartDate);
		}

		// Step to choose End Date
		if (chooseDate(driver, strEndDate)) {
			System.out.println("End Date : " + strEndDate + " selected");
		} else {
			System.out.println("Failed to select To Date : " + strEndDate);
		}

		// Step to click on Done button after date selection
		WebElement elmDoneBtn = driver.findElement(By.xpath(strDateSelectDoneBtn));
		elmDoneBtn.click();

		// Step to click on the Travelers field
		WebElement elmTravelersAndRoom = driver.findElement(By.xpath(strTravelersAndRoom));
		elmTravelersAndRoom.click();
		// Thread.sleep(10);

		// Step to choose rooms based on the given test data
		if (chooseRoomMembers(driver, intAdultsToChoose, intChilderensToChoose)) {
			System.out.println(
					"Adults selected : " + intAdultsToChoose + " and Childerens selected : " + intChilderensToChoose);
		} else {
			System.out.println("Failed to select Adults and Childerens");
		}

		// Step to select children age option from drop down
		WebElement elmSlectChildAge = driver.findElement(By.xpath(strChildrenAgeSelection));
		Select childAgeDropDown = new Select(elmSlectChildAge);
		childAgeDropDown.selectByValue(strChildAge);
		System.out.println("Childeren Age Selected as : " + strChildAge);

		// Step to click on the Done button after Travelers selection
		WebElement elmTravelersDoneBtn = driver.findElement(By.xpath(strTravelersDoneBtn));
		elmTravelersDoneBtn.click();
		System.out.println("Clicked on Done button");

		// Step to click on Search button after required search fields are chosen
		WebElement elmSearchBtn = driver.findElement(By.xpath(strSearchBtn));
		elmSearchBtn.click();
		System.out.println("Clicked on Search button");
		// Thread.sleep(30);

		// Step to select Sort By option from drop down
		WebElement elmSlectSortBy = driver.findElement(By.xpath(strSortBySelection));
		waitForElemetToBeVisible(driver, elmSlectSortBy, 60);
		Select sortByDropDown = new Select(elmSlectSortBy);
		sortByDropDown.selectByVisibleText(strSortByOption);
		System.out.println("Sort By dropdown selected with option as : " + strSortByOption);
		// Thread.sleep(10);

		// Step to enter minimum amount in the filter range
		WebElement elmPriceMinimum = driver.findElement(By.xpath(strPriceMinimumDollorInput));
		waitForElemetToBeVisible(driver, elmPriceMinimum, 60);
		// Using actions to perform TAB after entering the value to minimum field
		Actions act = new Actions(driver);
		act.moveToElement(elmPriceMinimum);
		act.click();
		elmPriceMinimum.sendKeys(strPriceGreterThanDollorAmount);
		act.sendKeys(Keys.TAB);
		act.sendKeys(Keys.TAB);
		System.out.println("Minium amount entered as: " + strPriceGreterThanDollorAmount);

		// Step to capture the results count from the results header message
		WebElement elmResultsHeaderMessage = driver.findElement(By.xpath(strResultsHeaderMessage));
		String strResultsCount = elmResultsHeaderMessage.getText().trim().split(" ")[0];
		System.out.println("Total hotels count : " + strResultsCount);

		// Step to close the browser once done
		driver.quit();
	}

	/*
	 * Method to choose date based on the passed date. This This works only for
	 * April month. We need to add more logic to select based on Specific month
	 */
	public static boolean chooseDate(WebDriver driver, String strDateToChoose) throws InterruptedException {
		boolean flag = false;
		String xpathDateToChooseLabels = strChooseDates.replaceAll("date-to-select", strDateToChoose);
		String xpathDateToChooseButtons = strChooseDateButtons.replaceAll("date-to-select", strDateToChoose);
		// System.out.println("Dynamic Locator for Date Labels: " +
		// xpathDateToChooseLabels);
		List<WebElement> elmDateLabels = driver.findElements(By.xpath(xpathDateToChooseLabels));
		List<WebElement> elmDateButtons = driver.findElements(By.xpath(xpathDateToChooseButtons));
		// System.out.println("Dynamic Locator for Date Buttons: " +
		// xpathDateToChooseButtons);
		int indexOfDate = 0;
		for (WebElement elmDateLabel : elmDateLabels) {
			String strDateValue = elmDateLabel.getAttribute("aria-label").split(",")[1].trim();
			if (strDateToChoose.contentEquals(strDateValue)) {
				// Thread.sleep(10);
				waitForElemetToBeVisible(driver, elmDateButtons.get(indexOfDate), 60);
				elmDateButtons.get(indexOfDate).click();
				flag = true;
				break;
			}
			indexOfDate = indexOfDate + 1;
		}
		return flag;
	}

	/*
	 * Method to choose room members. I.e No of adults and children's based on what
	 * was passed as and argument to this method
	 */
	public static boolean chooseRoomMembers(WebDriver driver, int intAdultsToChoose, int intChildernsToChoose)
			throws InterruptedException {
		boolean flag = false;
		WebElement elmDecreseAdultRoom = driver.findElement(By.xpath(strDecreaseAdults));
		WebElement elmIncreaseAdultRoom = driver.findElement(By.xpath(strIncreaseAdults));
		WebElement elmDecreseChildredRoom = driver.findElement(By.xpath(strDecreaseChildren));
		WebElement elmIncreaseChildredRoom = driver.findElement(By.xpath(strIncreaseChildren));
		waitForElemetToBeVisible(driver, elmDecreseAdultRoom, 60);
		waitForElemetToBeVisible(driver, elmDecreseChildredRoom, 60);
		elmDecreseAdultRoom.click();
		elmDecreseChildredRoom.click();
		for (int i = 1; i < intAdultsToChoose; i++) {
			waitForElemetToBeVisible(driver, elmIncreaseAdultRoom, 60);
			elmIncreaseAdultRoom.click();
			// Thread.sleep(2);
			flag = true;
		}
		for (int i = 0; i < intChildernsToChoose; i++) {
			waitForElemetToBeVisible(driver, elmIncreaseChildredRoom, 60);
			elmIncreaseChildredRoom.click();
			// Thread.sleep(2);
			flag = true;
		}

		return flag;
	}

	// Method to wait explicitly based on the element clickable condition
	public static void waitForElemetToBeClickable(WebDriver driver, WebElement elmToWaitFor, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(elmToWaitFor));
	}

	// Method to wait explicitly based on the element visible condition
	public static void waitForElemetToBeVisible(WebDriver driver, WebElement elmToWaitFor, int timeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
		wait.until(ExpectedConditions.visibilityOf(elmToWaitFor));
	}
}
