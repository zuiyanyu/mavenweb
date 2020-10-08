package javaBase.IO流.操作文件.Path类;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * TODO 1. Files.copy(formPath,toPath)  将文件从一个位置复制到另一个位置
 * TODO 2. Files.move(fromPath,toPath)  移动文件(即复制并删除原文件)
 * 2.1如果目标路径已经存在，那么复制或者移动将失败。
 * 2.2如果想要覆盖已有的路径，可以使用 StandardCopyOption.REPLACE_EXISTING 选项。
 * 2.3如果想要复制所有的文件属性，可以使用 StandardCopyOption.COPY_ATTRIBUTES 选项。
 * 2.4如果想同时选择两项：比如 拷贝所有的文件，并覆盖已经存在的文件
 *        //拷贝所有的文件，并覆盖已经存在的文件
 *         Files.move(Paths.get(""),Paths.get(""),StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.REPLACE_EXISTING );
 *2.5可以将移动操作定义为原子性的，这样就可以保证要么移动操作成功完成，要么源文件继续保持在原来的位置。
 *         Files.move(Paths.get(""),Paths.get(""),StandardCopyOption.ATOMIC_MOVE)
 *
 * TODO 3. FIles.copy(inputStream ,toPath) 将一个输入流复制到Path中(表示将输入流存储到硬盘上)
 * TODO 4. FIles.copy(fromPath,outputStream) 将一个Path复制到输入流中(表示将硬盘上读取到输出流中)
 *
 * TODO 5. Files.delete(path) 删除文件 (还可以删除空目录)
 * 如果删除文件不存在，这个方法就会抛出异常。
 * TODO 6. boolean deleted = Files.deleteIfExists(path)  删除文件(还可以删除空目录)
 * 删除文件不存在不会抛出异常，但是返回值为false
 * TODO 7.
 */
public class 复制_移动_删除文件_4 {
    public static void main(String[] args) throws IOException {
        //拷贝所有的文件，并覆盖已经存在的文件
        Files.move(Paths.get(""),Paths.get(""),StandardCopyOption.ATOMIC_MOVE,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.REPLACE_EXISTING );
    }
}





























