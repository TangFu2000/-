package com.jsu.filter;//package com.qst.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter",
        urlPatterns = {"/adindex.jsp","/alterComodity.jsp","/commodityManage.jsp","/userAlter.jsp","/userManage.jsp",
                "/AlterCommodityManageServlet","/AlterCommodityServlet","/CommodityManageServlet","/commoditySerchServlet",
                "/RemoveCommodityServlet","/SubmitCommodityServlet","/UserAlterServlet","/UserServlet"},
        initParams={
//                @WebInitParam(name="NewIndex",value = "NewIndex.jsp"),
//                @WebInitParam(name="login",value = "login.jsp"),
//                @WebInitParam(name="register",value = "register.jsp"),
//                @WebInitParam(name="adIndex",value = "adIndex.jsp"),
//                @WebInitParam(name="IndexServlet",value ="IndexServlet"),
//                @WebInitParam(name="LoginServlet",value ="LoginServlet"),
//                @WebInitParam(name="RegisterServlet",value ="RegisterServlet"),
        }
)
public class AuthFilter implements Filter {
    private FilterConfig config;

    public void init(FilterConfig config) throws ServletException {

        //http://localhost:8080/test/UsermangerServlet
        this.config=config;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;

        //判断用户是否登录
        HttpSession session=request.getSession();
        Object admin=session.getAttribute("admin");

        String path=request.getServletPath();

//        //获取参数
//        String NewIndex=config.getInitParameter("NewIndex");
//        String login=config.getInitParameter("login");
//        String register=config.getInitParameter("register");
////        String adIndex=config.getInitParameter("adIndex");
//        String IndexServlet=config.getInitParameter("IndexServlet");
//        String LoginServlet=config.getInitParameter("LoginServlet");
//        String RegisterServlet=config.getInitParameter("RegisterServlet");

        if(admin!=null){
            chain.doFilter(req, resp);
        }
        else{
            response.sendRedirect("adminLogin.jsp");
        }

    }

    public void destroy() {
    }

}
