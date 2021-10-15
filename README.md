        TEST TASK

You need to implement a project to automate website testing http://prestashop-automation.qatestlab.com.ua/ru/ in java,
    using Selenium WebDriver as an automation framework and Maven/Gradle as an auto-build tool.

Use version control systems (GitHub, Bitbucket...) when performing the task.
    The PageObject template must be followed while working on the project.

You will need to provide a link to the repository with the completed job for verification.

        Script for automation:

1. Open the homepage of the website
2. Check if the products' prices in section "Popular products" correspond to header currency (USD, EUR, UAH).
3. Show product prices in USD using the drop-down list in the header.
4. Search our catalogue using the word "dress".
5. Check if the "search results" page has "items: x" where x is the number of items actually found.
6. Check that the price of all results displayed is in dollars.
7. Set the sorting to "low to high".
8. Check that items are sorted by price, some items may be discounted and the price without discount is used when sorting.
9. For discounted items, a percentage discount is shown along with the price before and after the discount.
10. You need to check that the price before and after the discount is the same as the specified discount amount.

        Notes:

 - The results of the checks should be output to the console.
 - A log of the action must be written to a text file. The log should contain detailed information about the driver's
    actions (page openings, kicks, text inputs etc).
 - During the project implementation, the structure of classes and packages should be properly defined and classes
    grouped according to functionality.

        Additional, optional task items:

 - Connect TestNG or JUnit framework to describe the test project, use Assert class methods for checks.
 - Connect reporting, you can use Allure or ReportNG frameworks.