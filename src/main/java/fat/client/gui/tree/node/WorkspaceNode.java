package fat.client.gui.tree.node;

import fat.client.model.Repository;
import fat.client.model.Resource;
import fat.client.model.Workspace;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

import static java.util.Collections.enumeration;

@Data
@RequiredArgsConstructor
public class WorkspaceNode implements Node {

    private final Resource<Object, Repository> resource;

    public WorkspaceNode() {
        this.resource = new Workspace("Workspace");
    }

    @Override
    public String format() {
        return resource.getName();
    }

    @Override
    public TreeNode getChildAt(int i) {
        return resource.getChildrenAsNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return resource.getChildrenAsNodes().size();
    }

    @Override
    public TreeNode getParent() {
        return resource.getParentAsNode();
    }


    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public int getIndex(TreeNode treeNode) {
        return resource.getChildrenAsNodes().indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return enumeration(resource.getChildrenAsNodes());
    }

}
