package com.jsu.servlet;

import com.alibaba.fastjson.JSON;
import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.dao.BuyerDAO;
import com.jsu.dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/UserAlterServlet")
public class UserAlterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String id=request.getParameter("buyerId");
        BuyerDAO buyerDAO=new BuyerDAO();
        Buyer buyer=null;
        buyer=buyerDAO.getBuyerById(Integer.valueOf(id));
        System.out.println(buyer.toString());

        System.out.println("跳转");


        PrintWriter out = response.getWriter();
        String json = JSON.toJSONString(buyer);
        out.print(json);
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
