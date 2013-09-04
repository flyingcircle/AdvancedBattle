package me.capstone.advancedbattle.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to mark methods as being event handler methods
 */
// TODO : Figure out how to do this in Android
@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
public @interface EventHandler {
	
	Order order() default Order.DEFAULT;

}
