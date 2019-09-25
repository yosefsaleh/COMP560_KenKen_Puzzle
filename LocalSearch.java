package kenken;

import java.io.File;
import java.io.IOException;

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


public boolean solve(BoardMaker board) {


    return true;
}


}