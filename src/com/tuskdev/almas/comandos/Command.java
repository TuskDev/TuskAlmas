package com.tuskdev.almas.comandos;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Command {
    String[] aliases() default { "" };
    
    String description() default "";
    
    String usage() default "";
    
    String permission() default "";
}
