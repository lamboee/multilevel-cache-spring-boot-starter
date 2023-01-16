package cn.lamboee.cache.multilevel.core.notify;

import java.io.Serializable;

/**
 * cache operation event
 *
 * @author lambochen@yeah.net
 */
public interface Event extends Serializable {

    EventType type();

}
