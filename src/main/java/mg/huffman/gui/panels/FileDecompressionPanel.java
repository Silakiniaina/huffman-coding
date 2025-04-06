package mg.huffman.gui.panels;

import java.io.File;
import java.io.IOException;

import mg.huffman.io.HuffmanIO;

public class FileDecompressionPanel extends FileOperationsPanel {
    private final HuffmanIO huffmanIO;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public FileDecompressionPanel(HuffmanIO huffmanIO) {
        super("Decompress File");
        this.huffmanIO = huffmanIO;
    }

    /* -------------------------------------------------------------------------- */
    /*                             Overrides functions                            */
    /* -------------------------------------------------------------------------- */
    @Override
    protected String getInputLabelText() {
        return "Input File:";
    }

    @Override
    protected String getOutputLabelText() {
        return "Output File (.txt):";
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

            if (!outputPath.endsWith(".txt")) {
                showError("Output file must be a .txt file");
                return;
            }

            File inputFile = new File(inputPath);
            if (!inputFile.exists()) {
                showError("Input file does not exist");
                return;
            }

            huffmanIO.decompressFile(inputPath, outputPath);

            long inputSize = inputFile.length();
            long outputSize = new File(outputPath).length();

            showSuccess("File decompression successful!\nCompressed size: " + inputSize + " bytes\n" +
                        "Decompressed size: " + outputSize + " bytes");

        } catch (IOException e) {
            showError("Error during file decompression: " + e.getMessage());
        }
    }
}

