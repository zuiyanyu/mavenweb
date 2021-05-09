package redis.applications.message_verify.constant;

//与短信验证相关的静态常量同一配置到此处
public class CodeConfig {
    public static String PHONE_PREFIX= "phoneno:" ; //前缀
    public static String PHONE_SUFFIX =":code";     //前缀
    public static String COUNT_SUFFIX=":count" ;//计数器key后缀
    public static int CODE_LEN=6 ; //随机码长度
    public static int CODE_TIMEOUT=60*5 ; //随机码有效时间  5分钟
    public static int COUNT_TIMES_1DAY=300 ; //单日最多发送次数
    public static int SECONDS_PER_DAY=60*60*24 ;//单日秒数
    public static String HOST="localhost" ;//redis服务主机地址
    public static int PORT=6379 ;//redis服务端口号
}
