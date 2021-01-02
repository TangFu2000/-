package com.jsu.servlet;

import com.jsu.bean.Admin;
import com.jsu.dao.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String username= request.getParameter("username");
        String password= request.getParameter("password");

        Admin admin=new Admin(null,username,password);
        AdminDAO adminDAO=new AdminDAO();
        if(adminDAO.selectAdminCount(admin)>0){
            HttpSession session=request.getSession();
            session.setAttribute("admin",admin);
            System.out.println("登录成功！");
            request.getRequestDispatcher("adindex.jsp").forward(request, response);

        }else {
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('用户名或密码错误！');");
            writer.write("window.location.href='adminLogin.jsp'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
