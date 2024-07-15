package app.components.Seats;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.*;

import app.Models.types.AppColors;

public class SeatsButton {
    private JButton button;
    private boolean isAvailable = true;
    private boolean isSelected = false;
    private String seatsCODE;
    private int seatID;
    private Runnable callback;

    public SeatsButton(
        boolean isAvailable, 
        boolean isSelected, 
        String seatsCODE,
        int seatID,
        Runnable callback
    ) {
        this.seatID = seatID;
        this.callback = callback;

        this.button = new JButton();
        this.button = new JButton();
        this.button.setPreferredSize(new Dimension(50, 50));
        this.button.setMinimumSize(new Dimension(50, 50));
        this.button.setMaximumSize(new Dimension(50, 50));

        if(isAvailable) {
            this.button.setIcon(new ImageIcon("src/main/java/app/assets/car.png"));
        } else  {
            this.button.setIcon(new ImageIcon("src/main/java/app/assets/car (2).png"));
        }

        this.button.setToolTipText(seatsCODE);
        this.button.setBackground(AppColors.LAPIS_LAZULI);
        this.button.setOpaque(false);
        this.button.setBorderPainted(false);
        this.button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.button.setContentAreaFilled(false);
        this.button.setFocusPainted(false);

        this.isAvailable = isAvailable;
        this.isSelected = isSelected;
        this.seatsCODE = seatsCODE;
        this.seatID =  seatID;

        this.addEventlistener();
    }

    public JButton getButton() {
        return this.button;
    }

    private void addEventlistener () {
        this.button.addActionListener(e -> {
            if(this.isAvailable) {
                System.out.println("Se cambio el estado del asiento");
                if(this.isSelected) {
                    this.button.setIcon(new ImageIcon("src/main/java/app/assets/car.png"));
                    this.isSelected = false;
                    callback.run();
                } else {
                    this.button.setIcon(new ImageIcon("src/main/java/app/assets/car (1).png"));
                    this.isSelected = true;
                    callback.run();
                }
            }
        });
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public int getSeatID() {
        return this.seatID;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public String getSeatsCODE() {
        return this.seatsCODE;
    }

    public Runnable getCallback() {
        return this.callback;
    }
}
