package com.jsu.servlet;

import com.alibaba.fastjson.JSON;
import com.jsu.bean.Item;
import com.jsu.dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/AlterCommodityServlet")
public class AlterCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("修改商品servlet");

        String id= request.getParameter("itemId");
        ItemDAO itemDAO=new ItemDAO();
        Item item=itemDAO.getById(Integer.valueOf(id));
        System.out.println(item.toString());
        PrintWriter out = response.getWriter();
        String json = JSON.toJSONString(item);
        out.print(json);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
