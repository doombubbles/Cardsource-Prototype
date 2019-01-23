import javax.swing.*;
import java.awt.*;

public class JStringCompoennt extends DescValueComponent {

    JTextField textField;

    public JStringCompoennt(String string, String labelString) {
        super(labelString);
        textField = new JTextField(string);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        add(textField);
    }

    @Override
    public Object getDescValue() {
        return textField.getText();
    }
}
