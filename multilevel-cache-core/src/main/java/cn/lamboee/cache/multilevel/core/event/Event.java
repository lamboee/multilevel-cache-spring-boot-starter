package cn.lamboee.cache.multilevel.core.event;

import java.io.Serializable;

/**
 * cache operation event
 *
 * @author lambochen@yeah.net
 */
public interface Event extends Serializable {

    EventType type();

}
