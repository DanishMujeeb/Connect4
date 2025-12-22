import java.util.Arrays;
public class GameBoard { // Start of GameBoard class

    private boolean player1;
    private boolean player2; 
    private String colour;
    private String[][] board;

    GameBoard(String colour, int rows, int cols) // Constructor that initializes the instance variables
    {
        this.colour = colour;
        this.board = new String[rows][cols];
    }

    public void displayGameBoard() // This method displays the Gameboard
    {
        for(int i=0;i<6;i++) 
        {
            for(int j=0;j<7;j++)
                System.out.print(board[i][j]);
            System.out.println();
        }
    }
    
    public void checkWinner() // This method checks if a player has gotten a connect 4
    {
        // The following code checks if a player has gotten a connect 4 vertically
        for(int i=0;i<3;i++)
            for(int j=0;j<7;j++)
            {
                if(board[i][j].equals("R ") && board[i+1][j].equals("R ") && board[i+2][j].equals("R ") && board[i+3][j].equals("R "))
                    player1=true;
                else if(board[i][j].equals("Y ") && board[i+1][j].equals("Y ") && board[i+2][j].equals("Y ") && board[i+3][j].equals("Y "))//checks if player2 gets a connect 4 vertically
                    player2=true;
            }

        // The following code checks if a player has gotten a connect 4 horizontally
        for(int i=0;i<6;i++)
            for(int j=0;j<4;j++)
            {
                if(board[i][j].equals("R ") && board[i][j+1].equals("R ") && board[i][j+2].equals("R ") && board[i][j+3].equals("R "))
                    player1 = true;
                else if(board[i][j].equals("Y ") && board[i][j+1].equals("Y ") && board[i][j+2].equals("Y ") && board[i][j+3].equals("Y "))
                    player2 = true;
            }

        // The following code checks if a player has gotten a connect 4 diagonally (descending)
        for(int i=0;i<3;i++)
            for(int j=0;j<4;j++)
            {
                if(board[i][j].equals("R ") && board[i+1][j+1].equals("R ") && board[i+2][j+2].equals("R ") && board[i+3][j+3].equals("R "))
                    player1 = true;
                else if(board[i][j].equals("Y ") && board[i+1][j+1].equals("Y ") && board[i+2][j+2].equals("Y ") && board[i+3][j+3].equals("Y "))
                    player2 = true;
            }

        // The following code checks if a player has gotten a connect 4 diagonally (ascending)
        for(int i=0;i<3;i++)
            for(int j=3;j<7;j++)
            {
                if(board[i][j].equals("R ") && board[i+1][j-1].equals("R ") && board[i+2][j-2].equals("R ") && board[i+3][j-3].equals("R "))
                    player1=true;
                else if(board[i][j].equals("Y ") && board[i+1][j-1].equals("Y ") && board[i+2][j-2].equals("Y ") && board[i+3][j-3].equals("Y "))
                    player2=true;
            }
    }

    public String[][] getBoard() // This method returns the board
    {
        return board;
    }

    public void setBoard(String[][] board) // This method sets the board instance variable with the parameter that is passed
    {
        this.board = board;
    }

    public boolean checkPlayerOne() // Returns the current status of player1
    {
        return player1;
    }

    public boolean checkPlayerTwo() // Returns the current status of player2
    {
        return player2;
    }

    public void reset() // Resets the gameboard to its original state and sets player1 and player2 to false
    {
        for(int i=0;i<board.length;i++)
            for(int j=0;j<board[i].length;j++)
                board[i][j] = "- "; // Clears the gameboard
        player1 = false;
        player2 = false;
    }

}// End of GameBoard class