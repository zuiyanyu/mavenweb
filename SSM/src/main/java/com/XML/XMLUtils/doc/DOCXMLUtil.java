package com.XML.XMLUtils.doc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class DOCXMLUtil {
    /**
     * 1. 要读入一个XML文档，首先需要一个DocumentBuilder对象
     * @return
     * @throws ParserConfigurationException
     */
    private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder ;
    }
    //2. 读入xml，形成 document树 。
    //2.1输入源是 文件对象
    public static Document getDocument(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = getDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        return document ;
    }
    //2.2输入源是  url
    public static Document getDocument(URL xmlPathURL) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = getDocumentBuilder();
        Document document = documentBuilder.parse(xmlPathURL.toString());
        return document ;
    }
    //2.3输入源是  输入流
    public static Document getDocument(InputStream xmlFileInputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = getDocumentBuilder();
        Document document = documentBuilder.parse(xmlFileInputStream);
        return document ;
    }

    //3. 获取根元素，开始对文档树进行分析
    public static Element getRootElement(Document document){
        Element root = document.getDocumentElement();
        return root ;
    }

}
