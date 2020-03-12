package fat.client.gui;

import fat.client.gui.menu.Menu;
import fat.client.gui.resourcepanel.ResourcePanel;
import fat.client.gui.tree.Tree;
import fat.client.gui.util.ComponentSizeCalculator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;

    private Tree tree;

    private ResourcePanel resourcePanel;

    private MainFrame() {}

    public static MainFrame getInstance() {
        if (instance != null)
            return instance;

        return initialize();
    }

    private static MainFrame initialize() {
        instance = new MainFrame();

        instance.initializeFields();
        instance.initializeFrame();
        instance.initializeGUIComponents();

        return instance;
    }

    private void initializeFields() {
        tree = new Tree();
        resourcePanel = new ResourcePanel();
    }

    private void initializeFrame() {
        setTitle("Fat App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ComponentSizeCalculator.calculateMainFrameSize());
        setMinimumSize(ComponentSizeCalculator.calculateMainFrameMinSize());
        setMaximumSize(ComponentSizeCalculator.calculateMainFrameMaxSize());
        setExtendedState(MAXIMIZED_BOTH);
    }

    private void initializeGUIComponents() {
        setJMenuBar(new Menu());
        JSplitPane contentPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        contentPanel.setLeftComponent(new JScrollPane(tree));
        contentPanel.setRightComponent(new JScrollPane(resourcePanel));
        contentPanel.setDividerLocation(ComponentSizeCalculator.calculateSplitPaneDividerLocation());
        add(contentPanel, BorderLayout.CENTER);
    }

    public Tree getTree() {
        return tree;
    }

    public ResourcePanel getResourcePanel() {
        return resourcePanel;
    }
}