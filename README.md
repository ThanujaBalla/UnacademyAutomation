
# 🎓 Unacademy Automation Testing Framework

![Java](https://img.shields.io/badge/Java-17-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.28.0-green)
![Playwright](https://img.shields.io/badge/Playwright-1.41.0-blue)
![Cucumber](https://img.shields.io/badge/Cucumber-7.34.6-brightgreen)
![TestNG](https://img.shields.io/badge/TestNG-7.12.0-red)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36)
![ExtentReports](https://img.shields.io/badge/ExtentReports-5.1.2-purple)

## 📌 Project Overview

**Unacademy Automation** is a team-based web automation testing project developed as part of our Testing Training.

The project automates functional and end-to-end scenarios for the Unacademy website using a combination of:

- Selenium WebDriver
- Playwright
- Cucumber BDD
- TestNG
- Page Object Model (POM)
- Extent Reports
- Maven
- WebDriverManager
- Custom test-result and screenshot utilities

The framework was developed collaboratively, with different team members contributing test scenarios and framework components.

---

## 🎯 Project Objectives

The main objectives of this project are:

- Automate important Unacademy user workflows.
- Practice Selenium WebDriver automation.
- Implement BDD testing using Cucumber.
- Implement TestNG-based automation.
- Introduce Playwright automation into the framework.
- Apply Page Object Model design.
- Create reusable page and utility components.
- Capture screenshots for test execution results.
- Generate HTML execution reports using ExtentReports.
- Maintain test execution results for cumulative reporting.
- Practice Git and GitHub collaboration in a team environment.
- Integrate multiple automation approaches into a single framework.

---

# 🛠️ Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming language |
| Selenium WebDriver | Web UI automation |
| Playwright | Modern browser automation |
| Cucumber | BDD / feature-based testing |
| TestNG | Test execution and test management |
| Maven | Dependency and build management |
| Page Object Model | Framework design pattern |
| ExtentReports | HTML execution reporting |
| WebDriverManager | Browser driver management |
| Jackson | JSON result handling |
| Commons IO | Screenshot/file utilities |
| Eclipse | Development IDE |
| Git & GitHub | Version control and team collaboration |

---

# 🏗️ Framework Architecture

```text
UnacademyAutomation
│
├── Reports/
│   └── ExtentTestReports.html
│
├── Screenshots/
│   └── Test execution screenshots
│
├── src/
│   └── test/
│       ├── java/
│       │
│       ├── Base/
│       │   └── BaseTest
│       │
│       ├── Features/
│       │   ├── login.feature
│       │   └── signup.feature
│       │
│       ├── Listeners/
│       │   └── ExtentTestNGListener
│       │
│       ├── Pages/
│       │   └── Page Object classes
│       │
│       ├── Reports/
│       │   └── Reporting utilities
│       │
│       ├── Runners/
│       │   └── Cucumber Test Runner
│       │
│       ├── StepDefinitions/
│       │   └── Cucumber step implementations
│       │
│       ├── Tests/
│       │   └── TestNG automation classes
│       │
│       └── Utilities/
│           ├── ConfigReader
│           ├── DriverManager
│           ├── ScreenShotUtility
│           └── TestResultManager
│
├── test-results/
│   └── test-results.json
│
├── test-output/
│   └── TestNG execution output
│
├── pom.xml
├── testng.xml
└── .gitignore
````


# 🧪 Automation Coverage

The framework contains automation for multiple areas of the Unacademy website.

## 🔐 Login & Signup

Cucumber scenarios include:

* Login with valid mobile number and OTP
* Login with valid email and OTP
* Login with invalid mobile number
* Login with invalid email
* Login with invalid OTP
* Login with expired OTP
* Login from laptop
* Login from geographical location
* Successful logout
* Signup using valid mobile number
* Signup using invalid mobile numbers
* Signup using invalid OTP
* Signup using expired OTP
* Signup using different browsers
* First-time signup flow

Feature files are maintained under:

```text
src/test/java/Features/
```

Current feature files include:

```text
login.feature
signup.feature
```


# 🎯 Goal & Home Page Testing

The project includes TestNG automation for important home-page and goal-related functionality.

Examples include:

* Add and remove goals
* Goal persistence
* Logged-in goal changes
* Goal smoke testing
* Batches card
* Courses card
* Course navigation
* Direct deep links
* Doubts and Solutions
* Playlist card
* Practice card
* Test Series card
* Top Educators

# 🔎 Search Automation

The framework also contains TestNG-based search automation.

Covered scenarios include:

* Valid course search
* Valid instructor search
* Invalid keyword search
* Empty search
* Partial keyword search
* Trending educators
* Redirect to educator profile
* Special-character search
* Search result load time
* Clear search and perform a new search

Search-related test classes include:

```text
TC020_SearchValidCourse
TC021_SearchValidInstructor
TC023_SearchInvalidKeyword
TC024_EmptySearch
TC025_SearchPartialKeyword
TC026_TrendingEducators
TC027_RedirectToEducatorProfile
TC028_SearchSpecialCharacters
TC030_SearchResultLoadTime
TC031_ClearSearchNewSearch
```



# 📄 About Us Testing

The framework contains tests for the Unacademy About Us section:

```text
TS033_AboutUsPageTest
TS036_MissionImpactTest
TS037_AchievementsTest
TS038_JoinUsTest
TS039_JobOpeningsTest
TS041_ContactInformationTest
```

These tests validate different informational sections and navigation within the About Us area.

---

# 💳 Subscription & Payment Testing

The project includes subscription and payment-related automation.

Examples include:

* Subscription plan validation
* Authenticated subscription flow
* Subscription lifecycle testing
* Payment matrix testing
* Dummy/stub payment validation

Payment testing is designed so that local test execution can use a configurable payment mode rather than relying on real payment transactions.

---

# 🥒 Cucumber BDD Framework

The login and signup automation uses Cucumber BDD.

Example feature structure:

```gherkin
Feature: Unacademy Login

  Scenario: Login with valid mobile number and valid OTP

    Given I am on the Unacademy home page
    When I click on Login
    And I enter a valid mobile number
    And I click on Login Continue
    And I enter the OTP manually
    Then I should be logged in successfully
```

The Cucumber framework consists of:

```text
Features
    ↓
StepDefinitions
    ↓
Pages
    ↓
WebDriver
```

The Cucumber tests are executed through the TestNG Cucumber runner.

---

# 🧪 TestNG Framework

TestNG is used for the majority of the Java-based automation tests.

The framework uses:

```java
@BeforeMethod
@Test
@AfterMethod
```

for test setup, execution and cleanup.

The central TestNG suite is:

```text
testng.xml
```

The suite groups tests into logical areas such as:

* Login and Signup
* About Us
* Goal Tests
* Public Smoke Tests
* Subscription & Payment Tests

---

# 🎭 Playwright Support

Playwright has also been integrated into the framework.

The framework can initialize:

```text
Playwright
    ↓
Browser
    ↓
BrowserContext
    ↓
Page
```

Browser configuration supports:

* Chrome
* Microsoft Edge

Playwright execution can also be configured for:

* Headless execution
* Slow motion
* Browser timeout
* Authentication state
* Screenshots
* Tracing

Playwright artifacts are stored under:

```text
target/artifacts/
```

---

# 🧱 Page Object Model

The project follows the **Page Object Model (POM)** approach.

Instead of placing locators and browser interactions directly inside test classes, page-specific operations are maintained in page classes.

Example:

```text
Pages/
│
├── LoginPage
├── SignupPage
├── SearchPage
├── GoalSelectionPage
└── ...
```

This provides:

* Better code reusability
* Easier maintenance
* Separation of test logic and UI interaction
* Centralized locators
* Cleaner test classes

---

# ⚙️ Configuration

The project uses a configuration file for environment and execution settings.

Example configuration:

```properties
engine=playwright
baseUrl=https://unacademy.com
browser=chrome
headless=false
slowMo=350
timeoutMs=20000
paymentMode=stub
```

### 🔐 Important

Personal/local configuration such as:

```text
config.properties
```

should not be committed to GitHub.

The project uses `.gitignore` to keep local configuration and personal test data out of version control.

A team member should create their own local configuration file before executing tests.

---

# 🚀 Getting Started

## 1. Clone the Repository

```bash
git clone https://github.com/ThanujaBalla/UnacademyAutomation.git
```

Navigate into the project:

```bash
cd UnacademyAutomation
```

---

## 2. Import into Eclipse

1. Open Eclipse.
2. Select:

```text
File → Import
```

3. Choose:

```text
Maven → Existing Maven Projects
```

4. Select the cloned project.
5. Click **Finish**.
6. Allow Maven dependencies to download.

---

## 3. Verify Java

The project is configured for Java 17.

Check:

```bash
java -version
```

Recommended:

```text
Java 17+
```

---

## 4. Verify Maven

Check:

```bash
mvn -version
```

---

# ▶️ Running the Tests

## Run the complete TestNG suite

From Eclipse:

```text
Right Click testng.xml
        ↓
Run As
        ↓
TestNG Suite
```

Or from Maven:

```bash
mvn test
```

The Maven Surefire configuration uses:

```text
testng.xml
```

as the TestNG suite.

---

# 🥒 Running Cucumber Tests

The Cucumber tests can be executed through:

```text
src/test/java/Runners/TestRunner.java
```

Run:

```text
Right Click TestRunner.java
        ↓
Run As
        ↓
TestNG Test
```

Cucumber HTML output is generated under:

```text
target/cucumber-report.html
```

---

# 📊 Test Reports

The project uses **ExtentReports** for HTML reporting.

The generated report is stored under:

```text
Reports/
└── ExtentTestReports.html
```

The report provides:

* Test names
* PASS / FAIL status
* Execution details
* Execution timestamps
* Screenshots
* Failure information

The repository also maintains:

```text
test-results/
└── test-results.json
```

for storing test execution information used by the reporting utilities.

---

# 📸 Screenshots

Screenshots are captured for test execution and stored under:

```text
Screenshots/
```

Examples of information represented by screenshots:

* Successful test execution
* Failed test execution
* Browser state during failure
* Validation results
* UI state after test execution

Screenshots are included in the repository so that the test execution evidence can be reviewed directly from GitHub.

---

# 🔍 Test Execution Flow

The overall automation flow is:

```text
                 ┌─────────────────────┐
                 │     testng.xml      │
                 └──────────┬──────────┘
                            │
             ┌──────────────┴──────────────┐
             │                             │
             ▼                             ▼
       Cucumber Tests                TestNG Tests
             │                             │
             ▼                             ▼
       Feature Files                  Test Classes
             │                             │
             ▼                             ▼
      Step Definitions                Page Objects
             │                             │
             └──────────────┬──────────────┘
                            ▼
                    Selenium / Playwright
                            │
                            ▼
                    Unacademy Website
                            │
                            ▼
                     Test Execution
                            │
             ┌──────────────┼──────────────┐
             ▼              ▼              ▼
        Screenshots     JSON Results    Extent Report
```

---

# 🧹 Test Cleanup

After every test execution, the framework performs cleanup activities such as:

* Browser cleanup
* Driver cleanup
* Screenshot generation
* Test-result recording
* Playwright tracing/artifact handling
* Extent report updates

This helps keep test execution independent and makes failures easier to investigate.

---

# 🤝 Team Collaboration

This is a collaborative team automation project.

The project was developed using Git and GitHub with multiple contributors working on different test areas and framework components.

The team workflow involved:

```text
Create Feature/Test
        ↓
Develop Locally
        ↓
Commit Changes
        ↓
Push to Branch
        ↓
Fetch / Merge Team Changes
        ↓
Resolve Conflicts
        ↓
Run Regression Tests
        ↓
Push Final Changes
```

This project also provided practical experience with:

* Git branches
* Remote branches
* Fetching changes
* Pulling teammate changes
* Merge conflicts
* Conflict resolution
* Commit management
* Shared test framework development

---

# 📁 Important Project Directories

| Directory                       | Purpose                               |
| ------------------------------- | ------------------------------------- |
| `src/test/java/Base`            | Test setup and browser initialization |
| `src/test/java/Features`        | Cucumber feature files                |
| `src/test/java/Listeners`       | TestNG listeners                      |
| `src/test/java/Pages`           | Page Object classes                   |
| `src/test/java/Reports`         | Reporting components                  |
| `src/test/java/Runners`         | Cucumber runners                      |
| `src/test/java/StepDefinitions` | Cucumber step definitions             |
| `src/test/java/Tests`           | TestNG test classes                   |
| `src/test/java/Utilities`       | Reusable framework utilities          |
| `Reports`                       | Generated Extent report               |
| `Screenshots`                   | Test execution screenshots            |
| `test-results`                  | JSON test execution results           |
| `test-output`                   | TestNG generated output               |

---

# 🔧 Framework Highlights

### ✅ Multi-Framework Automation

Supports:

```text
Selenium + TestNG
Selenium + Cucumber
Playwright + TestNG
```

### ✅ Page Object Model

UI interactions are separated from test cases.

### ✅ Reusable Utilities

Common functionality is centralized into utility classes.

### ✅ Automatic Browser Driver Management

WebDriverManager is used for browser driver setup.

### ✅ Screenshot Evidence

Screenshots are captured and stored with the project.

### ✅ HTML Reporting

ExtentReports provides a visual test execution report.

### ✅ JSON Test Results

Execution status and screenshot information are maintained in JSON.

### ✅ Configurable Execution

Browser, engine and execution-related settings can be configured without modifying test classes.

---

# 📚 Learning Outcomes

Through this project, the team gained practical experience in:

* Selenium WebDriver
* Playwright automation
* TestNG
* Cucumber BDD
* Page Object Model
* Explicit waits
* Browser automation
* Cross-browser testing
* Test data/configuration management
* Screenshot handling
* HTML reporting
* Test result management
* Maven project management
* Git and GitHub collaboration
* Merge conflict resolution
* Team-based automation framework development

---

# ⚠️ Important Notes

This project is intended for **testing and training purposes**.

The automation interacts with a live web application, so:

* UI locators may change when the application changes.
* OTP-based scenarios may require manual OTP entry.
* Some scenarios may depend on account state.
* Some tests may require valid test data.
* Payment scenarios use the configured test/stub approach where applicable.
* Local configuration files containing personal information should remain outside Git.

---

## 👥 Contributors & Module Ownership

This project was developed collaboratively as part of our Testing Training.  
Each team member was responsible for automating and validating a specific functional area of the Unacademy platform.

| # | Contributor | GitHub Profile | Module / Area | Key Responsibilities |
|---|---|---|---|---|
| **1** | **Thanuja Balla** | [@ThanujaBalla](https://github.com/ThanujaBalla) | 🔐 **Sign Up & Login** | Automated the complete authentication journey including mobile-number signup, OTP-based login, signup across compatible devices, login using mobile number or email, login from different devices/locations, logout, and related positive and negative authentication scenarios. Integrated the authentication flows with the Cucumber, Selenium, TestNG and Page Object framework. |
| **2** | **Dinesh Chandra** | [@DineshChandra0403](https://github.com/DineshChandra0403) | 🔎 **Search Module** | Automated course, instructor and learning-content search scenarios. Covered search suggestions, valid/invalid and partial searches, educator profile navigation, trending searches, special-character searches, clearing and reusing search, and search-result validation/performance scenarios. |
| **3** | **Ilampooranan** | [@cyberpunk1105](https://github.com/cyberpunk1105) | 🎯 **Start Learning & Goal Management** | Automated competitive-exam course discovery and Goal management workflows, including browsing courses by exam category, adding and removing Goals, Goal persistence across refreshes/sessions, switching saved Goals, and navigation toward subscription from the saved Goal section. |
| **4** | **Praveen** | [@praveen-dotnet-hyd](https://github.com/praveen-dotnet-hyd) | 🏢 **About Us** | Automated informational and navigation scenarios for the About Us section, covering the About Us page, mission and vision, company achievements, career/job openings, and contact information to validate the accessibility and completeness of key company information. |
| **5** | **Amrutha** | [@amrutha-005](https://github.com/amrutha-005) | 💳 **Subscription & Payment** | Automated subscription and payment workflows including subscription-plan validation, preferred payment methods, secure payment-flow scenarios, active subscription details, renewal/upgrade workflows, and subscription cancellation/auto-renewal scenarios. Also contributed to the Playwright-based framework and related automation components. |
---
# 🔗 Repository

**GitHub Repository:**

[https://github.com/ThanujaBalla/UnacademyAutomation](https://github.com/ThanujaBalla/UnacademyAutomation)

---

# ⭐ Project Summary

**Unacademy Automation** demonstrates the development of a collaborative, maintainable web automation framework using modern Java testing technologies.

The project combines:

```text
Java
  +
Selenium
  +
Playwright
  +
Cucumber
  +
TestNG
  +
Page Object Model
  +
ExtentReports
  +
Maven
  +
Git/GitHub
```

The goal was not only to automate test cases, but also to gain practical experience in designing, maintaining and collaborating on a real-world-style automation framework.


