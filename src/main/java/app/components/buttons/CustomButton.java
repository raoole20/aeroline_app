package app.components.buttons;

import java.awt.*;
import javax.swing.*;

import app.Models.types.AppColors;

public class CustomButton {
    private JButton button;

    public CustomButton(
        String text
    ) {
        this.button = new JButton(text);
        button.setBackground(AppColors.GREEN);
        button.setForeground(Color.WHITE);

        button.setToolTipText(text);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
    }

    public JButton getButton() {
        return this.button;
    }

    public void setSecondary() {
        this.button.setBackground(AppColors.LAPIS_LAZULI);
    }
}
