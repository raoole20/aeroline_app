


package app.Models.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class MelanyView {
    private static JFrame mainPanel;

    public static void createAndShowGUI(JFrame container) {
        // Establecer el panel principal como el contenedor principal
        mainPanel = container;
        mainPanel.setLayout(new BorderLayout());
 
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel errorLabel = new JLabel("Error 500 - Internal Server Error");
        errorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        errorLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea messageArea = new JTextArea("Ha ocurrido un error interno del servidor. Por favor, inténtelo más tarde.");
        messageArea.setBackground(new Color(218, 238, 255)); // Establecer el color de fondo del JTextArea
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        messageArea.setAlignmentX(100);
        messageArea.setAlignmentY(0);
   
        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
//        panel.add(errorLabel, gbc);
        gbc.gridy = 1;
                messageArea.add(errorLabel, gbc);
        panel.add(scrollPane, gbc);

        // Agregar el panel al panel principal
        mainPanel.add(panel, BorderLayout.CENTER);


        // Establecer el tamaño del panel principal a pantalla completa
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel.setPreferredSize(screenSize);
        mainPanel.setBackground(new Color(218, 238, 255)); // Establecer el color de fondo del panel principal
        mainPanel.setVisible(true);
    }
}