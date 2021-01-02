package com.jsu.servlet;

import com.alibaba.fastjson.JSON;
import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/EndBidServlet")
public class EndBidServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取商品
        String id = request.getParameter("itemId");
        System.out.println(id);
        ItemDAO itemDAO=new ItemDAO();
        Item item=itemDAO.getById(Integer.valueOf(id));
        item.setState(2);
        Boolean flag=false;
        try {
            flag=itemDAO.alter(item);
            System.out.println("竞拍结束");
            //成功就跳转到index页面    重定向
            response.sendRedirect("/commodityAuction/NewIndex.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
