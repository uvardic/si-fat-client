package fat.client.gui.tree;

import fat.client.resource.Resource;

import javax.swing.tree.TreeNode;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Objects;

public class Node implements TreeNode {

    private final Resource resource;

    public Node(Resource resource) {
        this.resource = resource;
    }

    public String format() {
        return resource.getName();
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public TreeNode getChildAt(int i) {
        return resource.mapChildrenToNodes().get(i);
    }

    @Override
    public int getChildCount() {
        return resource.getChildren().size();
    }

    @Override
    public TreeNode getParent() {
        if (resource == null || resource.getParent() == null)
            return null;

        return new Node(resource.getParent());
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public int getIndex(TreeNode treeNode) {
        return resource.mapChildrenToNodes().indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return resource.getChildren().size() != 0;
    }

    @Override
    public boolean isLeaf() {
        return resource.getChildren().size() == 0;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(resource.mapChildrenToNodes());
    }

    @Override
    public String toString() {
        return String.format("Node{resource=%s}", resource);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node other = (Node) o;

            return Objects.equals(this.resource, other.resource);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resource);
    }

}
