import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JArrayComponent extends DescValueComponent {

    List<DescValueComponent> values;

    public JArrayComponent(DescValueComponent[] array, String name) {
        super(name);
        values = new ArrayList<>();
        for (DescValueComponent descValueComponent : array) {
            values.add(descValueComponent);
        }
    }

    @Override
    public Object getDescValue() {
        return values.stream().map(DescValueComponent::getDescValue).toArray();
    }
}
