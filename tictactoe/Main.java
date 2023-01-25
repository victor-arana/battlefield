package tictactoe;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var grid = new Grid();
        grid.autoFill();
        grid.print();    
    }
}

class Grid {
    int[][] grid;

    Grid(){
        this(3,3);
    } 
    
    Grid(int rows, int columns) {
        if (rows <= 0 && columns <= 0) throw new IllegalArgumentException();
        grid = new int[rows][columns];
    }

    void autoFill() {
        var random = new Random();
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
    }

    void print() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) { 
               String character =grid[i][j] == 0 ? "O" :
                                    grid[i][j] == 1 ? "X" :
                                        "-"; 
               System.out.print(character + " ");
           }
           System.out.println();
        } 
    }
}
