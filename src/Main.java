import java.util.*; // Allows us to get input from the user
import java.io.*; // Allows us to read and write to file
public class Main { // Start of main class
    
  public static Map<String, Integer> leaderboard = new HashMap<String, Integer>();

  public static void main(String[] args) { 

    homepage(); // Calls the homepage method 
    
  }// End of main function

  public static void homepage() // This method asks the user whether they would like to play the game or view the leaderboard
  {
    Scanner input = new Scanner(System.in);
    String player1 = "";
    String player2 = "";
    int usersDecision = 0;
    System.out.println("What would you like to do (Enter 1, 2 or 3)");
    System.out.println();
    System.out.println("1. Play Connect 4 (Multiplayer)");
    System.out.println("2. View Users Wins");
    System.out.println("3. Quit");
    System.out.println();

    try{
    
      usersDecision = Integer.parseInt(input.nextLine());
      if(usersDecision == 1) // Runs if the user enters 1
      {
        System.out.println();
        System.out.println("Player 1, what is your name");
        player1 = input.nextLine();
        System.out.println("Player 2, what is your name");
        player2 = input.nextLine();
        System.out.println();
        System.out.println(player1+" you will go first and be red");
        System.out.println(player2+" you will go second and be yellow");
        game(player1, player2); // Calls the game function and passes the players names as parameters
      }

      else if(usersDecision == 2) // Runs if the user enters 2
      {
        File file = new File("leaderboard.txt"); // Reads the contents from the leaderboard file
        Scanner in = new Scanner(file);
        while(in.hasNext())
          leaderboard.put(in.next(), Integer.parseInt(in.next())); // Adds key value pairs to the leaderboard map
        
        if(leaderboard.size() == 0)
        {
          System.out.println();
          System.out.println("Nobody has played the game yet");
          System.out.println();
          homepage();
        }
        else
        {
          System.out.println();
          for(Map.Entry<String, Integer> set : leaderboard.entrySet())
              System.out.println("Name: "+set.getKey()+" "+"Wins: "+set.getValue()+" "); // Displays the contents of the leaderboard map
          System.out.println();
          homepage();
        }
      }

      else if(usersDecision == 3)
        System.exit(1);
      
      else
      {
        System.out.println();
        homepage(); // Calls the homepage method when the user enters an invalid input
      }

    }catch(Exception e) // Catches any errors
    {
      System.out.println();
      homepage();
    }
  }

  public static void game(String player1, String player2) // This method will run the game and will create both the game board and the pieces
  {
    int turns = 0;
    int playerCol = 0;
    Pieces[] piece = new Pieces[2];
    GameBoard gameBoard = new GameBoard("blue", 6, 7); // Creates a game board object using the GameBoard class and passes the colour, rows and cols  
    System.out.println();
    gameBoard.reset(); // Calls the reset method in class GameBoard
    gameBoard.displayGameBoard(); // Calls the displayGameBoard method in the GameBoard class
    System.out.println();

    while(turns<42) // Will run so long as turns is less than 42
    {
      String[][] updatedGameBoard = gameBoard.getBoard(); // Assigns what getBoard returns to updateGameBoard

      try{

        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println(player1+" which column do you want to place your piece (the column on the far left is 1, to the right of that is 2, etc.)");
        playerCol = Integer.parseInt(input.nextLine());
        playerCol--;
        System.out.println();
        if(playerCol>6 || playerCol<0)
          System.out.println("Sorry that is an invalid input, as a result you lose your turn");

        else
        {
          piece[0] = new RedPiece("Red", "3cm", playerCol); // Creates a red piece object and passes its colour, size and location
          piece[0].placePiece(gameBoard); // Calls the placePiece method in the RedPiece class
          gameBoard.displayGameBoard(); // Calls the displayGameBoard method in the GameBoard class
          System.out.println();
          turns++;
          gameBoard.checkWinner(); // Calls the checkWinner method form the GameBoard class
          
          if(gameBoard.checkPlayerOne()) // Checks if player1 in class GameBoard is equal to true
          {
            System.out.println();
            System.out.println("Congratulations "+player1+" you won the game");
            System.out.println();
            if(leaderboard.keySet().contains(player1))
              leaderboard.put(player1, leaderboard.get(player1)+1); // Adds one to a player's wins, which are the values in the map
            else
              leaderboard.put(player1, 1); // Adds a key value pair to the map with the player's name as the key and 1 as its value
            if(!leaderboard.keySet().contains(player2))
              leaderboard.put(player2, 0); // Adds a key value pair to the map with the player's name as the key and 0 as its value
            gameBoard.reset();

            PrintWriter writer = new PrintWriter("leaderboard.txt"); // Allows us to write to the leaderboard file
            for(Map.Entry<String, Integer> set : leaderboard.entrySet())
              writer.print(set.getKey()+" "+set.getValue()+" "); // Writes the contents of the map to the file
            writer.close();
            homepage();
          }
        }

      }catch(Exception e) // Catches any errors
      {
        System.out.println("Sorry that's an invalid input, as a result you lose your turn");
      }

      try{

        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println(player2+" which column do you want to place your piece (the column on the far left is 1, to the right of that is 2, etc.)");
        playerCol = Integer.parseInt(input.nextLine());
        playerCol--;
        System.out.println();
        if(playerCol>6 || playerCol<0)
          System.out.println("Sorry that is an invalid input, as a result you lose your turn");

        else
        {
          piece[1] = new YellowPiece("Yellow", "3cm", playerCol); // Creates an object for the yellow piece and passes its colour, size and location 
          piece[1].placePiece(gameBoard); // Calls the placePiece function in the YellowPiece class
          gameBoard.displayGameBoard(); // Calls the displayGameBoard function in the GameBoard class
          System.out.println();
          turns++;
          gameBoard.checkWinner(); // Calls the checkWinner method form the GameBoard class

          if(gameBoard.checkPlayerTwo()) // Checks if player2 in class GameBoard is equal to true
          {
            System.out.println();
            System.out.println("Congratulations "+player2+" you won the game");
            System.out.println();
            if(leaderboard.keySet().contains(player2))
              leaderboard.put(player1, leaderboard.get(player2)+1); // Adds one to a player's wins, which are the values in the map
            else
              leaderboard.put(player2, 1); // Adds a key value pair to the map with the player's name as the key and 1 as its value
            if(!leaderboard.keySet().contains(player1))
              leaderboard.put(player1, 0); // Adds a key value pair to the map with the player's name as the key and 0 as its value
            gameBoard.reset();

            PrintWriter writer = new PrintWriter("leaderboard.txt"); // Allows us to write to the leaderboard file
            for(Map.Entry<String, Integer> set : leaderboard.entrySet())
              writer.print(set.getKey()+" "+set.getValue()+" "); // Writes the contents of the map to the file
            writer.close();
            homepage();
          }
        }

      }catch(Exception e) // Catches any errors
      {
        System.out.println("Sorry that's an invalid input, as a result you lose your turn");
      }
    }

    System.out.println();
    System.out.println("Looks like it was a draw");
    System.out.println();
    homepage(); // Calls the homepage function
  } 
  
} // End of main class
