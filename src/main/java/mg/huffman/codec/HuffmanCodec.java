package mg.huffman.codec;

import java.util.HashMap;
import java.util.Map;

public class HuffmanCodec {

    private HuffmanTree tree;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public HuffmanCodec() {
        this.setTree(new HuffmanTree());
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    public Map<Character, Integer> calculateFrequencies(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        return frequencies;
    }

    public Map<Character, Double> calculateProbabilities(Map<Character, Integer> frequencies, int totalChars) {
        Map<Character, Double> probabilities = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            probabilities.put(entry.getKey(), (double) entry.getValue() / totalChars);
        }
        return probabilities;
    }

    public String encode(String text) {
        Map<Character, String> encodingMap = tree.getEncodingMap();
        if (encodingMap.isEmpty()) {
            throw new IllegalStateException("Huffman tree has not been built!");
        }
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(encodingMap.get(c));
        }
        return encoded.toString();
    }

    public String decode(String encodedText) {
        if (tree.getRoot() == null) {
            throw new IllegalStateException("Huffman tree has not been built!");
        }
        return tree.decode(encodedText);
    }

    public String processText(String text) {
        Map<Character, Integer> frequencies = calculateFrequencies(text);
        Map<Character, Double> probabilities = calculateProbabilities(frequencies, text.length());
        tree.buildTree(probabilities);
        return encode(text);
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Getters                                  */
    /* -------------------------------------------------------------------------- */
    public HuffmanTree getTree() {
        return tree;
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Setters                                  */
    /* -------------------------------------------------------------------------- */
    public void setTree(HuffmanTree tree) {
        this.tree = tree;
    }

    
    
}
