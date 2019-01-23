import net.demilich.metastone.game.spells.desc.SpellArg;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public abstract class DescValueComponent extends JPanel {

    public JLabel label;

    public abstract Object getDescValue();

    public DescValueComponent(String name) {
        setLayout(new GridBagLayout());
        if (name != null) {
            label = new JLabel(name);
            GridBagConstraints c = new GridBagConstraints();
            c.ipadx = 5;
            add(label, c);
        }

    }

    public JComponent getJComponent() {
        return this;
    }

    public void setXButton(JPanel panel, Map<SpellArg, DescValueComponent> descValueMap) {
        JButton xButton = new JButton("X");
        xButton.setMargin(new Insets(0, 0, 0, 0));
        xButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0),
                BorderFactory.createLineBorder(Color.BLACK, 1, true)));
        xButton.addActionListener(e -> {
            panel.remove(this);
            SpellArg targetArg = null;
            for (Map.Entry<SpellArg, DescValueComponent> entry : descValueMap.entrySet()) {
                if (entry.getValue().equals(this)) {
                    targetArg = entry.getKey();
                    break;
                }
            }
            if (targetArg != null) {
                descValueMap.remove(targetArg);
            }
            panel.revalidate();
            panel.repaint();
        });
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.gridx = 3;
        add(xButton, c);
    }

}
