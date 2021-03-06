package fat.client.observer;

public interface Observable {

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Class<? extends Observer> observerClass, Object notification);

}
