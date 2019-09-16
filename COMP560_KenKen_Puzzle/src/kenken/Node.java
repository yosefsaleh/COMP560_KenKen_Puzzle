package kenken;

public class Node {
    String operation;
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

    public Node(int finalNumber, String operation, Node up, Node left, Node down, Node right, int solution) {
        this.finalNumber = finalNumber;
        this.operation = operation;
        this.up = up;
        this.left = left;
        this.down = down;
        this.right = right;
        this.solution = solution;
    }

}