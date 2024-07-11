package app.components.body;

import java.awt.*;
import javax.swing.*;

import app.Models.types.AppColors;
import app.Models.types.InnerRoutes;

public class BodyComponent {
    public JPanel mainPanel;

    public BodyComponent(JFrame parentElement) {
       this.mainPanel = new JPanel();
       this.mainPanel.setBackground(AppColors.COLUMBIA_BLUE);

       this.changeView();
       parentElement.add(this.mainPanel, BorderLayout.CENTER);
    }
    
    private void clearPanel() {
        this.mainPanel.removeAll(); // Elimina todos los componentes
        this.mainPanel.revalidate(); // Revalida el layout del panel
        this.mainPanel.repaint(); // Repinta el panel para reflejar la eliminación
    }

    public void changeView() {
        this.changeView(InnerRoutes.HOME);
    }

    public void changeView(InnerRoutes route) {
        this.clearPanel();

        switch (route) {
            case HOME:
                this.home();
                break;
            case FLIGHTS:
                this.flights();
                break;
            case PASSENGERS:
                this.passengers();
                break;
            case TICKETS:
                this.tickets();
                break;
            case REPORTS:
                this.reports();
                break;
            default:
                this.home();
                break;
        }
    }

    private void reports() {
        JLabel label = new JLabel("reports");
        mainPanel.add(label);
    }


    private void tickets() {
        JLabel label = new JLabel("tickets");
        mainPanel.add(label);
    }


    private void passengers() {
        JLabel label = new JLabel("passengers");
        mainPanel.add(label);
    }


    private void flights() {
        JLabel label = new JLabel("flights");
        mainPanel.add(label);
    }


    private void home() {
        JLabel label = new JLabel("home");
        mainPanel.add(label);
    }
}
