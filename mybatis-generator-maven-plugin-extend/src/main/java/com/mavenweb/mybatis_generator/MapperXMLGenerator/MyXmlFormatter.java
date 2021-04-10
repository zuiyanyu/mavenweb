package com.mavenweb.mybatis_generator.MapperXMLGenerator;

import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.config.Context;

public class MyXmlFormatter {
    protected Context context;

    public MyXmlFormatter() {
    }

    public String getFormattedContent(Document document) {
        return document.getFormattedContent();
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
