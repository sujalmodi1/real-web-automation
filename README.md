# Real Web Automation

A Selenium-based web automation testing framework built with Java, TestNG, Maven, and the Page Object Model (POM).

The framework automates the SauceDemo e-commerce application and demonstrates a complete end-to-end testing setup with reusable page classes, utilities, screenshots on failure, PDF validation, and Extent Reports.

## Tech Stack

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)
- Extent Reports
- Apache Commons IO
- Chrome WebDriver

## Framework Features

- Page Object Model architecture
- Reusable page classes
- TestNG test execution
- Maven-based test execution
- TestNG XML suite configuration
- Explicit browser configuration using ChromeOptions
- Screenshot capture on test failure
- PDF download validation
- Extent HTML reports
- Timestamped test reports
- Automatic report generation
- Centralized browser setup and teardown
- Assertion messages for easier failure analysis

## Project Structure

```text
real-web-automation/
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutCompletePage.java
│   │       │   ├── CheckoutInformationPage.java
│   │       │   ├── CheckoutOverviewPage.java
│   │       │   ├── LoginPage.java
│   │       │   └── ProductsPage.java
│   │       │
│   │       └── utilities/
│   │           ├── ExtentListener.java
│   │           ├── ExtentManager.java
│   │           ├── PdfUtility.java
│   │           └── ScreenshotUtility.java
│   │
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java
│           │
│           └── tests/
│               ├── LoginTest.java
│               ├── ProductsTest.java
│               ├── CartTest.java
│               ├── CheckoutTest.java
│               └── EndToEndTest.java
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Test Coverage

The framework currently contains **27 automated tests** covering:

- Login functionality
- Product sorting
- Product selection
- Cart functionality
- Checkout information
- Checkout overview
- Purchase completion
- Success message validation
- PDF generation and download validation
- Navigation back to the products page
- End-to-end purchase flow

## How to Run

### Prerequisites

Make sure the following are installed:

- Java
- Maven
- Google Chrome

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

### Run the Test Suite

From the project root, run:

```bash
mvn test
```

Maven Surefire is configured to execute the TestNG suite defined in `testng.xml`.

## Test Execution Flow

```text
mvn test
    ↓
Maven Surefire
    ↓
testng.xml
    ↓
TestNG Suite
    ↓
Login Tests
Products Tests
Cart Tests
Checkout Tests
End-to-End Tests
    ↓
Extent Reports
```

## End-to-End Test

The `EndToEndTest` validates the complete purchase workflow:

```text
Login
  ↓
Sort Products
  ↓
Add Products to Cart
  ↓
Verify Cart
  ↓
Checkout
  ↓
Enter Customer Information
  ↓
Verify Checkout Overview
  ↓
Complete Purchase
  ↓
Verify Success Message
  ↓
Generate PDF
  ↓
Verify PDF Download
  ↓
Return Home
  ↓
Verify Products Page
```

## Reporting

The framework uses **Extent Reports** to generate HTML test execution reports.

Reports are timestamped so previous reports are not overwritten.

Example:

```text
reports/
└── ExtentReport_2026-08-08_14-21-33.html
```

Generated reports are excluded from Git using `.gitignore`.

## Screenshots on Failure

When a test fails, `BaseTest` automatically invokes `ScreenshotUtility`.

Screenshots are saved using the test name and timestamp:

```text
screenshots/
└── verifyCompletePurchaseFLow-2026-08-08_14-21-33.png
```

Generated screenshots are excluded from Git.

## Test Results

Latest full suite execution:

```text
Tests run: 27
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

## Author

**Sujal**