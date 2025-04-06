package mg.huffman.codec;

import java.util.HashMap;
import java.util.Map;

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
    public String encodeCharacter(char character) {
        return encodingMap.get(character);
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
