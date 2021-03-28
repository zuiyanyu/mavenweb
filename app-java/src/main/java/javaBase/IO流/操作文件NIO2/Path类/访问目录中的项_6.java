package javaBase.IO流.操作文件NIO2.Path类;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO 1. Files.list
 * 方法可以返回一个可以读取目录中各个项的Stream<Path>对象。
 * 1.1目录是被惰性读取的，这使得处理具有大量项的目录可以变得更高效。
 * 1.2读取目录涉及需要关闭的系统资源。
 *         try(Stream<Path> entries = Files.list(pathToDirectory)){ ... }
 * 1.3 list方法不会进入子目录。(Files.walk方法可以处理目录中的所有子目录)
 * TODO 2. Files.walk(path)
 *         try(Stream<Path> walk = Files.walk(Paths.get(""));){
 *             //...
 *         }
 * 只要遍历的项是目录，那么在进入它之前，会继续访问它的兄弟项。
 * TODO 3.  Files.walk(path,depth)   限制访问的树的深度。
 * 两种walk方法都具有FileVisitOption... 的可变长参数，但是你只能提供一种选项：FOLLOW_LINKS ，即跟踪符号链接。
 *
 * TODO 4.如果要过来walk返回的路径，并且过滤涉及与目录存储相关的文件属性：尺寸，创建日期，类型（文件/目录/符号链接）
 * TODO    那么应该使用find方法来替代walk方法。 可以使用某个谓词函数来条用这个方法，该函数接受一个路径和一个BasicFileAttributes对象。
 * TODO    这样做唯一的优势就是效率高。 因为路径总是会被读入，所以这些属性很容易获取。
 */
public class 访问目录中的项_6 {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("");
        Path target = Paths.get("");
        Files.walk(source).forEach(p->{
            try{
                Path q =  target.resolve(source.relativize(p));
                if(Files.isDirectory(p)){
                    Files.createDirectory(q);
                }else{
                    Files.copy(p,q) ;
                }
            }catch (IOException e){
                throw new UncheckedIOException(e) ;
            }

        });

    }
}




























