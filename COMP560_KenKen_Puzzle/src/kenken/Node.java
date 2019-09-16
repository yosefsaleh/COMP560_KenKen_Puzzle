package kenken;

public class Node {
    char operation;
    char character;
    int finalNumber;
    Node up;
    Node left;
    Node down;
    Node right;
    int solution;

    // this class is used to create a node for each tile in the square. There are a number of parameters, such as:
    // finalNumber (which is the number we are trying to reach for a group of nodes) ex: A:11
    // operation (which is the operation that we are going to use for the group of nodes) ex: "+"
    // up, left, down, and right (which are used to know if neighboring nodes are grouped with the current node)
    // solution (which is the number solution for that specific tile on the kenken square)

    public Node(char character, int finalNumber, char operation) {
        this.finalNumber = finalNumber;
        this.character = character;
        this.operation = operation;
        up = null;
        left = null;
        down = null;
        right = null;
        solution = 0;
    }

}