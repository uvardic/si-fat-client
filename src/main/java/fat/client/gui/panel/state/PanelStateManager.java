package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;

public class PanelStateManager {

    private final DeleteResourcePanelState deleteResourcePanelState;

    private final FindResourcePanelState findResourcePanelState;

    private final SaveResourcePanelState saveResourcePanelState;

    private final UpdateResourcePanelState updateResourcePanelState;

//    Posto je isforsiran state pattern ovo nam kao ne treba
//    private PanelState currentState;

    public PanelStateManager(ResourcePanel resourcePanel) {
        deleteResourcePanelState = new DeleteResourcePanelState(resourcePanel);
        findResourcePanelState = new FindResourcePanelState(resourcePanel);
        saveResourcePanelState = new SaveResourcePanelState(resourcePanel);
        updateResourcePanelState = new UpdateResourcePanelState(resourcePanel);

//        currentState = saveResourcePanelState;
    }

//    public PanelState getCurrentState() {
//        return currentState;
//    }

    public void startDeleteResourcePanelState() {
        deleteResourcePanelState.initializePanel();
    }

    public void startFindResourcePanelState() {
        findResourcePanelState.initializePanel();
    }

    public void startAddResourcePanelState() {
        saveResourcePanelState.initializePanel();
    }

    public void startEditResourcePanelState() {
        updateResourcePanelState.initializePanel();
    }

}
