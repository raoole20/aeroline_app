package app.aeroline;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrameError {

    public static void ViewError(String message) {
        JFrame frame = new JFrame();
        final JTextField textField = new JTextField();
        textField.setText(message);

        frame.add(textField);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
