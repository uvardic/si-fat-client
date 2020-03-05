package fat.client.gui.tree;

import fat.client.resource.Resource;

import javax.swing.tree.TreeNode;
import java.util.*;

public class Node implements TreeNode {

    private final Resource resource;

    private final List<Node> children = new ArrayList<>();

    public Node(Resource resource) {
        this.resource = resource;
        mapChildren(resource);
    }

    private void mapChildren(Resource resource) {
        resource.getChildren()
                .stream()
                .map(Node::new)
                .forEach(children::add);
    }

    public String format() {
        return resource.getName();
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public TreeNode getChildAt(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return new Node(resource);
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public int getIndex(TreeNode treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return children.size() != 0;
    }

    @Override
    public boolean isLeaf() {
        return children.size() == 0;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children);
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
