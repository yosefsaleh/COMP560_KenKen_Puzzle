package kenken;

import java.io.File;
import java.io.FileNotFoundException;

public class BackTrack {

    int iterations;

    public BackTrack(BoardMaker board) {
        iterations = 0;
    }

public static void main(String[] args) throws FileNotFoundException {
    File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
    BoardMaker board = new BoardMaker(inputFile);
    int n = board.getPuzzleSize();
    int [][]solutionBoard = new int[n][n];
    int guess = 1;

    
    solutionBoard[1][0] = guess;
    checkRows(solutionBoard);


}
private boolean checkRows(int guess, int[][] solutionBoard) {
    for (int i = 0; i < solutionBoard.length; i++) {
        for (int j = 0; j < solutionBoard.length; j++) {
            if (guess==solutionBoard[i] || guess==solutionBoard[j]) {
                return false;
            }
        }
    }
}



    

}