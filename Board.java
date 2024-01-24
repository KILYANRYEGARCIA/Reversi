package reversi;

import java.util.ArrayList;

public class Board {
    
    private char oppositeToken;
    private int width, height, testX, testY;
    private Token[][] pieces = new Token[8][8];
    private int[][] directions = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private int[][] initialPieces = {{-1, -1}, {0, -1}, {0, 0}, {-1, 0}};
    private ArrayList<Integer> flipTokens = new ArrayList<>();
    
    public Board() {
        width = 8;
        height = 8;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void generateBoard() {
        // creates a 2D array of Tokens
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++)
                pieces[x][y] = new Token(x, y);
        }
        // sets initial center pieces
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0)
                pieces[width / 2 + initialPieces[i][0]][height / 2 + initialPieces[i][1]].setSide('X');
            else
                pieces[width / 2 + initialPieces[i][0]][height / 2 + initialPieces[i][1]].setSide('O');
        }  
    }
    
    public void showBoard() {
        // prints status of Token array
        System.out.println("  1 2 3 4 5 6 7 8 ");
        for (int y = 0; y < height; y++) {
            System.out.print(y + 1);
            for (int x = 0; x < width; x++)
                System.out.print(" " + pieces[x][y].getSide());
            System.out.print("\n");
        }
    }
    
    // sets piece to given side
    public void setPiece(char s, int x, int y) {
        pieces[x][y].setSide(s);
    }
    
    public char getPiece(int x, int y) {
        return pieces[x][y].getSide();
    }
    
    // returns false if inputted move falls outside board & v.v.
    public boolean isOnBoard(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < width;
    }
    
    // returns false if inputted move is repeated & v.v. 
    public boolean isRepeated(int x, int y) {
        return pieces[x][y].getSide() == ' ';
    }
    
    // returns false if inputted move does not score
    public boolean isValid(int x, int y, char s) {
        flipTokens.clear();
        if (s == 'X') 
            oppositeToken = 'O';
        else
            oppositeToken = 'X';
        for (int i = 0; i < 8; i++) {
            testX = x;
            testY = y;
            x += directions[i][0];
            y += directions[i][1];
            while (x >= 0 && x < width && y >= 0 && y < width && pieces[x][y].getSide() == oppositeToken) {
                x += directions[i][0];
                y += directions[i][1];
                if (x >= 0 && x < width && y >= 0 && y < width && pieces[x][y].getSide() == s) {
                    while (true) {
                        x -= directions[i][0];
                        y -= directions[i][1];
                        if (x == testX && y == testY)
                            break;
                        flipTokens.add(10 * x + y);                                 
                    }
                }   
            }
        }
        return !flipTokens.isEmpty();
    }
}
