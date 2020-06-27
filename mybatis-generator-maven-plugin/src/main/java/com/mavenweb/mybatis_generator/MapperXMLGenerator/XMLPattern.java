package com.mavenweb.mybatis_generator.MapperXMLGenerator;

import org.mybatis.generator.api.dom.OutputUtilities;

import java.util.List;

public class XMLPattern {
    public   String selectAll(String tabName,String resultType){
        StringBuilder sb = new StringBuilder();

        OutputUtilities.xmlIndent(sb,1);
        sb.append("<select id=\"queryAll\" resultMap=\" ").append(resultType).append( " \">");
        OutputUtilities.newLine(sb);

        OutputUtilities.xmlIndent(sb,2);
        sb.append("select * from").append(" ").append(tabName) ;
        OutputUtilities.newLine(sb);

        OutputUtilities.xmlIndent(sb,1);
        sb.append("</select>");
        OutputUtilities.newLine(sb);
        return sb.toString() ;
    }

    public String insertOne(String tabName,List<String> fields,String parameterType){
//        StringBuilder sb = new StringBuilder() ;
//
//        OutputUtilities.xmlIndent(sb,1);
//        sb.append(" <insert id=\"insertSelective\" parameterType=\"").append(parameterType).append("\">");
//        OutputUtilities.newLine(sb);
//
//        OutputUtilities.xmlIndent(sb,2);
//        sb.append("  insert into ").append(tabName).append(" ").append("values(");
//        int size = fields.size();
//        for(int i = 0; i < size-1;i++){
//           sb.append(fields.get(i)).append(",");
//        }
//        sb.append(fields.get(size-1))
//               .append(")") ;
//        OutputUtilities.newLine(sb);
//
//
//
//        OutputUtilities.xmlIndent(sb,1);
//        sb.append("</insert>");
//        OutputUtilities.newLine(sb);
//        return sb.toString();
        return null ;
    }


}
