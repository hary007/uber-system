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
    boolean available = true; // this variable will be true if a driver is ready to pick the customer up.
    int speed;

    Driver() {

    }

    Driver(String name, float rating, Landmark currentLocation){
        this.name=name;
        this.rating=rating;
        this.currentLocation=currentLocation;
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
}
class Customer extends Landmark{
    String name;
    Landmark pickupLocation;
    Landmark destination;
    void chooseDriver(){
        //return list of available drivers from the database
    }
    void choosePickupLocation(){
        System.out.println("Enter pickup Location");
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        int y=s.nextInt();
        s.close();
        pickupLocation.setAbscissa(x);
        pickupLocation.setOrdinate(y);
    }
    void choosedestination(){
        System.out.println("Enter pickup Location");
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        int y=s.nextInt();
        s.close();
        destination.setAbscissa(x);
        destination.setOrdinate(y);
    }
    void cancelBooking(Driver d){
        //driver available status to be changed.
        d.available=true;
        System.out.println("The booking is cancelled");
    }
}

class Journey {

    public static void main(String[] args) {
        System.out.println("Enter pickup Location:-");
        Scanner s=new Scanner(System.in);
        String pickupLocation;
        pickupLocation=s.next();
        System.out.println("Enter destination:-");
        String destination;
        destination=s.next();
        
        
        
    }
}