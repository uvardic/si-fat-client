package fat.client.gui.util;

import fat.client.gui.MainFrame;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class ComponentSizeCalculator {

    private static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    private static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private static final int MIN_WIDTH = 200;

    private static final int MIN_HEIGHT = 50;

    private static final int TEXT_FIELD_HEIGHT = 25;

    private static final int TOOLBAR_HEIGHT = 50;

    private ComponentSizeCalculator() {}

    public static Dimension calculateMainFrameSize() {
        return new Dimension(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
    }

    public static Dimension calculateMainFrameMinSize() {
        return new Dimension(MIN_WIDTH, MIN_HEIGHT);
    }

    public static Dimension calculateMainFrameMaxSize() {
        return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public static Dimension calculateTextFieldSize() {
        return new Dimension(SCREEN_WIDTH / 4, TEXT_FIELD_HEIGHT);
    }

    public static int calculateSplitPaneDividerLocation() {
        return SCREEN_WIDTH / 6;
    }

    public static int calculateResourcePanelDividerLocation() {
        return SCREEN_HEIGHT / 2 - TOOLBAR_HEIGHT;
    }

    public static void adjustResourcePanelDividerLocation() {
        MainFrame.getInstance()
                .getResourcePanel()
                .getContentPanel()
                .setDividerLocation(SCREEN_HEIGHT / 2 - TOOLBAR_HEIGHT);
    }

    public static MigLayout calculateLayoutForEditResourcePanel() {
        return new MigLayout("", String.format("[]%d[]", SCREEN_WIDTH / 2));
    }

}
