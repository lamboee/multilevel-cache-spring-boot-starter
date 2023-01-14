package cn.lamboee.cache.multilevel.core.event;

/**
 * @author lambochen@yeah.net
 */
public interface PublishWrapper extends Wrapper {

    default void publish(Event event) {
        if (event instanceof PutEvent) {
            publish((PutEvent) event);
            return;
        }

        if (event instanceof EvictEvent) {
            publish((EvictEvent) event);
            return;
        }

        if (event instanceof ClearEvent) {
            publish((ClearEvent) event);
            return;
        }

        throw new UnsupportedOperationException("Unsupported Event");
    }

    void publish(PutEvent event);

    void publish(EvictEvent event);

    void publish(ClearEvent event);


}
