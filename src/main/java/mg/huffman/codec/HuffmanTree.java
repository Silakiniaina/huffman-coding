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

    public void reconstructTree(Map<Character, String> codeMap) {
        root = new Node('\0', 0);
        for (Map.Entry<Character, String> entry : codeMap.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            Node current = root;
            for (int i = 0; i < code.length(); i++) {
                char bit = code.charAt(i);
                if (bit == '0') {
                    if (current.left == null) {
                        current.left = new Node('\0', 0);
                        current.left.isLeaf = false;
                    }
                    current = current.left;
                } else if (bit == '1') {
                    if (current.right == null) {
                        current.right = new Node('\0', 0);
                        current.right.isLeaf = false;
                    }
                    current = current.right;
                }
                if (i == code.length() - 1) {
                    current.character = character;
                    current.isLeaf = true;
                }
            }
        }
    }

    public String encodeCharacter(char character) {
        return encodingMap.get(character);
    }

    public String decode(String encodedText) {
        StringBuilder decoded = new StringBuilder();
        Node current = root;
        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }
            if (current.isLeaf) {
                decoded.append(current.character);
                current = root;
            }
        }
        return decoded.toString();
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
