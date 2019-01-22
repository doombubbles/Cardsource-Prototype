import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.cards.CardType;
import net.demilich.metastone.game.cards.Rarity;
import net.demilich.metastone.game.entities.heroes.HeroClass;
import net.demilich.metastone.game.spells.Spell;
import net.demilich.metastone.game.targeting.TargetSelection;
import org.reflections.Reflections;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpellsourceFactory {

    public static CardType[] getCardTypes() {
        return Arrays.stream(CardType.values()).filter(heroClass -> {
            switch (heroClass) {
                case GROUP:
                    return false;
                default:
                    return true;
            }
        }).collect(Collectors.toList()).toArray(CardType[]::new);
    }

    public static HeroClass[] getHeroClasses() {
        return Arrays.stream(HeroClass.values()).filter(heroClass -> {
            switch (heroClass) {
                case SELF:
                case OPPONENT:
                case INHERIT:
                    return false;
                default:
                    return true;
            }
        }).collect(Collectors.toList()).toArray(HeroClass[]::new);
    }

    public static Rarity[] getRarities() {
        return Arrays.stream(Rarity.values()).filter(rarity -> {
            switch (rarity) {
                case ALLIANCE:
                    return false;
                default:
                    return true;
            }
        }).collect(Collectors.toList()).toArray(Rarity[]::new);
    }

    public static TargetSelection[] getTargetSelections() {
        return TargetSelection.values();
    }

    public static String[] getSpells() {
        Reflections reflections = new Reflections("net.demilich.metastone.game.spells");
        Set<Class<? extends Spell>> subTypes = reflections.getSubTypesOf(Spell.class);

        return subTypes.stream().map(Class::getSimpleName).sorted(String::compareTo).toArray(String[]::new);
    }

    public static Class<? extends Spell> getSpell(String name) {
        Reflections reflections = new Reflections("net.demilich.metastone.game.spells");
        Set<Class<? extends Spell>> subTypes = reflections.getSubTypesOf(Spell.class);
        return subTypes.stream().filter(aClass -> aClass.getSimpleName().equalsIgnoreCase(name)).findFirst().orElseGet(null);
    }
}
