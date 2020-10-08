package javaBase.多线程.线程安全的集合04;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * TODO 在 Java SE 8中， Arrays类提供了大量并行化操作。
 * TODO 静态 Arrays.parallelSort 方法可以对一个基本类型值或对象的数组排序。
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class 并行数组算法_06 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8); // Read file into string
        String[] words = contents.split("[\\P{L}]+"); // Split along nonletters
        System.out.println(words);
    }
}
