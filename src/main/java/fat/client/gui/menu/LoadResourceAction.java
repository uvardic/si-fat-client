package fat.client.gui.menu;

import fat.client.gui.Action;
import fat.client.gui.Dialog;
import fat.client.metascheme.MetaSchemeParser;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

class LoadResourceAction extends Action {

    LoadResourceAction() {
        super("Load Resource", "Load Resource");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            MetaSchemeParser.parseDatabaseMetaData();
            Dialog.message("Database", "Connected!");
        } catch (SQLException e) {
            Dialog.error("Database", "Error while connecting!");
        }
    }
}
