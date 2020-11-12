package com.XML.DOC_Parse.read_xml.demo_01;

import com.XML.XMLUtils.doc.DOCXMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadXML_Demo01 {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        String xmlPath = "D:\\software\\java\\ideaU\\workspace\\mavenweb\\SSM\\src\\main\\java\\com\\XML\\DOC_Parse\\read_xml\\readxml.xml" ;

        Document document = DOCXMLUtil.getDocument(new File(xmlPath));
        Element rootElement = document.getDocumentElement();
        NodeList childNodes = rootElement.getChildNodes();



        /**
         * <?xml version="1.0"?>
         * <font>
         *     <name>hello</name>
         *     <size>10</size>
         * </font>
         * 预期 font的根元素下有两个子元素，但是解析器却报告说有五个。
         */
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            System.out.println("value = "+item.getTextContent());
        }
        System.out.println("==========忽略空白符============");
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if(item instanceof Element){
                Element element = (Element)item ;
                String textContent = element.getTextContent();
                System.out.println("value = "+textContent);
            }
        }
    }
}
