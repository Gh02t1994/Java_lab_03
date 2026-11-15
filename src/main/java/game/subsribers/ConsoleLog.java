package game.subsribers;

public class ConsoleLog implements IEventListener {
    @Override
    public void onEvent(String event) {
        System.out.println("Произошло событие: " + event);
    }
}