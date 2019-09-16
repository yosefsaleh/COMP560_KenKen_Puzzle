package kenken;

import java.io.File;

import java.util.Scanner; 

import java.io.FileNotFoundException;


public class BoardMaker {
	
	File inputFile;
	int puzzleSize; 
	String size;
	// made this into a char[][] so we can get the specific characters from the nextLine() scan
	char [][] boardMatrix;  
	Node [][] finalMatrix;
	String [] numbersAndOperations;
	public BoardMaker(File file) throws FileNotFoundException {
		
		inputFile = new File("/Users/yoyosef/git/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");  // import text file into variable
		Scanner scanner = new Scanner(inputFile); // Scanner object
		int lineNumber = 0; // indicates what line the scanner is at
		int row = 0; 
		int col = 0;
		int indx = 0;
		do {
			String currentLine = scanner.nextLine();
			//System.out.println(currentLine);
			// For the first iteration want to get the size of the puzzle and create our 2D array 
			if (lineNumber == 0) {
				puzzleSize = Integer.parseInt(currentLine);
				makeBoardArray(puzzleSize);
			}
			if (lineNumber > 0 && lineNumber <= puzzleSize) {
				for (col = 0;col <=puzzleSize - 1; col++) {
					// gets a char element from the nextLine() scan
					boardMatrix[row][col] = currentLine.charAt(col);
				}
				row++;
			}
			lineNumber++;
			// loop reaches here now because lineNumber is past puzzleSize

			// places the rest of the scanned lines into an array.. numbersAndOperations[0] will have 
			// A:11+ , numbersAndOperations[1] will have B:2/, ect.
			numbersAndOperations[indx] = currentLine;
			indx++;

			finalMatrix = getFinalMatix(boardMatrix, numbersAndOperations);


		} while (scanner.hasNextLine());
	}

	private Node[][] getFinalMatix(char[][] boardMatrix, String[] numbersAndOperations) {
		char tempOperation = 'a';
		int tempNumber = 0;

		// loop through 2D array of the board
		for(int i = 0; i < puzzleSize; i++) {
			for(int j = 0; j < puzzleSize; j++) {

				// extracts a char from the board
				char character = boardMatrix[i][j];

				// loops through the numbersAndOperations array
				for(int t = 0; t < numbersAndOperations.length; t++) {

					// checks if first element of a row matches the char on the board (ex: charAt(0) == 'A' and matches 'A' on the board)
					if(numbersAndOperations[t].charAt(0) == character) {

						// next three lines extract the number and operation from numbersAndOperations
						int num = numbersAndOperations[t].length();
						tempOperation = numbersAndOperations[t].charAt(num);
						tempNumber = getNumberFromString(numbersAndOperations[t]);
					}
				}

				// make a new Node for the board tile and update finalMatrix
				Node node = new Node(character, tempNumber, tempOperation);
				finalMatrix[i][j] = node;
			}
		}

		// calls method to check if the node has neighboring nodes
		checkNeighbors(finalMatrix);

		return finalMatrix;
	}

	private void checkNeighbors(Node[][] matrix) {

		// this method iterates through the rows and columns of the Node[][] and compares the nodes above, below,
		// left, and right of the current node to see if they have matching characters.. if they do, then update
		// the neighbor of the current node

		for(int row = 0; row < puzzleSize; row++) {
			for(int col = 0; col < puzzleSize; col++) {
				Node temp = matrix[row][col];

				if(col - 1 != 0) {
					Node temp2 = matrix[row][col-1];
					if(temp.character == temp2.character) {
						temp.left = matrix[row][col-1];
					}
				}
				if(col + 1 != puzzleSize) {
					Node temp2 = matrix[row][col+1];
					if(temp.character == temp2.character) {
						temp.right = matrix[row][col+1];
					}
				}
				if(row - 1 != 0) {
					Node temp2 = matrix[row-1][col];
					if(temp.character == temp2.character) {
						temp.up = matrix[row-1][col];
					}
				}
				if(row + 1 != puzzleSize) {
					Node temp2 = matrix[row+1][col];
					if(temp.character == temp2.character) {
						temp.down = matrix[row+1][col];
					}
				}
			}
		}
	}

	private int getNumberFromString(String string) {
		String result = "";
		for(int v = 0; v < string.length(); v++) {
			Character temporary = string.charAt(v);
			if(Character.isDigit(temporary)) {
				result += temporary;
			}
		}
		int finalAns = Integer.parseInt(result);

		return finalAns;
	}

	private char[][] makeBoardArray(int n) {
		
		boardMatrix = new char[n][n]; 
		
		return boardMatrix;
	}
	



}
