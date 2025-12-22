public abstract class Pieces{ // Creates an abstract class for the pieces

    private String colour; 
    private String size;
    private int playerCol;

    Pieces(String colour, String size, int playerCol) // Constructor that initializes the instance variables
    {
        this.colour = colour;
        this.size = size;
        this.playerCol = playerCol;
    }

    public abstract void placePiece(GameBoard gameBoard); // Abstract method that passes the gameBoard as a paramater

    public int getPlayerCol() // Returns the playerCol variable to make it accessible in other classes
    {
        return this.playerCol;
    }
    
} // End of Pieces class
