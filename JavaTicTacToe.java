import java.util.*;
class JavaTicTacToe{

public static Scanner UserInputS = new Scanner(System.in);
public static Scanner UserInputI = new Scanner(System.in);

public static int[] move = new int[2];
public static void main(String [] args){//main method declaration
    

    char[][] board = {{'|', '-','-','-','-','-', '+' ,'-','-','-','-','-', '+', '-','-','-','-','-', '+', '-','-','-','-','-', '|'}, 
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'}, 
    {'|', ' ',' ','_',' ',' ', '|' ,' ',' ','1',' ',' ', '|', ' ',' ','2',' ', ' ', '|', ' ',' ','3',' ',' ', '|'},
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', '-','-','-','-','-', '+' ,'-','-','-','-','-', '+', '-','-','-','-','-', '+', '-','-','-','-','-', '|'}, 
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'}, 
    {'|', ' ',' ','a',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', '-','-','-','-','-', '+' ,'-','-','-','-','-', '+', '-','-','-','-','-', '+', '-','-','-','-','-', '|'},
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'}, 
    {'|', ' ',' ','b',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', '-','-','-','-','-', '+' ,'-','-','-','-','-', '+', '-','-','-','-','-', '+', '-','-','-','-','-', '|'},
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'}, 
    {'|', ' ',' ','c',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', ' ',' ',' ',' ',' ', '|' ,' ',' ',' ',' ',' ', '|', ' ',' ',' ',' ', ' ', '|', ' ',' ',' ',' ',' ', '|'},
    {'|', '-','-','-','-','-', '+' ,'-','-','-','-','-', '+', '-','-','-','-','-', '+', '-','-','-','-','-', '|'}};

    board[2][9] = '1';
    board[2][15] = '2';
    board[2][21] = '3';
    board[6][3] = 'a';
    board[10][3] = 'b';
    board[14][3] = 'c';

    boolean [][] playerPos = new boolean [4][4];
    boolean [][] comPos = new boolean [4][4];
    boolean [][] pos = new boolean [4][4];
 
    setFalse(playerPos); //filling 2D array with false values
    setFalse(comPos);
    setFalse(pos);

    gameLoop(playerPos, comPos, pos, board);

    System.out.println("game over");
}

public static void gameLoop(boolean playerPos[][], boolean comPos[][], boolean pos[][], char[][] board) {

    int count = 0;
    boolean won = false;

    while (won == false) {

        comLoop(comPos, pos, board, playerPos); 
        count++;
        printBoard(board);

        if (count == 9) {
            System.out.println("draw!");
            won = true;
            break;
        }

        if (isWon(comPos)) {
            System.out.println("computer wins!");
            won = true;
            break;
        }

        userLoop(pos, playerPos, board);
        count++;
        printBoard(board);

        if (isWon(playerPos)) {
            System.out.println("player wins!");
            won = true;
            break;
        }         

    }

    }


public static void userLoop(boolean pos [][], boolean playerPos [][], char board [][]){


    System.out.println("What row would you like to place your piece? (a-c)");
    int row = getRow();

    System.out.println("What collum would you like to place your piece? (1-3)");
    int col = getCol();

    if (!(isOccupied(pos, row, col))) {
        pos[row][col] = true;
        playerPos[row][col] = true;
        placePeg(row, col, board, "player"); //for player
        
        

    } else if ((isOccupied(pos, row, col))) {

        System.out.println("Error this position is already occupied, please try again.");
        userLoop(pos, playerPos, board);

    }
}

public static void comLoop(boolean comPos[][], boolean pos [][], char board [][], boolean playerPos [][]){


    int comRow = getOpValue();  //if optimal value cant be found an 'optimal value' will be randomly generated
    int comCol = getOpValue();
  

    if (winningMove(playerPos, pos)){ //checking if the player can win on the next turn (defense is priority)
        comRow = move[0];
        comCol = move[1];
        System.out.println("blocked ");
    }

    if (winningMove(comPos, pos)){ //checking if the computer can win on the next turn
        comRow = move[0];
        comCol = move[1];
        System.out.println("Win!! ");
    }


    
    //System.out.println("comRow: " + comRow + " comCol: " + comCol);

    if (!(isOccupied(pos, comRow, comCol))) {
        pos[comRow][comCol] = true;
        comPos[comRow][comCol] = true;
        placePeg(comRow, comCol, board, "com"); //for computer
        
    } 

    else{
        comLoop(comPos, pos, board, playerPos);
    }
}


public static void printBoard(char board[][]) {

    for (char[] row: board) {
        for (char collum: row) {
            System.out.print(collum); //not println 
        }
        System.out.println();
    }

}

public static void placePeg(int r, int c, char board[][], String user) {

    r = convRow(r);
    c = convCol(c);

    if (user == "player")
        board[r][c] = 'x';

    if (user == "com")
        board[r][c] = 'o';

}


public static int getOpValue() {

    int random = (int)(Math.random() * 3) + 1;

    return random;
}

public static int convRow(int r) {

    switch (r) {

        case 1:
            r = 6;
            break;

        case 2:
            r = 10;
            break;

        case 3:
            r = 14;
            break;
    }

    return r;
}

public static int convCol(int c) {

    switch (c) {

        case 1:
            c = 9;
            break;

        case 2:
            c = 15;
            break;

        case 3:
            c = 21;
            break;
    }

    return c;
}

public static boolean isOccupied(boolean arr[][], int r, int c) {

    if (arr[r][c] == true)
        return true;

    else
        return false;

}
public static void setFalse(boolean array[][]) {

    for (int i = 0; i < array.length; i++) {
        for (int j = 0; j < array.length; j++) {
            array[i][j] = false;
        }
    }
}

public static int getRow() {

    String input;
    int convNum = -1;

        input = UserInputS.next();

        if (!(input.equalsIgnoreCase("a") || input.equalsIgnoreCase("b") || input.equalsIgnoreCase("c"))) {

        System.out.println("Error, invalid input!\nPlease input a valid row");
        return getRow();
       }

    switch (input) {

        case "a":
            convNum = 1;
            break;

        case "b":
            convNum = 2;
            break;

        case "c":
            convNum = 3;
    }

    return convNum;
}



public static int getCol() {

    int input;

        input = UserInputI.nextInt();
        if (input > 3) {
            System.out.println("Error, invalid input!\nPlease input a valid collumn");
            //UserInputI.nextInt();
            return getCol();

        }

    return input;
}


public static boolean isWon(boolean curr[][]) {


    if (curr[1][1] && curr[1][2] && curr[1][3]) //checking horizontal wins
        return true;

    if (curr[2][1] && curr[2][2] && curr[2][3])
        return true;

    if (curr[3][1] && curr[3][2] && curr[3][3])
        return true;


    if (curr[1][1] && curr[2][1] && curr[3][1]) //checking vertical wins
        return true;

    if (curr[1][2] && curr[2][2] && curr[3][2])
        return true;

    if (curr[1][3] && curr[2][3] && curr[3][3])
        return true;


    if (curr[1][1] && curr[2][2] && curr[3][3])
        return true;

    if (curr[1][3] && curr[2][2] && curr[3][1])
        return true;

    else
        return false;

}

public static boolean winningMove(boolean curr[][], boolean pos[][]){

    boolean win = false;

    for (int i = 1; i < 4; i++){

        if (curr[i][1] && curr[i][2] && (!(isOccupied(pos, i, 3)))){ //checking for horizontal wins
            move[0] = i;
            move[1] = 3;
            win = true;
            break;
        }

        if (curr[i][2] && curr[i][3]&& (!(isOccupied(pos, i, 1)))){ //checking for horizontal wins
            move[0] = i;
            move[1] = 1;
            win = true;
            break;
        }

        if (curr[i][1] && curr[i][3]&& (!(isOccupied(pos, i, 2)))){ //checking for horizontal wins
            move[0] = i;
            move[1] = 2;
            win = true;
            break;
        }

        if (curr[1][i] && curr[2][i] && (!(isOccupied(pos, 3, i)))){ //checking for vertical wins
            move[0] = 3;
            move[1] = i;
            win = true;
            break;
        }

        if (curr[2][i] && curr[3][i]&& (!(isOccupied(pos, 1, i)))){ //checking for vertical wins
            move[0] = 1;
            move[1] = i;
            win = true;
            break;
        }

        if (curr[1][i] && curr[3][i]&& (!(isOccupied(pos, 2, i)))){ //checking for vertical wins
            move[0] = 2;
            move[1] = i;
            win = true;
            break;
        }
    }

    if (curr[1][1] && curr[2][2]&& (!(isOccupied(pos, 3, 3)))){ //checking for diagnoal wins
        move[0] = 3;
        move[1] = 3;
        win = true;
    }

    if (curr[3][3] && curr[2][2]&& (!(isOccupied(pos, 1, 1)))){ //checking for diagnoal wins
        move[0] = 1;
        move[1] = 1;
        win = true;
    }

    if (curr[3][1] && curr[2][2]&& (!(isOccupied(pos, 1, 3)))){ //checking for diagnoal wins
        move[0] = 1;
        move[1] = 3;
        win = true;
    }

    if (curr[1][3] && curr[2][2]&& (!(isOccupied(pos, 3, 1)))){ //checking for diagnoal wins
        move[0] = 3;
        move[1] = 1;
        win = true;
    }

    if ( (curr[1][1] && curr[3][3] || curr[1][3] && curr[3][1]) && (!(isOccupied(pos, 2, 2)))){ //checking the center piece
        move[0] = 2;
        move[1] = 2;
        win = true;
    }

    return win;

}


}

