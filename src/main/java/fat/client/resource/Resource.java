package fat.client.resource;

import fat.client.gui.tree.Node;
import fat.client.observer.Observable;
import fat.client.observer.Observer;
import fat.client.resource.visitor.ResourceVisitor;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

@Getter
public abstract class Resource implements Observable, Serializable {

    private final String name;

    private final Resource parent;

    private final List<Resource> children = new ArrayList<>();

    private final List<Observer> observers = new ArrayList<>();

    Resource(String name, Resource parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract void acceptVisitor(ResourceVisitor visitor);

    public void addChild(Resource child) {
        if (child == null)
            throw new NullPointerException("Child can't be null!");

        if (children.contains(child))
            throw new IllegalArgumentException(String.format("Child: %s is already present!", child));

        children.add(child);
        notifyObservers(this);
    }

    public void addChildren(Resource... children) {
        Arrays.stream(children).forEach(this::addChild);
    }

    public void removeChild(Resource child) {
        children.remove(child);
        notifyObservers(this);
    }

    public void removeAllChildren() {
        children.clear();
        notifyObservers(this);
    }

    public List<Resource> getChildren() {
        return unmodifiableList(children);
    }

    public List<Node> mapChildrenToNodes() {
        return children.stream()
                .map(Node::new)
                .collect(Collectors.toList());
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer == null)
            throw new NullPointerException("Observer can't be null!");

        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Object notification) {
        observers.forEach(observer -> observer.update(notification));
    }

    @Override
    public String toString() {
        return String.format("Resource{name='%s'}", name);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Resource) {
            Resource other = (Resource) o;

            return Objects.equals(this.name, other.name) &&
                    Objects.equals(this.parent, other.parent);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

}
