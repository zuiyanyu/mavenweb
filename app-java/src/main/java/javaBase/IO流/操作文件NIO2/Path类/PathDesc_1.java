package javaBase.IO流.操作文件NIO2.Path类;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TODO 1. 输入输出关心的是文件的内容，这里关系的是磁盘上如何存储文件。
 * TODO 2. Path 接口和Files类是在 JDK7 中添加进来的，他们比 自JDK1.0以来就一直使用的File类要方便得多。
 * TODO 3. Files类可以用来移除，重命名文件，查询文件最后被修改的时间等。
 * TODO 4. Path表示的是一个目录名序列，其后还可以跟着一个文件名。
 * 路径中的第一个部件可以是根部件，比如： /  或者 C:\  ,而允许访问的根部件取决于文件系统。
 * 以根部件开始的路径是绝对路径；否则就是相对路径；
 * Path absolute = Paths.get("/home","myapp");  将得到 /home/myapp
 * Path relative = Paths.get("myprog","conf","user.properties");
 * TODO 5. 静态的Paths.get方法接收一个或多个字符串，并将它们用默认文件系统的路径分隔符（Unix系统是：/ ; Windows是：\）连接起来。
 * 然后它解析连接起来的结果，如果其表示的不是给定的文件系统中的合法路径，那么就抛出InvaildpathException。这个连接起来的结果就是一个Path对象。
 *
 * TODO 6. resolve() 方法
 * p.resolve(q)
 * 如果q 是绝对路径，结果是q ;
 * 如果q是相对路径，结果是 p/q （将q拼接到p后面作为结果）
 *
 * TODO 7. resolveSibling()方法
 * Path p =  Paths.get("/home", "myapp"); //得到的结果是/home/myapp
 * Path q =  p.resolveSibling("myapp2");  //得到的结果是 /home/myapp2
 * 它通过解析指定路径的父路径产生其兄弟路径。
 *
 * TODO 8. relativize()方法 （resolve的对立面）
 * p.relativize(r) 将产生路径q，而对q进行解析的结果是 r.
 * 例如： 以/home/cay 为目标，对/home/fred/myprog 进行相对化操作，会产生 ../fred/myprog
 * 其中，我们假设 .. 表示文件系统中的父目录
 * /home/cay
 * /home/fred/myprog
 * 相对化操作后：
 *  ../fred/myprog
 *
 * p = /a/b/ c/d
 * q=  /a/b/ d/c
 *  p.relativize(q)
 *处理过程为：
 * 1. 从第一部分开始逐个比较，直到找到不相同的那一部分。
 * 2. 从不公部分开始(包含此部分)，截取p后面的部分（c，d 两部分 ）, 并将每一部分变为 .. 的形式（c，d 变为  .. , ..两部分）。
 * 3. 从不公部分开始(包含此部分)，截取q后面的部分(d,c两部分)。
 * 4. 将 c，d 替换的部分 和  d,c两部分 进行拼接，得到 ../../d/c ，即为最终结果。
 *
 * TODO 9. toAbsolutePath() 方法将产生给定路径的绝对路径，该绝对路径从根部开始。
 * 例如：/a/b/c.txt  或者 c:\a\b\c.txt
 * TODO 10.normalize() 方法将移除所有冗余的 .和..部件(或者文件系统认为冗余的所有部件)
 * 例如：
 * TODO 10. 偶尔，可能需要与遗留系统的API交互，他们使用的是File类而不是Path接口。
 * TODO     Path接口有一个toFIle方法，而File类有一个toPath方法
 */
public class PathDesc_1 {
    public static void main(String[] args) throws IOException {
        //创建绝对路径 Path
        Path workPath = Paths.get("/home", "myapp"); // 将得到 /home/myapp
        System.out.println( workPath.toString());

        //创建相对路径 Path
        Path tmpPath =  Paths.get("work", "tmp");  //work/tmp
        System.out.println(tmpPath.toString());

        //组合路径 p.resolve(q)
        Path resolve = workPath.resolve(tmpPath);  // 组合路径： \home\myapp\work\tmp
        System.out.println(resolve.toString());

        resolve = workPath.resolve("tmp2");  // 组合路径：\home\myapp\tmp2
        System.out.println(resolve.toString());

        //resolveSibling()方法
        Path myapp2 = workPath.resolveSibling("myapp2");//\home\myapp2
        System.out.println(myapp2.toString());

        //relativize()方法 （resolve的对立面）
        Path relativizeTmp = Paths.get("/work","myapp","tmp");

        /**
         * /home/myapp
         *            /work/myapp/tmp
         *  ------------------------------
         * /home/myapp/work/myapp/tmp
         *  /.. /  .. /work/myapp/tmp
         * -----------------
         * ..\..\work\myapp\tmp
         */
        Path p1 = Paths.get("/home", "myapp"); // 将得到 /home/myapp
        Path relativize1 = p1.relativize(relativizeTmp); //..\..\work\myapp\tmp
        System.out.println(relativize1.toString());

        /**
         * /work /tmp
         * /work /myapp/tmp
         * ---------------
         * /work/tmp/myapp/tmp
         * /work/../myapp/tmp
         * --------------
         *      ../myapp/tmp
         *
         */
        Path p2 =  Paths.get("/work", "tmp");
        Path relativize2 = p2.relativize(relativizeTmp); // ..\myapp\tmp
        System.out.println(relativize2.toString());

        /**
         * /work /tmp/myapp
         * /work /myapp/tmp
         * ---------------
         * /work/tmp/myapp/myapp/tmp
         * /work/../../myapp/tmp
         * ----------------
         *      ../../myapp/tmp
         */
        Path p3 =  Paths.get("/work", "tmp","myapp");
        Path relativize3 = p3.relativize(relativizeTmp); //..\..\myapp\tmp
        System.out.println(relativize3.toString());

        /**
         * /home /cay
         * /home /fred/myprog
         * ---------------
         * /home/cay/fred/myprog
         * /home/../fred/myprog
         * --------------
         * ..\fred\myprog
         *
         *
         * /home /fred/myprog
         * /home /cay
         * ---------------
         * /home /fred/myprog/cay
         * /home /../../cay
         * ----------
         * ..\..\cay
         */
        Path p4 = Paths.get("/home/a/cay");
        Path p5 = Paths.get("/home/a/fred/myprog");
        System.out.println(p4.relativize(p5).toString());  // ..\fred\myprog
        System.out.println(p5.relativize(p4).toString());  //..\..\cay

        //TODO 1. /root/a/b/../c ，会消掉 b/.. 变成 /root/a/c ;
        //TODO 2. /root/a/b/../../c ，会先消掉 b/..  变成 /root/a/../c ，然后再消掉 a/.. 变成 /root/c ;
        //TODO 3. /root/a/b/.././c ，会消掉 b/.. 和 ./ 变成 /root/a/c ;
        Path p6 = Paths.get("/home/cay/b/../fred/../../myprog");
        Path normalize = p6.normalize(); // \home\myprog
        System.out.println(normalize.toString());

        Path source = Paths.get("/a/b/c");
        Path p = Paths.get("/a/b/c/d/e.txt");

        Path relativize = source.relativize(p);//relativize = d\e.txt
        System.out.println("relativize = "+relativize.toString());

        Path target = Paths.get("/k/j"); //\k\j\d\e.txt
        Path resolve1 = target.resolve(relativize);
        System.out.println("target.resolve(relativize) = "+resolve1.toString());

        //TODO Path类有许多有用的方法来将路径断开。
        Path p7 = Paths.get("/root","a","b","c.txt");
        Path parent = p7.getParent();
        System.out.println(parent.toString());//\root\a\b

        Path file = p7.getFileName() ;
        System.out.println(file.toString());//c.txt

        Path root = p7.getRoot();
        System.out.println(root.toString());// \

        //TODO 可以从Path对象中构建Scanner对象
//        Scanner in = new Scanner(Paths.get("/root/a/b/c.txt"));

        /**

         */
    }
}






































