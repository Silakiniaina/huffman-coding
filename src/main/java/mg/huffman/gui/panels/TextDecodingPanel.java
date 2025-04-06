package mg.huffman.gui.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import mg.huffman.codec.HuffmanCodec;
import mg.huffman.codec.HuffmanTree;

public class TextDecodingPanel extends BasePanel {
    private final HuffmanCodec codec;
    private final JTextArea inputArea;
    private final JTextArea outputArea;
    private final JTextArea dictionaryArea;
    private final JButton decodeButton;
    private final JButton setDictionaryButton;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public TextDecodingPanel(HuffmanCodec codec) {
        super();
        this.codec = codec;
        setLayout(new BorderLayout(5, 5));

        inputArea = createTextArea(10, 30);
        outputArea = createTextArea(20, 30);
        outputArea.setEditable(false);
        dictionaryArea = createTextArea(10, 30);
        decodeButton = new JButton("Decode");
        setDictionaryButton = new JButton("Set Dictionary");

        JPanel inputPanel = createLabeledPanel("Encoded Text:", new JScrollPane(inputArea));
        JPanel outputPanel = createLabeledPanel("Decoded Text:", new JScrollPane(outputArea));
        JPanel dictionaryPanel = createDictionaryPanel();

        JSplitPane leftSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, dictionaryPanel);
        leftSplit.setResizeWeight(0.5);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(leftSplit, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(outputPanel, BorderLayout.CENTER);

        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        mainSplit.setResizeWeight(0.5);

        add(mainSplit, BorderLayout.CENTER);
        add(decodeButton, BorderLayout.SOUTH);

        decodeButton.addActionListener(e -> decodeText());
        setDictionaryButton.addActionListener(e -> setDictionary());
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    private JTextArea createTextArea(int rows, int cols) {
        JTextArea textArea = new JTextArea(rows, cols);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JPanel createLabeledPanel(String label, Component component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(label), BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createDictionaryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Dictionary (e.g., {A=1010,B=011}):"), BorderLayout.NORTH);
        panel.add(new JScrollPane(dictionaryArea), BorderLayout.CENTER);
        panel.add(setDictionaryButton, BorderLayout.SOUTH);
        return panel;
    }

    private void setDictionary() {
        try {
            String dictText = dictionaryArea.getText().trim();
            if (dictText.isEmpty()) {
                showError("Please enter a dictionary");
                return;
            }

            Map<Character, String> encodingMap = new HashMap<>();
            dictText = dictText.replaceAll("[\\{\\}\\s]", "");
            String[] pairs = dictText.split(",");

            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length != 2) {
                    throw new IllegalArgumentException("Invalid dictionary format");
                }
                char character = keyValue[0].charAt(0);
                String code = keyValue[1];
                encodingMap.put(character, code);
            }

            HuffmanTree tree = new HuffmanTree();
            tree.setEncodingMap(encodingMap);

            Map<String, Character> decodingMap = new HashMap<>();
            for (Map.Entry<Character, String> entry : encodingMap.entrySet()) {
                decodingMap.put(entry.getValue(), entry.getKey());
            }
            tree.setDecodingMap(decodingMap);
            tree.reconstructTree(encodingMap);
            codec.setTree(tree);

            showSuccess("Dictionary set successfully!");

        } catch (Exception e) {
            showError("Error setting dictionary: " + e.getMessage());
        }
    }

    private void decodeText() {
        try {
            String encodedText = inputArea.getText();
            if (encodedText.isEmpty()) {
                showError("Please enter encoded text to decode");
                return;
            }

            if (codec.getTree().getEncodingMap().isEmpty()) {
                showError("Please set a dictionary first");
                return;
            }

            String decodedText = codec.decode(encodedText);
            outputArea.setText(decodedText);
            showSuccess("Decoding successful!");

        } catch (Exception e) {
            showError("Error during decoding: " + e.getMessage());
        }
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Override                                  */
    /* -------------------------------------------------------------------------- */
    @Override
    public void clear() {
        inputArea.setText("");
        outputArea.setText("");
        dictionaryArea.setText("");
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Getters                                  */
    /* -------------------------------------------------------------------------- */
    public HuffmanCodec getCodec() {
        return codec;
    }
    public JTextArea getInputArea() {
        return inputArea;
    }
    public JTextArea getOutputArea() {
        return outputArea;
    }
    public JTextArea getDictionaryArea() {
        return dictionaryArea;
    }
    public JButton getDecodeButton() {
        return decodeButton;
    }
    public JButton getSetDictionaryButton() {
        return setDictionaryButton;
    }
    
}