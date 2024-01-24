package reversi;

public class Player {
    
    private int score;
    private boolean isUser;
    private char side;
    
    public Player(char s) {
        score = 2;
        isUser = true;
        side = s;
    }
    
    // captures opponent's piece
    public void makeMove(Board b, int x, int y) {
        b.setPiece(side, x, y);
    }
    
    public char getSide() {
        return side;
    }
}
