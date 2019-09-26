package kenken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class BackTrack {
    int iterations;

    public BackTrack(BoardMaker board) {
        iterations = 0;
    }

    public static void main(String[] args) throws IOException {
	File inputFile = new File("/Users/edesern/Documents/COMP560_KenKen_Puzzle/COMP560_KenKen_Puzzle/src/kenken/Puzzle1.txt");
    BoardMaker board = new BoardMaker(inputFile);

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

public boolean checkCage(Node n, BoardMaker board, int sol) {
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
        //int sum = 0;
        int sum = sol;
        for(int i = 0; i < nodes.size(); i++) {
            //sum += nodes.get(i).solution;
            if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                if(nodes.get(i).solution == 0) {
                    return true;
                }
                sum+= nodes.get(i).solution;
            }
            //sum += nodes.get(i).solution;
        }
        if(sum == cage.total) {
            return true;
        }
    }
     else if(op == '-') {
    	 int difference = sol;
    	 for (int i = 0; i < nodes.size(); i++) {
             if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                 if(nodes.get(i).solution == 0) {
                     difference-= nodes.get(i).solution;
                     return true;
                 }
                 difference-= nodes.get(i).solution;
             }
    	 }
    	 if (difference == cage.total || difference*-1 == cage.total) {
    		 return true;
    	 } else {
    		 return false;
    	 }
    }
     else if(op == '/') {
        for (int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                if(nodes.get(i).solution == 0) {
                    return true;
                } else {
                    if((nodes.get(i).solution / sol) == cage.total || (sol / nodes.get(i).solution) == cage.total) {
                        return true;
                    } 
                }
            }
        }
        int first = nodes.get(0).solution;
        int second = nodes.get(1).solution;
        /*
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
        }*/
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
        } else {
        	return false;
        }
        */
    }
     else if(op == '*') {
        //int product = 1;
        int product = sol;
        for(int i = 0; i < nodes.size(); i++) {
            if(!((nodes.get(i).col == n.col) && (nodes.get(i).row == n.row))) {
                if(nodes.get(i).solution == 0) {
                    return true;
                }
                product*= nodes.get(i).solution;
            }
            //product *= nodes.get(i).solution;
        }
        if(product == cage.total) {
            return true;
        }
     } else if(op == '=') {
         if(sol == cage.total) {
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

    // messes up on 2/ 6.

    Node node = findEmptyCell(board);
    List<Integer> list = new ArrayList<Integer>();
    iterations++;
    Cage cage;
    int index = 0;
    for(int i = 0; i < board.cages.length; i++) {
        if(node.character == board.cages[i].letter) {
            index = i;
        }
    }
    cage = board.cages[index];
    char op = cage.oper;
    int total = cage.total;
    ArrayList<Node> nodes = cage.nodes;
        if(op == '+') {
            List<Integer> listt = findNumbers(total, board);
            if(node != null) {
                for(int solu = 0; solu < listt.size(); solu++) {
                    if(works(board.finalMatrix[node.row][node.col], listt.get(solu), board)) {
                        board.finalMatrix[node.row][node.col].solution = listt.get(solu);
                        if(solve(board)) {
                            return true;
                        } else {
                            board.finalMatrix[node.row][node.col].solution = 0;
                        }
                    }
                }
            }
        } else {
    if(node != null) {
        for(int sol = 1; sol < board.puzzleSize + 1; sol++) {
            if(works(board.finalMatrix[node.row][node.col], sol, board)) {
                board.finalMatrix[node.row][node.col].solution = sol;
                if(solve(board)) {
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
return false;
}

    private List<Integer> findNumbers(int sum, BoardMaker board) {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i <= board.puzzleSize; i++) {
            for(int j = 1; j <= board.puzzleSize; j++) {
                if(i + j == sum) {
                    if(!list.contains(i)) {
                        list.add(i);
                    }
                    if(!list.contains(j)) {
                        list.add(j);
                    }
                }
            }
        }
        return list;
    }


}