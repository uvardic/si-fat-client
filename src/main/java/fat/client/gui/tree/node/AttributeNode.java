package fat.client.gui.tree.node;

import fat.client.model.Entity;
import fat.client.model.Resource;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

import static java.util.Collections.enumeration;

@Data
@RequiredArgsConstructor
public class AttributeNode implements Node {

    private final Resource<Entity, Object> resource;

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
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return enumeration(resource.getChildrenAsNodes());
    }

}
