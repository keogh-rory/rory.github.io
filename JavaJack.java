import java.util.*;
import java.io.*;
/*
 *Program Name: Rory Keogh 
 *Program Date: May 4th 2019
 *Program Description: Black Jack mini game, that tracks user progress and allows the user to play against a simulated dealer
*/
public class JavaJack {
	
	static int hand;
	static int dealerHand;
	static int dealerHand2;
	static int card1;
	static int card2;
	static int cardA;
	static int Pwin;
	static int Dwin;
	static boolean draw;
	
	static Scanner UserInput = new Scanner(System.in);

	public static void main(String args[]) throws IOException, InterruptedException { //declaring main method
		
		menu(); //running menu method

	}
	
	
	public static void menu() throws IOException {
		
		int deck[] = {1,2,3,4,5,6,7,8,9,10}; //defining deck array which cards will be selected from 
		
		try {// try catch to catch if the user inputs a character as opposed to a number
		
			do {
				
				System.out.println("Welcome to the java black jack game, enter 1 to play, 2 to reset game data or 3 for instructions ");
				
					int menuChoice = UserInput.nextInt(); //Receiving integer from user for menu choice
					
					if (menuChoice == 1) { //menu option for when the user inputs 1
						
						do { //starting do while loop to play the game

							hand = drawTwo(deck);//defining the public hand variable as the result of the drawTwo method
							int dealersHand = dealer(deck); //defining the dealers hand variable as the result of the dealer method
							
							System.out.println("The dealer shows his first card which is " + cardA); //out printing to console the first card of the dealer
							
							int playerHand = PlayCards(deck,hand); //setting the playerHand to the result of the PlayCards method
			
							game(dealersHand, playerHand); //running the game method, which calculates who won the game
							
							System.out.println("would you like to play again (y/n)"); 
			
			
						} while (UserInput.next().equalsIgnoreCase("y")); //defining the event to continue the do while loop
		
						stat(false); //after the user chooses not to play again, the stat method will be run and the game data will be outputted to the user
						System.out.println(" ");
						System.out.println("return to menu? (y/n)");
			
					}
			
					if (menuChoice == 2) { //when the user inputs menu option 2
						System.out.println("Are you sure you would you like to reset game data? (y/n) ");
				
						if (UserInput.next().equalsIgnoreCase("y")) { //if the user chooses to reset game data 
							
							stat(true); //run the stat method with the boolean parameter set to true, so it will reset the files 
							System.out.println("Success! the game data has been cleared");
						}
				
						System.out.println("return to menu? (y/n)");
					}
			
					if (menuChoice == 3) { //presenting user with menu option 3 which simply outputs the game instructions
						instruct();//running the instruct method which pulls instructions from a text file
					}
					if (menuChoice != 1 && menuChoice != 2 && menuChoice != 3) { //bug fix 1, prevents user from entering a menu choice out of bounds
						System.out.println("Input out of bounds, would you like to return to menu? (y/n)");
					}
					
				}while (UserInput.next().equalsIgnoreCase("y"));
		}
					
				catch(InputMismatchException | InterruptedException e) {//catching invalid input 
					System.out.println("Invalid input!");
				}
	}
	/*
	 * The instruct method reads in from instruct.txt and outputs 
	 * the information to the console 
	 */
	public static void instruct() throws FileNotFoundException, InterruptedException {
		
		File instructions = new File("instruct.txt"); //creating file object	
		Scanner read = new Scanner(instructions); //creating scanner object for text file input 
		
		while (read.hasNextLine() == true) {
			String line = read.nextLine();
			System.out.println(line);
			Thread.sleep(1000);	//setting a 1 second delay between each line
		}
		read.close();//closing scanner
		System.out.println(" ");
		System.out.println("Would you like to return to the main menu? (y/n)");
	}
		
	/*
	 * The game method compares the dealers hand value to the players hand value to determine 
	 * who has one each individual game
	 */
	public static void game(int dealersHand, int playerHand) throws IOException {
		
		boolean game = true; //setting boolean so that once it has been determined a game is lost, the method won't continue
		
		if (playerHand == 0) { //checking if player is bust 
			System.out.println("You bust, dealer wins!");
			game = false;
			Dwin ++; //incrementing the Dwin counter
			
		}
		if (dealersHand == 0 && game == true) {// checking if dealer is bust if player busts first game is over regardless of dealers hand (*game favours the computer*)
			System.out.println("The dealer Busts! player wins, the final value of his hand was " + dealerHand2);
			game = false;
			Pwin++; //incrementing the Pwin counter
			
		}
		
		if (dealersHand > playerHand && game == true) { //if the dealers hand is greater than the players and the game is still running
			System.out.println("Your hand had a value of " + playerHand + " the dealers hand had a value of " + dealersHand);
			System.out.println("dealer wins! ");
			game = false;
			Dwin++;
			
		}
		if (dealersHand < playerHand && game == true) {//if the players hand is greater than the dealers and the game is still running
			System.out.println("Your hand had a value of " + playerHand + " the dealers hand had a value of " + dealersHand);
			System.out.println("player wins! ");
			game = false;
			Pwin++;
			
		}
		if (game) { //if the game is still true then a draw must be the conclusion 
			System.out.println("Your hand had a value of " + playerHand + " the dealers hand had a value of " + dealersHand);
			System.out.println("Draw!");
			draw = true;
		}
	}
	
	/*
	 * The draw method selects a number between the length of the deck array
	 * and 0, this number corresponds to a value in the deck array which becomes
	 * the players card
	 */
	public static int draw(int d []) {
		
		Random r = new Random(); //creating a random object, r
		int cardIndex = r.nextInt(d.length) + 0; // + 0 because arrays start at 0 
		int card = d[cardIndex]; //setting the card value equal to the value of the cardIndex number in the deck array
		return card;
	}
	
	/*
	 * The drawTwo method sets card1 and card2 to the result of each draw method runs and returns the total 
	 * hand value
	 */
	public static int drawTwo(int d[]) throws InterruptedException {
		
		card1 = draw(d);
		card2 = draw(d);
		
		//moved this from PlayCards method
		System.out.println("Your first card is valued at " + card1 + " and your second card is valued at " + card2); //outputting hand info
		Thread.sleep(1000); //delaying output by one second
		System.out.println("Your current hand is valued at " + (card1 + card2)); //outputting the total value of the hand 
		Thread.sleep(1000);

		
		int total = card1 + card2;
		
		return total;
	}
	
	/*
	 * The PlayCards method outputs the value of the users hand
	 * and it offers the user the choice of "sticking" or
	 * "hitting" (receiving another card)
	 */
	public static int PlayCards(int d[], int h) throws InterruptedException, IOException{
		
		int choice;
		//removed the card output from here and moved it to the drawTwo method
		System.out.println("Hit (1) Stand (2) "); 
		choice = UserInput.nextInt(); //Receiving choice weather or not the user wants another card
		
		//*bug fix 2* removed try catch here and added out of bounds if check 
		
		if (choice == 1) { //if the user decides to take a new card
			int nextCard = draw(d); //the integer newCard will be set to the result of the draw method
			hand = hand + nextCard; //the hand value will be updated
			
			Thread.sleep(1000);
			System.out.println("the card you drew has a value of..." + nextCard);
			
			
			//*added this line to update user
			System.out.println("Your current hand has a value of " + hand);
			
			
			boolean bust = bustCheck(hand); //checking to see if the users hand is bust by running the bustCheck method
			
			if (bust) { //when player busts set hand value to zero
				hand = 0;
		
			}
			else{ //if the player is still eligible to continue the game rerun the method
			PlayCards(d,hand); 
			}
		}
		
		if (choice == 2) { //if the user chooses to stick
			System.out.println("Sticking on this hand!");
		}
		if (choice != 1 && choice != 2){ //if the user inputs a number out of bounds
			System.out.println("error invalid input... sticking on this hand ");
		}
		return hand;
	}
	
	/*
	 * the bustCheck method, 
	 * receives the hand value within it's parameters 
	 * and checks if the hand is above 21 and returns a boolean variable 
	 */
	public static boolean bustCheck(int t) {
		
		boolean bust = false;
		
		if (t > 21) {
			bust = true;
		}
			return bust;
	}
	
	/*
	 * This method models a dealer player
	 * this "bot" will take a new card when the total is 16 or lower
	 * if the total is 17 or higher the computer will stick on its hand 
	 */
	public static int dealer(int [] d) {
		
		cardA = draw(d);
		int b = draw(d);
		int dealersHand = cardA + b;
		
		while (dealersHand < 17) {
			dealersHand = dealersHand + draw(d);
		}
			boolean dealerBust = bustCheck(dealersHand);
			
				if (dealerBust) {
					dealerHand2 = dealersHand;
					dealersHand = 0;
					
				}
						return dealersHand;
	}
	
	/*
	 * The stat file saves game data to a text file and pull from it
	 * to update a current win percentage, if the user wishes the saved text file can be deleted 
	 * so that the percentage starts again at 1
	 */
	public static void stat(boolean reset) throws IOException {
		
		File winFile = new File("win.txt"); //creating a file object called winFile
		FileWriter fw = new FileWriter(winFile, true); //creating a filewriter object called fw also appends the file as boolean is set to true
		PrintWriter pw = new PrintWriter(fw); //creating a printwriter object called pw 
		int a = 0;
		
		Scanner winScan = new Scanner(winFile); //creating scanner object 
	
			while (winScan.hasNextInt()) { //reading in from file
				a = winScan.nextInt();
			}
		
			int totalWins = a + Pwin; //setting the total wins (including wins from .txt file)
		
		if (Pwin > 0 && reset == false) { //updating text file with new total wins
		pw.println(totalWins);
		pw.close();
		}
		
		File totalFile = new File("total.txt"); //creating another file object
		FileWriter fw2 = new FileWriter(totalFile, true); //creating a filewriter object and setting append parameters to true
		PrintWriter pw2 = new PrintWriter(fw2); //creating printwriter object
		int b = 0;
		
		Scanner gameScan = new Scanner(totalFile);
		
		while (gameScan.hasNextInt()) { //reading in total games played
			b = gameScan.nextInt();
		}
		
			int total = (Pwin + Dwin); //calculating total games played (of this session)
			int totalGames = b + total; //updating total games played
			
			
			if (total > 0 && reset == false) {
			pw2.println(totalGames);
			pw2.close();
			}
			
			/* reseting counters
			 */
			Dwin = 0; 
			Pwin = 0;
			
			if (reset == true) { //reseting data files
				totalFile.delete(); //deleting total.txt file
				Pwin = 0; //reseting counters
				Dwin = 0;
			}
			
			if (reset == true) {
				winFile.delete(); //deleting win.txt file
			}
			
			if (reset == false) { //providing the user does not want to reset the files the win percentage will be calculated and data information outputted to console
			double winPercentage = Math.round((totalWins * 100.00) / totalGames); //calculating win percentage 
			System.out.println(" ");
			System.out.println("The total number of games played is: " + totalGames);
			System.out.println("The total number of games won by the user is: " + totalWins);
			System.out.println("The user win percentage currently stands at: " + winPercentage + "%");
			}
			
	}
}
