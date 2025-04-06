package mg.huffman.gui;

import mg.huffman.codec.HuffmanCodec;
import mg.huffman.codec.HuffmanTree;
import mg.huffman.io.HuffmanIO;

public class TabsManager {
    private final HuffmanCodec codec;
    private final HuffmanIO huffmanIO;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public TabsManager(HuffmanCodec codec, HuffmanIO huffmanIO) {
        this.codec = codec;
        this.huffmanIO = huffmanIO;
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    public void resetCodec() {
        codec.setTree(new HuffmanTree());
    }
}
