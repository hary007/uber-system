import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class City {
    int rows;
    int columns;
    int[][] grid;

    City(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new int[rows][columns];
    }

    City() {
    }

    void setRows(int rows) {
        this.rows = rows;
    }

    void setColumns(int columns) {
        this.columns = columns;
    }

    void makeGrid() {
        grid = new int[rows][columns];
    }
}

class Landmark extends City {
    String name;
    private int abscissa;
    private int ordinate;

    Landmark() { // default constructor for Landmark
        this.abscissa = 0;
        this.ordinate = 0;
        this.name = "Origin";
    }

    Landmark(Landmark obj) {
        this.name = obj.name;
        this.abscissa = obj.getAbscissa();
        this.ordinate = obj.getOrdinate();
    }

    void setName(String name) {
        this.name = name;
    }

    void setAbscissa(int a) {
        abscissa = a;
    }

    void setOrdinate(int b) {
        ordinate = b;
    }

    int getAbscissa() {
        return this.abscissa;
    }

    int getOrdinate() {
        return this.ordinate;
    }

}

class Cab {
    private String registerationNumber;
    String carType;

    void setRegNumber(String registerationNumber) {
        this.registerationNumber = registerationNumber;
    }

    void setCarType(String carType) {
        this.carType = carType;
    }
}

class Driver extends Cab {
    private String name;
    private float rating;
    Landmark currentLocation;
    boolean available; // this variable will be true if a driver is ready to pick the customer up.
    int speed;
    boolean notify;

    Driver() {

    }

    Driver(String name, float rating, Landmark currentLocation, boolean available, int speed) {
        this.name = name;
        this.rating = rating;
        this.currentLocation = currentLocation;
        this.available = available;
        this.speed = speed;
        this.notify = false;
    }

    void notifyAboutJourney() {
        this.notify = true;
    }

    void notification() {
        if (notify) {
            System.out.println("You have an avaiable ride");
            System.out.println("Journey Details: ");
        }
    }

    void changeAvailableStatus() {
        if (available)
            available = false;
    }

    void setName(String name) {
        this.name = name;
    }

    float getRating() {
        return this.rating;
    }

    void setCurrentLocation(Landmark l) {
        currentLocation = l;
    }

    void setSpeed(int speed) {
        this.speed = speed;
    }

    void setRating(float rating) {
        this.rating = rating;
    }

    String getName() {
        return this.name;
    }

    void startRide() {
        System.out.println("Your ride has started!");
    }

    void endRide() {
        System.out.println("You have reached your destination. Thank You!");
        // driver will become available again
        // the destination location of customer will become the current location of
        // driver. 1) take parameter in this method. 2) Use journey class
    }

    float calculateEta(int pickupX, int pickupY) {
        int distanceFromPickup = java.lang.Math.abs(currentLocation.getAbscissa() - pickupX)
                + java.lang.Math.abs(currentLocation.getOrdinate() - pickupY);

        // System.out.print("Distance from pickup = " + distanceFromPickup + " meters,
        // ");
        return (distanceFromPickup / speed);
    }
}

class Customer extends Landmark {
    String name;
    Landmark pickupLocation;
    Landmark destination;

    void chooseDriver() {
        // return list of available drivers from the database
    }

    void choosePickupLocation() {
        System.out.println("Enter pickup Location");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        int y = s.nextInt();
        s.close();
        pickupLocation.setAbscissa(x);
        pickupLocation.setOrdinate(y);
    }

    void choosedestination() {
        System.out.println("Enter pickup Location");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();
        int y = s.nextInt();
        s.close();
        destination.setAbscissa(x);
        destination.setOrdinate(y);
    }

    void cancelBooking(Driver d) {
        // driver available status to be changed.
        d.available = true;
        System.out.println("The booking is cancelled");
    }
}

class Journey {

    public static void main(String[] args) {

        Scanner sc = null;
        Scanner lg = null;
        Scanner in = new Scanner(System.in);

        try {
            // CUSTOMER LOGIN TAKES PLACE HERE
            try {
                lg = new Scanner(new FileReader("customerLoginData.txt"));
                String customerName, customerPassword;
                System.out.print("Please enter your name: ");
                customerName = in.next();
                System.out.print("Please enter your password: ");
                customerPassword = in.next();

                // System.out.println(customerName + "-" + customerPassword);

                int numberOfRegisteredCustomers = lg.nextInt();
                lg.nextLine();
                // System.out.println(numberOfRegisteredCustomers);
                boolean loggedInSuccessfully = false;

                for (int i = 0; i < numberOfRegisteredCustomers; i++) {
                    String s = lg.nextLine();
                    // System.out.println(s);
                    StringTokenizer st = new StringTokenizer(s);

                    String fileName = st.nextToken();
                    String filePass = st.nextToken();

                    if (fileName.compareTo(customerName) == 0) {
                        for (int count = 2; count > 0; count--) {
                            if (customerPassword.compareTo(filePass) == 0) {
                                loggedInSuccessfully = true;
                                break;
                            }

                            else {
                                System.out.println("You have " + (count) + " attempt(s) remaining. Please try again: ");
                                customerPassword = in.next();

                                if (count == 1) {
                                    if (customerPassword.compareTo(filePass) == 0)
                                        loggedInSuccessfully = true;
                                }
                            }
                        }

                    }

                }

                if (loggedInSuccessfully == false) {
                    System.out.println("Please try again later!");
                    return;
                }

                else {
                    System.out.println("\nWelcome to the App!\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getStackTrace());
            } finally {
                lg.close();
            }

            sc = new Scanner(new FileReader("city.txt"));

            int n = sc.nextInt();
            int m = sc.nextInt();
            City c = new City(n, m);

            int t = sc.nextInt();

            List<Landmark> landmarks = new ArrayList<Landmark>();

            for (int i = 0; i < t; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                String name = sc.next();

                Landmark k = new Landmark();
                k.setAbscissa(x);
                k.setOrdinate(y);
                k.setName(name);

                landmarks.add(k);
            }

            // for (int i = 0; i < t; i++) {
            // System.out.println(landmarks.get(i).name);
            // }

            System.out.println("Enter pickup Location:-");

            String pickupLocation;
            pickupLocation = in.next();
            int pickupX = 0, pickupY = 0;

            System.out.println("Enter destination:-");

            String destination;
            int destX = 0, destY = 0;

            destination = in.next();
            for (int i = 0; i < landmarks.size(); i++) {
                if (pickupLocation.equals(landmarks.get(i).name)) {
                    pickupX = landmarks.get(i).getAbscissa();
                    pickupY = landmarks.get(i).getOrdinate();
                    System.out.println("pickup " + pickupX + " " + pickupY);
                } else if (destination.equals(landmarks.get(i).name)) {
                    destX = landmarks.get(i).getAbscissa();
                    destY = landmarks.get(i).getOrdinate();
                    System.out.println("dest " + destX + " " + destY);
                }
            }

            // System.out.println(pickupX);
            sc = new Scanner(new FileReader("driverData.txt"));
            int numberOfDrivers = sc.nextInt();
            sc.nextLine();

            List<Driver> drivers = new ArrayList<>();
            for (int i = 0; i < numberOfDrivers; i++) {
                String line = sc.nextLine();
                // System.out.println(line);
                StringTokenizer st = new StringTokenizer(line);
                String nameOfDriver = st.nextToken();
                Float rating = Float.parseFloat(st.nextToken());
                String l = st.nextToken();
                Integer available = Integer.parseInt(st.nextToken());
                Integer speed = Integer.parseInt(st.nextToken());
                Landmark la = new Landmark();
                for (int j = 0; j < landmarks.size(); j++) {
                    if (landmarks.get(j).name.equals(l)) {
                        la = new Landmark(landmarks.get(j));
                        break;
                    }
                }
                boolean availability;
                if (available == 0) {
                    availability = false;
                } else {
                    availability = true;
                }
                Driver d = new Driver(nameOfDriver, rating, la, availability, speed);
                drivers.add(d);
            }
            // Distance between the pickup and destination
            int distance = java.lang.Math.abs(destX - pickupX) + java.lang.Math.abs(pickupY - destY);
            System.out.println("Distance:" + distance + " meters");

            // Display the list of available drivers
            System.out.println();
            System.out.println("Available Drivers: ");
            int availableDriversNumber = 0;
            for (int i = 0; i < drivers.size(); i++) {
                if (drivers.get(i).available) {
                    availableDriversNumber++;
                    System.out.print("Index: ");
                    System.out.print(i + 1);
                    System.out.print(", ");
                    System.out.print("Name: ");
                    System.out.print(drivers.get(i).getName());
                    System.out.print(", Rating: ");
                    System.out.print(drivers.get(i).getRating());
                    System.out.print(", Speed: " + drivers.get(i).speed + " m/minutes");
                    System.out.print(", ETA: "); // ETA before the customer has booked a cab.

                    float minutes;
                    minutes = drivers.get(i).calculateEta(pickupX, pickupY);
                    System.out.print(minutes);
                    System.out.println(" minutes");
                }
            }
            // Display the estimated fare
            /*
             * Algo for calculating the fare : 1. fare = 9*distance 2. if total availabe
             * cars is less than 4 the fare would be increased by 1.2 times 3. if time of
             * booking is after 6:00 pm(and before 8:00am) then the fare would be increased
             * by 1.2 times
             */

            double fare = 0;
            fare = 9 * distance;
            if (availableDriversNumber <= 2) {
                fare = 1.2 * fare;
            }

            System.out.println("Total Fare: " + fare + " Rs.");
            System.out.println("\nPlease enter the index of the selected driver:");
            int indexForDriver = in.nextInt() - 1;
            // Ensuring the entered index is within the avaialbe list
            while (indexForDriver > drivers.size() || !drivers.get(indexForDriver).available) {
                System.out.println("Please enter the index from the above list only!!");
                indexForDriver = in.nextInt() - 1;
            }

            // notifying the selected driver about the trip
            drivers.get(indexForDriver).notifyAboutJourney();

            // DRIVER LOGIN TAKES PLACE HERE
            try {
                lg = new Scanner(new FileReader("driverLoginData.txt"));
                int numberOfRegisteredDrivers = lg.nextInt();
                lg.nextLine();
                Driver chosenDriver = drivers.get(indexForDriver);
                System.out.println(chosenDriver.getName());
                System.out.print("Please enter your password: ");
                String chosenDriverPassword = in.next();

                boolean loggedInSuccessfully = false;

                for (int i = 0; i < numberOfRegisteredDrivers; i++) {
                    String s = lg.nextLine();
                    StringTokenizer st = new StringTokenizer(s);
                    String fileName = st.nextToken();
                    String filePass = st.nextToken();

                    if (fileName.compareTo(chosenDriver.getName()) == 0) {
                        for (int count = 2; count > 0; count--) {
                            if (chosenDriverPassword.compareTo(filePass) == 0) {
                                loggedInSuccessfully = true;
                                break;
                            }

                            else {
                                System.out.println("You have " + (count) + " attempt(s) remaining. Please try again: ");
                                chosenDriverPassword = in.next();

                                if (count == 1) {
                                    if (chosenDriverPassword.compareTo(filePass) == 0) {
                                        loggedInSuccessfully = true;
                                    }
                                }
                            }
                        }
                    }
                }

                if (loggedInSuccessfully == false) {
                    System.out.println("Please try again later!");
                    return;
                }

                else {
                    System.out.println("\nWelcome to the App!\n");
                }
                chosenDriver.changeAvailableStatus(); // add a method for driver to choose if he wants to cancel the
                // ride

            } catch (FileNotFoundException e) {
                System.out.println(e.getStackTrace());
            } finally {
                lg.close();
            }

        } catch (

        FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } finally {
            sc.close();
        }

    }
}