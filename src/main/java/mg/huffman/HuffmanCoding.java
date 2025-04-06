package mg.huffman;

import mg.huffman.io.HuffmanIO;

public class HuffmanCoding {
    
    /* -------------------------------------------------------------------------- */
    /*                                    Test                                    */
    /* -------------------------------------------------------------------------- */
    public static void main(String[] args) {
        try {
            if (args.length < 3) {
                System.out.println("Usage: java HuffmanCoding [compress] inputFile outputFile");
                return;
            }
            
            String mode = args[0];
            String inputFile = args[1];
            String outputFile = args[2];
            
            HuffmanIO huffmanIO = new HuffmanIO();
            
            if ("compress".equals(mode)) {
                huffmanIO.compressFile(inputFile, outputFile);
                System.out.println("File compressed successfully!");
            }else {
                System.out.println("Unrecognized mode. Use 'compress'");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
