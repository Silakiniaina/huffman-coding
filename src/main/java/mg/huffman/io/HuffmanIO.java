package mg.huffman.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.Map;

import mg.huffman.codec.HuffmanCodec;

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
