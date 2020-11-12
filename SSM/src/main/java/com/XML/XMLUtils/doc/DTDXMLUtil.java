package com.XML.XMLUtils.doc;

import com.XML.DTD.dtd_demo01.DTDErrorHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DTDXMLUtil {
    public static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        //开启DTD xml验证
        documentBuilderFactory.setValidating(true);
        //忽略节点中的空白字符; 同时要对xml进行验证，比如DTD验证，解析时候才能真正忽略空白符。
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new DTDErrorHandler());
        return documentBuilder ;
    }
}
