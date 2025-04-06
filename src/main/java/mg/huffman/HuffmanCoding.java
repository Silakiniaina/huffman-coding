package mg.huffman;

import mg.huffman.io.HuffmanIO;

public class HuffmanCoding {
    
    /* -------------------------------------------------------------------------- */
    /*                                    Test                                    */
    /* -------------------------------------------------------------------------- */
    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                System.out.println("Usage: java HuffmanCoding [compress|decompress] inputFile outputFile");
                return;
            }
            
            String mode = args[0];
            String inputFile = args[1];
            String outputFile = args[2];
            
            HuffmanIO huffmanIO = new HuffmanIO();
            
            if ("compress".equals(mode)) {
                huffmanIO.compressFile(inputFile, outputFile);
                System.out.println("File compressed successfully!");
            } else if ("decompress".equals(mode)) {
                huffmanIO.decompressFile(inputFile, outputFile);
                System.out.println("File decompressed successfully!");
            } else {
                System.out.println("Unrecognized mode. Use 'compress' or 'decompress'.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
