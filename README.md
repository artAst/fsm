FSM (Finite State Machine) Application
=====
Overview
------
The FSM Application also known as FINITE-STATE-MACHINE-APP is a sofware application design to manage passenger and vehicle state during transportation.
Here are the following states included:

* Waiting Vehicle
* Passenger Boarded
* Waiting to Pay
* Waiting for Change
* Riding Vehicle
* Waiting to Stop
* Got Off

Entities
------
The Application consist of 3 entities
located in *com.fsm.model*:

* FSMState
* Passenger
* Vehicle

Testing the app
------
Maven is used to run the unit tests in the app. 
Or you can run it in a [conventional way using this method](http://stackoverflow.com/questions/2235276/how-to-run-junit-test-cases-from-the-command-line)

These are the test items for the app
within the test source package *com.fsm.service.impl* and *com.fsm.api*:

* PassengerServiceImplTest
* VehicleServiceImplTest
* FSMRestApiControllerTest

In command line:
```
mvn test
```
To run all of the test items.

And
```
mvn -Dtest=PassengerServiceImplTest test
```
To run a single test item.