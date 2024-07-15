package app.aeroline.aeroline;

import javax.swing.*;
import app.Models.types.InnerRoutes;
import app.Services.FlightService;
import app.classes.Routes;
import app.components.body.BodyComponent;
import app.components.sidebar.Sidebar;

public class App {
    private JFrame mainPanel;
    private ImageIcon icon = new ImageIcon(getClass().getResource("/app/assets/icon.png"));
    
    // childrens
    private Sidebar sideMenu;
    private BodyComponent bodyComponent;

    // routes controller
    private Routes routes;

    // conexion a la base de datos
    private FlightService flightService;

    public App(
        FlightService flightService
    ) {
        this.flightService = flightService;

        this.mainPanel = new JFrame("AeroLine App");
        this.mainPanel.setResizable(true);
        this.mainPanel.pack();
        this.mainPanel.setIconImage(this.icon.getImage());

        // Establece el icono en el JFrame
        this.sideMenu = new Sidebar(this.mainPanel);
        this.bodyComponent = new BodyComponent(
            this.mainPanel,
            this.flightService
        );

        // rouutes
        this.routes = new Routes(
            null,
            (Object route) -> {
                this.bodyComponent.changeView((InnerRoutes) route);
            }
        );

        // add reference to routes
        this.sideMenu.setRouter(this.routes);
        this.bodyComponent.setRouter(this.routes);

        this.routes.setRoute(InnerRoutes.HOME);
    }

    public void printMainMenu() {
        mainPanel.setSize(800, 600);
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setVisible(true);
    }
}
