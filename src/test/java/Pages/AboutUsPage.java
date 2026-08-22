package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutUsPage {

    WebDriver driver;
    WebDriverWait wait;

    // =========================
    // TS033 - About Us page
    // =========================

    // OUR MISSION & IMPACT heading
    By missionImpact =
            By.xpath("//p[normalize-space()='OUR MISSION & IMPACT']");


    // =========================
    // TS036 - Mission & Impact
    // =========================

    // Mission description
    By missionDescription =
            By.xpath("//h2[contains(normalize-space(), 'Democratise access to high')]");

    // Active Learners
    By activeLearners =
            By.xpath("//p[normalize-space()='Active Learners']");

    // Top Educators
    By topEducators =
            By.xpath("//p[normalize-space()='Top Educators']");
    
 // TS037 - Our Journey

    By journey2015 =
            By.xpath("//p[normalize-space()='2015']");

    By journey2015Description =
            By.xpath("//p[contains(normalize-space(), 'From a YouTube channel that started in 2010')]");

    By journey2017 =
            By.xpath("//p[normalize-space()='2017']");

    By journey2018 =
            By.xpath("//p[normalize-space()='2018']");

    By journey2020 =
            By.xpath("//p[normalize-space()='2020']");
    
 // =========================
 // TS038 - Come Join Us
 // =========================

 By joinUsHeading =
         By.xpath("//p[normalize-space()='COME JOIN US']");

 By joinUsDescription =
         By.xpath("//h2[contains(normalize-space(), 'Let’s build the future of education together')]");

 By openRoles =
         By.xpath("//p[contains(normalize-space(), 'Roles open in Design')]");

 By seeOpenPositions =
         By.xpath("//a[@href='/careers']//button[normalize-space()='See open positions']");
 
 By viewAllJobOpenings =
	        By.xpath("//button[@aria-label='View all job openings']");
 
 
//Public Relations section
By publicRelations =
      By.xpath("//h3[normalize-space()='Public relations']");

//Press email
By pressEmail =
      By.xpath("//a[@href='mailto:press@unacademy.com']");

//Contact Us section
By contactUs =
      By.xpath("//h3[normalize-space()='Contact us']");

//Help email
By helpEmail =
      By.xpath("//a[@href='mailto:help@unacademy.com']");


    // Constructor
    public AboutUsPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    // =========================
    // TS033 methods
    // =========================

    public void openAboutUsPage() {

        driver.get("https://unacademy.com/about");
    }

    public boolean isMissionImpactDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(missionImpact)
        ).isDisplayed();
    }


    // =========================
    // TS036 methods
    // =========================

    public boolean isMissionImpactHeadingDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(missionImpact)
        ).isDisplayed();
    }

    public boolean isMissionDescriptionDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(missionDescription)
        ).isDisplayed();
    }

    public boolean isActiveLearnersDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(activeLearners)
        ).isDisplayed();
    }

    public boolean isTopEducatorsDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(topEducators)
        ).isDisplayed();
    }
    
    
 // TS037 - Our Journey methods

    public boolean isJourney2015Displayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(journey2015)
        ).isDisplayed();
    }

    public boolean isJourney2015DescriptionDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(journey2015Description)
        ).isDisplayed();
    }

    public boolean isJourney2017Displayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(journey2017)
        ).isDisplayed();
    }

    public boolean isJourney2018Displayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(journey2018)
        ).isDisplayed();
    }

    public boolean isJourney2020Displayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(journey2020)
        ).isDisplayed();
    }
    
    
 // =========================
 // TS038 methods
 // =========================

 public boolean isJoinUsHeadingDisplayed() {

     return wait.until(
             ExpectedConditions.visibilityOfElementLocated(joinUsHeading)
     ).isDisplayed();
 }

 public boolean isJoinUsDescriptionDisplayed() {

     return wait.until(
             ExpectedConditions.visibilityOfElementLocated(joinUsDescription)
     ).isDisplayed();
 }

 public boolean isOpenRolesDisplayed() {

     return wait.until(
             ExpectedConditions.visibilityOfElementLocated(openRoles)
     ).isDisplayed();
 }

 public boolean isSeeOpenPositionsDisplayed() {

     return wait.until(
             ExpectedConditions.visibilityOfElementLocated(seeOpenPositions)
     ).isDisplayed();
 }

 public void clickSeeOpenPositions() {

     wait.until(
             ExpectedConditions.elementToBeClickable(seeOpenPositions)
     ).click();
 }

 public boolean isViewAllJobOpeningsDisplayed() {

     return wait.until(
             ExpectedConditions.visibilityOfElementLocated(viewAllJobOpenings)
     ).isDisplayed();
 }
 
 
//Verify Public Relations section
public boolean isPublicRelationsDisplayed() {

  return wait.until(
          ExpectedConditions.visibilityOfElementLocated(publicRelations)
  ).isDisplayed();
}

//Verify Press email
public boolean isPressEmailDisplayed() {

  return wait.until(
          ExpectedConditions.visibilityOfElementLocated(pressEmail)
  ).isDisplayed();
}

//Verify Contact Us section
public boolean isContactUsDisplayed() {

  return wait.until(
          ExpectedConditions.visibilityOfElementLocated(contactUs)
  ).isDisplayed();
}

//Verify Help email
public boolean isHelpEmailDisplayed() {

  return wait.until(
          ExpectedConditions.visibilityOfElementLocated(helpEmail)
  ).isDisplayed();
}
}