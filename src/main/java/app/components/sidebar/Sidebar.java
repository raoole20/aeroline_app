package app.components.sidebar;

import javax.swing.*;

import java.awt.*;

import app.Models.types.AppColors;
import app.Models.types.InnerRoutes;
import app.classes.Routes;

public class Sidebar {
    private int width = 55;
    private int height = 200;
    private Dimension sideMenuSize = new Dimension(this.width, this.height);
    private Dimension sideMenuSizeExpanded = new Dimension(200, 600);
    private boolean isExpanded = false;
    @SuppressWarnings("unused")
    private Dimension sideMenuSizeCollapsed;

    // children components
    private JButton toggleMenuButton;
    private JButton homeButton;
    private JButton settingsButton;
    private JPanel sideMenuPanel;

    // route controller
    private Routes router;

    public Sidebar(JFrame parentElement) {
        // build
        this.sideMenuPanel = new JPanel();
        this.sideMenuPanel.setLayout(new BoxLayout(this.sideMenuPanel, BoxLayout.Y_AXIS));

        // set default size
        this.sideMenuPanel.setMinimumSize(this.sideMenuSize);
        this.sideMenuPanel.setPreferredSize(this.sideMenuSize);
        this.sideMenuPanel.setMaximumSize(this.sideMenuSize);
        this.sideMenuPanel.setSize(this.sideMenuSize);

        // set background
        this.sideMenuPanel.setBackground(AppColors.LAPIS_LAZULI);

        // build children
        this.buildMenuButton();
        this.buildHomeButton();
        this.buildSetingsButton();

        // add to parent
        parentElement.add(this.sideMenuPanel, BorderLayout.WEST);
    }

    public void setRouter(Routes router) {
        this.router = router;
    }

    private void buildMenuButton() {
        this.toggleMenuButton = new JButton();
        this.toggleMenuButton.setFocusPainted(false); // Esto evita que se pinte el borde de enfoque
        this.toggleMenuButton.setIcon(new ImageIcon(getClass().getResource("./menu_15.png"))); // NOI18N
        this.toggleMenuButton.setContentAreaFilled(false);
        this.toggleMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.toggleMenuButton.setBorderPainted(false); // Opcional, si también quieres quitar el borde.

        this.toggleMenuButton.setBorder(BorderFactory.createEmptyBorder(15, 20, 0, 0));

        this.toggleMenuAction();

        this.sideMenuPanel.add(this.toggleMenuButton);
    }

    private void buildHomeButton() {
        this.homeButton = new JButton();
        this.homeButton.setFocusPainted(false); // Esto evita que se pinte el borde de enfoque
        this.homeButton.setIcon(new ImageIcon(getClass().getResource("./home.png"))); // NOI18N
        this.homeButton.setContentAreaFilled(false);
        this.homeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.homeButton.setBorderPainted(false); // Opcional, si también quieres quitar el borde.
        this.homeButton.setForeground(Color.white);

        this.homeButton.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 0));

        // actions
        this.handlerRedirectHome();

        this.sideMenuPanel.add(this.homeButton);
    }

    private void buildSetingsButton() {
        this.settingsButton = new JButton();
        this.settingsButton.setFocusPainted(false); // Esto evita que se pinte el borde de enfoque
        this.settingsButton.setIcon(new ImageIcon(getClass().getResource("./settings.png"))); // NOI18N
        this.settingsButton.setContentAreaFilled(false);
        this.settingsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.settingsButton.setBorderPainted(false); // Opcional, si también quieres quitar el borde.
        this.settingsButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 0, 0));
        this.settingsButton.setForeground(Color.white);

        // actions
        this.handlerRedirectSettings();

        this.sideMenuPanel.add(this.settingsButton);
    }

    private void toggleMenuAction() {
        this.toggleMenuButton.addActionListener(e -> {
            int widthStep = (this.sideMenuSizeExpanded.width - sideMenuSize.width) / 20;
            int heightStep = (this.sideMenuSizeExpanded.height - sideMenuSize.height) / 20;

            Timer timer = new Timer(1, event -> {
                Dimension currentDimension = sideMenuPanel.getSize();
                int nextWidth = currentDimension.width + widthStep;
                int nextHeight = currentDimension.height + heightStep;

                // Verifica si se alcanzó o superó el tamaño deseado
                if ((widthStep > 0 && nextWidth > this.sideMenuSizeExpanded.width)
                        || (heightStep > 0 && nextHeight > this.sideMenuSizeExpanded.height)) {
                    sideMenuPanel.setSize(this.sideMenuSizeExpanded);
                    ((Timer) event.getSource()).stop();
                    this.sideMenuSizeExpanded = new Dimension(nextWidth, nextHeight);
                } else {
                    sideMenuPanel.setMinimumSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setPreferredSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setMaximumSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setSize(new Dimension(nextWidth, nextHeight));
                }

                sideMenuPanel.revalidate();
            });

            Timer ouTimer = new Timer(1, event -> {
                Dimension currentDimension = sideMenuPanel.getSize();

                int nextWidth = currentDimension.width - widthStep;
                int nextHeight = currentDimension.height - heightStep;
            
                // int currentWidth = sideMenuSizeExpanded.width;
                // Verifica si se alcanzó o está por debajo del tamaño deseado
                if ((widthStep > 0 && nextWidth <= this.sideMenuSize.width)
                        || (heightStep > 0 && nextHeight < this.sideMenuSize.height)) {
                    sideMenuPanel.setSize(this.sideMenuSize);
                    ((Timer) event.getSource()).stop();
                    this.sideMenuSizeCollapsed = new Dimension(nextWidth, nextHeight);
                } else {
                    sideMenuPanel.setMinimumSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setPreferredSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setMaximumSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setSize(new Dimension(nextWidth, nextHeight));
                }
            
                sideMenuPanel.revalidate();
            });

            if(isExpanded) {
                ouTimer.start();
                this.hideButtonText();  
            } else {
                this.showButtonText();
                timer.start();
            }

            isExpanded = !isExpanded;
        });
    }

    private void handlerRedirectHome() {
        this.homeButton.addActionListener(e -> {
            this.router.setRoute(InnerRoutes.HOME);
        });
    }

    private void handlerRedirectSettings() {
        this.settingsButton.addActionListener(e -> {
            this.router.setRoute(InnerRoutes.REPORTS);
        });
    }

    private void showButtonText() {
        this.toggleMenuButton.setText("");        
        this.homeButton.setText("Home");
        this.settingsButton.setText("Settings");
    }

    private void hideButtonText() {
        this.toggleMenuButton.setText("");
        this.homeButton.setText("");
        this.settingsButton.setText("");
    }
}
