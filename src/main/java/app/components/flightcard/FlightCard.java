package app.components.flightcard;

import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import javax.swing.*;

import app.Models.DTOs.Flight;
import app.Models.types.AppColors;
import app.Models.types.InnerRoutes;
import app.Models.types.Payload;
import app.classes.Routes;
import app.components.buttons.CustomButton;

public class FlightCard {
    private JPanel mainPanel;
    private JPanel parentPanelElement;
    private Flight data;

    // router controlle
    private Routes routes;

    public FlightCard(
            JPanel parentElement,
            Flight data,
            Routes routes) {
        this.routes = routes;
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        this.parentPanelElement = parentElement;
        this.data = data;

        // add childrens
        this.mainPanel.add(this.buildHeader(), BorderLayout.NORTH);
        this.mainPanel.add(this.buildFeature(), BorderLayout.CENTER);
        this.mainPanel.add(this.buildActionButtons(), BorderLayout.SOUTH);
        this.parentPanelElement.add(this.mainPanel);
    }

    public JPanel buildHeader() {
        JPanel header = new JPanel();
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JPanel companyPanel = new JPanel();
        companyPanel.setBackground(Color.white);
        companyPanel.setLayout(new BorderLayout());
        this.buildCompanyPanel(companyPanel);

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BorderLayout());
        this.buildTimePanel(timePanel);

        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BorderLayout());
        this.buildPricePanel(pricePanel);

        header.add(companyPanel);
        header.add(timePanel);
        header.add(pricePanel);
        return header;
    }

    public JPanel buildFeature() {
        // Crear el borde Matte para la parte superior e inferior
        Border topAndBottomBorders = new MatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY);
        // Crear el EmptyBorder para el espaciado interno
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5); // Ajusta los valores según necesites
        // Combinar ambos bordes
        Border combinedBorder = new CompoundBorder(topAndBottomBorders, emptyBorder);

        JPanel feature = new JPanel();
        feature.setBackground(AppColors.COLUMBIA_BLUE);
        feature.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        feature.setBorder(combinedBorder);

        JLabel featureText = new JLabel("Feature:");
        featureText.setForeground(Color.black);
        featureText.setFont(new Font("Arial", 1, 14));

        JLabel feature1 = new JLabel("Carga USB");
        feature1.setForeground(Color.black);
        feature1.setFont(new Font("Arial", 0, 14)); 
        feature1.setIcon(new ImageIcon(getClass().getResource("/app/assets/usb.png")));

        JLabel feature2 = new JLabel("Sin limite de equipaje");
        feature2.setForeground(Color.black);
        feature2.setFont(new Font("Arial", 0, 14));
        feature2.setIcon(new ImageIcon(getClass().getResource("/app/assets/suitcase.png")));

        JLabel feature3 = new JLabel("Entretenimiento");
        feature3.setForeground(Color.black);
        feature3.setFont(new Font("Arial", 0, 14));
        feature3.setIcon(new ImageIcon(getClass().getResource("/app/assets/tv.png")));

        feature.add(featureText);
        feature.add(Box.createRigidArea(new Dimension(10, 0)));
        feature.add(feature1);
        feature.add(Box.createRigidArea(new Dimension(10, 0)));
        feature.add(feature2);
        feature.add(Box.createRigidArea(new Dimension(10, 0)));
        feature.add(feature3);

        return feature;
    }

    public JPanel buildActionButtons() {
        JPanel actionButtons = new JPanel();
        actionButtons.setBackground(Color.WHITE);
        actionButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        actionButtons.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

        CustomButton selectButton = new CustomButton("CHOOSE FLIGHT");
        selectButton.getButton().addActionListener((e) -> {
            Payload payload = new Payload();
            payload.flighhtID = this.data.getId();
            this.routes.setRoute(
                InnerRoutes.TICKETS,
                payload
            );
        });
        selectButton.setSecondary();    
        actionButtons.add(selectButton.getButton());
        return actionButtons;
    }
    private void buildCompanyPanel(
            JPanel companyPanelParent) {
        JPanel wrapperLogoPanel = new JPanel();
        wrapperLogoPanel.setBackground(Color.white);
        wrapperLogoPanel.setLayout(new BorderLayout());

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.white);
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel logoText = new JLabel(this.data.airline);
        logoText.setForeground(AppColors.BLACK_COLOR);
        logoText.setFont(new Font("Arial", Font.BOLD, 20));
        var logo = new JButton();
        logo.setBorder(BorderFactory.createEmptyBorder());
        logo.setIcon(new ImageIcon(getClass().getResource("/app/assets/visa.png")));
        logo.setToolTipText("Search");
        logo.setBackground(AppColors.LAPIS_LAZULI);
        logo.setOpaque(false);
        logo.setBorderPainted(false);
        logo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logo.setContentAreaFilled(false);
        logo.setFocusPainted(false);

        JPanel logoPanelFooter = new JPanel();
        logoPanelFooter.setBackground(Color.white);
        logoPanelFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel logoFooterText = new JLabel(this.data.flightNumber);
        logoFooterText.setForeground(Color.GRAY);
        CustomButton logoFooterButton = new CustomButton("Bussines");
        // * add to parent
        logoPanel.add(logo);
        // logoPanel.add(logoText);

        // * add to foother */
        logoPanelFooter.add(logoFooterText);
        logoPanelFooter.add(logoFooterButton.getButton());

        // * add to wrapper
        wrapperLogoPanel.add(logoPanel, BorderLayout.NORTH);
        wrapperLogoPanel.add(logoPanelFooter, BorderLayout.SOUTH);

        companyPanelParent.add(wrapperLogoPanel);
    }

    private void buildPricePanel(
            JPanel pricePanelParent) {
        JPanel wrapperPricePanel = new JPanel();
        wrapperPricePanel.setBackground(Color.white);
        wrapperPricePanel.setLayout(new BorderLayout());

        JLabel priceText = new JLabel("$  " + this.data.price + "/ tax");
        priceText.setForeground(AppColors.LAPIS_LAZULI);
        priceText.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel priceFooterText = new JLabel();
        priceFooterText.setForeground(AppColors.COLUMBIA_BLUE);
        priceFooterText.setFont(new Font("Arial", Font.BOLD, 12));
        priceFooterText.setText("<html><strike>$ 3.000.000</strike></html>");

        wrapperPricePanel.add(priceText, BorderLayout.CENTER);
        wrapperPricePanel.add(priceFooterText, BorderLayout.SOUTH);

        pricePanelParent.add(wrapperPricePanel);
    }

    private void buildTimePanel(
            JPanel timePanelParent) {
        JPanel wrapperTimePanel = new JPanel();
        wrapperTimePanel.setBackground(Color.white);
        wrapperTimePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.white);

        JPanel timeIconPanel = new JPanel();
        timeIconPanel.setBackground(Color.white);
        timeIconPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        timeIconPanel.add(this.ImageIconButton());

        JLabel timeIconText = new JLabel("1h 30m - Direct");
        timeIconText.setForeground(Color.GRAY);
        timeIconText.setFont(new Font("Arial", Font.BOLD, 12));
        JPanel centerTimeIconTextPanel = new JPanel();
        centerTimeIconTextPanel.setBackground(Color.white);
        // centerTimeIconTextPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
        // 1));
        centerTimeIconTextPanel.setLayout(new BorderLayout());
        centerTimeIconTextPanel.add(timeIconText, BorderLayout.EAST);

        // * add to center panel
        centerPanel.add(timeIconPanel, BorderLayout.CENTER);
        centerPanel.add(centerTimeIconTextPanel, BorderLayout.SOUTH);

        wrapperTimePanel.add(this.timePanelElement(this.data.departureTime, this.data.departure));
        wrapperTimePanel.add(centerPanel);
        wrapperTimePanel.add(this.timePanelElement(this.data.arrivalTime, this.data.arrival));

        timePanelParent.add(wrapperTimePanel);
    }

    public JPanel timePanelElement(String time, String footer) {
        JPanel timePanel = new JPanel();
        timePanel.setBackground(Color.white);
        timePanel.setLayout(new BorderLayout());

        JLabel timeText = new JLabel(time);
        timeText.setForeground(AppColors.BLACK_COLOR);
        timeText.setFont(new Font("Arial", Font.BOLD, 23));

        JLabel timeFooterText = new JLabel(footer);
        timeFooterText.setForeground(Color.GRAY);
        timeFooterText.setFont(new Font("Arial", Font.BOLD, 12));

        timePanel.add(timeText, BorderLayout.NORTH);
        timePanel.add(timeFooterText, BorderLayout.SOUTH);
        return timePanel;
    }

    public JButton ImageIconButton() {
        var image = new JButton();
        image.setBorder(BorderFactory.createEmptyBorder());
        image.setIcon(new ImageIcon(getClass().getResource("/app/assets/clean_image_with_provided_airplane.png")));
        image.setToolTipText("Search");
        image.setBackground(AppColors.LAPIS_LAZULI);
        image.setOpaque(false);
        image.setBorderPainted(false);
        image.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        image.setContentAreaFilled(false);
        image.setFocusPainted(false);

        return image;
    }
}
