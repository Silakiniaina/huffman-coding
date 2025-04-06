package mg.huffman.io;

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
