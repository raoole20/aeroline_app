package app.aeroline.aeroline;

import java.awt.*;
import javax.swing.*;

import app.Models.types.AppColors;


// $columbia-blue: rgba(210, 231, 255, 1);
// $lapis-lazuli: rgba(16, 84, 129, 1);
public class App {
    private JFrame mainPanel;

    public App() {
        System.out.println("App constructor");
    }

    public void printMainMenu() {
        this.mainPanel = new JFrame("AeroLine App");
        var tempLabel = new JLabel("Hello World");

        JPanel mainContainer = new JPanel(new BorderLayout()); 

        JPanel sideMenuPanel = new JPanel();
        sideMenuPanel.setLayout(new BoxLayout(sideMenuPanel, BoxLayout.Y_AXIS));
        sideMenuPanel.add(new JLabel("Menu Item 1"));
        sideMenuPanel.add(new JLabel("Menu Item 2"));

        JButton home = new JButton();
        home.setIcon(new ImageIcon(getClass().getResource("./home (Custom).png"))); // NOI18N
        home.setContentAreaFilled(false);
        home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        home.setBorderPainted(false); // Opcional, si también quieres quitar el borde.
        sideMenuPanel.add(home);

        sideMenuPanel.setBackground(AppColors.LAPIS_LAZULI);

        Dimension sideMenuSize = new Dimension(100, 200); // Ancho de 200 y altura máxima posible
        sideMenuPanel.setMinimumSize(sideMenuSize);
        sideMenuPanel.setPreferredSize(sideMenuSize);
        sideMenuPanel.setMaximumSize(sideMenuSize);
        sideMenuPanel.setSize(sideMenuSize);

        JPanel bodyPanel = new JPanel();
        bodyPanel.add(tempLabel);
        bodyPanel.setBackground(AppColors.COLUMBIA_BLUE);

        JButton button = new JButton("Click me");
        button.addActionListener(e -> {
            var newDimension = new Dimension(200, 600);

            int widthStep = (newDimension.width - sideMenuSize.width) / 100;
            int heightStep = (newDimension.height - sideMenuSize.height) / 100;


            Timer timer = new Timer(10, event -> {
                Dimension currentDimension = sideMenuPanel.getSize();
                int nextWidth = currentDimension.width + widthStep;
                int nextHeight = currentDimension.height + heightStep;
    
                // Verifica si se alcanzó o superó el tamaño deseado
                if ((widthStep > 0 && nextWidth > newDimension.width) || (heightStep > 0 && nextHeight > newDimension.height)) {
                    sideMenuPanel.setSize(newDimension);
                    ((Timer)event.getSource()).stop();
                } else {
                    sideMenuPanel.setMinimumSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setPreferredSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setMaximumSize(new Dimension(nextWidth, nextHeight));
                    sideMenuPanel.setSize(new Dimension(nextWidth, nextHeight));
                }
    
                sideMenuPanel.revalidate();
            });
    
            timer.start();
        });

        bodyPanel.add(button);
        
        mainContainer.add(sideMenuPanel, BorderLayout.WEST); 
        mainContainer.add(bodyPanel, BorderLayout.CENTER);

        mainPanel.add(mainContainer);
        mainPanel.setSize(800, 600);
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setVisible(true);
    }
}
