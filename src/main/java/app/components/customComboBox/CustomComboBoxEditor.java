package app.components.customComboBox;

import java.util.ArrayList;
import javax.swing.*;

import app.Models.types.AppColors;

import java.awt.*; 

public class CustomComboBoxEditor {
    private JPanel mainPanel;
    private JLabel label;
    private JComboBox<Object> comboBox;
    private JButton icon = new JButton();

    public CustomComboBoxEditor(
        JPanel parentElement,
        String text,
        String url,
        ArrayList<String> options
    ) {
        this.mainPanel = new JPanel();
        this.mainPanel.setPreferredSize(new Dimension(150, 48));
        this.mainPanel.setMinimumSize(new Dimension(100, 48));
        this.mainPanel.setMaximumSize(new Dimension(150, 48));
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBackground(new Color(0, 0, 0, 0));
        this.mainPanel.setOpaque(false);

        this.label = new JLabel(text);

        options.add("test 1");
        options.add("test 1");
        options.add("test 1");
        options.add("test 1");
        options.add("test 1");
        options.add("test 1");
        this.comboBox = new JComboBox<Object>(options.toArray());
        this.comboBox.setBackground(Color.WHITE);
        this.setIcon(url);

        this.mainPanel.add(this.label, BorderLayout.NORTH);
        this.mainPanel.add(this.comboBox, BorderLayout.CENTER);

        parentElement.add(this.mainPanel);
    }

    public void setIcon(String url) {
        this.icon.setFocusPainted(false); // Esto evita que se pinte el borde de enfoque
        this.icon.setIcon(new ImageIcon(url)); // NOI18N
        this.icon.setContentAreaFilled(false);
        this.icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.icon.setBorderPainted(false); // Opcional, si también quieres quitar el borde.
        this.icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        this.icon.setForeground(Color.white);

        // this.mainPanel.add(this.icon, BorderLayout.WEST);
    }


}