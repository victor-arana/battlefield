package tictactoe;

public class Main {
    public static void main(String[] args) {
        var grid = new Grid();
        grid.autoFill();
        grid.print();    
    }
}

class Grid {
    int m;
    int n;

    Grid(){
        this(3,3);
    } 
    
    Grid(int rows, int columns) {
        m = rows;
        n = columns;
    }

    void autoFill() {
        System.out.println("Autofilling grid");
    }

    void print() {
        System.out.println("Printing grid");
    }
}
