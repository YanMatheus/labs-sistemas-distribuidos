// (c) classe copiada de https://stackoverflow.com/questions/23804675
package client.GUI;

import java.io.File;

public class FileNode {

    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        String name = file.getName();
        return name.equals("") ? file.getAbsolutePath() : name;
    }

}
