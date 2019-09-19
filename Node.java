package kenken;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

public class Node{
    char operation;
    char character;
    int finalNumber;
    Node up;
    Node left;
    int[] possibleNumbers;
    Node down;
    Node right;
    int solution;
    int row;
    int col;
    HashSet setOfNumbers;




    // this class is used to create a node for each tile in the square. There are a number of parameters, such as:
    // finalNumber (which is the number we are trying to reach for a group of nodes) ex: A:11
    // operation (which is the operation that we are going to use for the group of nodes) ex: "+"
    // up, left, down, and right (which are used to know if neighboring nodes are grouped with the current node)
    // solution (which is the number solution for that specific tile on the kenken square)

    //int[] nums = board.getPossibleNumbers(board.puzzleSize);
    

    public Node(char character, int row, int col) {
        this.character = character;
        this.finalNumber = 0;
        this.row = row;
        this.col = col;
    }


    public int getFinalNumber(){
        return finalNumber;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return col;
    }
    public void setFinalNumber(int i) {
        finalNumber = i;
    }



}