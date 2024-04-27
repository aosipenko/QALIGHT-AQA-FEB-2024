package org.web.tracing;

import org.web.tracing.samples.RootPage;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TraceDemo {

    public static void main(String[] args) {
        Map<String, Object> map = tree(RootPage.class);
        System.out.println(map);
    }

    public static Map<String, Object> tree(Class c) {
        Map<String, Object> tree = new HashMap<>();
        getBN(c.getDeclaredFields()).forEach(f -> {
            tree.put(f.getType().getName(), tree(f.getType()));
        });
        return tree;
    }

    private static List<Field> getBN(Field[] fields) {
        return Arrays.stream(fields).filter(
                f -> Arrays.stream(f.getType().getAnnotations()).anyMatch(
                        annotation -> annotation.annotationType().equals(BusinessNode.class)
                )
        ).collect(Collectors.toList());
    }

}
