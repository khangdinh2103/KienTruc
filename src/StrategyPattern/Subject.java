package StrategyPattern;

public interface  Subject {
        void attach(IObserver observer);
        void detach(IObserver observer);
        void notifyObservers(String message);
}
