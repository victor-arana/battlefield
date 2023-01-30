package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var grid = new Grid();
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

    Character[][] unflatMatrix(int m, int n, char[] a){
        Character[][] output = new Character[m][n];
        int k = 0; 
        int i = 0;
        while (i < m) {
            int j = 0;
            while (j < n) {
                output[i][j] = a[k]; 
                k++;
                j++;       
            }
            i++;
        }
        return output;        
    }

    
    Integer[][] parseState(String state) {
        Character[][] stateMatrix  = unflatMatrix(3, 3, state.toCharArray());
        return parseStateMatrix(stateMatrix);
    }

    Integer[][] parseStateMatrix(Character[][] stateMatrix) {
        int m = stateMatrix.length;
        int n = stateMatrix[0].length;

        Integer[][] result = new Integer[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n;j++) {
                result[i][j] = parseSlot(stateMatrix[i][j]);                 
            }
        }
        return result;
    }

    Integer parseSlot(Character character) {
        Integer value = 0;
        switch (character) {
            case 'O':
                value = 0;
                break; 
            case 'X':
                value = 1;
                break;
            case '_':
            default:
                value = null;
        }
        return value;
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
               String character = null;
               if (grid[i][j] == null) {
                    character = "_";
                } else if ((int) grid[i][j] == 0) {
                    character = "O";
                } else if ((int) grid[i][j] == 1) {
                    character = "X";
                } else {
                    character = "_";
                }
                System.out.print(character + " ");
           }
           System.out.printf("| %n");
        } 
        System.out.println("---------");
    }
}
