package com.jsu.servlet;

import com.alibaba.fastjson.JSON;
import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.dao.ItemDAO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("AddOrderServlet：doPost");
        //获取商品id
        String id = request.getParameter("itemId");
        System.out.println(id);
        getBookById(request,response,Integer.valueOf(id));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AddOrderServlet:doGet");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取商品
        String id = request.getParameter("itemId");
        System.out.println(id);
        String price=request.getParameter("price");
        System.out.println(price);
        HttpSession session=request.getSession();
        Buyer buyer=(Buyer) session.getAttribute("buyer");
        if (buyer!=null){
            System.out.println(buyer.toString());
        }else{
            System.out.println("session空");
        }

        ItemDAO itemDAO=new ItemDAO();
        Item item=itemDAO.getById(Integer.valueOf(id));
        item.setBuyerId(Integer.valueOf(buyer.getBuyerId()));
        item.setMaxPrice(Double.valueOf(price));
        Boolean flag=false;
        try {
            flag=itemDAO.alter(item);
            System.out.println("修改");
            //登录成功就跳转到index页面    重定向
            response.sendRedirect("/commodityAuction/auction.jsp?itemId="+item.getItemId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();
        String json = JSON.toJSONString(flag);
        out.flush();
        out.close();
    }

    //获取book
    public void getBookById(HttpServletRequest request, HttpServletResponse response,Integer id) throws IOException {
        PrintWriter out = response.getWriter();
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.getById(id);
        System.out.println("AddOrderServlet :"+item.toString());
        String json = JSON.toJSONString(item);
        out.print(json);
        out.flush();
        out.close();
    }
}
