package mg.huffman.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Component;

import mg.huffman.codec.HuffmanCodec;

public class TextEncodignPanel extends BasePanel{
    
    private final HuffmanCodec codec;
    private final JTextArea inputArea;
    private final JTextArea outputArea;
    private final JTextArea mapArea;
    private final JButton encodeButton;

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

}
