package game.subsribers;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<IEventListener> listeners;

    public EventManager() {
        listeners = new ArrayList<>();
    }

    public void subscribe(IEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(IEventListener listener) {
        listeners.remove(listener);
    }

    public void Sent_notify(String event) {
        for (IEventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
