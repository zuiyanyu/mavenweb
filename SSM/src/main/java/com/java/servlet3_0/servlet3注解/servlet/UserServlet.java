package com.java.servlet3_0.servlet3注解.servlet;

import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServletBean {
    public UserServlet(){
        System.out.println("UserServlet 构造方法");
    }
    private String userName ;
    public void setUserName(String userName ){
        this.userName = userName ;
    }
    /**
     * Overridden method of {@link HttpServletBean}, invoked after any bean properties
     * have been set. Creates this servlet's WebApplicationContext.
     */
//    @Override
//    protected final void initServletBean() throws ServletException {
//        getServletContext().log("Initializing Spring " + getClass().getSimpleName() + " '" + getServletName() + "'");
//        if (logger.isInfoEnabled()) {
//            logger.info("Initializing Servlet '" + getServletName() + "'");
//        }
//        long startTime = System.currentTimeMillis();
////        initFrameworkServlet();
////        try {
////            this.webApplicationContext = initWebApplicationContext();
////            initFrameworkServlet();
////        }
////        catch (ServletException | RuntimeException ex) {
////            logger.error("Context initialization failed", ex);
////            throw ex;
////        }
////
////        if (logger.isDebugEnabled()) {
////            String value = this.enableLoggingRequestDetails ?
////                    "shown which may lead to unsafe logging of potentially sensitive data" :
////                    "masked to prevent unsafe logging of potentially sensitive data";
////            logger.debug("enableLoggingRequestDetails='" + this.enableLoggingRequestDetails +
////                    "': request parameters and headers will be " + value);
////        }
//
//        if (logger.isInfoEnabled()) {
//            logger.info("Completed initialization in " + (System.currentTimeMillis() - startTime) + " ms");
//        }
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.append(" UserServlet ")
                .append(" add by CustomServletContainerInitializer")
                .flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

//    @Override
//    public void init() throws ServletException {
//        System.out.println("初始化UserServlet");
//        super.init();
//    }
}
