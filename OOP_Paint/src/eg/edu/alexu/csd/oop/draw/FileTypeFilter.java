package eg.edu.alexu.csd.oop.draw;

import java.io.File;
import javax.swing.filechooser.*;

public class FileTypeFilter extends FileFilter {

    private final String extension;
    private final String describtion;

    public FileTypeFilter(String extension,String describtion) {
        this.extension=extension;
        this.describtion=describtion;
    }
    public boolean accept(File f) {
        if(f.isDirectory())
            return true;

        return f.getName().endsWith(extension);
    }


    public String getDescription() {
        return describtion+String.format(" (%s)", extension);
    }

}
