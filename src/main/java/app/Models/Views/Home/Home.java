package app.Models.Views.Home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import app.Models.DTOs.Flight;
import app.Models.types.AppColors;
import app.Services.FlightService;
import app.classes.Routes;
import app.components.customComboBox.CustomComboBoxEditor;
import app.components.flightcard.FlightCard;
import app.components.searchbar.SearchBar;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Home {
    private JPanel mainPanel;
    @SuppressWarnings("unused")
    private Dimension mainPanelDimension = new Dimension(Integer.MAX_VALUE, 300);

    // filters 
    private CustomComboBoxEditor from;
    private CustomComboBoxEditor to;
    private SearchBar searchBar;
    // childrens
    private JPanel optionsMenuPanel;
    private JPanel resultPanel;
    private JPanel bodyPanel;
    private JPanel resultContainer;

    private ArrayList<String> countries = new ArrayList<>(Arrays.asList(
        "Select a country...",
        "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina",
        "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados",
        "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana",
        "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cabo Verde", "Cambodia", "Cameroon",
        "Canada", "Central African Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo, Democratic Republic of the",
        "Congo, Republic of the", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark",
        "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
        "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia",
        "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana",
        "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland",
        "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North",
        "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
        "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives",
        "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova",
        "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
        "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway", "Oman",
        "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines",
        "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
        "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
        "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands",
        "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Sweden",
        "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo",
        "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine",
        "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City",
        "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"
    ));

    // routes controller
    private Routes routes;

    // Service
    private FlightService flightService;

    public Home(
        JPanel parentElement, 
        Routes routes,
        FlightService flightService
        ) {
        this.routes = routes;
        this.flightService = flightService;

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        this.resultContainer = new JPanel();

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

        this.from = new CustomComboBoxEditor(
                optionsMenuPanel,
                "From:",
                "/app/assets/home.png",
                this.countries);
        this.to = new CustomComboBoxEditor(
                optionsMenuPanel,
                "To:",
                "/app/assets/home.png",
                this.countries);
        // new CustomComboBoxEditor(
        //         optionsMenuPanel,
        //         "Depature Date:",
        //         "/app/assets/home.png",
        //         true);
        // new CustomComboBoxEditor(
        //         optionsMenuPanel,
        //         "Return Date:",
        //         "/app/assets/home.png",
        //         true);

        this.mainPanel.add(this.optionsMenuPanel, BorderLayout.NORTH);
    }

    public void flitersChangedEvents(ActionListener callback) {
       this.from.getComboBox().addActionListener(callback);
       this.to.getComboBox().addActionListener(callback);

    }

    public ActionListener callbackChangeComboBox(FlightService service) {
        return (e) -> {
            var from = this.from.getComboBox().getSelectedItem().toString();
            var to = this.to.getComboBox().getSelectedItem().toString();

            if(from.equals("Select a country...") && to.equals("Select a country...")) {
                service.getFlightsAsync()
                    .thenAccept(result -> {
                        System.out.println("Success getFlightsAsync");
                        clearresultContainer();
                        bodyContainer(result);
                        repaintresultContainer();
                    });
            }

            service.getFlightFlithByFilters(
                from,
                to)
                .thenAccept(result -> {
                    System.out.println("Success getFlightsAsync filters");
                    clearresultContainer();
                    bodyContainer(result);
                    repaintresultContainer();
                });
        };
    }

    public void bodyHome() {
        this.bodyPanel = new JPanel();
        this.bodyPanel.setLayout(new BorderLayout());
        this.bodyPanel.setOpaque(false);
        this.bodyPanel.setBorder(new EmptyBorder(10, 10, 0, 0));

        this.searchBar = new SearchBar(
           (search) -> {   
            flightService.getFlightByCountries(search)
                .thenAccept(result -> {
                    System.out.println("Success getFlightsAsync filters");
                    clearresultContainer();
                    bodyContainer(result);
                    repaintresultContainer();
                });
           }
        );

        this.bodyPanel.add(searchBar.getSearchBar(), BorderLayout.NORTH);
        this.bodyPanel.add(this.bodyContainer(null), BorderLayout.CENTER);
        this.mainPanel.add(this.bodyPanel, BorderLayout.CENTER);
    }

    public void clearresultContainer() {
        this.resultContainer.removeAll();
    }

    public void repaintresultContainer() {
        this.resultContainer.revalidate();
        this.resultContainer.repaint();
    }

    public JPanel bodyContainer(ArrayList<Flight> results) {
        this.resultContainer.setLayout(new BorderLayout());
        resultContainer.setBackground(Color.WHITE);
 
        this.resultPanel = new JPanel(); // ** Container for the results // <--------------------------------------------------------------------------------------------------------------------------------------
        this.resultPanel.setLayout(new BoxLayout(this.resultPanel, BoxLayout.Y_AXIS));
        this.resultPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(this.resultPanel);
        scrollPane.setBorder(new EmptyBorder(10, 0, 0, 10));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(Color.white);

        this.setResults(results);

        resultContainer.add(this.bodyContainerHeader(), BorderLayout.NORTH);
        resultContainer.add(scrollPane, BorderLayout.CENTER);
        return resultContainer;
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
        if(results == null) {
            var label = new JLabel("No results found");
            this.resultPanel.add(label);
            return;
        }

        for (Flight result : results) {
            var resultCardElement = new FlightCard(
                this.resultPanel,
                result,
                this.routes
            );
            this.resultPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

    }

    public void loader(JPanel parentElement) {
        try {
            Icon icon = new ImageIcon(getClass().getResource("/app/assets/Flying airplane.gif"));
            JLabel label = new JLabel(icon);

            this.resultContainer.add(label, BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
