package fat.client.gui.panel.state;

import fat.client.gui.panel.ResourcePanel;

public class OperationPanelStateManager {

    private final DeleteOperationPanelState deleteResourcePanelState;

    private final FindOperationPanelState findResourcePanelState;

    private final SaveOperationPanelState saveResourcePanelState;

    private final UpdateOperationPanelState updateResourcePanelState;

    private OperationPanelState currentState;

    public OperationPanelStateManager(ResourcePanel resourcePanel) {
        deleteResourcePanelState = new DeleteOperationPanelState(resourcePanel);
        findResourcePanelState = new FindOperationPanelState(resourcePanel);
        saveResourcePanelState = new SaveOperationPanelState(resourcePanel);
        updateResourcePanelState = new UpdateOperationPanelState(resourcePanel);

        currentState = saveResourcePanelState;
    }

    public OperationPanelState getCurrentState() {
        return currentState;
    }

    public void startDeleteResourcePanelState() {
        currentState = deleteResourcePanelState;
        deleteResourcePanelState.initializePanel();
    }

    public void startFindResourcePanelState() {
        currentState = findResourcePanelState;
        findResourcePanelState.initializePanel();
    }

    public void startAddResourcePanelState() {
        currentState = saveResourcePanelState;
        saveResourcePanelState.initializePanel();
    }

    public void startEditResourcePanelState() {
        currentState = updateResourcePanelState;
        updateResourcePanelState.initializePanel();
    }

}
