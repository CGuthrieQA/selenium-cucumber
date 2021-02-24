# Setup

## Dependencies

Get cucumber installed, I used eclipse. Make sure you have selenium installed and the driver(s) you want to use in your `src/test/resources` folder.

stuff to add to the pom.xml
```xml
<!-- junit -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>
<!-- selenium -->
<dependency>
	<groupId>org.seleniumhq.selenium</groupId>
	<artifactId>selenium-java</artifactId>
	<version>3.141.59</version>
</dependency>
<!-- annotated cucumber -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>6.8.1</version>
    <scope>test</scope>
</dependency>
<!-- cucumber junit -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-junit</artifactId>
    <version>6.8.1</version>
    <scope>test</scope>
</dependency>
```

## Adding a feature file

In your `src/test/resources` folder make a new folder called `cuke`

Inside of that folder make a new file called `something.feature` replace something with the project name or whatever you want this set of tests to be doing.

```gherkin
Feature: what is the name of the feature
  describe it

  Scenario: What is the scenario

  #or

  Scenario Outline: What is the scenario
    Given that "something"
    When I do a "<RightColumn>"
    And I also do something else
    Then I should expect "<LeftColumn>"

    Examples: 
      | LeftColumn | RightColumn |
      | Foo | Bar |
      | Lorem | Ipsum |
```

## Setting up your Cuke Runner

In your `src/test/java` make a new package called `cucumber`

Inside that package create a new class called `CukeRunner.java` we will run our JUnit tests from this file.

```java
@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/resources/cuke", // where your features are stored
	stepNotifications = true // so that JUnit will display the test steps
	)
public class CukeRunner {

}
```

## Setting up StepDefs

In your `src/test/java.cucumber` package, make a new package called `stepdefs`

Inside this package make a new class called `SomethingStepDef.java` replace something with the project name or whatever you want this set of tests to be doing.

Here we need to set up our driver and close it...

```java
private static RemoteWebDriver driver;

@Before
public void setup() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
    driver = new ChromeDriver(chromeCfg());
}
```
...

```java
@After
public static void tearDown() {
    driver.quit();
    System.out.println("driver closed");
}
```

## Building Step Definitions

Once we run our first JUnit test from `CukeRunner.java` we can check the JUnit logs for some pre-constructed methods we can insert between the @Before and @After annotated methods in `SomethingStepDef.java`

The logic for tests can go in here.