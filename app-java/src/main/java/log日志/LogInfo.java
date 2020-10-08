package log日志;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * •    日志记录可以被定向到不同的处理器， 用于在控制台中显示， 用于存储在文件中等。
 * •    应用程序可以使用多个日志记录器， 它们使用类似包名的这种具有层次结构的名字，
 *      例如， com.mycompany.myapp 0
 * •    在默认情况下，日志系统的配置由配置文件控制。 如果需要的话， 应用程序可以替换这个配置。
 * •    日志记录可以采用不同的方式格式化，例如，纯文本或 XML。
 *
 * TODO  要想将日志记录发送到其他地方， 就要添加其他的处理器。 日志 API为此提供了两个很
 * TODO  有用的处理器， 一个是 FileHandler ; 另 一个是 SocketHandler。
 * TODO  FileHandler, 它可以收集文件中的记录。SocketHandler 将记录发送到特定的主机和端口.
 *        可以像下面这样直接将记录发送到默认文件的处理器：
 *        FileHandler handler = new FileHandler();
 *        1ogger.addHandl er(handler)
 *
 */
public class LogInfo {
    //TODO 未被任何变量引用的日志记录器可能会被垃圾回收。 为了防止这种情况发生， 用一个静态变量存储日志记录器的一个引用。
    public static Logger LOGGER = Logger.getLogger(LogInfo.class.getName());
    public static void main(String[] args) throws IOException {
        betterLog();
    }

    /**
     * TODO 2.高级日志  企业级（ industrial-strength) 日志
     * TODO 在一个专业的应用程序中，不要将所有的日志都记录到一个全局日志记录器中，而是可以自定义日志记录器。
     *      可以调用 getLogger 方法创建或获取记录器：
     *      private static final Logger myLogger = Logger.getLogger("com.mycompany.myapp"):
     *
     */
    public static void betterLog() throws IOException {
        Logger logger = Logger.getLogger(LogInfo.class.getName());


        FileHandler handler = new FileHandler();
        logger.addHandler(handler) ;
        logger.info("betterLog - info log");
        handler.flush();

    }

    /**
     * TODO 1.基本日志
     * 要生成简单的日志记录，可以使用全局日志记录器（global logger) 并调用其 info 方法
     */
    public static void baseLog(){
        Logger globalLog = Logger.getGlobal();

        //日志等级 ： INFO > WARNING   (打印高等级的必定会打印低等级的日志)
        //globalLog.setLevel(Level.INFO);
        globalLog.setLevel(Level.WARNING);

        globalLog.info("打印的info日志内容");
        globalLog.warning("打印的warning日志内容");
    }
}