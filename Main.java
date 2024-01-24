package reversi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        
        System.out.println("Welcome to Reversi!");  
        
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        int move, moveX, moveY, moveCount = 0;
        char sideInPlay;
        
        // players
        Player p1 = new Player('X');
        Player p2 = new Player('O');
        
        // board setup
        Board b1 = new Board();
        b1.generateBoard();
        sideInPlay = p1.getSide();
        
        // game loop
        while (true) {
            // sets hints 
            for (int i = 0; i < b1.getWidth(); i++) {
                for (int j = 0; j < b1.getHeight(); j++) {
                    if (b1.isValid(i, j, sideInPlay)) {
                        b1.setPiece('.', i, j);
                    }
                }
            }
            
            // displays board
            b1.showBoard();
            
            // receives move coords in two digits
            System.out.print("Make a move: ");
            move = Integer.parseInt(reader.readLine());
            
            // separates x and y values
            moveX = move / 10 - 1;
            moveY = move % 10 - 1;
            
            // checks if move falls within board
            if (!(b1.isOnBoard(moveX, moveY))) 
                System.out.println("Your move does not lie on the board.");
            
            // checks if move is not repeated
            else if (!(b1.isRepeated(moveX, moveY))) 
                System.out.println("Your move has already been made.");
            
            // checks if move is valid within board
            else if (!(b1.isValid(moveX, moveY, sideInPlay))) {
                System.out.println("Your move is invalid.");
            }
            
            // continues if all conditions are met
            else {
                // decides which player moves
                moveCount++;
                if (moveCount % 2 == 1) {
                    p1.makeMove(b1, moveX, moveY);
                    sideInPlay = p2.getSide();
                }
                else {
                    p2.makeMove(b1, moveX, moveY);
                    sideInPlay = p1.getSide();
                }
            }
            
            for (int i = 0; i < b1.getWidth(); i++) {
                for (int j = 0; j < b1.getHeight(); j++) {
                    if (b1.getPiece(i, j) == '.') {
                        b1.setPiece(' ', i, j);
                    }
                }
            }
        }
        
        
    }
    
}
