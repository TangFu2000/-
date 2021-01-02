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
import java.util.List;

@WebServlet(urlPatterns = "/CommodityServlet")
public class CommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

//		System.out.println("WatchPost");
        // 声明out对象
        PrintWriter out = response.getWriter();

        //获取商品类型
        String type=request.getParameter("type");
        System.out.println(type+":CommodityServlet");
        // 通过DAO工厂获得DAO实现类实例
        if(type.equals("book"))
        {
            getBook(request,response);// 返回json对象     {"stu1":stu1, "stu2":stu2,"stu3":stu3 }
        }else if (type.equals("stamp")) {
            getStamp(request,response);
        }
//        else if (type.equals("watch")) {
//            out.print(getWatch());
//        }
//
//        else if (type.equals("wine")) {
//            out.print(getWine());
//        }
//        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    //获取book
    public void getBook(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        ItemDAO itemDAO = new ItemDAO();
        List<Item> lists = itemDAO.getBookAll();
        String json = JSON.toJSONString(lists);
        out.print(json);
        out.flush();
        out.close();
    }

    //获取stamp
    public void getStamp(HttpServletRequest request, HttpServletResponse response) throws IOException{

        PrintWriter out = response.getWriter();
        ItemDAO itemDAO = new ItemDAO();
        List<Item> lists = itemDAO.getStampAll();
        String json = JSON.toJSONString(lists);
        out.print(json);
        out.flush();
        out.close();
    }

//    //获取watch
//    public JSONArray getWatch() {
//        ItemDAO itemDAO=new ItemDAO();
//        List<Item> listAll = null;
//        listAll = itemDAO.getWatchAll();
//
//        //实例化json
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < listAll.size(); i++) {
//            // 实例化一个Book用来获取t_book中的信息
//            Item item = listAll.get(i);
////         	System.out.println(bb.getPicture());
//            jsonArray.put(i,item);
////			System.out.println(WatchJson.getString(i));
//        }
//        return jsonArray;
//    }
//
//
//    // 获取stamp
//    public JSONArray getStamp() {
//        ItemDAO itemDAO=new ItemDAO();
//        List<Item> listAll = null;
//        listAll = itemDAO.getStampAll();
//
//        //实例化json
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < listAll.size(); i++) {
//            // 实例化一个Book用来获取t_book中的信息
//            Item item = listAll.get(i);
////         	System.out.println(bb.getPicture());
//            jsonArray.put(i,item);
////			System.out.println(WatchJson.getString(i));
//        }
//        return jsonArray;
//    }
//    // 获取wine
//    public JSONArray getWine() {
//        ItemDAO itemDAO=new ItemDAO();
//        List<Item> listAll = null;
//        listAll = itemDAO.getWineAll();
//
//        //实例化json
//        JSONArray jsonArray=new JSONArray();
//        for (int i = 0; i < listAll.size(); i++) {
//            // 实例化一个Book用来获取t_book中的信息
//            Item item = listAll.get(i);
////         	System.out.println(bb.getPicture());
//            jsonArray.put(i,item);
////			System.out.println(WatchJson.getString(i));
//        }
//        return jsonArray;
//    }
}
