# Grocery UI

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

Application was developed to keep in mind that it will grow in future. MVC design is followed, but it's not completely followed due to time constraint.

Modularization was followed to keep the similar functionalities in single module.

| Module Name | Description |
| --- | --- |
| <b>component</b> | It contains all the logic which is need for template interaction. |
| <b>service</b> | It contains the logic to call backend REST API. |

Some shortcuts or cases that are not handled:

1. Detailed exception handling and handle different types of HTTP response status.
2. Detailed Logging throughout the application to gather as much as information.
3. Code coverage is not done.
4. UI is not that interactive, containing only basic functionality to allow to upload file, display max. price report in a data table and then show price trend for the item when clicked.

##### Request Flow

User's Browser Interaction ➡ Component ➡ Service ➡ Backend REST API

## Install & Running

### Prerequisites
* npm

NPM must be exported in user's PATH variable.

### Pull from git
```
$ git clone https://bitbucket.org/sathishcgi/groceryassignment.git
$ cd groceryassignment/grocery-ui
```

### Build & run

* Install NPM dependencies
```
$ npm install
```

* Build
```
$ ng build
```

* Run test
```
$ ng test
```

* Run the application
```
$ ng serve
```

## Development server

Run `ng serve` for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.
