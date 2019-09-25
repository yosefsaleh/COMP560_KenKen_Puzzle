package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
		BoardMaker board = new BoardMaker(inputFile);

		// call the backtracking algorithm and solve the kenken puzzle
		BackTrack back = new BackTrack(board);
		back.main(args);
		back.solve(board);
		//System.out.println(back.iterations);

		for(int i = 0; i < board.puzzleSize; i++) {
			System.out.println(" ");
			for(int j = 0; j < board.puzzleSize; j++) {
				System.out.print(board.finalMatrix[i][j].solution);
			}
		}

		// call the local search algorithm and solve the kenken puzzle
		//LocalSearch local = new LocalSearch(board);
		//local.main(args);
		//local.solve(board);
		//System.out.println(local.iterations);
		
		
	}
}