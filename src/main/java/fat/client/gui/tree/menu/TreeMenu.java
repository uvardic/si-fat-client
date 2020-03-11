package fat.client.gui.tree.menu;

import fat.client.gui.Action;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeMenu extends JPopupMenu {

    private final List<Action> actions = new ArrayList<>();

    public TreeMenu() {}

    public void addAction(Action action) {
        if (action == null)
            throw new NullPointerException("Action can't be null!");

        if (actions.contains(action))
            throw new IllegalArgumentException(String.format("Action %s is already present!", action));

        actions.add(action);
    }

    public void addActions(Action... actions) {
        Arrays.stream(actions).forEach(this::addAction);
    }

    public void removeAllActions() {
        actions.clear();
    }

    public void showActions() {
        actions.forEach(this::add);
    }

}
