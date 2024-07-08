package app.aeroline.aeroline;
import javax.swing.*;

public class App {

    public App() {
        // añadir las instancias de  las clases que necesito para trabajar aca
        System.out.println("App constructor");
    }




    public void printMainMenu() {
        var mainPanel = new JFrame("AeroLine App");

        // FIXME: este campo es temporal solo esta para probar que todo funcione bien
        var tempLabel = new JLabel("Hello World");

        // de alguna manera se tiene que colocar los hijos de manera dinamica
        mainPanel.add(tempLabel);

        mainPanel.setSize(800, 600);
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainPanel.setVisible(true);
    }
}
