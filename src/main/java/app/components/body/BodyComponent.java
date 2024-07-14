package app.components.body;

import java.awt.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import app.Models.Views.Home.Home;
import app.Models.types.AppColors;
import app.Models.types.InnerRoutes;

public class BodyComponent {
    private JPanel mainPanel;

    // default childrens
    private Home home;

    public BodyComponent(JFrame parentElement) {
        this.mainPanel = new JPanel();
        // this.mainPanel.setBackground(AppColors.COLUMBIA_BLUE);
        this.mainPanel.setBackground(Color.WHITE); // RGBA, donde A es el componente alfa
        this.mainPanel.setLayout(new BorderLayout());

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
        JDateChooser dateChooser = new JDateChooser();
        // Configura el dateChooser según necesites
        // Por ejemplo, añadirlo a un panel
        mainPanel.add(dateChooser);
        // mainPanel.add(label);
    }

    private void tickets() {
        JLabel label = new JLabel("tickWets");
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
        this.home = new Home(this.mainPanel);
    }
}
