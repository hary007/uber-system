import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

class City {
    int rows;
    int columns;
    int[][] grid;
    List<Landmark> landmarks;

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

    void setLandmarks(List<Landmark> landmark) {
        this.landmarks = landmark;
    }

    boolean checkLandmarks(List<Landmark> l) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getAbscissa() >= columns || l.get(i).getOrdinate() >= rows) {
                return false;
            }
        }
        return true;
    }
}

class Landmark {
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

    Landmark(String name, int x, int y) {
        this.name = name;
        abscissa = x;
        ordinate = y;
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

    String getName() {
        return this.name;
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
    float eta;

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
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        eta = ((float) distanceFromPickup / (float) speed);
        eta = Float.parseFloat(numberFormat.format(eta));

        return eta;
    }
}

class Customer {
    String name;
    Landmark pickupLocation;
    Landmark destination;

    Customer(String name) {
        this.name = name;
    }

    void setPickupLocation(String name, int x, int y) {
        pickupLocation = new Landmark(name, x, y);
    }

    void setDestination(String name, int x, int y) {
        destination = new Landmark(name, x, y);
    }

    void cancelBooking(Driver d) {
        // driver available status to be changed.
        d.available = true;
        System.out.println("The booking is cancelled");
    }
}

class Journey {

    public static long countLineFast(String fileName) {

        long lines = 0;

        try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) { // returns the number of lines
                                                                                        // of the opened file
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            lines = count;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) {

        Scanner sc = null;
        Scanner lg = null;
        Scanner in = new Scanner(System.in);

        try {
            // CUSTOMER LOGIN TAKES PLACE HERE
            String customerName = new String();
            String customerPassword = new String();
            try {

                HashMap<String, String> customerLoginData = new HashMap<>();

                long numberOfRegisteredCustomers = countLineFast("customerLoginData.txt");
                lg = new Scanner(new FileReader("customerLoginData.txt"));
                // System.out.println(numberOfRegisteredCustomers);

                boolean loggedInSuccessfully = false;

                // storing customer login data in a hashmap
                for (long i = 0; i < numberOfRegisteredCustomers; i++) {
                    String s = lg.nextLine();
                    // System.out.println(s);
                    StringTokenizer st = new StringTokenizer(s);

                    String fileName = st.nextToken();
                    String filePass = st.nextToken();
                    customerLoginData.put(fileName, filePass);
                }
                // asking if existing user or new user.
                char choice;
                do {
                    System.out.println("Press 'c' to create new account or 'l' to login with an existing account");
                    choice = in.next().charAt(0);
                } while (choice != 'c' && choice != 'l');

                // adding new user
                if (choice == 'c') {
                    System.out.println("Enter new username: ");
                    do {
                        customerName = in.next();
                        if (customerLoginData.containsKey(customerName)) {
                            System.out.println("UserName already in use. Please enter some other username: ");
                        } else {
                            System.out.println("Enter password: ");
                            customerPassword = in.next();
                            customerLoginData.put(customerName, customerPassword);
                            System.out.println("Account created succesfully. Login again to enter app.");
                            System.out.println();

                            // to append the new details in the database ie the customerLoginData.txt file
                            PrintWriter outstream = new PrintWriter(
                                    new FileOutputStream("customerLoginData.txt", true));
                            outstream.println();
                            outstream.print(customerName + " " + customerPassword);
                            outstream.close();
                            break;
                        }
                    } while (customerLoginData.containsKey(customerName));
                }
                try {
                    Thread.sleep(1000); // just to make app more realistic.
                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println("\tWelcome to Login Page.");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }

                // Asking for login details
                do {
                    System.out.println("Please enter a valid username or type 'quit' to terminate the app: ");
                    customerName = in.next();
                    if (customerName.equals("quit")) {
                        System.out.println("Thanks for using our application. Have a good Day.");
                        System.exit(0);
                    }
                } while (!customerLoginData.containsKey(customerName));

                //System.out.println(customerLoginData); // Comment it out.

                for (int count = 2; count >= 0; count--) {
                    System.out.print("Please enter your password: ");
                    customerPassword = in.next();
                    if (customerPassword.equals(customerLoginData.get(customerName))) {
                        loggedInSuccessfully = true;
                        break;
                    }

                    else {
                        System.out.println("You have " + (count) + " attempt(s) remaining.");
                        // customerPassword = in.next();

                        if (count == 1) {
                            if (customerPassword.equals(customerLoginData.get(customerName)))
                                loggedInSuccessfully = true;
                        }
                    }
                }

                if (loggedInSuccessfully == false) {
                    System.out.println("Please try again later!");
                    System.exit(0);
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
            List<String> availableLandmarks = new ArrayList<>();

            for (int i = 0; i < t; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                String name = sc.next();

                Landmark k = new Landmark();
                k.setAbscissa(x);
                k.setOrdinate(y);
                k.setName(name);

                landmarks.add(k);
                availableLandmarks.add(name);
            }
            // adding the list of landmarks to the city
            c.setLandmarks(landmarks);
            // confirming that the landmarks are within the city.
            if (!c.checkLandmarks(landmarks)) {
                System.out.println("Landmark can not be out of city...Please check your input");
                System.exit(0);
            }

            Customer customer = new Customer(customerName);

            String pickupLocation;

            do {
                System.out.println("Enter valid pickup Location:-");
                pickupLocation = in.next();

            } while (!availableLandmarks.contains(pickupLocation));

            String destination;
            do {
                System.out.println("Enter valid destination (different from the pickup Location):-");
                destination = in.next();

            } while (!availableLandmarks.contains(destination) || pickupLocation.equals(destination));

            int pickupX = 0, pickupY = 0;
            int destX = 0, destY = 0;

            for (int i = 0; i < landmarks.size(); i++) { // we are getting the coordinates of locations which have been
                                                         // entered by the customer
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
            customer.setPickupLocation(pickupLocation, pickupX, pickupY);
            customer.setDestination(destination, destX, destY);

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

            // DRIVER LOGIN TAKES PLACE HERE
            boolean loggedInSuccessfully = false;
            boolean acceptRide = true;
            do {
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
                 * cars is less than 4 the fare would be increased by 1.2 times
                 * 
                 */

                double fare = 0;
                fare = 9 * distance;
                if (availableDriversNumber <= 4) {
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
                System.out.println("Notification Sent to the driver...Waiting for the response from the driver.");
                try {
                    lg = new Scanner(new FileReader("driverLoginData.txt"));
                    int numberOfRegisteredDrivers = lg.nextInt();
                    lg.nextLine();
                    Driver chosenDriver = drivers.get(indexForDriver);
                    System.out.println(chosenDriver.getName());
                    System.out.print("Please enter your password: ");
                    String chosenDriverPassword = in.next();

                    for (int i = 0; i < numberOfRegisteredDrivers; i++) {
                        String s = lg.nextLine();
                        StringTokenizer st = new StringTokenizer(s);
                        String fileName = st.nextToken();
                        String filePass = st.nextToken();

                        if (fileName.compareTo(chosenDriver.getName()) == 0) {
                            loggedInSuccessfully = false;
                            for (int count = 2; count > 0; count--) {
                                if (chosenDriverPassword.compareTo(filePass) == 0) {
                                    loggedInSuccessfully = true;
                                    break;
                                }

                                else {
                                    System.out.println(
                                            "You have " + (count) + " attempt(s) remaining. Please try again: ");
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
                        System.out.println("Driver is unable to login, please select any other driver. ");
                        try {
                            Thread.sleep(1000); // just to make app more realistic.
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                        // System.exit(0);
                    }

                    else {
                        char accepted;

                        // Options for the driver.
                        System.out.println("\n\tWelcome to the App!\n");
                        try {
                            Thread.sleep(1000); // just to make app more realistic.
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        System.out.println("You have an available ride from " + pickupLocation + " to " + destination);
                        System.out.println("Fare provided will be: " + fare);
                        System.out.println("Type 'y' to accept the ride and 'n' to reject the ride");
                        accepted = in.next().charAt(0);
                        if (accepted == 'y') {
                            System.out.println("Ride Accepted...Please reach " + pickupLocation + " for pickup.");
                            acceptRide = true;
                            chosenDriver.changeAvailableStatus();// driver is now unavailable and no one else can call
                                                                 // the same driver again
                            try {
                                Thread.sleep(1000); // just to make app more realistic.
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            System.out.println("In Customer Screen");
                            System.out.println("The driver has accepted your ride. Would you like to cancel?");
                            System.out.println("Please type 'y' if you want to cancel and 'n' if you want to proceed");
                            char cancelRide = in.next().charAt(0);
                            if (cancelRide == 'y') {
                                customer.cancelBooking(drivers.get(indexForDriver));
                                System.exit(0);
                            }
                            float currentETA = chosenDriver.eta;
                            System.out.println("Your ride has been accepted, the driver will arrive in " + currentETA
                                    + " minutes");

                            // to continuosly display the ETA...
                            // assume that after every 1 second in reality 30 seconds have passed in the app

                            while (currentETA > 0.0f) {
                                try {
                                    if (currentETA >= 0.5f) {
                                        currentETA -= 0.5f;
                                        try {
                                            Thread.sleep(1000);
                                            System.out.println("ETA: " + currentETA + " minutes");
                                        } catch (Exception e) {
                                            System.out.println(e);
                                        }
                                    } else {
                                        if (currentETA > 0.0f) {
                                            int sleepingTime = Math.round((currentETA * 1000) / 30);
                                            try {
                                                Thread.sleep(sleepingTime);
                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }
                                        }
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                            }
                            System.out.println("The driver has reached your pickup location...Have a Safe Journey");
                            try {
                                Thread.sleep(3000); // just to make app more realistic.
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            System.out.println("You have reached your destination..");
                            System.out.println("Thanks For Using Our App!!");

                        } else if (accepted == 'n') {
                            System.out.println(
                                    "Ride rejected...Giving Control back to customer to change the selected driver");
                            try {
                                Thread.sleep(1000); // just to make app more realistic.
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            System.out.println("The driver has rejected your ride...Please select any other driver");
                            try {
                                Thread.sleep(1000); // just to make app more realistic.
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            acceptRide = false;

                        }
                    }

                } catch (FileNotFoundException e) {
                    System.out.println(e.getStackTrace());
                } finally {
                    lg.close();
                }

            } while (!loggedInSuccessfully || !acceptRide);

        } catch (

        FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } finally {
            in.close();
            sc.close();
        }

    }
}