package app.components.searchbar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;

import javax.swing.*;

import app.Models.types.AppColors;

public class SearchBar {
    private JPanel mainPanel;
    private JPanel searchContainer;

    // children
    private JTextField searchInput;
    private JButton searchButton;

    //status 
    private boolean firstFocus = true;

    public SearchBar() {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        this.mainPanel.setOpaque(false);

        this.searchContainer = new JPanel();
        this.searchContainer.setOpaque(false);
        this.searchContainer.setBorder(BorderFactory.createLineBorder(AppColors.LAPIS_LAZULI));
        this.buildSearchInput();
        this.buildSearchButton();

        this.mainPanel.add(this.searchContainer);
    }

    public JPanel getSearchBar() {
        return this.mainPanel;
    }

    private void buildSearchInput() {
        this.searchInput = new JTextField("Search...");
        this.searchInput.setPreferredSize(new Dimension(300, 20));
        this.searchInput.setMinimumSize(new Dimension(300, 20));
        this.searchInput.setMaximumSize(new Dimension(350, 20));
        this.searchInput.setBorder(BorderFactory.createLineBorder(AppColors.LAPIS_LAZULI));
        this.searchInput.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        this.searchInput.setForeground(Color.GRAY);
        this.searchInput.setForeground(Color.GRAY);

        this.searchInput.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchInput.getText().equals("Search...") && firstFocus) {
                    searchInput.setText("");
                    searchInput.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchInput.getText().isEmpty()) {
                    searchInput.setForeground(Color.GRAY);
                    searchInput.setText("Search...");
                }
            }
        });
        this.searchInput.setColumns(20);
        this.searchInput.setToolTipText("Search...");

        this.searchContainer.add(this.searchInput);
    }

    private void buildSearchButton() {
        this.searchButton = new JButton();
        this.searchButton.setPreferredSize(new Dimension(50, 20));
        this.searchButton.setMinimumSize(new Dimension(50, 20));
        this.searchButton.setMaximumSize(new Dimension(50, 20));
        this.searchButton.setIcon(new ImageIcon("src/main/java/app/assets/search.png"));
        this.searchButton.setToolTipText("Search");
        this.searchButton.setBackground(AppColors.LAPIS_LAZULI);
        this.searchButton.setOpaque(false);
        this.searchButton.setBorderPainted(false);
        this.searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.searchButton.setContentAreaFilled(false);
        this.searchButton.setFocusPainted(false);

        this.searchContainer.add(this.searchButton);
    }
}
