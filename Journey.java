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

    Landmark() {
    };

    Landmark(Landmark l) {
        this.name = l.name;
        this.abscissa = l.abscissa;
        this.ordinate = l.ordinate;
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
    int available; // this variable will be true if a driver is ready to pick the customer up.
    // how to change the value of available in the database once the ride has been
    // confirmed.
    int speed;

    Driver() {

    }

    Driver(String name, float rating, Landmark currentLocation, int available) {
        this.name = name;
        this.rating = rating;
        this.currentLocation = currentLocation;
        this.available = available;
    }

    void changeAvailableStatus() {
        if (available == 1)
            available = 0;
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
}

class Customer extends Landmark { // why is customer extending landmark
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
        d.available = 1;
        System.out.println("The booking is cancelled");
    }
}

class Journey {

    static List<Driver> drivers;
    static List<Landmark> landmarks;

    public static void main(String[] args) {

        Scanner sc = null;
        try { // user input inside try or not
            sc = new Scanner(new FileReader("city.txt"));

            int n = sc.nextInt();
            int m = sc.nextInt();
            City c = new City(n, m);

            int t = sc.nextInt();

            landmarks = new ArrayList<Landmark>();

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
            Scanner s = new Scanner(System.in);

            String pickupLocation;
            pickupLocation = s.next(); // keep iterating till right location is not entered
            int pickupX, pickupY;

            System.out.println("Enter destination:-");

            String destination;
            int destX, destY;

            destination = s.next();
            for (int i = 0; i < landmarks.size(); i++) {
                if (pickupLocation == landmarks.get(i).name) {
                    destX = landmarks.get(i).getAbscissa();
                    destY = landmarks.get(i).getOrdinate();
                } else if (destination == landmarks.get(i).name) {
                    pickupX = landmarks.get(i).getAbscissa();
                    pickupY = landmarks.get(i).getOrdinate();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        } finally {
            sc.close();
        }

    }
}