/*
ye * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package misc;

/**
 *
 * @author Jonathan
 */
import java.util.Scanner;

public class Poker_Old {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        do {
            round();
            System.out.println("Do you want to continue playing?");
        } while (!scan.next().equals("no"));

    }

    public static void round() {

        dealCards();

        evaluateHands();

        displayResults();

    }

    private static void dealCards() {
    }

    private static void evaluateHands() {
    }

    private static void displayResults() {
    }
}
