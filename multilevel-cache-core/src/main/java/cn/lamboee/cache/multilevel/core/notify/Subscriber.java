package cn.lamboee.cache.multilevel.core.notify;

/**
 * @author lambochen@yeah.net
 */
public interface Subscriber extends Wrapper {

    default void subscribe(Event event) {
        if (event instanceof PutEvent) {
            subscribe((PutEvent) event);
            return;
        }

        if (event instanceof EvictEvent) {
            subscribe((EvictEvent) event);
            return;
        }

        if (event instanceof ClearEvent) {
            subscribe((ClearEvent) event);
            return;
        }

        throw new UnsupportedOperationException("Unsupported Event");
    }

    void subscribe(PutEvent event);

    void subscribe(EvictEvent event);

    void subscribe(ClearEvent event);

}
