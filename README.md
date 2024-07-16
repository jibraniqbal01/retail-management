# retail-management


Store management system is used to calculate the final amount after applying various discounts. Discounts are divided into two parts:
1. Percentage based discount
2. Amount based discount

According to the given input, percent based discount is calculated on non grocery items. After that the calculated discount is deducted from the total amount. If total amount exceeds 100$ than addition 5$ discount is given for every multiple of 100$. After that this discount is deducted from the amount calculated above.

# Requirements:
1. Java17
2. Gradle
3. Postman -- Tool to post request

# Installation

1. Clone the project
2. Build project: ./gradlew retail-management clean build
3. Run project: java -jar  retail-management/build/libs/retail-management-0.0.1-SNAPSHOT.jar


It will download the required jars and embedded tomcat server will run.
	

# Usage 
1. Open Postman and select method as post.
2. Click on body .
3. Click on raw tab and enter json(Find below the JSON under #Tests) data and put Content-Type as application/json.
4. Access the application by entering the URL http://localhost:8080/store/bill in the URL bar if postman

sssss

