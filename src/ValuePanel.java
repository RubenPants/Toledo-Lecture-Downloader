
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ValuePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel label;
    private JTextField field;
    private String input;


    ValuePanel(String name, String defaultString) {
        setLayout(new GridLayout());

        input = defaultString;
        label = new JLabel(name, SwingConstants.CENTER);
        field = new JTextField(defaultString);
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = field.getText();
                try {
                    input = typed;
                } catch (NumberFormatException e) {
                    input = "";
                }
            }
        });

        add(label);
        add(field);
    }

    String getValue() {
        return input;

    }

    public void setEditable(boolean editable) {
        field.setEditable(editable);
    }

    public void setValue(String input) {
        field.setText(input);
        this.input = input;
    }
}
