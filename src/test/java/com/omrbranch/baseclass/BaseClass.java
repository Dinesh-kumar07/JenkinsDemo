package com.omrbranch.baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	public static WebDriver driver;
	static Select select;

	// project_path
	public static String getProjectPath() {
		String property = System.getProperty("user.dir");
		return property;
	}

	// property file
	public String getPropertyFileValue(String key) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(getProjectPath() + "\\config\\config.properties"));
		Object object = properties.get(key);

		String valueOf = String.valueOf(object);
		return valueOf;
	}

	// browser launch
	public static void browserLaunch1(String browserType) {
		switch (browserType) {
		case "CHROME":
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			driver = new FirefoxDriver();
			break;
		case "EDGE":
			driver = new EdgeDriver();
			break;
		case "IE":
			driver = new InternetExplorerDriver();
			break;
		case "SAFARI":
			driver = new SafariDriver();
			break;

		default:
			break;
		}
	}

//  To launch the browser
	public static void browserLaunch() {
		driver = new ChromeDriver();
	}

	// 1 To enter the URL
	public static void enterApplnUrl(String url) {
		driver.get(url);
		driver.navigate().to(url);

	}

	// 2 To maximize the window
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	// refresh page
	public static void pageRefresh() {
		driver.navigate().refresh();
	}

	// window size change
	public static void windowSizeChange() {
		driver.manage().window().setSize(new Dimension(0, 0));
	}

	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}

	public void disableEdgeNotification() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--disable-notifications");
	}

	public void disableChromeNotification() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
	}

	public void disableFirefoxNotification() {

		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotifications.enabled", false);
		profile.setPreference("dom.push.enabled", false);

		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
	}

//  To get title
	public String getApplnTitle() {
		String title = driver.getTitle();
		return title;
	}

	// To get current URL
	public String getAppnUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

//  To get parent Window
	public String parentWindowId() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}

	// to get all windows
	public Set<String> getAllWindowsId() {
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles;
	}

	// Switch to child Window
	public void childwindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String childWindow : windowHandles) {
			
			if (!parentWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}

	// Close current window
	public static void closeWindow() {
		driver.close();

	}

	// Close all windows
	public void quitWindow() {
		driver.quit();
	}

	// To find locator by ID
	public static WebElement findLocatorById(String attributeValue) {
		WebElement findElement = driver.findElement(By.id(attributeValue));
		return findElement;
	}

	// To find locator by name
	public static WebElement findLocatorByName(String attributeValue) {
		WebElement findElement = driver.findElement(By.name(attributeValue));
		return findElement;
	}

	// To find locator by className
	public static WebElement findLocatorByClassName(String attributeValue) {
		WebElement findElement = driver.findElement(By.className(attributeValue));
		return findElement;
	}

	// To find locator by Attribute value
	public static WebElement findLocatorByXpath(String attributeValue) {
		WebElement findElement = driver.findElement(By.xpath(attributeValue));
		return findElement;
	}

	// To enter the text using sendkeys
	public static void elementSendKeys(WebElement element, String emailid) {
		elementVisibility(element);

		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(emailid);
		}

	}

	public static void elementSendKeys(WebElement element, String data, Keys enter) {
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys("data", data, Keys.ENTER);
		}

	}

	public static void elementSendKeysEnter(WebElement element, String data) {
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data, Keys.ENTER);
		}
	}

	public static void elementSendKeys1(WebElement element, String data) {
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.sendKeys(data);
		}
	}

	// to perform click
	public static void elementClick(WebElement element) {
		elementVisibility(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.click();
		}
	}

	// Insert value in textbox using JS
	public void elementSendKeysJs(WebElement element, String data) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value','" + data + "')", element);
	}

	// Click button using JS
	public static void elementClickJs(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}

	// scroll down
	public void scrollDown(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	// scroll up
	public void scrollUp(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	// Scroll into view
	public void scrollToElement(WebElement element) {
		WebElement scrollToElement = driver.findElement(By.id("elementID"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", scrollToElement);
	}

	// Scroll by pixel
	public void scrollByPixel(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")");
	}

	// to accept alert
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	// to dismiss alert
	public void cancelAlert() {
		driver.switchTo().alert().dismiss();
	}

	// prompt alret
	public void promptAlert(String data) {
		driver.switchTo().alert().sendKeys(data);
	}

	// To select dropdown by text
	public static void selectOptionByText(WebElement element, String text) {
		elementVisibility(element);
		select = new Select(element);
		select.selectByVisibleText(text);
	}

	// To select dropdown by value
	public static void selectOptionByValue(WebElement element, String text) {
		elementVisibility(element);
		select = new Select(element);
		select.selectByValue(text);
	}

	// To select dropdown by index
	public void selectOptionByIndex(WebElement element, int index) {
		elementVisibility(element);
		select = new Select(element);
		select.selectByIndex(index);
	}

	// Get all options from dropdown as text
	public List<String> getAllOptionsText(WebElement element) {
		List<String> lstText = new ArrayList<>();
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String text = webElement.getText();
			lstText.add(text);
		}
		return lstText;
	}

	// get all options from dropdown select
	public List<String> getAllOptionsclick(WebElement element) {

		select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			webElement.click();
		}
		return null;
	}

	// get the first selected option in dropdown
	public String firstSelectedOption(WebElement element) {
		select = new Select(element);
		WebElement firstSelectedOption = select.getFirstSelectedOption();
//		String selectedOption = select.getFirstSelectedOption().getText();
//		System.out.println(selectedOption);
		String text = firstSelectedOption.getText();
		return text;
	}

	// Verify if dropdown in multi selected
	public boolean isMultiSelected(WebElement element) {
		Select s = new Select(element);
		boolean multiple = s.isMultiple();
		return multiple;
	}

	// movetoElement
	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	// Drag & drop
	public void dragAndDrop(WebElement source, WebElement destination) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, destination).perform();
	}

	// Drag and drop by offset
	public void dragAndDrop1(WebElement sourceElement, WebElement targetElement) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(sourceElement).moveToElement(targetElement).release().build().perform();

	}

	// Right Click
	public void rightClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}

	// Double Click
	public void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	// To switch to frame by index
	public void switchFrameByIndex(int index) {
		driver.switchTo().frame(index);
	}

	// To switch to frame using id
	public void switchFrameById(WebElement element) {
		driver.switchTo().frame(element);
	}

	// switch to parent frame
	public void switchToParentFrame() {
		driver.switchTo().defaultContent();
	}

	// To get text
	public static String elementGetText(WebElement element) {
		elementVisibility(element);
		String text = element.getText();
		return text;
	}

	// To get the inserted value from textbox

	// 99%--->value fixed
	public String elementGetAttributeValue(WebElement element) {
		elementVisibility(element);
		String attribute = null;
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			attribute = element.getAttribute("value");
		}
		return attribute;

	}

	// 8_1: 1%--->value is NOT fixed
	public String elementGetAttributeValue(WebElement element, String attributeName) {
		elementVisibility(element);
		String attribute = null;
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			attribute = element.getAttribute(attributeName);
		}
		return attribute;
	}

	// implicit wait
	public void implicitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secs));
	}

	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	// explicit wait
	public static void elementVisibility(WebElement element) {
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driverWait.until(ExpectedConditions.visibilityOf(element));

	}

	// Fluent wait
	public static void fluentWait() {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(60));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(Exception.class);
	}

	public void webDriverWait() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement element = wait
				.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(By.id("elementID"))));
		element.click();
	}

	//
	public static boolean elementIsDisplayed(WebElement element) {
		elementVisibility(element);
		boolean displayed = element.isDisplayed();
		return displayed;
	}

	//
	public static boolean elementIsEnabled(WebElement element) {
		elementVisibility(element);
		boolean enabled = element.isEnabled();
		return enabled;
	}

	//
	public boolean elementIsSelected(WebElement element) {
		elementVisibility(element);
		boolean selected = element.isSelected();
		return selected;
	}

	// De_select by Index
	public void deselectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.deselectByIndex(index);
	}

	// De_select by Attribute
	public void deselectByAttribute(WebElement element, String attribute) {
		Select s = new Select(element);
		s.deselectByValue(attribute);
	}

	// De_select by Text
	public void deselectByVisibleText(WebElement element, String attribute) {
		Select s = new Select(element);
		s.deselectByVisibleText(attribute);
	}

	// De_select All
	public void deselectAll(WebElement element) {
		Select s = new Select(element);
		s.deselectAll();
	}

	// 42 Clear Textbox
	public void elementClear(WebElement element) {
		elementVisibility(element);
		if (elementIsDisplayed(element) && elementIsEnabled(element)) {
			element.clear();
		}

	}

	// Take Screenshot
	public void screenshot(String sName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File s = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s, new File(getProjectPath() + "\\images\\" + sName + ".png"));
	}

	// Take screenshot of an element
	public void screenshot(WebElement element, String sName) throws IOException {
		File s = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s, new File(getProjectPath() + "\\images\\" + sName + ".png"));
	}

	// Insert value and press enter
	public void pressEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// excel read getting output

	public static String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
		String res = null;
		File file = new File(getProjectPath() + "\\src\\test\\resources\\signupdata.xlsx");
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);

		CellType type = cell.getCellType();

		switch (type) {
		case STRING:
			res = cell.getStringCellValue();
			break;
		case NUMERIC:

			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
				res = dateformat.format(dateCellValue);
			} else {
				double numericCellValue = cell.getNumericCellValue();
				long round = Math.round(numericCellValue);
				if (round == numericCellValue) {
					res = String.valueOf(round);

				} else {
					res = String.valueOf(numericCellValue);
				}
			}
			break;
		default:
			break;
		}
		return res;
	}

	// update
	public void updateCellData(String sheetName, int rownum, int cellnum, String oldData, String newData)
			throws IOException {
		File file = new File(getProjectPath() + "\\src\\test\\resources\\signupdata.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		String value = cell.getStringCellValue();
		if (value.equals(oldData)) {
			cell.setCellValue(newData);
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
	}

	// new value insert write

	public static void setCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		File file = new File(getProjectPath() + "\\src\\test\\resources\\signupdata.xlsx");
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);

		cell.setCellValue(data);

		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
	}

	// 54 take screenshot
	public void screenshot1(String sName) throws IOException {
		File s = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(s, new File(getProjectPath() + "\\images\\" + sName + ".png"));
	}

	public byte[] screenshot2() {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		byte[] screenshotAs = screenshot.getScreenshotAs(OutputType.BYTES);
		return screenshotAs;
	}

	// 55 assertion
	public void verifyEquals(String expected, String actual) {
		Assert.assertEquals(expected, actual);
	}

	// 56 assertion
	public void verifyEquals(String expected, String actual, String message) {
		Assert.assertEquals(expected, actual, message);
	}

	// assertion
	public void verifyEquals(int expected, int actual) {
		Assert.assertEquals(expected, actual);
	}

	// iterate and click
	public List<String> iterateandclick(WebElement element, String attributeValue) {

		List<WebElement> findElement = driver.findElements(By.xpath(attributeValue));
		for (int i = 0; i < findElement.size(); i++) {
			findElement.get(1).click();
		}
		return null;
	}

	// iterate and click
	public void iterateAndClick(List<WebElement> btnContinue) {

		btnContinue.get(0).click();
	}

	public void verifyRoomType(List<String> devString) {
		List<Boolean> c = new ArrayList<Boolean>();
		System.out.println("----------------------------");
		for (String webElement : devString) {
			System.out.println(webElement);
			boolean endsWith = webElement.endsWith("Standard");
			c.add(endsWith);
		}
		boolean verify = c.contains(false);
		// boolean verify = c.stream().allMatch(Boolean::booleanValue);
		System.out.println(verify);

		if (verify == true) {
			System.out.println("The Selected room types are Standard");
		} else {
			System.out.println("No Standard rooms are present");
		}
	}

	// Generic method to verify sorting order (handles both integers and strings)
	public <S extends Comparable<S>> String verifyOrder(List<WebElement> elements, boolean isAscending,
			boolean isInteger) {
		List<S> originalList = new ArrayList<S>();
		List<S> sortedList = new ArrayList<S>();

		for (WebElement element : elements) {
			String text = element.getText();
			if (isInteger) {
				String cleanedText = text.replaceAll("[^0-9]", "");
				S value = (S) Integer.valueOf(cleanedText); // Cast to Integer
				originalList.add(value);
			} else {
				originalList.add((S) text); // Cast to String
			}
		}
		sortedList.addAll(originalList);

		if (isAscending) {
			Collections.sort(sortedList);
		} else {
			Collections.sort(sortedList, Collections.reverseOrder());
		}

		System.out.println("Dev List: " + originalList);
		System.out.println("Qa List : " + sortedList);

		// Compare the original list with the sorted list
		if (originalList.equals(sortedList)) {
			return isAscending ? "Low to High Sorted/Ascending Order" : "High to Low Sorted/Descending Order";

		} else {
			return "Not Sorted";
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Comparable<T>> String verifyOrder1(List<WebElement> elements, boolean isAscending,
			boolean isInteger) {
		List<T> originalList = new ArrayList<>();
		List<T> sortedList = new ArrayList<>();

		for (WebElement element : elements) {
			String text = element.getText();
			T value;

			if (isInteger) {
				String cleanedText = text.replaceAll("[^0-9]", "");
				value = (T) Integer.valueOf(cleanedText);
			} else {
				value = (T) text;
			}

			originalList.add(value);
		}

		sortedList.addAll(originalList);

		// Determine sorting order using switch case
		switch (isAscending ? "ASCENDING" : "DESCENDING") {
		case "ASCENDING":
			Collections.sort(sortedList);
			break;
		case "DESCENDING":
			Collections.sort(sortedList, Collections.reverseOrder());
			break;
		}

		System.out.println("Dev List: " + originalList);
		System.out.println("Qa List : " + sortedList);

		// Compare the original list with the sorted list
		if (originalList.equals(sortedList)) {
			return isAscending ? "Low to High Sorted/Ascending Order" : "High to Low Sorted/Descending Order";

		} else {
			return "Not Sorted";
		}
	}
}