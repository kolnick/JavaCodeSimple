package io.jar;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author caochaojie
 * @date 2019/09/06
 */
public interface Scan {

    String CLASS_SUFFIX = ".class";

    Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate);

    default Set<Class<?>> search(String packageName) {
        return search(packageName, null);
    }

}
