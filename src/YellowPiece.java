public class YellowPiece extends Pieces{ // The YellowPiece class inherits everything from its parent class Pieces

    // Uses the constructor in Pieces to initialize the instance variables
    YellowPiece(String colour, String size, int playerCol) 
    {
        super(colour, size, playerCol); 
    }

    public void placePiece(GameBoard gameBoard) // Overloaded method that places the piece in the board
    {
        boolean piecePlaced = false;
        for(int j=0;j<7;j++)
            if(j == super.getPlayerCol())
                for(int i=5;i>=0;i--)
                {
                    String[][] newBoard = gameBoard.getBoard(); // Gets the board from the GameBoard class and sets it equal to newBoard
                    if(newBoard[i][j] == "- ")
                    {
                        newBoard[i][j]="Y ";
                        gameBoard.setBoard(newBoard);
                        piecePlaced = true;
                    }
                    if(piecePlaced)
                        break;
                }
    }

} // End of YellowPiece class
