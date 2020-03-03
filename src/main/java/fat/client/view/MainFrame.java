package fat.client.view;

import fat.client.view.util.ComponentSizeCalculator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private MainFrame() {}

    public static MainFrame getInstance() {
        if (instance == null)
            instance = new MainFrame();

        return instance;
    }

    public void start() {
        initializeFrame();
        initializeComponents();
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("Fat App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ComponentSizeCalculator.calculateMainFrameSize());
        setMinimumSize(ComponentSizeCalculator.calculateMainFrameMinSize());
        setMaximumSize(ComponentSizeCalculator.calculateMainFrameMaxSize());
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void initializeComponents() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // to be replaced by tree and table
        splitPane.setLeftComponent(new JScrollPane(new JPanel()));
        splitPane.setRightComponent(new JScrollPane(new JPanel()));
        splitPane.setDividerLocation(ComponentSizeCalculator.calculateSplitPaneDividerLocation());

        add(splitPane, BorderLayout.CENTER);
    }

}
