package mg.huffman.codec;

public class Node implements Comparable<Node> {
    char character;
    double probability;
    Node left;
    Node right;
    boolean isLeaf;
    
}