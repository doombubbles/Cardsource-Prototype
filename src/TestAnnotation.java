

import net.demilich.metastone.game.cards.Rarity;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(TestAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    public Rarity rarity() default Rarity.FREE;
    public String string() default "";

}
