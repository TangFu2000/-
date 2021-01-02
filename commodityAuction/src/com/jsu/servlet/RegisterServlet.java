package com.jsu.servlet;

import com.jsu.bean.Buyer;
import com.jsu.dao.BuyerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/RegisterServlet")
public class RegisterServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("RegisterServlet");

        //response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        //获取前台提交的email和密码

        String name=new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //对数据进行封装，自己封装成一个对象
        Buyer buyer=new Buyer(null,name,email,password,0.0);

        System.out.println(buyer.toString());

        //保存Buyer到数据库BuyerDAO
        BuyerDAO buyerDAO = new BuyerDAO();

        //判断是否有相同的email
        Integer count = buyerDAO.selectBuyerEmailCount(email);
        if (count > 0) {
            //数据库中有相同的参数
            //通过response对象给客户端一个错误提示

            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('申请的email已经被占用！');");
            writer.write("window.location.href='register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }else {
            //flag是否注册成功
            boolean flag = buyerDAO.saveBuyer(buyer);

            if (flag) {
                //注册成功就跳转到登录页面    重定向
                response.sendRedirect("/commodityAuction/login.jsp");
            } else {
                //注册失败就返回注册页面   请求转发
                RequestDispatcher dispatcher = request.getRequestDispatcher("/commodityAuction/register.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
