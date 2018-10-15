import javax.swing.*;

public class MainFrame extends JFrame {

    private Screen currentScreen;

    public MainFrame() {
        setTitle("Cardsource");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    //method to update the menubar of the frame
    public void updateMenubar() {
        setJMenuBar(new CardSourceMenuBar(this, currentScreen));
    }
}
