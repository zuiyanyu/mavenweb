package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet 与 JavaBean 的区别就是要在 WEB-INF 中建立一个 web.xml， 在其中指向自己写的
 * Servlet， 声明名称、 类型、 路径即可。 使用方法和 JavaBean 当然也不一样， 通过浏览器直接访问
 * 这个 Servlet 了。
 */
public class HttpServletTest extends HttpServlet {
    private String userName = "userName";

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getSession().getServletContext();
        ServletContext context = servletContext.getContext("/httpServlet/httpServletTest.do");
        System.out.println("context = "+context);
        String serverInfo = context.getServerInfo();
        System.out.println("serverInfo="+serverInfo);

        //重定向
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("");
//        requestDispatcher.forward(req,resp);
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Map map = new HashMap<>() ;
        String contextPath = req.getContextPath();
        System.out.println("contextPath="+contextPath);
        map.put("contextPath",contextPath);


        map.put("this.userName ",userName);
        //获取全局初始化的参数
        HttpSession session = req.getSession();
        ServletContext servletContext = session.getServletContext();
        String userNameGlobal = servletContext.getInitParameter("userName");
        System.out.println("userNameGlobal="+userNameGlobal);
        map.put("userNameGlobal",userNameGlobal) ;

        String requestMmethod = req.getMethod();
        System.out.println("requestMmethod=" + requestMmethod);
        map.put("requestMmethod",requestMmethod) ;

        //向页面输出信息
        //置全部的头部信息
        response.setContentType("text/html");
        //在使用 PrintWriter 或者 ServletOutputStream 向文档写东西前， 需要设置全部的头部信息
        PrintWriter out=response.getWriter();
        //在页面上输出内容
        out.println("<html><body><h1>This is a Servlet test.<br>"+map+"</h1></body></html>");
        out.flush();
    }

    /**Servlet 加载并实例化后， 容器必须在它能够处理客户端请求前初始化它。 初始化的过程主要是读取永久的配置信息，
     *  昂贵资源（ 例如 JDBC 连接） 以及其它仅仅需要执行一次的任务。
     *  通过调用它的 init 方法并给它传递唯一的一个（ 每个 Servlet 定义一个） ServletConfig 对象完成这个过程。
     *  给它传递的这个配置对象允许 Servlet 访问容器的配置信息中的名称－ 值对（ name-value） 初始化参数。
     *
     * ServletConfig 描述了 Servlet 的运行环境
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        String userName = config.getInitParameter("userName");
        System.out.println("HttpServletTest初始化的参数：userName="+userName);
        this.setUserName(userName);
        super.init(config);
    }
}
