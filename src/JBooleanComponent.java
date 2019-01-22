import javax.swing.*;

public class JBooleanComponent extends DescValueComponent {

    JCheckBox checkBox;

    public JBooleanComponent(Boolean start, String name) {
        super(name);
        checkBox = new JCheckBox();
        if (start != null) {
            checkBox.setSelected(start);
        }
        add(checkBox);
    }

    @Override
    public Object getDescValue() {
        return checkBox.isSelected();
    }
}
