package mg.huffman.gui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public abstract class FileOperationsPanel extends BasePanel {
    protected final JTextField inputField;
    protected final JTextField outputField;
    protected final JButton browseInputButton;
    protected final JButton browseOutputButton;
    protected final JButton actionButton;

    /* -------------------------------------------------------------------------- */
    /*                                Constructors                                */
    /* -------------------------------------------------------------------------- */
    public FileOperationsPanel(String actionButtonText) {
        super();
        setLayout(new GridBagLayout());

        inputField = new JTextField(30);
        outputField = new JTextField(30);
        browseInputButton = new JButton("Browse");
        browseOutputButton = new JButton("Browse");
        actionButton = new JButton(actionButtonText);

        setupLayout();
        setupListeners();
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel(getInputLabelText()), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(inputField, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(browseInputButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel(getOutputLabelText()), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(outputField, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(browseOutputButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(actionButton, gbc);
    }

    private void setupListeners() {
        browseInputButton.addActionListener(e -> {
            String path = browseForFile();
            if (path != null) {
                inputField.setText(path);
            }
        });

        browseOutputButton.addActionListener(e -> {
            String path = browseForFile();
            if (path != null) {
                outputField.setText(path);
            }
        });

        actionButton.addActionListener(e -> performAction());
    }

    /* -------------------------------------------------------------------------- */
    /*                             Abstract functions                             */
    /* -------------------------------------------------------------------------- */
    protected abstract String getInputLabelText();
    protected abstract String getOutputLabelText();
    protected abstract void performAction();

    /* -------------------------------------------------------------------------- */
    /*                             Override functions                             */
    /* -------------------------------------------------------------------------- */
    @Override
    public void clear() {
        inputField.setText("");
        outputField.setText("");
    }
}