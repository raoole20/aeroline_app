package app.Models.Views.Home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import app.Models.DTOs.Flight;
import app.Models.types.AppColors;
import app.components.customComboBox.CustomComboBoxEditor;
import app.components.flightcard.FlightCard;
import app.components.searchbar.SearchBar;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Home {
    private JPanel mainPanel;
    @SuppressWarnings("unused")
    private Dimension mainPanelDimension = new Dimension(Integer.MAX_VALUE, 300);

    // childrens
    private JPanel optionsMenuPanel;
    private JPanel resultPanel;

    public Home(JPanel parentElement) {
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Establece el color de fondo con transparencia
        this.mainPanel.setBackground(new Color(0, 0, 0, 0)); // RGBA, donde A es el componente alfa

        // Hace que el JPanel sea transparente
        this.mainPanel.setOpaque(false);

        // default childrens
        this.optionsMenu(); // NORT
        this.bodyHome(); // CENTER

        parentElement.add(this.mainPanel, BorderLayout.CENTER);
    }

    public void optionsMenu() {
        this.optionsMenuPanel = new JPanel();
        this.optionsMenuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        this.optionsMenuPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.optionsMenuPanel.setBackground(Color.WHITE);

        new CustomComboBoxEditor(
                optionsMenuPanel,
                "From:",
                "src/main/java/app/assets/home.png",
                new ArrayList<>());
        new CustomComboBoxEditor(
                optionsMenuPanel,
                "To:",
                "/src/main/java/app/assets/home.png",
                new ArrayList<>());
        new CustomComboBoxEditor(
                optionsMenuPanel,
                "Depature Date:",
                "/src/main/java/app/assets/home.png",
                new ArrayList<>());
        new CustomComboBoxEditor(
                optionsMenuPanel,
                "Return Date:",
                "/src/main/java/app/assets/home.png",
                new ArrayList<>());

        this.mainPanel.add(this.optionsMenuPanel, BorderLayout.NORTH);
    }

    public void bodyHome() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setOpaque(false);
        bodyPanel.setBorder(new EmptyBorder(10, 10, 0, 0));

        var searchBar = new SearchBar();

        bodyPanel.add(searchBar.getSearchBar(), BorderLayout.NORTH);
        bodyPanel.add(this.bodyContainer(), BorderLayout.CENTER);
        this.mainPanel.add(bodyPanel, BorderLayout.CENTER);
    }

    private JPanel bodyContainer() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);

        this.resultPanel = new JPanel(); // ** Container for the results
                                         // <--------------------------------------------------------------------------------------------------------------------------------------
        this.resultPanel.setLayout(new BoxLayout(this.resultPanel, BoxLayout.Y_AXIS));
        this.resultPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(this.resultPanel);
        scrollPane.setBorder(new EmptyBorder(10, 0, 0, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(Color.white);

        ArrayList<Flight> flights = new ArrayList<Flight>(Arrays.asList(
            new Flight("1", "FL100", "New York", "London", "src/main/java/app/assets/search.png", "08:00", "20:00", "500", "Airline A", "12h"),
            new Flight("2", "FL101", "Tokyo", "San Francisco", "src/main/java/app/assets/search.png", "09:00", "21:00", "700", "Airline B", "12h"),
            new Flight("3", "FL102", "Berlin", "Paris", "src/main/java/app/assets/search.png", "10:00", "12:00", "200", "Airline C", "2h"),
            new Flight("4", "FL103", "Sydney", "Los Angeles", "src/main/java/app/assets/search.png", "11:00", "23:00", "900", "Airline D", "12h"),
            new Flight("5", "FL104", "Dubai", "Toronto", "src/main/java/app/assets/search.png", "12:00", "00:00", "1000", "Airline E", "12h"),
            new Flight("6", "FL105", "São Paulo", "New York", "src/main/java/app/assets/search.png", "13:00", "01:00", "800", "Airline F", "12h"),
            new Flight("7", "FL106", "Cairo", "Rome", "src/main/java/app/assets/search.png", "14:00", "16:00", "300", "Airline G", "2h"),
            new Flight("8", "FL107", "Bangkok", "Tokyo", "src/main/java/app/assets/search.png", "15:00", "21:00", "400", "Airline H", "6h"),
            new Flight("9", "FL108", "Moscow", "Berlin", "src/main/java/app/assets/search.png", "16:00", "18:00", "250", "Airline I", "2h"),
            new Flight("10", "FL109", "Lima", "Buenos Aires", "src/main/java/app/assets/search.png", "17:00", "21:00", "350", "Airline J","4h")
            )
        );
        this.setResults(flights);

        container.add(this.bodyContainerHeader(), BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        return container;
    }

    private JPanel bodyContainerHeader() {
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(10, 0, 0, 0));

        var title = new JLabel("Select Return Flight");
        title.setFont(new Font("Arial", Font.BOLD, 20));

        var smallText = new JLabel("Select the return flight that best suits your needs");
        smallText.setFont(new Font("Arial", Font.PLAIN, 12));
        smallText.setForeground(AppColors.TEXT_COLOR_SECONDARY);
        smallText.setBorder(new EmptyBorder(0, 20, 0, 0));
        smallText.setAlignmentY(-1);

        header.add(title);
        header.add(smallText);
        return header;
    }

    public void setResults(ArrayList<Flight> results) {
        this.resultPanel.removeAll();

        for (Flight result : results) {
            var resultCardElement = new FlightCard(
                this.resultPanel,
                result
            );
            this.resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        this.resultPanel.revalidate();
        this.resultPanel.repaint();
    }

    public void loader(JPanel parentElement) {
        try {
            Icon icon = new ImageIcon("src/main/java/app/assets/Flying airplane.gif");
            JLabel label = new JLabel(icon);

            parentElement.add(label, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
