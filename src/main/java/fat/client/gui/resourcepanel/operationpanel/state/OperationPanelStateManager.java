package fat.client.gui.resourcepanel.operationpanel.state;


import fat.client.gui.resourcepanel.ResourcePanel;

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

    public void startSaveResourcePanelState() {
        currentState = saveResourcePanelState;
        saveResourcePanelState.initializePanel();
    }

    public void startUpdateResourcePanelState() {
        currentState = updateResourcePanelState;
        updateResourcePanelState.initializePanel();
    }

}
