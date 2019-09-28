package kenken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LocalSearch {
    int iterations;


    public LocalSearch(BoardMaker board) {
        iterations = 0;
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
// this one is different than the backtracking one because we are finding the
// error between the cage number and the nodes in the cage
// for example: if cage is A:11+ and the nodes inside the cage
// add up to 8.. the error would be 11-8 = 3. This is used later to determine
// which nodes to switch based on the lowest error
public double checkCage(Node n, Node t, BoardMaker board) {
    Cage cage;
    int index = 0;
    for(int i = 0; i < board.cages.length; i++) {
        if(t.character == board.cages[i].letter) {  
            index = i;
        }
    }
    cage = board.cages[index];
    char op = cage.oper;
    ArrayList<Node> nodes = cage.nodes;
    int newSolution = n.solution;
    if(op == '+') {
        int sum = newSolution;
        for(int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == t.col) && (nodes.get(i).row == t.row))) {
                sum+= nodes.get(i).solution;
            }
        }
        return cage.total - sum;
    }
     else if(op == '-') {
    	 int difference = newSolution;
    	 for (int i = 0; i < nodes.size(); i++) {
             if(!((nodes.get(i).col == t.col) && (nodes.get(i).row == t.row))) {
                 difference-= nodes.get(i).solution;
             }
         }
         if(difference < 0) {
            difference = difference *-1;
         }
         // maybe change this
    	 return cage.total - difference;
    }
     else if(op == '/') {
        int first = newSolution;
        int second = 0;
        for (int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == t.col) && (nodes.get(i).row == t.row))) {
                second = nodes.get(i).solution;
            }
        }
        int right = 0;
        int one = first/second;
        int two = second/first;
        if(one > two) {
            right = one;
        } else {
            right = two;
        }
        return cage.total - right;
    }
     else if(op == '*') {
        int product = newSolution;
        for(int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == t.col) && (nodes.get(i).row == t.row))) {
                product*= nodes.get(i).solution;
            }
        }
        return cage.total - product;
    }
    else if(op == '=') {
        return cage.total - newSolution;
    }
    return 100;
}

// checks each node if it satisfies the row, column, and cage
public boolean works(Node node, int num, BoardMaker board) {
    if(checkRow(node.row, num, board) && checkCol(node.col, num, board)) {
        return true;
    }
    return false;
}



// local search algorithm to solve kenken.. can get stuck at a local maximum so a 
// solution is not guarenteed.
public void solve(BoardMaker board) {
    // fills board with numbers that satisfy the unique row and col rules.. ignores the cages for now. our 
    // hill climbing algorithm will use cage value/operation to climb the hill.
    fill(board);
    int numIterations = 0;

    // make a while loop that randomly selects an i and j..
    while(numIterations < 500) {
        iterations++;

        // randomly select an i and a j per iteration
        int i = (int)(Math.random() * ((board.puzzleSize-1 - 0) + 1)) + 0;
        int j = (int)(Math.random() * ((board.puzzleSize-1 - 0) + 1)) + 0;
        double up = 1000000000;
        double left = 200000000;
        double down = 300000000;
        double right = 40000000;
        double min = 100000;
        double original = checkCage(board.finalMatrix[i][j], board.finalMatrix[i][j], board);
        if(original < min) {
            min = original;
        }

        // check up node
        if(i-1 >= 0) {
            up = checkCage(board.finalMatrix[i-1][j], board.finalMatrix[i][j], board);
        }
        if(up < min) {
            min = up;
        }

        // check left node
        if(j-1 >= 0) {
            left = checkCage(board.finalMatrix[i][j-1], board.finalMatrix[i][j], board);
        }
        if(left < min) {
            min = left;
        }

        // check down node
        if(i+1 < board.puzzleSize) {
            down = checkCage(board.finalMatrix[i+1][j], board.finalMatrix[i][j], board);
        }
        if(down < min) {
            min = down;
        }

        // check right node
        if(j+1 < board.puzzleSize) {
            right = checkCage(board.finalMatrix[i][j+1], board.finalMatrix[i][j], board);
        }
        if(right < min) {
            min = right;
        }
        // check which nodes to switch
        if(min == original) {
            numIterations++;
            continue;
        }
        if(min == up) {
            switchNodes(board.finalMatrix[i][j], board.finalMatrix[i-1][j], board);
            numIterations++;
            continue;
        }
        if(min == left) {
            switchNodes(board.finalMatrix[i][j], board.finalMatrix[i][j-1], board);
            numIterations++;
            continue;
        }
        if(min == down) {
            switchNodes(board.finalMatrix[i][j], board.finalMatrix[i+1][j], board);
            numIterations++;
            continue;
        }
        if(min == right) {
            switchNodes(board.finalMatrix[i][j], board.finalMatrix[i][j+1], board);
            numIterations++;
            continue;
        }

    }

}


    // switches node.solution between two nodes.
    public void switchNodes(Node node, Node node2, BoardMaker board) {

        int temp = node2.solution;
        node2.solution = node.solution;
        node.solution = temp;
    }


    // fills board with numbers that satisfy unique row and column.. ignores cages
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