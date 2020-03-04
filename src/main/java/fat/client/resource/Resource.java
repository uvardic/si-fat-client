package fat.client.resource;

import fat.client.observer.Observable;
import fat.client.observer.Observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public abstract class Resource implements Observable, Serializable {

    private final String name;

    private final List<Resource> children = new ArrayList<>();

    private final List<Observer> observers = new ArrayList<>();

    Resource(String name) {
        this.name = name;
    }

    public void addChild(Resource child) {
        if (child == null)
            throw new NullPointerException("Child can't be null!");

        children.add(child);
        notifyObservers(this);
    }

    public void addChildren(Resource... children) {
        Arrays.stream(children).forEach(this::addChild);
    }

    public String getName() {
        return name;
    }

    public List<Resource> getChildren() {
        return unmodifiableList(children);
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

            return Objects.equals(this.name, other.name);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
