## Intro
This README would normally document whatever steps are necessary to get your application up and running.

What is this Assignment for?
As Full stack developer , you want to show case your skills

#### What is given?
data/vegetables.xls- grocery price data * Item Name - Vegetable/fruit/grocery name * Date- date * Price - price of the item on that date

## The Task
Rest End Point

1.Generate a list of vegetable/fruit sorted by name, their maximum price ,date on which price was max

2.Generate a report for each Item Name, showing how their price trended

GUI

-Design UI to show the data [rest output] based on Item Name

## Application Modules

The complete functionality is being developed to keep in mind the maximum decoupling among the modules (parent packages).

| Module Name | Description |
| --- | --- |
| <b>core</b> | This package contains core models and exceptions module. The package will contain all the common logic which can be used by other packages/modules. |
| <b>dao</b> | This module contains all configuration and classes that will be responsible for database interaction (e.g. repository and entity classes) . |
| <b>service</b> | This module contains all configuration and classes that will be responsible for business logic (e.g. business validations and any other business logic). |
| <b>web</b> | This module contains all the endpoints of the application |
| <b>scheduler</b> | This module scheduler which runs every minute to generate maximum price and price trend report. |

Some shortcuts or cases that are not handled:

1. Detailed exception handling and handle different types of HTTP related issues.
2. Hardcoded values which can be moved to some static constants.
3. Detailed Logging throughout the application to gather as much as information.
4. Smart file processing (e.g. partial file and large file processing). Currently, application will process whole file or will not process at all. Also, current logic doesn't expect an item to be present multiple times on the same date. If same product exists on same date multiple times, then current logic will fail when we try to upload the same file again.
5. Application allows a maximum <b>32 MB</b> file size to upload.
6. Only CSV files allowed to process.
7. 100% code coverage using tests is not complete.
8. UI is not that interactive, containing only basic functionality to allow to upload file, display max. price report in a data table and then show price trend for the item when clicked.
9. Add security to access REST APIs.

##### Request Flow

User's Request ➡ Endpoint/Web ➡ Service ➡ DAO ➡ Data Repository ➡ Storage (DB, In-Memory etc.)

<b>Scheduler</b> ➡ It runs every minute to generate max. price and price trend report asynchronously. The idea behind writing scheduler to generate stats report in background instead of generating it on a call. With live report generation, API response will be slow. Reports show the latest snapshot of price and trend instead of live.

It can be improved using additional tools like caching.

<b>Note* - Current application contains vegetables-short.csv file that has a set of defined grocery items. We can extend the file to add more grocery items.  </b>

## Install & Running

### Prerequisites
* JDK 1.8
* Maven 3.5.2

Maven and JDK 1.8 must be exported in user's PATH variable.

### Pull from git
```
$ git clone https://bitbucket.org/sathishcgi/groceryassignment.git
$ cd groceryassignment/grocery-api
```

### Build & run

* Build
```
$ mvn clean package
```

* Run test
```
$ mvn test
```

* Run the application
```
$ cd target
$ java -jar grocery-api.jar
```

#### Application Endpoints

* ##### Swagger UI
```
$ http://localhost:8080/swagger-ui.html
```

* ##### In-memory MongoDB Database
```
$ http://localhost:8080/h2-console

DB Url - mongodb://localhost:27017/grocery
```

* ##### Grocery Item File Upload
```
$ http://localhost:8080/api/groceries/upload (HTTP POST)
```

* ##### Get Max. Price Report - Display snapshot of max. price report.
```
$ http://localhost:8080/api/groceries/reports/max-price (HTTP GET)
```

* ##### Get Price Trend report for the item
```
$ http://localhost:8080/api/groceries/reports/price-trend/{itemName} (HTTP GET)

Example - http://localhost:8080/api/groceries/reports/price-trend/Kakadi
```
