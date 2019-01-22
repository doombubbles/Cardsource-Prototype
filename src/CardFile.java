import net.demilich.metastone.game.cards.CardParser;
import net.demilich.metastone.game.cards.desc.CardDesc;
import net.demilich.metastone.game.utils.ResourceInputStream;
import net.demilich.metastone.game.utils.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

public class CardFile extends File {


    public CardFile(String pathname) {
        super(pathname);
    }

    public CardFile(File file) {
        super(file.getAbsolutePath());
    }


    public CardDesc convertToCardDesc() {
        Collection<ResourceInputStream> resourceInputStreams = null;
        try {
            resourceInputStreams = ResourceLoader.loadInputStreams(getAbsolutePath(), true);
            CardParser cardParser = new CardParser();
            return cardParser.parseCard(resourceInputStreams.stream().findFirst().get()).getDesc();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
