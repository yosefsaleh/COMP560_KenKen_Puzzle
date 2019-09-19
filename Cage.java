package kenken;

import java.util.ArrayList;

public class Cage {

public int total;
public char letter;
public char oper;
public ArrayList<Node> nodes;

public Cage(int g, char op, char letter, ArrayList<Node> nodes) {
    total = g;
    oper = op;
    this.letter = letter;
    this.nodes = nodes;
}

public int getTotal() {
    return total;
}

public char getOperation() {
    return oper;
}


public boolean contains(Node node) {
    if(!nodes.contains(node)) {
        return false;
    } else {
        return true;
    }
}

public ArrayList<Node> getNodes() {
    return nodes;
}

public boolean containsEmptyCell() {
    for(int i = 0; i < nodes.size(); i++) {
        if(nodes.get(i).finalNumber == 0) {
            return true;
        }
    }
    return false;
    }

    public void printCage() {
        String string = "";
        for (Node n : nodes) {
            string += "("+n.getRow()+","+n.getColumn()+")";
        }
        string += "/n" + String.valueOf(oper);
        System.out.println(string);
        }
    }






