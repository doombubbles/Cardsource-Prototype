import javax.swing.*;

public class JStringCompoennt extends DescValueComponent {

    JTextField textField;

    public JStringCompoennt(String string, String labelString) {
        super(labelString);
        textField = new JTextField(string);
        add(textField);
    }

    @Override
    public Object getDescValue() {
        return textField.getText();
    }
}
