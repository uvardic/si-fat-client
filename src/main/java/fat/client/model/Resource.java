package fat.client.model;

import fat.client.gui.tree.node.Node;

import java.util.List;

// might remove parent
public interface Resource<Parent, Child> {

    void addChild(Child child);

    String getName();

    Node getParentAsNode();

    List<Node> getChildrenAsNodes();

}
