package kenken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LocalSearch {
    int iterations;


    public LocalSearch(BoardMaker board) {
        iterations = 0;
    }


public static void main(String[] args) throws IOException {
    File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
    BoardMaker board = new BoardMaker(inputFile);
    int n = board.getPuzzleSize();


}

// iterates through row and checks if the number is already in the row.. if so, return true.
public boolean checkRow(int row, int num, BoardMaker board) {
    for(int indx = 0; indx < board.puzzleSize; indx++) {
        if(board.finalMatrix[row][indx].solution == num) {
            return false;
        }
    }
    return true;
}

// iterates through column and checks if the number is already in the row.. if so, return true.
public boolean checkCol(int col, int num, BoardMaker board) {
    for(int indx = 0; indx < board.puzzleSize; indx++) {
        if(board.finalMatrix[indx][col].solution == num) {
            return false;
        }
    }
    return true;
}

// returns the next empty cell
public Node findEmptyCell(BoardMaker board) {
    int index1 = 0;
    int index2 = 0;
    int no = 0;
    for(int i = 0; i< board.puzzleSize; i++) {
        for(int j = 0; j < board.puzzleSize; j++) {
            if(board.finalMatrix[i][j].solution == 0) {
                return board.finalMatrix[i][j];
            } else {
                no++;
            }
        }
    }
    if(no == board.puzzleSize*board.puzzleSize) {
        return null;
    }
    return board.finalMatrix[index1][index2];
}

// checks to see if the cage satisfies the final number and the operation
public boolean checkCage(Node n, BoardMaker board) {
    Cage cage;
    int index = 0;
    for(int i = 0; i < board.cages.length; i++) {
        if(n.character == board.cages[i].letter) {  
            index = i;
        }
    }
    cage = board.cages[index];
    char op = cage.oper;
    ArrayList<Node> nodes = cage.nodes;
    if(op == '+') {
        int sum = n.solution;
        for(int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                if(nodes.get(i).solution == 0) {
                    return true;
                }
                sum+= nodes.get(i).solution;
            }
        }
        if(sum == cage.total) {
            return true;
        }
    }
     else if(op == '-') {
    	 int difference = n.solution;
    	 for (int i = 0; i < nodes.size(); i++) {
             if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                 if(nodes.get(i).solution == 0) {
                     return true;
                 }
                 difference-= nodes.get(i).solution;
             }
    	 }
    	 if (difference == cage.total) {
    		 return true;
    	 }
    }
     else if(op == '/') {
        int first = nodes.get(0).solution;
        int second = nodes.get(1).solution;
        if(first == 0) {
            return true;
        }
        if(second == 0) {
            return true;
        }
        if((first / second) == cage.total || (second / first) == cage.total) {
            return true;
        }
    }
     else if(op == '*') {
        int product = n.solution;
        for(int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                if(nodes.get(i).solution == 0) {
                    return true;
                }
                product*= nodes.get(i).solution;
            }
        }
        if(product == cage.total) {
            return true;
        }
    }
    else if(op == '=') {
        if(nodes.get(0).solution == cage.total){
            return true;
        }
    }
    return false;
}

// checks each node if it satisfies the row, column, and cage
public boolean works(Node node, int num, BoardMaker board) {
    if(checkRow(node.row, num, board) && checkCol(node.col, num, board)) {
        return true;
    }
    return false;
}




public boolean solve(BoardMaker board) {
    // fills board with numbers that satisfy the unique row and col rules.. ignores the cages for now. our 
    // hill climbing algorithm will use cage value/operation to climb the hill.
    fill(board);

    for(int i = 0; i < board.puzzleSize; i++) {
        for(int j = 0; j < board.puzzleSize; j++) {
            checkCage(board.finalMatrix[i][j], board);
        }
    }


    return false;
}


    public boolean fill(BoardMaker board) {
    Node node = findEmptyCell(board);
    if(node != null) {
        for(int sol = 1; sol < board.puzzleSize + 1; sol++) {
            if(works(board.finalMatrix[node.row][node.col], sol, board)) {
                board.finalMatrix[node.row][node.col].solution = sol;
                if(fill(board)) {
                    return true;
                } else {
                    board.finalMatrix[node.row][node.col].solution = 0;
                }
            }
        }
    return false;
} else {
    return true;
    }
}


}