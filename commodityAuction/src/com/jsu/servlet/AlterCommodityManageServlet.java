package com.jsu.servlet;

import com.jsu.bean.Buyer;
import com.jsu.dao.BuyerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AlterCommodityManageServlet")
public class AlterCommodityManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String id=request.getParameter("postBuyerId");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String balance=request.getParameter("balance");
        Buyer buyer=new Buyer(Integer.valueOf(id),name,email,password,Double.valueOf(balance));
        BuyerDAO buyerDAO=new BuyerDAO();
        try {
            buyerDAO.alterByAdmin(buyer);
            System.out.println("修改用户");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(buyer.toString());
        System.out.println("跳转");
        response.sendRedirect("/commodityAuction/UserServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
