package priv.mm.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * InputStreamDemo
 *
 * @author maodh
 * @since 04/02/2018
 */
public class InputStreamDemo {

    public static void main(String[] args) {
        File file = new File("/Users/maomao/IdeaProjects/Demo/src/main/java/priv/mm/io/声声慢.txt");
        try (InputStream in = new FileInputStream(file)) {
            final int length = in.available();
            if (length > 0) {
                byte[] bytes = new byte[length];
                if (length == in.read(bytes)) {
                    System.out.println("文件读取成功！" + System.lineSeparator());
                    System.out.println(new String(bytes, StandardCharsets.UTF_8));
                } else {
                    System.out.println("文件读取失败！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
