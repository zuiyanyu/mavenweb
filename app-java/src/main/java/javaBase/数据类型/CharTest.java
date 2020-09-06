package javaBase.数据类型;

import org.junit.Test;

/**
 * TODO Unicode 字符超过了 65 536 个（16 位的 Unicode 字符集），现在， 16 位的 char 类型已经不能满足描述所有 Unicode 字符的需要了。
 * TODO 1. 码点 （ code point ) 是指与一个编码表中的某个字符对应的代码值。
 * 1.1  在 Unicode 标准中，码点采用十六进制书写，并加上前缀 U+, 例如 U+0041 就是拉丁字母 A 的码点。
 * 1.2  Unicode 的码点可以分成 17 个代码级别 （codeplane)
 * 1.3 TODO 第一个代码级别称为基本的多语言级别 （basicmultilingual plane ), 码点从 U+0000 到 U+FFFF, 其中包括经典的 Unicode 代码；
 *     其余的 16个级另丨〗码点从 U+10000 到 U+10FFFF , 其中包括一些辅助字符（supplementary character)。
 *TODO 2. UTF-16 编码采用不同长度的编码表示所有 Unicode 码点。在基本的多语言级别中，每个字符用 16 位表示，通常被称为代码单元（code unit) ;
 *         而辅助字符采用一对连续的代码单元进行编码。
 * UTF-16 编码采用不同长度的编码表示所有 Unicode 码点。在基本的多语言级别中，每个
 * 字符用 16 位表示，通常被称为代码单元（code unit) ; 而辅助字符采用一对连续的代码单元
 * 进行编码。这样构成的编码值落人基本的多语言级别中空闲的 2048 字节内， 通常被称为替
 * 代区域（surrogate area) [ U+D800 ~ U+DBFF 用于第一个代码单兀，U+DC00 ~ U+DFFF 用
 * 于第二个代码单元]。这样设计十分巧妙， 我们可以从中迅速地知道一个代码单元是一个字
 * 符的编码，还是一个辅助字符的第一或第二部分。 例如，⑪是八元数集（http://math.ucr.edu/
 * home/baez/octonions) 的一个数学符号，码点为 U+1D546, 编码为两个代码单兀 U+D835 和
 * U+DD46。（关于编码算法的具体描述见 http://en.wikipedia.org/wiki/UTF-l6 ).
 *
 * TODO 3. 强烈建议不要在程序中使用 char 类型， 除非确实需要处理 UTF-16 代码单元。最好将字符串作为抽象数据类型处理
 * TODO 在 Java 中，char 类型描述了 UTF-16 编码中的一个代码单元。
 */
public class CharTest {

    @Test
    public  void charTest1(){
        char ch = '\u2122'; //  注册符号： ™
        System.out.println(ch);
         ch = '\u03C0'; //   符号：π
        System.out.println(ch);

        byte b = (byte) 300;//44
        System.out.println(b);
    }
}
