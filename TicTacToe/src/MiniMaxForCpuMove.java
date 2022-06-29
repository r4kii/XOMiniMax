public class MiniMaxForCpuMove {
    public int evaluate(char[][] gameBoard) {

        for (int row = 0; row < 5; row += 2) {
            if ((gameBoard[row][0] == gameBoard[row][2]) &&
                    (gameBoard[row][2] == gameBoard[row][4])) {
                if (gameBoard[row][0] == XO.cpu)
                    return +10;
                else if (gameBoard[row][0] == XO.player)
                    return -10;
            }
        }
        for (int col = 0; col < 5; col += 2) {
            if ((gameBoard[0][col] == gameBoard[2][col]) &&
                    (gameBoard[2][col] == gameBoard[4][col])) {
                if (gameBoard[0][col] == XO.cpu)
                    return +10;

                else if (gameBoard[0][col] == XO.player)
                    return -10;
            }
        }
        if ((gameBoard[0][0] == gameBoard[2][2]) && (gameBoard[2][2] == gameBoard[4][4])) {
            if (gameBoard[0][0] == XO.cpu)
                return +10;
            else if (gameBoard[0][0] == XO.player)
                return -10;
        }
        if ((gameBoard[0][4] == gameBoard[2][2]) && (gameBoard[2][2] == gameBoard[4][0])) {
            if (gameBoard[0][4] == XO.cpu)
                return +10;
            else if (gameBoard[0][4] == XO.player)
                return -10;
        }
        return 0;
    }

    /*MiniMax Algorithm to find Cpu's Move. Cpu is the maximizing player.
    * Cpu assumes the other player to be optimal*/
    public int miniMax(char[][] gameBoard, boolean isMax, int depth) {

        if (evaluate(gameBoard) == 10)
            return 10 - depth;
        else if (evaluate(gameBoard) == -10)
            return depth - 10;
        else if (!XO.isMovesLeft(gameBoard))
            return 0;

        if (isMax) {
            int bestVal = Integer.MIN_VALUE;
            for (int i = 0; i < 5; i += 2) {
                for (int j = 0; j < 5; j += 2) {
                    if (gameBoard[i][j] == ' ') {
                        gameBoard[i][j] = XO.cpu;
                        bestVal = Math.max(bestVal, miniMax(gameBoard, !isMax, depth + 1));
                        gameBoard[i][j] = ' ';
                    }
                }
            }
            return bestVal;
        } else {
            int bestVal = Integer.MAX_VALUE;
            for (int i = 0; i < 5; i += 2) {
                for (int j = 0; j < 5; j += 2) {
                    if (gameBoard[i][j] == ' ') {
                        gameBoard[i][j] = XO.player;
                        bestVal = Math.min(bestVal, miniMax(gameBoard, !isMax, depth + 1));
                        gameBoard[i][j] = ' ';
                    }
                }
            }
            return bestVal;
        }

    }

    public int[] findCpuMove(char[][] gameBoard) {
        int bestVal = Integer.MIN_VALUE;
        int[] cpuMove = new int[2];
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                if (gameBoard[i][j] == ' ') {
                    gameBoard[i][j] = XO.cpu;
                    int moveVal = miniMax(gameBoard, false, 0);
                    gameBoard[i][j] = ' ';
                    if (moveVal > bestVal) {
                        cpuMove[0] = i;
                        cpuMove[1] = j;
                        bestVal = moveVal;
                    }

                }
            }
        }
        return cpuMove;
    }
}
