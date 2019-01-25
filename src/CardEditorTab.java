import io.vertx.core.json.Json;
import net.demilich.metastone.game.cards.CardType;
import net.demilich.metastone.game.cards.Rarity;
import net.demilich.metastone.game.cards.desc.CardDesc;
import net.demilich.metastone.game.entities.heroes.HeroClass;
import net.demilich.metastone.game.spells.Spell;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;
import net.demilich.metastone.game.spells.desc.condition.Condition;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class CardEditorTab extends JPanel {

    CardFile cardFile;
    CardDesc cardDesc;

    private JTextField name;
    private JTextField mana;
    private JComboBox type;
    private JComboBox heroClass;
    private JComboBox rarity;
    private JTextArea description;
    private JEnumComponent targetSelection;
    private JSpellComponent spell;

    public CardEditorTab(CardFile cardFile) {
        this.cardFile = cardFile;
        cardDesc = cardFile.convertToCardDesc();

        setLayout(new GridBagLayout());

        JPanel basicInfoPanel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = .5;
        add(basicInfoPanel, c);
        basicInfoPanel.setLayout(new GridBagLayout());

        JPanel spellPanel = new JPanel();
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = .5;
        add(spellPanel, c);
        spellPanel.setLayout(new GridBagLayout());

        name = addNameComponent(basicInfoPanel);
        mana = addManaComponent(basicInfoPanel);
        type = addCardTypeComponent(basicInfoPanel);
        heroClass = addHeroClassComponent(basicInfoPanel);
        rarity = addRarityComponent(basicInfoPanel);
        description = addDescriptionComponent(basicInfoPanel);

        targetSelection = addTargetSelectionComponent(spellPanel);

        spell = addSpellComponent(spellPanel);

        updateCardDesc();
    }

    public JTextField addNameComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();

        JLabel nameLabel = new JLabel("Name:");
        c.ipadx = 5;
        c.anchor = GridBagConstraints.EAST;
        basicInfoPanel.add(nameLabel, c);

        JTextField nameTextField = new JTextField(cardDesc.getName());
        c.ipadx = 5;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(nameTextField, c);
        return nameTextField;
    }

    public JTextField addManaComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel manaLabel = new JLabel("Cost:");
        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 5;
        c.anchor = GridBagConstraints.EAST;
        basicInfoPanel.add(manaLabel, c);

        JTextField manaTextField = new JTextField(cardDesc.getBaseManaCost() + "");

        manaTextField.setMinimumSize(new Dimension(30, manaTextField.getPreferredSize().height));
        manaTextField.setHorizontalAlignment(JTextField.CENTER);
        c.gridx = 1;
        c.gridy = 1;
        c.ipadx = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(manaTextField, c);
        return manaTextField;
    }

    public JComboBox addCardTypeComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel typeLabel = new JLabel("Card Type:");
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        c.ipadx = 5;
        basicInfoPanel.add(typeLabel, c);

        JComboBox typeComboBox = new JComboBox(SpellsourceFactory.getCardTypes());
        if (cardDesc.type != null) {
            typeComboBox.setSelectedItem(cardDesc.getType());
        }

        typeComboBox.setPreferredSize(new Dimension(150, typeComboBox.getPreferredSize().height));
        c.gridx = 1;
        c.gridy = 2;
        c.ipadx = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(typeComboBox, c);
        return typeComboBox;
    }


    public JComboBox addHeroClassComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel classLabel = new JLabel("Hero Class:");
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        c.ipadx = 5;
        basicInfoPanel.add(classLabel, c);

        JComboBox classComboBox = new JComboBox(SpellsourceFactory.getHeroClasses());
        if (cardDesc.heroClass != null) {
            classComboBox.setSelectedItem(cardDesc.getHeroClass());
        }

        classComboBox.setPreferredSize(new Dimension(100, classComboBox.getPreferredSize().height));
        c.gridx = 1;
        c.gridy = 3;
        c.ipadx = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(classComboBox, c);
        return classComboBox;
    }

    public JComboBox addRarityComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel rarityLabel = new JLabel("Rarity:");
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        c.ipadx = 5;
        basicInfoPanel.add(rarityLabel, c);

        JComboBox rarityComboBox = new JComboBox(SpellsourceFactory.getRarities());
        if (cardDesc.rarity != null) {
            rarityComboBox.setSelectedItem(cardDesc.getRarity());
        }

        rarityComboBox.setPreferredSize(new Dimension(100, rarityComboBox.getPreferredSize().height));
        c.gridx = 1;
        c.gridy = 4;
        c.ipadx = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(rarityComboBox, c);
        return rarityComboBox;
    }


    public JTextArea addDescriptionComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel descriptionLabel = new JLabel("Description:");
        c.gridx = 0;
        c.gridy = 5;
        c.ipadx = 5;
        c.anchor = GridBagConstraints.EAST;
        basicInfoPanel.add(descriptionLabel, c);

        JTextArea descriptionTextArea = new JTextArea(cardDesc.getDescription());
        descriptionTextArea.setRows(3);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setMinimumSize(new Dimension(200, 70));

        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setPreferredSize(new Dimension(200, 56));

        c.gridx = 1;
        c.gridy = 5;
        c.ipadx = 0;

        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(descriptionScrollPane, c);
        return descriptionTextArea;
    }

    public JEnumComponent addTargetSelectionComponent(JPanel basicInfoPanel) {
        GridBagConstraints c = new GridBagConstraints();
        JLabel targetSelectionLabel = new JLabel("Target Selection:");
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.EAST;
        c.ipadx = 5;
        basicInfoPanel.add(targetSelectionLabel, c);

        JEnumComponent targetSelectionComboBox = new JEnumComponent(SpellsourceFactory.getTargetSelections(), cardDesc.getTargetSelection(), null);

        targetSelectionComboBox.setPreferredSize(new Dimension(205, targetSelectionComboBox.getPreferredSize().height));
        c.gridx = 1;
        c.gridy = 6;
        c.ipadx = 0;
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        basicInfoPanel.add(targetSelectionComboBox, c);
        return targetSelectionComboBox;
    }

    public JSpellComponent addSpellComponent(JPanel target) {

        GridBagConstraints c = new GridBagConstraints();
        JLabel spellLabel = new JLabel("Spell:");
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.anchor = GridBagConstraints.NORTH;
        target.add(spellLabel, c);


        JSpellComponent spellComponent = new JSpellComponent(cardDesc.spell);

        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 0;
        c.anchor = GridBagConstraints.SOUTH;
        target.add(spellComponent, c);

        target.add(spellComponent);
        return spellComponent;
    }

    public void updateCardDesc() {
        if (name != null) {
            cardDesc.name = name.getText();
        }

        if (mana != null) {
            cardDesc.baseManaCost = Integer.parseInt(mana.getText());
        }

        if (type != null) {
            cardDesc.type = CardType.valueOf(type.getSelectedItem().toString());
        }

        if (heroClass != null) {
            cardDesc.heroClass = HeroClass.valueOf(heroClass.getSelectedItem().toString());
        }

        if (rarity != null) {
            cardDesc.rarity = Rarity.valueOf(rarity.getSelectedItem().toString());
        }

        if (description != null) {
            cardDesc.description = description.getText();
        }

        cardDesc.id = null;

        if (cardDesc.spell != null) {
            cardDesc.spell.spellStream(10, true).forEach(spellDesc -> {
                if (spellDesc.containsKey(SpellArg.CONDITION)) {
                    Condition condition = (Condition) spellDesc.get(SpellArg.CONDITION);
                    spellDesc.put(SpellArg.CONDITION, condition.getDesc());
                }
            });
        }



        Scanner scanner = new Scanner(Json.encodePrettily(cardDesc));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("net.demilich")) {
                line = line.substring(0, line.indexOf("net.demilich")) + line.substring(line.lastIndexOf(".") + 1);
            }

            System.out.println(line);
        }


    }
}
