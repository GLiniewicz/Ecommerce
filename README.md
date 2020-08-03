# Ecommerce

Run procedure using command prompt: {project directory} mvn test -Dbrowser=chrome
 
Description:

1. Language: In my Selenium Project I am using Java language and Ecplise IDE ver. 2020-03.
2. Design patter: Page Object Model.
3. POM: As per the Page Object Model, I have maintained a class for every web page. 
4. Packages: I have separate Packages for Pages and Tests. All the web pages related classes come under PageObject package and all the tests related classes come under Tests package.
5. Test Base Class: Test Base Class (Base.java) deals with all the common functions used by all the pages. This class is responsible for loading configuration, initializing the WebDriver, Implicit Waits, Extent Reports and also to create object of FileInputStream which is responsible for pointing towards the file from which the data should be read. 
6. Screenshots: Screenshots will be captured and stored in a separate folder and the screenshots of failed test cases will be added in the extent reports
7. Test Data: To derive test data I am using @DataProvider which is part of TestNG, and part of the data will be kept in excell sheet (TestData.xlsx). By using TestData.xlsx I pass test data and handel data driven testing. I use Apache POI to handel excell sheets. 
8.TestNG: Using TestNG for Assertions, Grouping, Parallel execution and derive data with @DataProvider annotation.
9. Maven: Using Maven for build, execution in command prompt ( with a help of surefire plugin ) and dependency purpose.
10. Version control: Git
11. Extent Reports: For the reporting purpose, I am using Extent Reports. It generates easy to read HTML reports which is located in 'reports' folder inside of the project.
