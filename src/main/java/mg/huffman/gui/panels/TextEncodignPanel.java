package mg.huffman.gui.panels;

import javax.swing.JButton;
import javax.swing.JTextArea;

import mg.huffman.codec.HuffmanCodec;

public class TextEncodignPanel extends BasePanel{
    
    private final HuffmanCodec codec;
    private final JTextArea inputArea;
    private final JTextArea outputArea;
    private final JTextArea mapArea;
    private final JButton encodeButton;

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
