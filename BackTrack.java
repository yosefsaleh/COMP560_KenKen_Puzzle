package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BackTrack {
    int iterations;

    public BackTrack(BoardMaker board) {
        iterations = 0;
    }

    public static void main(String[] args) throws IOException {
    File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
    BoardMaker board = new BoardMaker(inputFile);
    int n = board.getPuzzleSize();
    int [][]solutionBoard = new int[n][n];
    final int lower = 1;
    int guess = 1;
   /* for (int i = 0; i < solutionBoard.length; i++) {
        for (int j = 0; j< solutionBoard.length; j++) {
            int random = (int) (Math.random() * (board.getPuzzleSize())) + lower;
            board.finalMatrix[i][j].solution = random;
        }
    }*/
    for (int i = 0; i < board.finalMatrix.length; i++) {
        System.out.println(" ");
        for (int j = 0; j < board.finalMatrix.length; j++){
            System.out.print(board.finalMatrix[i][j].solution);
        }
    }








}

// iterates through row and checks if the number is already in the row.. if so, return true.
public boolean checkRow(int col, int num, BoardMaker board) {
    for(int indx = 0; indx < board.puzzleSize; indx++) {
        if(board.finalMatrix[indx][col].solution == num) {
            return false;
        }
    }
    return true;
}

// iterates through column and checks if the number is already in the row.. if so, return true.
public boolean checkCol(int row, int num, BoardMaker board) {
    for(int indx = 0; indx < board.puzzleSize; indx++) {
        if(board.finalMatrix[row][indx].solution == num) {
            return false;
        }
    }
    return true;
}

public Node findEmptyCell(BoardMaker board) {
    Node node = null;
    for(int i = 0; i< board.puzzleSize; i++) {
        for(int j = 0; j < board.puzzleSize; j++) {
            if(board.finalMatrix[i][j].solution == 0) {
                node = board.finalMatrix[i][j];
            }
        }
    }
    return node;
}

public boolean checkCage(Node n, BoardMaker board) {
    Cage cage;
    int index = 0;
    for(int i = 0; i < board.cages.length; i++) {
        if(n.character == board.cages[i].letter) {
            index = i;
        }
    }
    cage = board.cages[index];

    if(cage.containsEmptyCell()) {
        return false;
    }
    char op = cage.oper;
    int num = cage.total;
    ArrayList<Node> nodes = cage.nodes;
    if(op == '+') {
        int sum = 0;
        for(int i = 0; i < nodes.size(); i++) {
            sum += nodes.get(i).solution;
        }
        if(sum == cage.total) {
            return true;
        }
    }
    if(op == '-') {
        int first = nodes.get(0).solution;
        int second = nodes.get(1).solution;
        if((first - second) == cage.total || (second - first) == cage.total) {
            return true;
        }
    }
    if(op == '/') {
        int first = nodes.get(0).solution;
        int second = nodes.get(1).solution;
        if((first / second) == cage.total || (second / first) == cage.total) {
            return true;
        }
    }
    if(op == '*') {
        int product = 0;
        for(int i = 0; i < nodes.size(); i++) {
            product *= nodes.get(i).solution;
        }
        if(product == cage.total) {
            return true;
        }
    }
    return false;
}

public boolean works(Node node, int num, BoardMaker board) {
    if(checkRow(node.row, num, board)) {
        return false;
    }
    if(checkCol(node.col, num, board)) {
        return false;
    }
    if(checkCage(node, board)) {
        return false;
    }
    return true;
}


public boolean solve(BoardMaker board) {
    Node node = findEmptyCell(board);
/*    for (int i = 0; i < board.getPuzzleSize(); i ++) {
        for (int j = 0; j < board.getPuzzleSize(); j++) {
            if(board.finalMatrix[i][j].solution == 0) {
                for (int sol = 1; sol <= board.getPuzzleSize(); sol++) {
                    if (works(board.finalMatrix[i][j], sol, board)) {
                        board.finalMatrix[i][j].solution = sol;
                        System.out.println("this is working");
                        if(solve(board)) {
                            return true;
                        }
                    }
                }
            }
        }
    }
    return false;
*/
    if(node != null) {
        for(int sol = 1; sol <= board.puzzleSize; sol++) {
            if(works(node,sol, board)) {
                board.finalMatrix[node.row][node.col].solution= sol;
                if(solve(board)) {
                    return true;
                }
                board.finalMatrix[node.row][node.col].solution= 0;
            }
        }
        return false;
    } else {
        return true;
    }

}



    

}