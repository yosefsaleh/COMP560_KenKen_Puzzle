package kenken;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
	HashSet set = new HashSet();
	Cage[] cages;
	int totalNumLines; // number of lines in the input file
	char [][] boardMatrix; // a char[][] so we can get the specific characters from the nextLine() scan
	String [] numbersAndOperations; // an array that keeps track of all the lines that involve operators
	Node [][] finalMatrix; // what the final game board should look like before we start filling it with solutions 
	int startNumFill = 0; // indicates what line of the scanner to start filling in operations array from 
	
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

		// Fills BoardArray with leters... used later to create nodes
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
		
		// fills numberAndOperations with the letter, the final number, and the operation attached
		do {
			String currentLine = scanner.nextLine();
			numbersAndOperations[indx] = currentLine;
			indx++;
		} while(scanner.hasNextLine());		
		finalMatrix = getFinalMatix(boardMatrix, numbersAndOperations);	

	}

	private Node[][] getFinalMatix(char[][] boardMatrix, String[] numbersAndOperations) {
		char tempOperation = 'a';
		int tempNumber = 0;
		cages = new Cage[numbersAndOperations.length];
		

		// loop through 2D array of the board
		// this code fills finalMatrix with nodes for every tile in the kenken puzzle
		// later, we use this to create cages that group the nodes together.
		for(int i = 0; i < puzzleSize; i++) {
			for(int j = 0; j < puzzleSize; j++) {

				// extracts a char from the board
				char character = boardMatrix[i][j];

				// make a new Node for the board tile and update finalMatrix
				Node node = new Node(character, i, j);
				finalMatrix[i][j] = node;
				
			}
		}

		// loops through the numbersAndOperations array
		// this code is for creating cages for every letter in the kenken puzzle.
		for(int t = 0; t < numbersAndOperations.length; t++) {

			// checks if first element of a row matches the char on the board (ex: charAt(0) == 'A' and matches 'A' on the board)
			char character = numbersAndOperations[t].charAt(0);
		
				// next three lines extract the number and operation from numbersAndOperations
				int num = numbersAndOperations[t].length();
				tempOperation = numbersAndOperations[t].charAt(num - 1);
				tempNumber = getNumberFromString(numbersAndOperations[t]);

				// now we make a new ArrayList containing nodes that share the same letter (A, A), (B, B), ect.
				// calls my getArrayList method.
				ArrayList<Node> arr = getArrayList(character);

				// create new cage with the final number of the cage, the operation of the cage, the letter of
				// the cage, and the arrayList containing all the nodes in the cage.
				Cage cage = new Cage(tempNumber, tempOperation, character, arr);
				cages[t] = cage;
				// we add our cages to an array.. This is used
				// to save the cages since we create them locally inside the for loops.
				
		}


		// calls method to check if the node has neighboring nodes
		checkNeighbors(finalMatrix);

		// this returns the finalMatrix of nodes, but it also creates a hashset of cages, so that's more useful.
		return finalMatrix;
	}

	// creates an ArrayList of nodes with similar characters.
	public ArrayList<Node> getArrayList(char character) {
		ArrayList<Node> arr = new ArrayList<Node>();
		for(int i = 0; i < puzzleSize; i++) {
			for(int j = 0; j < puzzleSize; j++) {
				if(finalMatrix[i][j].character == character) {
					arr.add(finalMatrix[i][j]);
				}
			}
		}
		//System.out.println(arr);
		return arr;
	}

	// prints our finalMatrix (Matrix with Nodes)
	public void printFinalMatrix() {
		for (int i = 0; i < puzzleSize; i++) {
			System.out.println(" ");
			for(int j = 0; j < puzzleSize; j++) {
				System.out.print(finalMatrix[i][j].finalNumber + " ");
			}
		}
	}


	// this method iterates through the rows and columns of the Node[][] and compares the nodes above, below,
	// left, and right of the current node to see if they have matching characters.. if they do, then update
	// the neighbor of the current node
	private void checkNeighbors(Node[][] matrix) {
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

	// parses an int from a string containing chars and ints (ex: takes in "A:11+" and returns 11).
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


	// makes the board arrays of size n.
	private char[][] makeBoardArray(int n) {
		
		boardMatrix = new char[n][n]; 
		finalMatrix = new Node[n][n];
		return boardMatrix;
	}


	// makes the numbersAndOperations array used to fill them with "A:11+", "B:2/", ect.
	private String[] makeNumAndOpsArray() {
		numbersAndOperations = new String[totalNumLines - startNumFill];
		return numbersAndOperations;
	}

	public Cage[] getCages() {
		return cages;
	}




	public int[] getPossibleNumbers(int puzzleSize) {
		int[] array = new int[puzzleSize];
        for(int i =0; i < puzzleSize; i++) {
			array[i] = i+1;
		}

		return array;
	}
	public int getPuzzleSize() {
		return puzzleSize;
	}
}
