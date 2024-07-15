package app.Models.Views.Tickets;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import app.Models.DTOs.Seat;
import app.Services.FlightService;
import app.classes.Routes;
import app.components.Seats.SeatsButton;

public class TicketsView {
    private JPanel mainPanel;

    // services
    private FlightService flightService;

    // Formulario
    private JPanel formPanel;
    private JTextField name;
    private JTextField lastName;
    private JTextField email;
    private JTextField identityCard;
    private JTextField seatsNumber;
    private JButton SubmitButton;

    // Asientos Selector
    private JPanel seatsPanelWrapper;
    private JPanel seatsPanelResult;
    private ArrayList<SeatsButton> seatsButtons;

    // Route controller
    private Routes route;

    public TicketsView(
            JPanel parentElement,
            FlightService flightService,
            Routes route) {
        this.flightService = flightService;
        this.route = route;

        this.mainPanel = new JPanel();
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.mainPanel.setBackground(Color.WHITE);
        this.mainPanel.setLayout(new BorderLayout());

        this.buildSeatsPanelWrapper();

        this.mainPanel.add(this.buildBackButtonPanel(), BorderLayout.NORTH);
        this.mainPanel.add(this.bluidForm(), BorderLayout.WEST);
        this.mainPanel.add(this.buildSeatsForm(), BorderLayout.CENTER);

        parentElement.add(this.mainPanel, BorderLayout.CENTER);

        this.seatsButtons = new ArrayList<SeatsButton>();

        // events
        this.sumbmitButtonEvent();
        this.onlyNumber(this.identityCard);
        this.onlyLetters(this.name);
        this.onlyLetters(this.lastName);
    }

    private JPanel buildBackButtonPanel() {
        JPanel backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        backButtonPanel.setBackground(Color.WHITE);
        backButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back");
        backButtonPanel.add(backButton, BorderLayout.WEST);

        backButton.addActionListener(e -> {
            this.route.setRoute(this.route.getPrevRoutes());
        });
        return backButtonPanel;
    }

    private void buildSeatsPanelWrapper() {
        this.seatsPanelWrapper = new JPanel();
        this.seatsPanelWrapper.setLayout(new BorderLayout());
        this.seatsPanelWrapper.setBackground(Color.white);
    }

    private JPanel bluidForm() {
        this.formPanel = new JPanel();
        this.formPanel.setLayout(new BorderLayout());
        this.formPanel.setPreferredSize(new Dimension(300, 300));
        this.formPanel.setBackground(Color.white);
        this.formPanel.setBorder(BorderFactory.createTitledBorder(
                "Formulario"));

        this.formPanel.setBackground(Color.white);

        this.name = new JTextField();
        this.lastName = new JTextField();
        this.email = new JTextField();
        this.identityCard = new JTextField();
        this.SubmitButton = new JButton("Submit");
        this.seatsNumber = new JTextField();
        seatsNumber.setText("0");
        seatsNumber.setEnabled(false);

        var wrapperForm = new JPanel();
        wrapperForm.setBackground(Color.white);
        wrapperForm.setLayout(new BoxLayout(wrapperForm, BoxLayout.Y_AXIS));

        wrapperForm.add(this.fieldWrapper(this.identityCard, new JLabel("Identity Card")));
        wrapperForm.add(this.fieldWrapper(this.name, new JLabel("Name")));
        wrapperForm.add(this.fieldWrapper(this.lastName, new JLabel("Last Name")));
        wrapperForm.add(this.fieldWrapper(this.email, new JLabel("Email")));
        wrapperForm.add(this.fieldWrapper(this.seatsNumber, new JLabel("Asientos")));

        this.formPanel.add(wrapperForm, BorderLayout.CENTER);
        this.formPanel.add(this.buttonWrapper(SubmitButton), BorderLayout.SOUTH);

        return this.formPanel;
    }

    private JPanel buildSeatsForm() {
        var seatsExternalMaring = new JPanel();
        seatsExternalMaring.setLayout(new BorderLayout());
        seatsExternalMaring.setBackground(Color.WHITE);
        seatsExternalMaring.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        var seatsPanel = new JPanel();
        seatsPanel.setLayout(new BorderLayout());
        seatsPanel.setBackground(Color.WHITE);
        seatsPanel.setBorder(BorderFactory.createTitledBorder(
                "Asientos"));

        var seatsInternalMargin = new JPanel();
        seatsInternalMargin.setLayout(new BorderLayout());
        seatsInternalMargin.setBackground(Color.WHITE);
        seatsInternalMargin.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JScrollPane scrollPane = new JScrollPane(this.seatsPanelWrapper);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setOpaque(false);
        scrollPane.setBackground(Color.white);

        seatsInternalMargin.add(scrollPane, BorderLayout.CENTER);

        seatsPanel.add(seatsInternalMargin, BorderLayout.CENTER);
        seatsExternalMaring.add(seatsPanel, BorderLayout.CENTER);
        return seatsExternalMaring;
    }

    private JPanel fieldWrapper(
            JTextField field,
            JLabel label) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BorderLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        wrapper.setBackground(Color.WHITE);

        wrapper.setPreferredSize(new Dimension(300, 20));
        wrapper.setSize(new Dimension(300, 60));
        wrapper.setMinimumSize(new Dimension(200, 60));
        wrapper.setMaximumSize(new Dimension(300, 60));
        wrapper.add(label, BorderLayout.NORTH);
        wrapper.add(field, BorderLayout.CENTER);

        return wrapper;
    }

    private JPanel buttonWrapper(
            JButton button) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new FlowLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        wrapper.setBackground(Color.WHITE);

        wrapper.add(button);
        return wrapper;
    }

    public void setSeatsButtons(ArrayList<Seat> seats) {
        JPanel flowPanelSeats = new JPanel();
        flowPanelSeats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        flowPanelSeats.setBackground(Color.white);
        flowPanelSeats.setLayout(new BoxLayout(flowPanelSeats, BoxLayout.Y_AXIS));

        var controll = 0;
        var tempContainer = new JPanel();
        tempContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        for (Seat seat : seats) {
            if (controll == 10) {
                flowPanelSeats.add(tempContainer);
                flowPanelSeats.add(Box.createRigidArea(new Dimension(0, 10)));
                tempContainer = new JPanel();
                tempContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                controll = 0;
            } else {
                boolean is5 = controll == 5;

                if (is5) {
                    tempContainer.add(Box.createRigidArea(new Dimension(50, 0)));
                }
                var button = new SeatsButton(
                        seat.status == 0, // estado 1 == comprado
                        false,
                        seat.seatsCode,
                        seat.id,
                        () -> {
                            var numeroDeAsientosSeleccionados = 0;
                            for (SeatsButton seatButton : this.seatsButtons) {
                                if (seatButton.getIsSelected()) {
                                    numeroDeAsientosSeleccionados++;
                                }
                            }
                            this.seatsNumber.setText(String.valueOf(numeroDeAsientosSeleccionados));
                        });
                this.seatsButtons.add(button);
                tempContainer.add(button.getButton());
                controll++;
            }
        }
        this.seatsPanelWrapper.add(flowPanelSeats, BorderLayout.CENTER);
    }

    public void clearPanel() {
        this.seatsPanelWrapper.removeAll();
        this.seatsPanelWrapper.revalidate();
        this.seatsPanelWrapper.repaint();
    }

    public void rePaint() {
        this.seatsPanelWrapper.revalidate();
        this.seatsPanelWrapper.repaint();
    }

    private void sumbmitButtonEvent() {
        this.SubmitButton.addActionListener(e -> {
            var cedula = this.identityCard.getText();
            var nombre = this.name.getText();
            var apellido = this.lastName.getText();
            var correo = this.email.getText();
            var asientos = Integer.parseInt(this.seatsNumber.getText());

            if (cedula.isEmpty()
                    || nombre.isEmpty()
                    || apellido.isEmpty()
                    || correo.isEmpty()
                    || asientos == 0
                    || !isEmail(correo)) {
                var messageNombre = nombre.isEmpty() ? "Nombre" : "";
                var messageApellido = apellido.isEmpty() ? "Apellido" : "";
                var messageCorreo = correo.isEmpty() ? "Correo" : !isEmail(correo) ? "Correo no valido" : "";
                var messageCedula = cedula.isEmpty() ? "Cedula" : "";
                var messageAsientos = asientos == 0 ? "Debe seleccionar almenos un asiento" : "";

                JOptionPane.showMessageDialog(
                        null,
                        "Faltan Los siguientes campos por llenar: \n"
                                + messageNombre + "\n"
                                + messageApellido + "\n"
                                + messageCorreo + "\n"
                                + messageCedula + "\n"
                                + messageAsientos);
                return;
            }
            // Carga el GIF en un JLabel
            // Crea el contenido del diálogo
            Icon icon = new ImageIcon(getClass().getResource("/app/assets/Flying airplane.gif"));
            JLabel label = new JLabel(icon);
            String message = "Procesando compra...";
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(new JLabel(message), BorderLayout.NORTH);
            contentPanel.add(label, BorderLayout.CENTER);

            // Crea el diálogo personalizado
            JDialog dialog = new JDialog();
            dialog.setLayout(new BorderLayout());
            dialog.setTitle("Proceso en curso");
            dialog.setModal(true);
            dialog.setContentPane(contentPanel);
            dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialog.setSize(300, 200); // Ajusta el tamaño según sea necesario
            dialog.setLocationRelativeTo(null); // Centra el diálogo

            this.flightService.comprarVuelo(
                    this.route.getPayload().flighhtID,
                    this.seatsButtons,
                    asientos
                    ).thenAccept(result -> {
                        dialog.dispose();
                        if (result) {
                            dialog.removeAll();
                            dialog.setVisible(false);

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Compra realizada con exito");
                            this.route.setRoute(this.route.getPrevRoutes());
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Error al realizar la compra");
                        }
                    });
            // Muestra el diálogo
            dialog.setVisible(true);

        });
    }

    private void onlyNumber(JTextField field) {
        field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isDigit(c)) {
                    evt.consume();
                }
            }
        });
    }

    private void onlyLetters(JTextField field) {
        field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();
                if (!Character.isLetter(c)) {
                    evt.consume();
                }
            }
        });
    }

    private Boolean isEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }
}
