package mg.huffman.codec;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private Node root;
    private Map<Character, String> encodingMap;
    private Map<String, Character> decodingMap;

    /* -------------------------------------------------------------------------- */
    /*                                Constructors                                */
    /* -------------------------------------------------------------------------- */
    public HuffmanTree() {
        this.setEncodingMap(new HashMap<>());
        this.setDecodingMap(new HashMap<>());
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    public void buildTree(Map<Character, Double> probabilities) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Double> entry : probabilities.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node parent = new Node(left, right);
            priorityQueue.add(parent);
        }
        root = priorityQueue.poll();
        generateCodes(root, "");
    }
    
    private void generateCodes(Node node, String code) {
        if (node.isLeaf) {
            encodingMap.put(node.character, code);
            decodingMap.put(code, node.character);
            return;
        }
        generateCodes(node.left, code + "0");
        generateCodes(node.right, code + "1");
    }

    public String encodeCharacter(char character) {
        return encodingMap.get(character);
    }

    public char decodeString(String code) {
        return decodingMap.get(code);
    }




    /* -------------------------------------------------------------------------- */
    /*                                   Getters                                  */
    /* -------------------------------------------------------------------------- */
    public Node getRoot() {
        return root;
    }
    public Map<Character, String> getEncodingMap() {
        return encodingMap;
    }
    public Map<String, Character> getDecodingMap() {
        return decodingMap;
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Setter                                   */
    /* -------------------------------------------------------------------------- */
    public void setRoot(Node root) {
        this.root = root;
    }
    public void setEncodingMap(Map<Character, String> encodingMap) {
        this.encodingMap = encodingMap;
    }
    public void setDecodingMap(Map<String, Character> decodingMap) {
        this.decodingMap = decodingMap;
    }
    
}
