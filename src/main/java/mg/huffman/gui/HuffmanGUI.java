package mg.huffman.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import mg.huffman.codec.HuffmanCodec;
import mg.huffman.gui.panels.FileCompressionPanel;
import mg.huffman.gui.panels.FileDecompressionPanel;
import mg.huffman.gui.panels.TextDecodingPanel;
import mg.huffman.gui.panels.TextEncodingPanel;
import mg.huffman.io.HuffmanIO;

public class HuffmanGUI extends JFrame {
    private final HuffmanCodec codec;
    private final HuffmanIO huffmanIO;
    private final TabsManager tabsManager;

    /* -------------------------------------------------------------------------- */
    /*                                 Constructor                                */
    /* -------------------------------------------------------------------------- */
    public HuffmanGUI() {
        codec = new HuffmanCodec();
        huffmanIO = new HuffmanIO();
        initializeUI();
        tabsManager = new TabsManager(codec, huffmanIO);
    }

    /* -------------------------------------------------------------------------- */
    /*                                  Functions                                 */
    /* -------------------------------------------------------------------------- */
    private void initializeUI() {
        setTitle("Huffman Coding Tool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 900);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane tabbedPane = createTabbedPane();
        JPanel buttonPanel = createButtonPanel(tabbedPane);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Text Encoding", new TextEncodingPanel(codec));
        tabbedPane.addTab("Text Decoding", new TextDecodingPanel(codec));
        tabbedPane.addTab("File Compression", new FileCompressionPanel(huffmanIO));
        tabbedPane.addTab("File Decompression", new FileDecompressionPanel(huffmanIO));
        return tabbedPane;
    }

    private JPanel createButtonPanel(JTabbedPane tabbedPane) {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton clearButton = new JButton("Clear Current Tab");

        clearButton.addActionListener(e -> {
            int selectedTab = tabbedPane.getSelectedIndex();
            if (selectedTab >= 0 && selectedTab < tabbedPane.getTabCount()) {
                Component component = tabbedPane.getComponentAt(selectedTab);
                if (component instanceof ClearablePanel) {
                    ((ClearablePanel) component).clear();
                }
            }
        });

        buttonPanel.add(clearButton);
        return buttonPanel;
    }
}