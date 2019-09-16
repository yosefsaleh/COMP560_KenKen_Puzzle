package kenken;

import java.io.File;

import java.util.Scanner; 

import java.io.FileNotFoundException;


public class BoardMaker {
	
	File inputFile;
	int puzzleSize; 
	String size;
	String [][] boardMatrix;  
	public BoardMaker(File file) throws FileNotFoundException {
		
		inputFile = new File("/Users/yoyosef/git/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");  // import text file into variable
		Scanner scanner = new Scanner(inputFile); // Scanner object
		int lineNumber = 0; // indicates what line the scanner is at
		int row = 0; 
		int col = 0;
		do {
			String currentLine = scanner.nextLine();
			//System.out.println(currentLine);
			// For the first iteration want to get the size of the puzzle and create our 2D array 
			if (lineNumber == 0) {
				puzzleSize = Integer.parseInt(currentLine);
				makeBoardArray(puzzleSize);
			}
			if (lineNumber > 0 && lineNumber <= puzzleSize) {
				for (;col <=puzzleSize - 1; col++) {
					boardMatrix[row][col] = currentLine;
				}
				row++;
			}
			lineNumber++;
		} while (scanner.hasNextLine());
	}
	private String[][] makeBoardArray(int n){ 
		
		boardMatrix = new String[n][n]; 
		
		return boardMatrix;
	}

}
