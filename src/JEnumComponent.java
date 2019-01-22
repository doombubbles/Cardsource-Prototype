import javax.swing.*;

public class JEnumComponent extends DescValueComponent {

    public JComboBox comboBox;

    public JEnumComponent(Object[] objects, Object starter, String name) {
        super(name);
        comboBox = new JComboBox(objects);
        if (starter != null) {
            comboBox.setSelectedItem(starter);
        }
        add(comboBox);
    }

    @Override
    public Object getDescValue() {
        return comboBox.getSelectedItem().toString();
    }
}
