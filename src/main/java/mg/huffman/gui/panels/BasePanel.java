package mg.huffman.gui.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import mg.huffman.gui.ClearablePanel;

public abstract class BasePanel extends JPanel implements ClearablePanel {

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public BasePanel() {
       setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    
}
