package mg.huffman.codec;

public class Node implements Comparable<Node> {
    char character;
    double probability;
    Node left;
    Node right;
    boolean isLeaf;

    public Node(char character, double probability) {
        this.setCharacter(character);
        this.setProbability(probability);
        this.setLeft(null);
        this.setRight(null);
        this.setLeaf(true);
    }

    public Node(Node left, Node right) {
        this.setLeft(left);
        this.setRight(right);
        this.setLeaf(false);
        this.setCharacter('\0');
        this.setProbability(left.getProbability() + right.getProbability());
    }

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