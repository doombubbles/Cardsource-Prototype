import net.demilich.metastone.game.spells.Spell;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class JSpellComponent extends DescValueComponent {

    Class<? extends Spell> spellClass;
    JComboBox spellClassComboBox;

    Map<SpellArg, DescValueComponent> descValueMap;

    public JSpellComponent(SpellDesc spell) {
        super(null);
        setLayout(new GridBagLayout());
        this.spellClass = spell.getDescClass();


        spellClassComboBox = addSpellClassComponent();

        descValueMap = new HashMap<>();

        int i = 1;
        for (Map.Entry<SpellArg, Object> spellArgObjectEntry : spell.entrySet()) {
            SpellArg spellArg = spellArgObjectEntry.getKey();
            if (spellArg == SpellArg.CLASS) {
                continue;
            }
            DescValueComponent component = SpellArgHandler.handleSpellArg(spellArg, spellArgObjectEntry.getValue());
            if (component != null) {
                component.setXButton(this, descValueMap);
                descValueMap.put(spellArg, component);
                GridBagConstraints c = new GridBagConstraints();
                c.gridy = i++;
                c.gridx = 1;
                c.anchor = GridBagConstraints.WEST;
                add(component.getJComponent(), c);

            }
        }



    }


    public JComboBox addSpellClassComponent() {
        GridBagConstraints c = new GridBagConstraints();
        label = new JLabel("Spell Class:");
        c.gridx = 0;
        c.gridy = 0;
        this.add(label, c);

        JComboBox spellClassComboBox = new JComboBox(SpellsourceFactory.getSpells());
        if (spellClass != null) {
            spellClassComboBox.setSelectedItem(spellClass.getSimpleName());
        }
        //spellClassComboBox.setMaximumSize(new Dimension(300, spellClassComboBox.getHeight()));

        //spellClassComboBox.setPreferredSize(new Dimension(100, spellClassComboBox.getPreferredSize().height));
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = 5;
        c.anchor = GridBagConstraints.WEST;
        this.add(spellClassComboBox, c);
        return spellClassComboBox;
    }

    @Override
    public Object getDescValue() {
        spellClass = SpellsourceFactory.getSpell(spellClassComboBox.getSelectedItem().toString());
        SpellDesc desc = new SpellDesc(spellClass);

        for (Map.Entry<SpellArg, DescValueComponent> spellArgHasDescValueEntry : descValueMap.entrySet()) {
            desc.put(spellArgHasDescValueEntry.getKey(),
                    spellArgHasDescValueEntry.getValue().getDescValue());
        }
        return desc;
    }
}
