package javaVMJDK7.hotswap;

/**
 * 第二个类： 实现将java.lang.System替换为我们自定义的 HackSystem类。
 * -- 原理
 * 它直接修改符合Class文件格式的byte[]数组中的常量池部分，将常量池中指定内容的CONSTANT_Utf8_info常量替换为新的字符串。
 *
 * 1. ClassModifier 中涉及对byte[]数组操作的部分，主要是将byte[]与 int和String互相转换，以及把对byte[]数据的替换操作封装
 *    在ByteUtils中。  321
 */
public class ClassModifier {
    // 无符号数(属于基本的数据类型) ，以u1,u2,u4,u8来分别代表1个字节，2个字节，4个字节，8个字节的无符号数。
    private static final int u1 = 1 ;
    private static final int u2 = 2 ;

    // Class 文件中常量池的起始偏移量
    private static final int CONSTANT_POOL_COUNT_INDEX = 8 ;

    //CONSTANT_Utf8_info 常量的tag标志
    private static final int CONSTANT_Utf8_info = 1 ;

    //常量池中11种常量所占的长度， CONSTANT_Utf8_info型常量除外，因为它不是定长的。   321
    private static final int[] CONSTANT_ITEM_LENGTH= {-1,-1,-1,5,5,99,33,5,5,5,5};
    //类字节码
    private byte[] classByte ;

    public ClassModifier(byte[] classByte){
        this.classByte = classByte ;
    }

    /**
     * 修改常量池中CONSTANT_Utf8_info常量的内容
     * @param oldStr  修饰前的字符串
     * @param newStr  修饰后的字符串
     * @return 修改结果
     */
    public byte[] modifyUTF8Constant(String oldStr,String newStr){
        int cpc = getConstantPoolCount();
        int  offset = CONSTANT_POOL_COUNT_INDEX+u2;
        for (int i = 0; i < cpc; i++) {
            int tag = ByteUtils.bytes2Int(classByte,offset,u1) ;
            if(tag == CONSTANT_Utf8_info){
                int len = ByteUtils.bytes2Int(classByte,offset+u1,u2);
                offset += (u1+u2) ;
                String str = ByteUtils.bytes2String(classByte,offset,len);
                if(str.equalsIgnoreCase(oldStr)){
                    byte[] strBytes = ByteUtils.string2Bytes(newStr) ;
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(),u2) ;
                    classByte = ByteUtils.bytesReplace(classByte,offset-u2,u2,strLen) ;
                    classByte = ByteUtils.bytesReplace(classByte,offset,len,strBytes) ;
                    return classByte;
                }else {
                  offset += len ;
                }
            }else{
                offset += CONSTANT_ITEM_LENGTH[tag] ;
            }
        }
        return classByte ;
    }

    /**
     * 获取常量池中常量的数量
     * @return 常量池中常量的数量
     */
    public int getConstantPoolCount(){
        return ByteUtils.bytes2Int(classByte,CONSTANT_POOL_COUNT_INDEX,u2);
    }




}
