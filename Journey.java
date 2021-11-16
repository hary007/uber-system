import java.util.*;
class City{
    int rows;
    int columns;
    int [][]grid;
    City(int rows, int columns){
        this.rows=rows;
        this.columns=columns;
        grid=new int[rows][columns];
    }
    City(){}
    void setRows(int rows){
        this.rows=rows;
    }
    void setColumns(int columns){
        this.columns=columns;
    }
    void makeGrid(){
        grid=new int[rows][columns];
    }
}
class Journey{
    public static void main(String[] args) {
        City c=new City(12,14);
        
    }
}