package kenken;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BoardMaker {

	File inputFile;
	int puzzleSize;
	String size;
	// made this into a char[][] so we can get the specific characters from the
	// nextLine() scan
	char[][] boardMatrix;
	int totalNumLines;
	Node[][] finalMatrix;
	String[] numbersAndOperations;
	int startNumFill = 0;

=======
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner; 
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BoardMaker {
	
	File inputFile; // text file that contains all the information about the KenKen puzzle
	int puzzleSize; // size of the puzzle board
	int totalNumLines; // number of lines in the input file
	char [][] boardMatrix; // a char[][] so we can get the specific characters from the nextLine() scan
	String [] numbersAndOperations; // an array that keeps track of all the lines that involve operators
	Node [][] finalMatrix; // what the final game board should look like before we start filling it with solutions 
	int startNumFill = 0; // indicates what line of the scanner to start filling in operations array from 
	
>>>>>>> 819daa5a20bb86d79ba646ad7727c70c1ff3ebe9
	public BoardMaker(File file) throws IOException {
		
		inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");  // import text file into variable
		Scanner scanner = new Scanner(inputFile); // Scanner object
		int lineNumber = 0; // indicates what line the scanner is at
		int row = 0; 
		int col = 0;
		int indx = 0;
		
		// Created a Buffered reader and used a simple while loop to read the file and get the total number of lines for the file
		// Need this number to initialize numbersAndOperation array
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		totalNumLines = 0;
		while (reader.readLine() != null) 
		totalNumLines++;
		reader.close();	
<<<<<<< HEAD
=======
		
		
>>>>>>> 819daa5a20bb86d79ba646ad7727c70c1ff3ebe9
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
			startNumFill++;

		} while (startNumFill <= puzzleSize);
		makeNumAndOpsArray();
		
		do {
			String currentLine = scanner.nextLine();
			numbersAndOperations[indx] = currentLine;
			indx++;
		} while(scanner.hasNextLine());		
		finalMatrix = getFinalMatix(boardMatrix, numbersAndOperations);	
<<<<<<< HEAD
		
=======
>>>>>>> 819daa5a20bb86d79ba646ad7727c70c1ff3ebe9
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
						tempOperation = numbersAndOperations[t].charAt(num - 1);
						tempNumber = getNumberFromString(numbersAndOperations[t]);
					}
				}

				// make a new Node for the board tile and update finalMatrix
				Node node = new Node(i, j);
				finalMatrix[i][j] = node;
				Cage cage = new Cage(tempNumber, tempOperation);
				cage.setNodes(node);
			}
		}

		// calls method to check if the node has neighboring nodes
		checkNeighbors(finalMatrix);

		return finalMatrix;
	}
	public void printFinalMatrix() {
		for (int i = 0; i < puzzleSize; i++) {
			System.out.println(" ");
			for(int j = 0; j < puzzleSize; j++) {
				System.out.print(finalMatrix[i][j].finalNumber + " ");
				//System.out.print(finalMatrix[i][j].row);
				//System.out.print(finalMatrix[i][j].col + " ");
			}
		}
	}

	private void checkNeighbors(Node[][] matrix) {

		// this method iterates through the rows and columns of the Node[][] and compares the nodes above, below,
		// left, and right of the current node to see if they have matching characters.. if they do, then update
		// the neighbor of the current node

		for(int row = 0; row < puzzleSize; row++) {
			for(int col = 0; col < puzzleSize; col++) {
				Node temp = matrix[row][col];

				if(col - 1 >= 1) {
					Node temp2 = matrix[row][col-1];
					if(temp.character == temp2.character) {
						temp.left = matrix[row][col-1];
					}
				}
				if(col + 1 < puzzleSize) {
					Node temp2 = matrix[row][col+1];
					if(temp.character == temp2.character) {
						temp.right = matrix[row][col+1];
					}
				}
				if(row - 1 >= 0) {
					Node temp2 = matrix[row-1][col];
					if(temp.character == temp2.character) {
						temp.up = matrix[row-1][col];
					}
				}
				if(row + 1 < puzzleSize) {
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
		finalMatrix = new Node[n][n];
		
		return boardMatrix;
	}
	private String[] makeNumAndOpsArray() {
		numbersAndOperations = new String[totalNumLines - startNumFill];
		return numbersAndOperations;
	}
<<<<<<< HEAD

	public int[] getPossibleNumbers(int puzzleSize) {
		int[] array = new int[puzzleSize];
        for(int i =0; i < puzzleSize; i++) {
			array[i] = i+1;
		}
=======
	
	// Helper method to visualize and test the creation of the final game board 
	public void printFinalMatrix() {
		for (int i = 0; i < puzzleSize; i++) {
			System.out.println(" ");
			for (int j = 0; j < puzzleSize; j++) {
				System.out.print(finalMatrix[i][j].character);
				System.out.print(finalMatrix[i][j].finalNumber);
				System.out.print(finalMatrix[i][j].operation + " ");
				
				}
		}
	}
>>>>>>> 819daa5a20bb86d79ba646ad7727c70c1ff3ebe9

		return array;
	}
	public int getPuzzleSize() {
		return puzzleSize;
	}
}
