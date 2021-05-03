## Example of Spring Boot + Cucumber + Selenide, Test Allure Report


#### To trigger tests execution, please follow the steps below:
```
$ git clone https://github.com/iselishev/springboot-cucumber-selenide.git

$ mvn clean verify
$ mvn allure:report

$ mvn clean verify -Dspring.profiles.active=perf-user
$ mvn allure:report

$ mvn clean verify -Dspring.profiles.active=problem-user
$ mvn allure:report

...