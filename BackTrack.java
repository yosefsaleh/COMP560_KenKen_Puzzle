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

public Node findEmptyCell(BoardMaker board) {
    Node node = null;
    for(int i = 0; i< board.puzzleSize; i++) {
        for(int j = 0; j < board.puzzleSize; j++) {
            if(board.finalMatrix[i][j].solution == 0) {
                node = board.finalMatrix[i][j];
            }
        }
    }
    System.out.println(node.character);
    return node;
}

public boolean checkCage(Node n, BoardMaker board, int sol) {
    Cage cage;
    int index = 0;
    for(int i = 0; i < board.cages.length; i++) {
        if(n.character == board.cages[i].letter) {
            //System.out.println(board.cages[i].letter);
            index = i;
        }
    }
    cage = board.cages[index];
    //System.out.println(cage.total);

    //if(cage.containsEmptyCell()) {
    //   return true;
    //}
    char op = cage.oper;
    //System.out.println(op);
    int num = cage.total;
    ArrayList<Node> nodes = cage.nodes;
    if(op == '+') {
        //int sum = 0;
        int sum = sol;
        for(int i = 0; i < nodes.size(); i++) {
            //sum += nodes.get(i).solution;
            //if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
            //    sum+= nodes.get(i).solution;
            //}
            sum += nodes.get(i).solution;
        }
        if(sum <= cage.total) {
            return true;
        }
    }
    if(op == '-') {
        int first = nodes.get(0).solution;
        int second = nodes.get(1).solution;
        if(first == 0) {
            if(second == 0) {
                return true;
            } else if(second - sol == cage.total) {
                return true;
            } else if(sol - second == cage.total) {
                return true;
            }
        } else if(first - sol == cage.total) {
            return true;
        } else if(sol - first == cage.total) {
            return true;
        }
        //if((first - second) == cage.total || (second - first) == cage.total) {
        //    return true;
        //}
    }
    if(op == '/') {
        int first = nodes.get(0).solution;
        int second = nodes.get(1).solution;
        if(first == 0) {
            if(second == 0) {
                return true;
            } else if(second/sol == 0) {
                return true;
            } else if(sol/second == 0) {
                return true;
            }
        } else if(first/sol == 0) {
            return true;
        } else if(sol/first == 0) {
            return true;
        }
        /*if(first == 0 && second ==0) {
            return true;
        }
        if(first == 0) {
            return true;
        }
        if(second == 0) {
            return true;
        }
        if((first / second) == cage.total || (second / first) == cage.total) {
            return true;
        }
        */
    }
    if(op == '*') {
        //int product = 1;
        int product = sol;
        for(int i = 0; i < nodes.size(); i++) {
            //if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
            //    product+= nodes.get(i).solution;
            //}
            product *= nodes.get(i).solution;
        }
        if(product <= cage.total) {
            return true;
        }
    }
    return false;
}

public boolean works(Node node, int num, BoardMaker board) {
    if(checkRow(node.row, num, board) && checkCol(node.col, num, board) && checkCage(node,board, num)) {
        return true;
    }
    return false;
}


public boolean solve(BoardMaker board) {
    Node node = findEmptyCell(board);
    if(node == null) {
        return true;
    }
        {
            for (int sol = 1; sol <= board.puzzleSize; sol++) {
                if (works(node, sol, board)) {
                    node.solution = sol;
                    if (solve(board)) {
                        return true;
                    }
                    node.solution = 0;
                }
            }
            return false;
        }
}



    

}