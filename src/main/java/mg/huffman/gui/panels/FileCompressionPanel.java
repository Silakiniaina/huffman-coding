package mg.huffman.gui.panels;

import java.io.File;
import java.io.IOException;

import mg.huffman.io.HuffmanIO;

public class FileCompressionPanel extends FileOperationsPanel {
    private final HuffmanIO huffmanIO;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public FileCompressionPanel(HuffmanIO huffmanIO) {
        super("Compress File");
        this.huffmanIO = huffmanIO;
    }

    /* -------------------------------------------------------------------------- */
    /*                              Override function                             */
    /* -------------------------------------------------------------------------- */
    @Override
    protected String getInputLabelText() {
        return "Input File (.txt):";
    }

    @Override
    protected String getOutputLabelText() {
        return "Output File:";
    }

    @Override
    protected void performAction() {
        try {
            String inputPath = inputField.getText();
            String outputPath = outputField.getText();

            if (inputPath.isEmpty() || outputPath.isEmpty()) {
                showError("Please specify both input and output files");
                return;
            }

            if (!inputPath.endsWith(".txt")) {
                showError("Input file must be a .txt file");
                return;
            }

            File inputFile = new File(inputPath);
            if (!inputFile.exists()) {
                showError("Input file does not exist");
                return;
            }

            huffmanIO.compressFile(inputPath, outputPath);

            long inputSize = inputFile.length();
            long outputSize = new File(outputPath).length();

            showSuccess("File compression successful!\nOriginal size: " + inputSize + " bytes\n" +
                        "Compressed size: " + outputSize + " bytes\n" +
                        "Compression ratio: " + String.format("%.2f", (double)inputSize/outputSize) + ":1");

        } catch (IOException e) {
            showError("Error during file compression: " + e.getMessage());
        }
    }
}
