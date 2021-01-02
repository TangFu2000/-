package com.jsu.servlet;

import com.jsu.bean.Buyer;
import com.jsu.dao.BuyerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet( request,  response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("LoginServlet");

        //response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取前台提交的email和密码
        String email = request.getParameter("username");
        String password = request.getParameter("password");

        //根据email和密码查询申请人
        BuyerDAO buyerDAO=new BuyerDAO();

        Buyer buyer=buyerDAO.getBuyerByEmailAndPass(email,password);

        //buyer==null,返回登录页面，不为空，就进入主页面
        if(buyer!=null){
            //账号写入session
            HttpSession session=request.getSession();
            session.setAttribute("buyer",buyer);
            //登录成功就跳转到index页面    重定向
            response.sendRedirect("/commodityAuction/NewIndex.jsp");

        }else {
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！');");
            writer.write("window.location.href='/commodityAuction/register.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }
}
