package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
<<<<<<< HEAD
		File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
		BoardMaker board = new BoardMaker(inputFile);


		//board.printFinalMatrix();

=======
		File inputFile = new File("/Users/yoyosef/git/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
		BoardMaker board = new BoardMaker(inputFile);
		
		
		
		// Use these for testing the creation of the board
		//System.out.println(Arrays.deepToString(board.boardMatrix));
		//System.out.println(Arrays.deepToString(board.numbersAndOperations));
		
		//board.printFinalMatrix();
		
		
>>>>>>> 819daa5a20bb86d79ba646ad7727c70c1ff3ebe9
	}
}