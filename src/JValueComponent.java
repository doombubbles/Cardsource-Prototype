import net.demilich.metastone.game.spells.desc.valueprovider.ValueProviderDesc;

import javax.swing.*;

public class JValueComponent extends DescValueComponent {

    boolean isValueProvider;

    JTextField jTextField;
    JButton toValueProviderButton;
    JButton toIntegerButton;

    public JValueComponent(boolean valueProvider, Integer integer, String name) {
        super(name);
        isValueProvider = valueProvider;
        initAsInteger(integer);
    }

    public JValueComponent(boolean valueProvider, Object object, String name) {
        super(name);
        isValueProvider = valueProvider;
        if (object instanceof ValueProviderDesc) {
            initAsValueProvider((ValueProviderDesc) object);
        }
    }

    public void initAsValueProvider(ValueProviderDesc desc) {

    }

    public void initAsInteger(Integer integer) {
        jTextField = new JTextField();
        if (integer != null) {
            jTextField.setText(integer.toString());
        }

        toValueProviderButton = new JButton("+");
        toValueProviderButton.addActionListener(e -> {
            convertToValueProvider();
        });
    }



    private void convertToValueProvider() {
        isValueProvider = true;
        remove(jTextField);
        remove(toIntegerButton);

    }



    @Override
    public Object getDescValue() {
        if (isValueProvider) {
            return null;
        } else {
            return Integer.parseInt(jTextField.getText());
        }
    }
}
