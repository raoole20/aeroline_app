package app.Models.Views.Config;

import javax.swing.*;
import java.awt.*;

public class ConfigView {
    private JPanel mainPanel;

    public ConfigView(JPanel parentElement) {
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.WHITE); // RGBA, donde A es el componente alfa
        this.mainPanel.setLayout(new BorderLayout());

        JLabel tempLabel = new JLabel("Test config view");

        this.mainPanel.add(tempLabel, BorderLayout.CENTER);

        parentElement.add(this.mainPanel, BorderLayout.CENTER);
    }


    
}
