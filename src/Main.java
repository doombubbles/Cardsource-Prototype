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

    /**
     * Switches the screen to a different screen of the program
     * @param newScreen the screen to switch to
     */
    public static void switchScreen(Screen newScreen) {
        mainFrame.remove(mainFrame.getCurrentScreen());
        mainFrame.setCurrentScreen(newScreen);
        mainFrame.add(newScreen);
        mainFrame.updateMenubar();
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public static void openFile(File file) {
        CardFile quizFile = new CardFile(file);
        CardEditorScreen quizEditorScreen = new CardEditorScreen(quizFile);
        switchScreen(quizEditorScreen);
    }

    /**
     * Chooses a quiz file to open
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
     * Creates a new quiz file
     */
    public static void newFile() {

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("New");
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Spellsource Card Files", "json"));
        int result = jFileChooser.showSaveDialog(Main.getMainFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser.getSelectedFile();
            CardFile quizFile = new CardFile(file);
            CardEditorScreen cardEditorScreen = new CardEditorScreen(quizFile);
            if (mainFrame.getCurrentScreen() instanceof CardEditorScreen) {
                CardEditorScreen editorScreen = (CardEditorScreen) mainFrame.getCurrentScreen();
                /*
                if (!editorScreen.saveChangesFirst()) {
                    return;
                }
                */
            }
            switchScreen(cardEditorScreen);

        }
    }

    /**
     * Save the file currently being edited
     * @return whether the file was successfully saved
     */
    public static boolean saveFile() {
        if (!(mainFrame.getCurrentScreen() instanceof CardEditorScreen)) {
            return false;
        }

        CardEditorScreen quizEditorScreen = (CardEditorScreen) mainFrame.getCurrentScreen();
        return false;//quizEditorScreen.saveFile();
    }


    /**
     * Save the file currently being edited to a different location
     * @return whether the file was successfully saved
     */
    public static boolean saveFileAs() {
        if (!(mainFrame.getCurrentScreen() instanceof CardEditorScreen)) {
            return false;
        }

        CardEditorScreen cardEditorScreen = (CardEditorScreen) mainFrame.getCurrentScreen();
        return false;//cardEditorScreen.saveFileAs();
    }
}
