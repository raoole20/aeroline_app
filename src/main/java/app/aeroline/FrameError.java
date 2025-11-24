package app.aeroline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameError {
    public static void ViewError(String message, Runnable onRetry) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 220);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(220, 53, 69)); // rojo vibrante

        JLabel titleLabel = new JLabel("Error al conectar la base de datos", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        frame.add(titleLabel, BorderLayout.NORTH);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(messageLabel, BorderLayout.CENTER);

        JButton retryButton = new JButton("Reintentar");
        retryButton.setFont(new Font("Arial", Font.BOLD, 16));
        retryButton.setBackground(new Color(255, 255, 255));
        retryButton.setForeground(new Color(220, 53, 69));
        retryButton.setFocusPainted(false);
        retryButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                if (onRetry != null) {
                    onRetry.run();
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 53, 69));
        buttonPanel.add(retryButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

