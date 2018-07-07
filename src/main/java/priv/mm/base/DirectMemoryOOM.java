package priv.mm.base;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * DirectMemoryOOM
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * @author maodh
 * @since 22/05/2018
 */
public class DirectMemoryOOM {

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(1024 * 1024 * 1024);
        }
    }
}