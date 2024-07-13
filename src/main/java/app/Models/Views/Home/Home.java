package app.Models.Views.Home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import app.Models.types.AppColors;
import java.awt.*;
import java.util.ArrayList;

public class Home {
    private JPanel mainPanel;
    private Dimension mainPanelDimension = new Dimension(Integer.MAX_VALUE, 300);

    // childrens
    private JPanel optionsMenuPanel;

    public Home(
        JPanel parentElement
    ) {
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
        this.optionsMenuPanel.setLayout(new FlowLayout());
        this.optionsMenuPanel.setBackground(AppColors.LAPIS_LAZULI);

        this.buttonMenu(optionsMenuPanel, "From:", "./settings.png");
        this.buttonMenu(optionsMenuPanel, "To:", "./settings.png");
        this.buttonMenu(optionsMenuPanel, "Depature Date:", "./settings.png");
        this.buttonMenu(optionsMenuPanel, "Return Date:", "./settings.png");
        this.mainPanel.add(this.optionsMenuPanel, BorderLayout.NORTH);
    }

    // FIXME: crea un custom componente para este componente
    public void buttonMenu(
        JPanel jpanelParent,
        String text,
        String url
    ) {
        var comboBoxOptions = new ArrayList<String>();
        comboBoxOptions.add("Colombia");
        comboBoxOptions.add("option 2");

        JPanel customButton = new JPanel();
        customButton.setLayout(new BorderLayout());
        customButton.setBackground(new Color(0, 0, 0, 0));
        customButton.setOpaque(false);

        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        var comboBox = new JComboBox<Object>(comboBoxOptions.toArray());
        // Quitar el borde
        comboBox.setBorder(BorderFactory.createEmptyBorder());
        comboBox.putClientProperty("JComboBox.buttonDarkShadow", Color.WHITE); // Intenta cambiar el color del botón de desplegar
        UIManager.put("ComboBox.buttonDarkShadow", Color.WHITE); // Cambia el color para todos los JComboBox
        
        // Cambiar el color de fondo
        comboBox.setBackground(AppColors.LAPIS_LAZULI); // Usa el color que prefieras

        // Cambiar el color de la fuente
        comboBox.setForeground(Color.white); // Usa el color que prefieras

        // Para cambiar el color de fondo y de la fuente de la lista desplegable también
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(Color.WHITE); // Color de fondo de los ítems
                c.setForeground(Color.BLACK); // Color de la fuente de los ítems
                return c;
            }
        });
        var icon = new JButton();
        icon.setFocusPainted(false); // Esto evita que se pinte el borde de enfoque
        icon.setIcon(new ImageIcon(getClass().getResource(url))); // NOI18N
        icon.setContentAreaFilled(false);
        icon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        icon.setBorderPainted(false); // Opcional, si también quieres quitar el borde.
        icon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        icon.setForeground(Color.white);

        customButton.add(label, BorderLayout.NORTH);
        customButton.add(comboBox, BorderLayout.CENTER);
        customButton.add(icon, BorderLayout.WEST);
        jpanelParent.add(customButton, BorderLayout.CENTER);
    }

    public void bodyHome() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        // bodyPanel.setBorder(BorderFactory.createLineBorder(AppColors.LAPIS_LAZULI, 2)
        // );
        bodyPanel.setOpaque(false);
        bodyPanel.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);
        // container.setBorder(BorderFactory.createLineBorder(AppColors.LAPIS_LAZULI, 2)
        // );

        this.loader(container);
        bodyPanel.add(container, BorderLayout.CENTER);

        this.mainPanel.add(bodyPanel, BorderLayout.CENTER);
    }

    // FIXME: create a custom component for this component
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
