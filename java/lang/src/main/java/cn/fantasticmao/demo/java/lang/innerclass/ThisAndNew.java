package cn.fantasticmao.demo.java.lang.innerclass;

/**
 * ThisAndNew
 * 1. 使用 .this 可在内部类中获取外部类对象的引用：ThisAndNew.this.value
 * 2. 使用 .new 可在外部类对象上获取内部类的引用：new ThisAndNew().new Inner().foo()
 */
public class ThisAndNew {
    private String value = ".this and .new";

    private class Inner {
        String foo() {
            // .this
            return ThisAndNew.this.value;
        }
    }

    public static void main(String[] args) {
        // .new
        System.out.println(new ThisAndNew().new Inner().foo());
    }
}
