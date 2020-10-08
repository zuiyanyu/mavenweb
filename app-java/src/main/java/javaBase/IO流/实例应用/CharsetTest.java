package javaBase.IO流.实例应用;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 编码
 * TODO 1.平台使用的编码方式可以由静态方法Charset.defaultCharset 返回。
 * TODO 2.静态方法 Charset.availableCharsets 会返回所有可用的Charset实例，返回结果是一个从字符集的规范名
 * TODO 到Charset对象的映射表。
 * TODO 3. StandardCharsets类具有类型为Charset的静态变量，用于表示每种Java虚拟机都必须支持的字符编码方式。
 * TODO 4. 为了获得另一种方式的Charset，可以是使用静态的forName方法：
 *  Charset UTF_8 = Charset.forName("UTF-8");
 *
 * TODO 5.在读入和写出文本时候，应该使用Charset对象。
 * 比如将一个字节数组转换字符串：
 * String str = new String(bytes,StandardCharsets.UTF_8);
 *
 */
public class CharsetTest {
    public static void main(String[] args) {
        Charset charset = StandardCharsets.UTF_8 ; //设置需要的编码
        System.out.println(Charset.defaultCharset()); //获取默认编码
        System.out.println(Charset.availableCharsets()); //获取所有可以支持的编码

    }
}
