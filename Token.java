package reversi;

public class Token {
    
    private char side;
    private int xpos, ypos;
    
    public Token(int i, int j) {
        side = ' '; 
    }
    
    public void setSide(char s) {
        side = s;
    }
    
    public char getSide() {
        return side;
    }
    
    
}
