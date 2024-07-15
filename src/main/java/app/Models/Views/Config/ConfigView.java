package app.Models.Views.Config;

import javax.swing.*;
import java.awt.*;

public class ConfigView {
    private JPanel mainPanel;

    public ConfigView(JPanel parentElement) {
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.WHITE); // RGBA, donde A es el componente alfa
        this.mainPanel.setLayout(new BorderLayout());

        parentElement.add(this.mainPanel, BorderLayout.CENTER);

        // build
        buildInfoContainer();
    }

    public void buildInfoContainer() {
        JOptionPane creatorContainer = new JOptionPane();
        creatorContainer.setBackground(Color.WHITE);
        creatorContainer.setLayout(new BorderLayout());
        creatorContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel container = new JPanel();
        container.setBackground(Color.WHITE);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel creatorLabel = new JLabel("Creado por: ");
        JButton creatorButton = new JButton("Ver detalles");

        creatorButton.addActionListener((e) -> {
            JOptionPane.showMessageDialog(creatorContainer, "Creado por: \n Raul Espina \n Melany Molero");
        });

        container.add(creatorLabel);
        container.add(creatorButton);
        creatorContainer.add(container);
        this.mainPanel.add(creatorContainer, BorderLayout.WEST);
    }
}
