package com.mavenweb.Funcations.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;

/**
 * Yaml文件有自己独立的语法，常用作配置文件使用，相比较于xml和json而言，减少很多不必要的标签或者括号，阅读也更加清晰简单；本篇主要介绍下YAML文件的基本语法，以及如何在Java中实现读写逻辑
 *
 * TODO 1.基本语法
 * todo 1.1 使用空格 Space 缩进表示分层，不同层次之间的缩进可以使用不同的空格数目，但是同层元素一定左对齐，即前面空格数目相同
 *          （不要使用tab）
 * todo 1.2  #表示单行注释
 * todo 1.3 破折号 "- "后面跟一个空格（a dash and space）表示列表
 * todo 1.4 用冒号和空格表示键值对 key: value
 * todo 1.5 简单数据（scalars，标量数据）可以不使用引号括起来，包括字符串数据
 * todo 1.6 用单引号或者双引号括起来的被当作字符串数据，在单引号或双引号中使用C风格的转义字符
 *
 * todo 1. 数组写法
 * 一个简单的数组，用-来列出即可，如下
 *
 * - apple
 * - orange
 * - banana
 *
 * todo 2. 对象
 * 一个简单的kv对象
 *
 * fruit:
 *   name: banana
 *   amount: 3
 *   price: 4.99
 *
 * todo 3. 对象数组
 * 首先大结构是数组，但是数组内部是一个kv结构的对象
 *
 * -
 *   name: apple
 *   price: 1.23
 * -
 *   name: orange
 *   price: 1.33
 * -
 *   name: banana
 *   price: 2.33
 *
 * todo 4. 数组对象
 * 首先大结构是对象，对象内部的成员是数组
 *
 * name:
 *   - apple
 *   - orange
 *   - banana
 * price:
 *   - 4.99
 *   - 2.34
 *   - 3.99
 *
 * todo 5. 多维数组
 * 用中括号包括起来，形成一个二维数组
 *
 * - [apple, 3.88]
 * - [orange, 3.99]
 * - [banana, 2.99]
 *
 * todo 6. 对象的扩展写法
 * 对于kv结构的对象，支持通过大括号的方式来替代，简化配置文件的行数
 *
 * love: {name: apple, price: 2.99}
 * hite: {name: orange, price: 1.99}
 *
 * 上面的配置，等同于
 * love:
 *   name: apple
 *   price: 2.99
 * hite:
 *   name: orange
 *   price: 1.99
 *
 *TODO 2. YAML文件读写
 * 在Java生态环境中，读写YAML文件算是比较简单的一个事情了，一个是自己读取文件，然后按照语法进行解析（属于自己造轮子）；另外一个就是利用开源库来读写，这里当然是选择已经颇为完善的开源库来处理了
 * todo 2.1. 依赖
 * pom文件中添加maven依赖，版本号查询最新的即可
 *
 * <dependency>
 *   <groupId>org.yaml</groupId>
 *   <artifactId>snakeyaml</artifactId>
 *   <version>1.17</version>
 * </dependency>
 *
 *
 */
public class YamlUtil {
    //TODO 2. YAML文件读写
    public static InputStream loadStream(String path) throws IOException {
        if (path.startsWith("http")) {
            URL url = new URL(path);
            return url.openStream();
        } else if (path.startsWith("/")) {
            return new FileInputStream(path);
        } else {
            return YamlUtil.class.getClassLoader().getResourceAsStream(path);
        }
    }

    public static <T> T loadConf(String path, Class<T> clz) throws IOException {
        try (InputStream inputStream = loadStream(path)) {
            Yaml yaml = new Yaml();
            return yaml.loadAs(inputStream, clz);
        }
    }

    public static <T> void dumpConf(String save, T obj) throws IOException {
        Yaml yaml = new Yaml();
        yaml.dump(obj, new BufferedWriter(new FileWriter(save)));
    }
}
