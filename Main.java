package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
		BoardMaker board = new BoardMaker(inputFile);


		//board.printFinalMatrix();
		
		
		
		// Use these for testing the creation of the board
		//System.out.println(Arrays.deepToString(board.boardMatrix));
		//System.out.println(Arrays.deepToString(board.numbersAndOperations));
		
		//board.printFinalMatrix();
		BackTrack back = new BackTrack(board);
		back.main(args);


		System.out.println(back.solve(board));

		for(int i = 0; i < board.puzzleSize; i++) {
			System.out.println(" ");
			for(int j = 0; j < board.puzzleSize; j++) {
				System.out.print(board.finalMatrix[i][j].solution);
			}
		}
		
		
	}
}