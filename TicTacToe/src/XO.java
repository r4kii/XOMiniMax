import java.util.*;

public class XO {

    static MiniMaxForCpuMove cm = new MiniMaxForCpuMove();
    static char player = 'X';
    static char cpu = 'O';

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 5; i += 2)
        {
            for (int j = 0; j < 5; j += 2)
            {
                if (board[i][j] == ' ')  return true;
            }
        }
        return false;
    }

    public static boolean checkValidPos(int pos,char[][] gameBoard){
        int []rc = returnAddress(pos);
        return gameBoard[rc[0]][rc[1]] == ' ';
    }

    public static int [] returnAddress(int pos){
        int row = pos/3 +pos/3;
        int col;
        if(pos%3==0) col= pos%3;
        else col =pos%3 +pos%3;
        return new int[]{row,col};
    }

    public static void placePiece(char[][]gameBoard, int pos, char turn ){

        int[] rc = returnAddress(pos);
        gameBoard[rc[0]][rc[1]]= turn;

    }

    public static void placePiece(char [][]gameBoard, int []pos, char turn){

        gameBoard[pos[0]][pos[1]]=turn;

    }

    public static String checkWinner(char[][] gameBoard) {
        int result = cm.evaluate(gameBoard);
        if(result==-10){
            printGameBoard(gameBoard);
            System.out.println("You Win..Hurray");
            return "player";
        }
        else if(result==10){
            printGameBoard(gameBoard);
            System.out.println("You Lose :(");
            return "cpu";
        }
        else if(result==0 && !isMovesLeft(gameBoard)){
            //printGameBoard(gameBoard);
            System.out.println("TIE!!...");
            return "TIE";
        }
        return "";
    }

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

         printGameBoard(gameBoard);

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("Enter your placements (0-8)");
            int playerPos = scan.nextInt();
            while(!checkValidPos(playerPos,gameBoard)){
                System.out.println("Position Already Taken\n Enter a diff position:)");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, player);

            String result = checkWinner(gameBoard);
            if (result.length() > 0) {
                break;
            }

            int []cpuPos = cm.findCpuMove(gameBoard);
            placePiece(gameBoard, cpuPos, cpu);

            result = checkWinner(gameBoard);
            if (result.length() > 0) {
                break;
            }
            printGameBoard(gameBoard);
        }
    }
}

