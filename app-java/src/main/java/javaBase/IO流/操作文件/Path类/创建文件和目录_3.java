package javaBase.IO流.操作文件.Path类;

/**
 * TODO 1. Files.createDirectory(path) 非递归创建新目录
 * 其中，路径中除了最后一个部件外，其他的部分必须是已经存在的。
 * TODO 2. Files.createDirectorys(path) 递归创建新目录
 * TODO 3. Files.createFile(path); 创建一个空文件 (操作是原子性的)
 * 如果文件已经存在了，这个调用就会抛出异常。
 * 如果文件不存在，该文件被创建，并且其他程序在此过程中是无法执行文件创建操作的。
 * TODO 4. 检查文件是否存在是原子性的。
 * TODO 5. 创建临时文件或者临时目录
 * Path tmpPath = Files.createTempFile(dir_pathh,prefix,suffix)
 * Path tmpPath = Files.createTempFile(prefix,suffix)
 * Path tmpPath = Files.createTempFile(dir_pathh,prefix)
 * Path tmpPath = Files.createTempFile(prefix)
 * 在适合临时文件的位置，或者给定的父目录中，创建一个临时文件或者目录。返回创建的文件或者目录的路径。
 * 其中，dir是一个Path对象,prefix 和suffix是可以为null的字符串.
 *
 * 例如：调用Files.createTempFile(null,".txt");可能会返回一个像 /tmp/1235563453355.txt这样的路径。
 *
 * TODO 6. 在创建文件或者目录时，可以指定属性，例如文件的拥有者和权限。执行细节取决于文件系统。
 */
public class 创建文件和目录_3 {
    public static void main(String[] args) {

    }
}
