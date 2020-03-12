package fat.client.metascheme;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fat.client.gui.MainFrame;
import fat.client.resource.Resource;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MetaSchemeWriter {

    public static void write(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            Resource rootResource = MainFrame.getInstance().getTree().getRoot().getResource();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(rootResource));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
