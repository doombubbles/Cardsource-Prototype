import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.*;

import net.demilich.metastone.game.spells.*;
import org.reflections.Reflections;

import java.util.stream.IntStream;


public class SpellParser {

    public static final String path = "";

    public static void main(String[] args) throws NoSuchMethodException {
        Reflections reflections = new Reflections("net.demilich.metastone.game.spells");
        Set<Class<? extends Spell>> subTypes = reflections.getSubTypesOf(Spell.class);



    }

}
