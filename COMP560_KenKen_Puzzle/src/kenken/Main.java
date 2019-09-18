package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("/Users/yoyosef/git/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
		BoardMaker board = new BoardMaker(inputFile);
		
		
		
		// Use these for testing the creation of the board
		//System.out.println(Arrays.deepToString(board.boardMatrix));
		//System.out.println(Arrays.deepToString(board.numbersAndOperations));
		
		//board.printFinalMatrix();
		
		
	}
}