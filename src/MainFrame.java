import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    JTabbedPane jTabbedPane;
    JLabel hiLabel;
    ArrayList<CardEditorTab> tabs;

    public MainFrame() {
        setTitle("Cardsource");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        tabs = new ArrayList<>();

        setJMenuBar(new CardSourceMenuBar(this));


        setLayout(new BorderLayout());
        hiLabel = new JLabel();
        hiLabel.setText("Hi! Open a card JSON file to get started");
        hiLabel.setVisible(true);
        hiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(hiLabel, BorderLayout.CENTER);


        //initTabbedPane();
    }

    public void initTabbedPane() {
        jTabbedPane = new JTabbedPane();
        add(jTabbedPane);
        jTabbedPane.setTabPlacement(SwingConstants.BOTTOM);
        jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void newCardTab(CardFile cardFile) {
        if (jTabbedPane == null) {
            initTabbedPane();
            hiLabel.setVisible(false);
        }
        CardEditorTab tab = new CardEditorTab(cardFile);
        tabs.add(tab);
        jTabbedPane.addTab(cardFile.getName().replace(".json", ""),
                null, tab, cardFile.getPath());
        jTabbedPane.setVisible(true);
        jTabbedPane.setTabComponentAt(tabs.size() - 1, tabComponent(tab));

    }

    public JPanel tabComponent(CardEditorTab tab) {
        String title = tab.cardFile.getName().replace(".json", "");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(new Color(0,0,0,0));
        JLabel label = new JLabel(title);
        jPanel.add(label, BorderLayout.CENTER);
        JButton button = new JButton("X");
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0),
                BorderFactory.createLineBorder(Color.BLACK, 1, true)));
        button.addActionListener(e -> {
            jTabbedPane.removeTabAt(jTabbedPane.indexOfTab(title));
            tabs.removeIf(tab1 -> tab1.cardFile.getPath().equals(tab.cardFile.getPath()));
        });
        jPanel.add(button, BorderLayout.EAST);
        return jPanel;
    }

    //method to update the menubar of the frame
    public void updateMenubar() {
        setJMenuBar(new CardSourceMenuBar(this));
    }
}
