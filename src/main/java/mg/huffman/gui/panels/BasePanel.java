package mg.huffman.gui.panels;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mg.huffman.gui.ClearablePanel;

public abstract class BasePanel extends JPanel implements ClearablePanel {

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public BasePanel() {
       setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    protected void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    protected void showError(String message) {
        showMessage(message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected void showSuccess(String message) {
        showMessage(message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
