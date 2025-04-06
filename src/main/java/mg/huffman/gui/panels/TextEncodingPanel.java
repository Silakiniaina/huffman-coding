package mg.huffman.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Component;
import java.lang.reflect.Constructor;
import java.util.Map;

import mg.huffman.codec.HuffmanCodec;

public class TextEncodingPanel extends BasePanel{
    
    private HuffmanCodec codec;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JTextArea mapArea;
    private JButton encodeButton;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public TextEncodingPanel(HuffmanCodec codec) {
        super();
        this.codec = codec;
        setLayout(new BorderLayout(5, 5));

        inputArea = createTextArea();
        outputArea = createTextArea();
        outputArea.setEditable(false);
        mapArea = createTextArea();
        mapArea.setEditable(false);
        encodeButton = new JButton("Encode");

        JPanel inputPanel = createLabeledPanel("Text to Encode:", new JScrollPane(inputArea));
        JPanel outputPanel = createLabeledPanel("Encoded Text:", new JScrollPane(outputArea));
        JPanel mapPanel = createLabeledPanel("Huffman Codes:", new JScrollPane(mapArea));

        JSplitPane textSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, outputPanel);
        textSplit.setResizeWeight(0.5);
        JSplitPane mainSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, textSplit, mapPanel);
        mainSplit.setResizeWeight(0.7);

        add(mainSplit, BorderLayout.CENTER);
        add(encodeButton, BorderLayout.SOUTH);

        encodeButton.addActionListener(e -> encodeText());
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(15, 30);
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

    private void encodeText() {
        try {
            String inputText = inputArea.getText();
            if (inputText.isEmpty()) {
                showError("Please enter text to encode");
                return;
            }

            String encodedText = codec.processText(inputText);
            outputArea.setText(encodedText);

            StringBuilder mapText = new StringBuilder();
            for (Map.Entry<Character, String> entry : codec.getTree().getEncodingMap().entrySet()) {
                mapText.append("'")
                       .append(entry.getKey() == '\n' ? "\\n" : entry.getKey())
                       .append("': ")
                       .append(entry.getValue())
                       .append("\n");
            }
            mapArea.setText(mapText.toString());

            showSuccess("Encoding successful!\nOriginal size: " + inputText.length() * 8 + " bits\n" +
                        "Encoded size: " + encodedText.length() + " bits");

        } catch (Exception e) {
            showError("Error during encoding: " + e.getMessage());
        }
    }

    @Override
    public void clear() {
        inputArea.setText("");
        outputArea.setText("");
        mapArea.setText("");
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
    public JTextArea getMapArea() {
        return mapArea;
    }
    public JButton getEncodeButton() {
        return encodeButton;
    }

    /* -------------------------------------------------------------------------- */
    /*                                   Setters                                  */
    /* -------------------------------------------------------------------------- */
    public void setCodec(HuffmanCodec codec) {
        this.codec = codec;
    }
    public void setInputArea(JTextArea inputArea) {
        this.inputArea = inputArea;
    }
    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }
    public void setMapArea(JTextArea mapArea) {
        this.mapArea = mapArea;
    }
    public void setEncodeButton(JButton encodeButton) {
        this.encodeButton = encodeButton;
    }

}
