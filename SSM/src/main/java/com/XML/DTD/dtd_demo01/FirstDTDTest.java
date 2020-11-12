package com.XML.DTD.dtd_demo01;

import com.XML.XMLUtils.doc.DTDXMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class FirstDTDTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
       String xlmPaht = "D:\\software\\java\\ideaU\\workspace\\mavenweb\\SSM\\src\\main\\java\\com\\XML\\DTD\\dtd_demo01\\DTDtest.xml" ;

        DocumentBuilder documentBuilder = DTDXMLUtil.getDocumentBuilder();
        Document document = documentBuilder.parse(new File(xlmPaht));
        Element  rootElement = document.getDocumentElement();
        NodeList childNodes = rootElement.getChildNodes();

        /**
         * <?xml version="1.0"?>
         * <font>
         *     <name>hello</name>
         *     <size>10</size>
         * </font>
         * 预期 font的根元素下有两个子元素，但是解析器却报告说有五个。
         */
        /**
         *  documentBuilderFactory.setIgnoringElementContentWhitespace(true);
         *  <!DOCTYPE font SYSTEM "../dtds/first.dtd">
         *  经过设置和验证后，可以忽略空白符
         */
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            System.out.println("value = "+item.getTextContent());
        }
//        System.out.println("==========忽略空白符============");
//        for (int i = 0; i < childNodes.getLength(); i++) {
//            Node item = childNodes.item(i);
//            if(item instanceof Element){
//                Element element = (Element)item ;
//                String textContent = element.getTextContent();
//                System.out.println("value = "+textContent);
//            }
//        }
    }



}
