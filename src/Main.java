import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Main {

    private static MainFrame mainFrame;

    public static void main(String[] args){
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public static MainFrame getMainFrame() {
        return mainFrame;
    }

    public static void openFile(File file) {
        CardFile cardFile = new CardFile(file);
        mainFrame.newCardTab(cardFile);
    }

    /**
     * Chooses a card file to open
     */
    public static void chooseOpenFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Spellsource Card Files", "json"));
        int result = jFileChooser.showOpenDialog(mainFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            openFile(file);
        }
    }

    /**
     * Creates a new card file
     */
    public static void newFile() {

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("New");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Spellsource Card Files", "json"));
        int result = jFileChooser.showSaveDialog(Main.getMainFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            CardFile quizFile = new CardFile(file);

        }
    }

    /**
     * Save the file currently being edited
     * @return whether the file was successfully saved
     */
    public static boolean saveFile() {
        return false;
    }


    /**
     * Save the file currently being edited to a different location
     * @return whether the file was successfully saved
     */
    public static boolean saveFileAs() {
        return false;
    }
}
