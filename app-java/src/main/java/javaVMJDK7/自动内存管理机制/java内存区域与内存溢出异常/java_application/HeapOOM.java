package javaVMJDK7.自动内存管理机制.java内存区域与内存溢出异常.java_application;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.代码限制大小为20M不可扩展：将堆的最小值:-Xms 参数与最大值: -Xmx 参数设置为一样即可避免堆自动扩展。
 * 2. 参数： -XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存溢出异常的时候Dump出当前的内存堆转储为快照，以便后面进行分析。
 * -XX:+HeapDumpOnOutOfMemoryError
 * 该配置会把快照保存在用户目录或者tomcat目录下，也可以通过 -XX:HeapDumpPath=/tmp/heapdump.hprof 来显示指定路径
 * 此外，OnOutOfMemoryError参数允许用户指定当出现oom时，指定某个脚本来完成一些动作。
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/heapdump.hprof -XX:OnOutOfMemoryError="sh ~/test.sh"
 *
 * 3.  -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject{}
    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
