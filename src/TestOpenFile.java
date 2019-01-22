import net.demilich.metastone.game.*;
import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.cards.CardParser;
import net.demilich.metastone.game.cards.desc.CardDesc;
import net.demilich.metastone.game.utils.ResourceInputStream;
import net.demilich.metastone.game.utils.ResourceLoader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;


public class TestOpenFile {

    public static final String path = "/Users/jamesgale/Documents/GitHub/Spellsource-Server/cards/src/main/resources/cards/";

    public static void main(String[] args) throws IOException, URISyntaxException {
        Collection<ResourceInputStream> resourceInputStreams = ResourceLoader.loadInputStreams(path + "hearthstone/kobolds_and_catacombs/paladin/spell_lesser_pearl_spellstone.json", true);
        System.out.println(resourceInputStreams.size());
        CardParser cardParser = new CardParser();
        CardDesc card1 = cardParser.parseCard(resourceInputStreams.stream().findFirst().get()).getDesc();

        System.out.println("hi");
    }
}
