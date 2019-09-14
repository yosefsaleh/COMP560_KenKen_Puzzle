package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		File inputFile = new File("/Users/yoyosef/git/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
		BoardMaker board = new BoardMaker(inputFile);
	}
}
