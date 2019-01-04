package priv.mm.design_pattern.singleton;

import javax.annotation.concurrent.ThreadSafe;

/**
 * InnerClassMode
 *
 * @author maodh
 * @since 2019/1/4
 */
@ThreadSafe
public class InnerClassMode {

    private static class Singleton {
        private static final InnerClassMode INSTANCE = new InnerClassMode();
    }

    private InnerClassMode() {
    }

    public static InnerClassMode getInstance() {
        return Singleton.INSTANCE;
    }
}
