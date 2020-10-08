package javaBase.IO流.操作文件.Path类;

/**
 * ---------- java.nio.file.Files ---------------
 * TODO 1. static boolean exists(Path path)
 * TODO 2. static boolean isHideden(Path path)
 * TODO 3. static boolean isReadable(Path path)
 * TODO 4. static boolean isWritable(Path path)
 * TODO 5. static boolean isExecutable(Path path)
 * TODO 6. static boolean isRegularFile(Path path)
 * TODO 7. static boolean isDirectory(Path path)
 * TODO 8. static boolean isSymbolicLink(Path path)
 * 检查由路径指定的文件的给定属性
 * TODO 9. static long size(Path path)
 * 获取文件按字节数度量的尺寸
 * TODO 10. A  readAttributes(Path path,Class<A> type,LinkOption ... options)
 * 读取类型为A的文件属性
 * TODO 11. static UserPrincipal Files.getOwner(path)
 * 获取文件的拥有者
 *
 *
 * ---------------java.nio.file.attribute.BasicFileAttributes 7.0---------------------
 * 所有的文件系统都会报告一个基本属性集，他们被封装在BasicFileAttributes接口中，这些属性与上述信息部分重叠。
 * BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
 * TODO 11. FileTime creationTime()       文件初次创建时间
 * TODO 12. FileTime lastAccessTime()     文件最后访问时间
 * TODO 13. FileTime lastModifiedTime()   文件最后修改时间
 * TODO 14. boolean isRegularFile()  是否是常规文件
 * TODO 15. boolean isDirectory()    是否是目录
 * TODO 16. boolean isSymbolicLink() 是否是符号链接
 * TODO 17. long size()
 * TODO 18. Object fileKey()    文件主键
 * 这是某种类的对象，具体所属类与文件系统相关，有肯能是文件的唯一标识符，也可能不是。
 *
 * 要获取这些属性，可以调用：
 * BasicFileAttributes basicFileAttributes = Files.readAttributes(path, BasicFileAttributes.class);
 *
 * 如果用户的系统兼容POSIX，那么可以获取一个：PosixFileAttributes
 * PosixFileAttributes basicFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
 * 然后从中找到组拥有者，以及文件的拥有者，组合访问权限等。
 *
 *
 *
 */
public class 获取文件信息_5 {
    public static void main(String[] args) {

    }
}


















