/**
 * 
 */
package rs.codecentric.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vladimir.vasic@codecentric.de
 *
 */
public abstract class BaseBean implements Serializable {

	private static final long serialVersionUID = 7647650153788340411L;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    protected abstract void init();

    protected abstract void done();

    @PostConstruct
    private void _init() {
        log.info("Initializing " + this.getClass().getName());
        init();
    }

    @PreDestroy
    private void _done() {
        log.info("Destroying " + this.getClass().getName());
        done();
    }
}
