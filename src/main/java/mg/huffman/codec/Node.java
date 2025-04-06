package mg.huffman.codec;

public class Node implements Comparable<Node> {
    char character;
    double probability;
    Node left;
    Node right;
    boolean isLeaf;

    public char getCharacter() {
        return character;
    }
    public void setCharacter(char character) {
        this.character = character;
    }
    public double getProbability() {
        return probability;
    }
    public void setProbability(double probability) {
        this.probability = probability;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public boolean isLeaf() {
        return isLeaf;
    }
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
    
}