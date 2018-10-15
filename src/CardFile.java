import java.io.File;

public class CardFile extends File {
    public CardFile(String pathname) {
        super(pathname);
    }

    public CardFile(File file) {
        super(file.getPath() + ".json");
    }

}
