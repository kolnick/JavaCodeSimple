package io.jar;

import java.util.Set;
import java.util.function.Predicate;

/**
 * @author caochaojie
 * @date 2019/09/06
 */
public class ScanExecutor implements Scan {

    private volatile static ScanExecutor instance;

    @Override
    public Set<Class<?>> search(String packageName, Predicate<Class<?>> predicate) {
        Scan fileSc = new FileScanner();
        Set<Class<?>> fileSearch = fileSc.search(packageName, predicate);
        Scan jarScanner = new JarScanner();
        Set<Class<?>> jarSearch = jarScanner.search(packageName, predicate);
        fileSearch.addAll(jarSearch);
        return fileSearch;
    }

    private ScanExecutor() {
    }

    public static ScanExecutor getInstance() {
        if (instance == null) {
            synchronized (ScanExecutor.class) {
                if (instance == null) {
                    instance = new ScanExecutor();
                }
            }
        }
        return instance;
    }

}
