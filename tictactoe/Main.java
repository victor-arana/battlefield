package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var grid = new Grid();
        grid.autoFill();
        grid.readState();
        grid.print();    
    }
}

class Grid {
    Integer[][] grid;

    Grid(){
        this(3,3);
    } 
    
    Grid(int rows, int columns) {
        if (rows <= 0 && columns <= 0) throw new IllegalArgumentException();
        grid = new Integer[rows][columns];
    }
    
    void readState() {
        Scanner scanner = new Scanner(System.in);
        String state = scanner.nextLine();
        grid = parseState(state);
    }
    
    Integer[][] parseState(String state) {
        String firstRow = state.substring(0,3);
        System.out.println(firstRow);
        String secondRow = state.substring(3,6);
        System.out.println(secondRow);
        String thirdRow = state.substring(6,9);
        System.out.println(thirdRow);
        return grid;
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
        System.out.println("---------");
        for(int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for(int j = 0; j < grid[i].length; j++) { 
               String character =grid[i][j] == 0 ? "O" :
                                    grid[i][j] == 1 ? "X" :
                                        "-"; 
               System.out.print(character + " ");
           }
           System.out.printf("| %n");
        } 
        System.out.println("---------");
    }
}
