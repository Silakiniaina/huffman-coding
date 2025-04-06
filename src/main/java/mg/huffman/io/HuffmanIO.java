package mg.huffman.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import mg.huffman.codec.HuffmanCodec;
import mg.huffman.codec.HuffmanTree;

public class HuffmanIO {
    
    private HuffmanCodec codec;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public HuffmanIO(){
        this.setCodec(new HuffmanCodec());
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    public void compressFile(String inputFile, String outputFile) throws IOException {
        byte[] fileData = Files.readAllBytes(Paths.get(inputFile));
        String text = new String(fileData, StandardCharsets.UTF_8);
        String encodedText = codec.processText(text);
        Map<Character, String> encodingMap = codec.getTree().getEncodingMap();

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(outputFile))) {
            out.writeInt(text.length());
            out.writeInt(encodingMap.size());
            for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
                out.writeInt((int) entry.getKey());
                out.writeUTF(entry.getValue());
            }
            out.writeInt(encodedText.length());
            BitSet bitSet = new BitSet();
            for (int i = 0; i < encodedText.length(); i++) {
                if (encodedText.charAt(i) == '1') {
                    bitSet.set(i);
                }
            }
            int numBytes = (encodedText.length() + 7) / 8;
            byte[] bytes = new byte[numBytes];
            for (int i = 0; i < numBytes * 8; i++) {
                if (i < encodedText.length() && bitSet.get(i)) {
                    bytes[i/8] |= (1 << (7 - (i % 8)));
                }
            }
            out.write(bytes);
        }
    }

    public void decompressFile(String inputFile, String outputFile) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(inputFile))) {
            int textLength = in.readInt();
            int dictSize = in.readInt();
            Map<Character, String> encodingMap = new HashMap<>();
            Map<String, Character> decodingMap = new HashMap<>();
            for (int i = 0; i < dictSize; i++) {
                char character = (char) in.readInt();
                String code = in.readUTF();
                encodingMap.put(character, code);
                decodingMap.put(code, character);
            }
            HuffmanTree tree = new HuffmanTree();
            tree.setEncodingMap(encodingMap);
            tree.setDecodingMap(decodingMap);
            tree.reconstructTree(encodingMap);
            codec.setTree(tree);
            int numBits = in.readInt();
            int numBytes = (numBits + 7) / 8;
            byte[] bytes = new byte[numBytes];
            in.readFully(bytes);
            StringBuilder encodedText = new StringBuilder(numBits);
            for (int i = 0; i < numBits; i++) {
                int byteIndex = i / 8;
                int bitIndex = 7 - (i % 8);
                boolean bit = ((bytes[byteIndex] >> bitIndex) & 1) == 1;
                encodedText.append(bit ? '1' : '0');
            }
            String decodedText = codec.decode(encodedText.toString());
            if (decodedText.length() != textLength) {
                System.err.println("Warning: Decoded text length (" + decodedText.length() +
                                ") different from original (" + textLength + ")");
            }
            Files.write(Paths.get(outputFile), decodedText.getBytes(StandardCharsets.UTF_8));
        }
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Getters                                  */
    /* -------------------------------------------------------------------------- */
    public HuffmanCodec getCodec() {
        return codec;
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Setters                                  */
    /* -------------------------------------------------------------------------- */
    public void setCodec(HuffmanCodec codec) {
        this.codec = codec;
    }
}
