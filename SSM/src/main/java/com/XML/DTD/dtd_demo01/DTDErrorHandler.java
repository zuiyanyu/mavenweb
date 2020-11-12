package com.XML.DTD.dtd_demo01;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DTDErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("DTDErrorHandler警告");
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("DTDErrorHandler错误:"+exception.getMessage());

    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("DTDErrorHandler致命错误");

    }
}
