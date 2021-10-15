mvn clean install
#Copy-Item -Path "src\test\java\resources\environment.properties" -Destination "target\surefire-reports\environment.properties" -Force -Verbose
#Copy-Item -Path "src\test\java\resources\allure.properties" -Destination "target\surefire-reports\allure.properties" -Force -Verbose
allure generate target/surefire-reports -o target/allure-report
#allure generate target/surefire-reports
allure open target/allure-report
#allure open allure-report