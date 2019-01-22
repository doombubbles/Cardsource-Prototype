import javax.swing.*;
import java.awt.*;

public abstract class DescValueComponent extends JPanel {

    public JLabel label;

    public abstract Object getDescValue();

    public DescValueComponent(String name) {
        if (name != null) {
            label = new JLabel(name);
            add(label);
        }

    }

    public JComponent getJComponent() {
        return this;
    }

}
