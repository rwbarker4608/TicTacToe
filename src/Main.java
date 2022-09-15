/**
 *Description
 * Our game is almost ready! Now let's combine what we’ve learned in the previous stages to make a game of tic-tac-toe
 * that two players can play from the beginning (with an empty grid) through to the end (until there is a draw, or one
 * of the players wins).
 *
 * The first player has to play as X and their opponent plays as O.
 * Objectives
 *
 * In this stage, you should write a program that:
 *
 *     Prints an empty grid at the beginning of the game.
 *     Creates a game loop where the program asks the user to enter the cell coordinates, analyzes the move for
 *     correctness and shows a grid with the changes if everything is okay.
 *     Ends the game when someone wins or there is a draw.
 *
 * You need to output the final result at the end of the game. Good luck!
 *
 * Example
 * The greater-than symbol followed by a space (> ) represents the user input. Note that it's not part of the input.
 *
 * Example 1.
 *
 * ---------
 * |       |
 * |       |
 * |       |
 * ---------
 * > 2 2
 * ---------
 * |       |
 * |   X   |
 * |       |
 * ---------
 * > 2 2
 * This cell is occupied! Choose another one!
 * > two two
 * You should enter numbers!
 * > 1 4
 * Coordinates should be from 1 to 3!
 * > 1 1
 * ---------
 * | O     |
 * |   X   |
 * |       |
 * ---------
 * > 3 3
 * ---------
 * | O     |
 * |   X   |
 * |     X |
 * ---------
 * > 2 1
 * ---------
 * | O     |
 * | O X   |
 * |     X |
 * ---------
 * > 3 1
 * ---------
 * | O     |
 * | O X   |
 * | X   X |
 * ---------
 * > 2 3
 * ---------
 * | O     |
 * | O X O |
 * | X   X |
 * ---------
 * > 3 2
 * ---------
 * | O     |
 * | O X O |
 * | X X X |
 * ---------
 * X wins
 *
 */

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {
    public static String toe[][] = new String[3][3];
    public static Scanner scanner = new Scanner(System.in);
    public static boolean xWin = false;
    public static boolean oWin = false;
    public static boolean empty = true;
    public static boolean game = true;
    public static boolean xTurn = true;
    public static boolean oTurn = true;
    public static int moveOne = 0;
    public static int moveTwo = 0;
    public static int emptyCount = 0;

    public static void main(String[] args) {
        fillGame();
        print();
        prompt();
    }


    public static void fillGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                toe[i][j] = " ";
            }
        }
    }

    public static void print() {
        System.out.print("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("\n|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + toe[i][j]);
            }
            System.out.print(" |");
        }
        System.out.println("\n---------");
    }

    public static void prompt() {
        while (game) {
            if (xTurn) {
                updateX();
                print();
                result();
            }
            if (oTurn) {
                updateO();
                print();
                result();
            }
        }

    }

    public static void updateX() {
        while (xTurn) {
            try {
                moveOne = scanner.nextInt();
                moveTwo = scanner.nextInt();
                if ((1 > moveOne || moveOne > 3) || (1 > moveTwo || moveTwo > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (toe[moveOne - 1][moveTwo - 1].equals("X") || toe[moveOne - 1][moveTwo - 1].equals("O")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    toe[moveOne-1][moveTwo -1] = "X";
                    xTurn = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
        oTurn = true;
    }
    public static void updateO() {
        while (oTurn) {
            try {
                moveOne = scanner.nextInt();
                moveTwo = scanner.nextInt();
                if ((1 > moveOne || moveOne > 3) || (1 > moveTwo || moveTwo > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (toe[moveOne - 1][moveTwo - 1].equals("X") || toe[moveOne - 1][moveTwo - 1].equals("O")) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    toe[moveOne-1][moveTwo -1] = "O";
                    oTurn = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }
        xTurn = true;
    }
    public static void checkEmpty() {
        emptyCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (toe[i][j].equals(" ")) {
                    emptyCount++;
                }
            }
        }
        if (emptyCount == 0) {
            empty = false;
        }
    }
    public static void gameOver() {
        xTurn =false;
        oTurn = false;
        game =false;
    }

    public static void result() {
        checkEmpty();
        for (int i = 0; i < 3; i++) {
            //Across row wins
            if ((toe[i][0].equals("X")) && (toe[i][1].equals("X")) && (toe[i][2].equals("X"))) {
                xWin = true;
            }
            if ((toe[i][0].equals("O")) && (toe[i][1].equals("O")) && (toe[i][2].equals("O"))) {
                oWin = true;
            }
            //Top to bottom wins
            if ((toe[0][i].equals("X")) && (toe[1][i].equals("X")) && (toe[2][i].equals("X"))) {
                xWin = true;
            }
            if ((toe[0][i].equals("O")) && (toe[1][i].equals("O")) && (toe[2][i].equals("O"))) {
                oWin = true;
            }
        }

        //Diagonal wins
        if ((toe[0][0].equals("X")) && (toe[1][1].equals("X")) && (toe[2][2].equals("X")) ||
                (toe[0][2].equals("X")) && (toe[1][1].equals("X")) && (toe[2][0].equals("X"))) {
            xWin = true;
        }
        if ((toe[0][0].equals("O")) && (toe[1][1].equals("O")) && (toe[2][2].equals("O")) ||
                (toe[0][2].equals("O")) && (toe[1][1].equals("O")) && (toe[2][0].equals("O"))) {
            oWin = true;
        }

        if (xWin && !oWin) {
            System.out.println("X wins");
            gameOver();
        }
        if (oWin && !xWin) {
            System.out.println("O wins");
            gameOver();
        }
        if (!xWin && !oWin && !empty) {
            System.out.println("Draw");
            gameOver();
        }
    }
}
