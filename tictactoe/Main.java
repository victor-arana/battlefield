package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var grid = new Grid();
        grid.readState();
        grid.analizeState();
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

    void analizeState() {
        // Test diagonals
        boolean xWinsByDiagonal = testDiagonal(1,grid);
        boolean oWinsByDiagonal = testDiagonal(0,grid);

        // Test rows
        boolean xWinsByRow = false;
        boolean oWinsByRow = false;
        for (int i = 0; i < grid.length; i++) {
            xWinsByRow = testRow(i, 1, grid);
            oWinsByRow = testRow(i, 0, grid);
            if (xWinsByRow || oWinsByRow) break;
        }

        // Test columns
        boolean xWinsByColumn = false;
        boolean oWinsByColumn = false;
        for (int j = 0; j < grid[0].length; j++) {
            xWinsByColumn = testColumn(j, 1, grid);
            oWinsByColumn  = testColumn(j, 0, grid);
            if (xWinsByColumn || oWinsByColumn) break;
        }

        boolean xWins = xWinsByDiagonal || xWinsByRow || xWinsByColumn;
        boolean oWins = oWinsByDiagonal || oWinsByRow || oWinsByColumn;
        boolean gameIsNotFinished = !xWins && !oWins && gridHasEmptyCells(grid);
        boolean draw = !xWins && !oWins && !gridHasEmptyCells(grid);

        System.out.println("X wins: " + xWins);
        System.out.println("O wins: " + oWins);
        System.out.println("Game not finished: " + gameIsNotFinished);
        System.out.println("Draw: " + draw);
    }
    
    boolean gridHasEmptyCells(Integer[][] grid){
        boolean gridHasEmptyCells = false;
        for (Integer[] row: grid) {
            for(Integer cell: row){
                if(cell == null) {
                    gridHasEmptyCells = true;
                    break;
                }
            }
        }
        return gridHasEmptyCells;
    }
    
    boolean testDiagonal(Integer i, Integer[][] a) {
       boolean backSlashIsFull  = a[0][0] != null && a[1][1] != null && a[2][2] != null;
       boolean backSlashWins = backSlashIsFull 
                && (a[0][0].equals(i) && a[0][0].equals(a[1][1]) && a[0][0].equals(a[2][2]));

        boolean forwardSlashIsFull  = a[0][2] != null && a[1][1] != null && a[1][1] != null;
        boolean forwardSlashWins = forwardSlashIsFull
                && (a[0][2].equals(i) && a[0][2].equals(a[1][1]) && a[0][2].equals(a[1][1]));
        return backSlashWins || forwardSlashWins;
       
    }

    boolean testColumn(int c, Integer i, Integer[][] a) {
        boolean isFull = a[0][c] != null && a[1][c] != null && a[2][c] != null;
        return isFull 
            && (a[0][c].equals(i) && a[1][c].equals(i) && a[2][c].equals(i));
    }

    boolean testRow(int row, Integer i, Integer[][] a) {
        boolean rowIsFull = a[row][0] != null && a[row][1] != null && a[row][2] != null;
        return rowIsFull 
            && (a[row][0].equals(i) && a[row][1].equals(i) && a[row][2].equals(i));
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
