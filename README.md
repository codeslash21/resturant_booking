# Restaurant Booking App
This restaurant booking app will allow restaurant owners to register their restaurants, to add time slots for their restaurant. Using this app customer can search restaurant based on search criteria like Location, Rating, Food type, Cuisine, Cost etc. Customer can book restaurant for an available slot on a specific date for n number of people. Customer can also see all their bookings.

## Main Components of the App
The major components of the Restaurant Booking Application consist of the following:
1. **CLI for the User Interface:** We'll use the Command Line Interface (or CLI) for the user interface. For this, we'll need to have Java monitor the CLI for user input, so the user can enter commands to search for available rooms, book rooms, and so on.
2. **Java code:** The second main component is the Java code itself—this is where we add our business logic for the app.
3. **Java collections:** Finally, we'll use Java collections for in-memory storage of the data we need for the app, such as the users' names, room availability, and so on.

![image](https://github.com/codeslash21/hotel-reservation/assets/32652085/7a0d65f0-878a-413d-9a18-5fcc62f78058)

## Application Architecture
The app can be separated into the following layers:
1. **User interface (UI):** including a main menu for the customer who want to search and book a restaurant, and an admin menu for restaurant owner.
2. **Resources**: will act as our Application Programming Interface (API) to our UI.
3. **Services:** will communicate with our resources, and each other, to build the business logic necessary to provide data to our UI.
4. **Data models:** will be used to represent the domain that we're using within the system (e.g., restaurant, booking, customers and owner).

## Project Requirements
### Restaurant Owner Scenarios
- Restaurant owner will be able to `Sign Up`/`Login` to the application.
- After login owners will be able to `register restaurants`.
- Owners can add available `time slots` for the restaurants.
- Owners can see details about their registered restaurants.

### Customer Scenarios
- Customer will be able to `Sign Up`/`Login` to the application.
- Customer will be able to `search restaurant` with the search criterias `name`, `city`, `area`, `rating`, `cost`, `veg/non-veg`, `cuisine` etc.
- After selecting a restaurant customer will be able to book table at the restaurant for n people on a specific date and time slot.
- Customer can all their bookings.

### Other specification
- Customer can book a restaurant for max 7 days in future.
- Duration for each time slot is one hour.
- Login password is stored in encrypted format.
- User will not be able to login if login credentials are wrong.



## Getting Started
To run this projects follow these steps -
- Open git and go to the directory where you want to download the project and type the following command 
```
git clone https://github.com/codeslash21/restaurant_booking.git
```
- After you execute the above command type to enter into the project directory `restaurant_booking`
```
cd restaurant_booking
```
- Then go to the `src` folder by typing `cd src`
- Run `javac RestaurantBooking.java` to compile the java which contains the `main` method
- Run `java RestaurantBooking` to execute the `.class` file that is created by compiling using above command.
- Now, you can see the application UI and can interact with the application by giving input through CLI.
