package com.jsu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/IndexServlet")
public class IndexServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //注销的业务
        HttpSession session = request.getSession();
        if(session.getAttribute("buyer") != null){
            if(request.getParameter("login") != null && request.getParameter("login").toString().equals("no")){
                //清除session
                session.setAttribute("buyer",null);
            }
        }

        request.getRequestDispatcher("NewIndex.jsp").forward(request, response);
    }
}
