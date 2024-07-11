package app.aeroline.aeroline;

import javax.swing.*;

import app.Models.types.InnerRoutes;
import app.classes.Routes;
import app.components.body.BodyComponent;
import app.components.sidebar.Sidebar;

public class App {
    private JFrame mainPanel;

    // childrens
    private Sidebar sideMenu;
    private BodyComponent bodyComponent;

    // routes controller
    private Routes routes;

    public App() {
        this.mainPanel = new JFrame("AeroLine App");

        // default children
        this.sideMenu = new Sidebar(this.mainPanel);
        this.bodyComponent = new BodyComponent(this.mainPanel);

        // rouutes
        this.routes = new Routes(
            InnerRoutes.HOME,
            (Object route) -> {
                this.bodyComponent.changeView((InnerRoutes) route);
            }
        );

        // add reference to routes
        this.sideMenu.setRouter(this.routes);
    }

    public void printMainMenu() {
        mainPanel.setSize(800, 600);
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setVisible(true);
    }
}
