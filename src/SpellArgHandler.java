import net.demilich.metastone.game.cards.Attribute;
import net.demilich.metastone.game.spells.NullSpell;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;
import net.demilich.metastone.game.spells.desc.valueprovider.ValueProvider;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.Arrays;

public class SpellArgHandler {

    public static DescValueComponent handleSpellArg(SpellArg spellArg, Object value) {
        switch (spellArg) {
            case EXCLUSIVE:
            case CANNOT_RECEIVE_OWNED:
            case FULL_MANA_CRYSTALS:
            case IGNORE_SPELL_DAMAGE:
            case RANDOM_TARGET:
                if (value instanceof Boolean) {
                    return new JBooleanComponent((Boolean) value, toCorrectCasing(spellArg.toString()));
                } else return new JBooleanComponent(null, toCorrectCasing(spellArg.toString()));
            case ATTRIBUTE:
                try {
                    Attribute a = Attribute.valueOf((String) value);
                    return new JEnumComponent(Attribute.values(), a, toCorrectCasing(spellArg.toString()));
                } catch (IllegalArgumentException e) {
                    return new JEnumComponent(Attribute.values(), null, toCorrectCasing(spellArg.toString()));
                }
            case SPELL:
            case SPELL1:
            case SPELL2:
                if (value instanceof SpellDesc) {
                    return new JSpellComponent((SpellDesc) value);
                } else return new JSpellComponent(NullSpell.create());
            case SPELLS:
                if (value instanceof SpellDesc[]) {
                    SpellDesc[] spellDescs = (SpellDesc[]) value;
                    return new JArrayComponent((DescValueComponent[]) Arrays.stream(spellDescs).map(JSpellComponent::new).toArray(), toCorrectCasing(spellArg.toString()));
                }
                return null;
            case CARD:
            case DESCRIPTION:
            case GROUP:
            case NAME:
                if (value instanceof String) {
                    return new JStringCompoennt((String) value, toCorrectCasing(spellArg.toString()));
                }
                return null;
            case CARDS:
                if (value instanceof String[]) {
                    String[] strings = (String[]) value;
                    return new JArrayComponent((DescValueComponent[]) Arrays.stream(strings).map(s -> new JStringCompoennt(s, "")).toArray(), toCorrectCasing(spellArg.toString()));
                }
                return null;
            case ATTACK_BONUS:
            case ARMOR_BONUS:
            case BOARD_POSITION_ABSOLUTE:
            case HOW_MANY:
            case HP_BONUS:
            case VALUE:
                if (value instanceof Integer) {
                    return new JValueComponent(false, (Integer) value, toCorrectCasing(spellArg.toString()));
                } else if (value instanceof ValueProvider) {
                    return new JValueComponent(true, value, toCorrectCasing(spellArg.toString()));
                }
                return null;

            case CONDITION:
                System.out.println("it's the condition thing");
                return null;
            default:
                return null;

        }
    }


    public static String toCorrectCasing(String s) {
        return StringUtils.capitalize(s.toString().toLowerCase().replace("_", " ") + ":");
    }
}
