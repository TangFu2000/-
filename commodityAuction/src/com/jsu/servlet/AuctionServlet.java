package com.jsu.servlet;

import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.dao.BuyerDAO;
import com.jsu.dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AuctionServlet")
public class AuctionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println("AuctionServlet");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//		重定向
//		response.sendRedirect("IndexServlet");
//		doPost(request, response);
//        System.out.println("AuctionServletDoPost");
        //获取商品id
        Integer id = Integer.valueOf(request.getParameter("id"));
        //获取商品类型
        String type = request.getParameter("type");
//		System.out.println(id);
        System.out.println("auctionServletType:" + type);

//		System.out.println(time);
        //重定向
//		response.sendRedirect("auction.jsp");
        //判断是哪个表
        if (type.equals("book")) {

            /**
             *     private Integer itemId;
             *     private String itemName;
             *     private Double initPrice;
             *     private Double maxPrice;
             *     private String info;
             *     private Date addTime;
             *     private Date endTime;
             *     private String type;
             *     private String img;
             *     private Integer buyerId;
             *     private Integer state;
             */

            Item item = getItem(request, response, id);
            Long time = getTime(id);
            Buyer buyer = getBuyer(request, response, item.getBuyerId());
            Double maxPrice = item.getMaxPrice();
            Double price = item.getInitPrice();
            if (item != null) {
                request.setAttribute("type", type);
                request.setAttribute("buyer", buyer);
                request.setAttribute("time", time);
                request.setAttribute("price", price);
                request.setAttribute("maxPrice", maxPrice);
                request.setAttribute("commodity", item);
                request.getRequestDispatcher("/auction.jsp").forward(request, response);
            }

            System.out.println(item.toString());


//            //执行t_book表的操作
//            try {
//
//                //根据id返回book信息
//                Book thisEntity = getBook(request, response, id);
//                //获得时间差
//                Long time = getBookTime(id);
//                //获得物品所有者
//                User user=getUser(request, response,thisEntity.getUserId());
//                //获得最大价格
//                float maxPrice=thisEntity.getMaxPrice();
//                System.out.println("maxPrice:"+maxPrice);
//                float price=thisEntity.getPrice();
////				System.out.println(user.getName());
//                if(thisEntity != null) {
//                    request.setAttribute("type", type);
//                    request.setAttribute("user", user);
//                    request.setAttribute("time", time);
//                    request.setAttribute("price", price);
//                    request.setAttribute("maxPrice", maxPrice);
//                    request.setAttribute("commodity", thisEntity);
//                    request.getRequestDispatcher("auction.jsp").forward(request, response);
//                }
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//
//        //wacth
//        else if (type.equals("watch")) {
//            try {
//                //根据id返回信息
//                Watch thisEntity = getWatch(request, response, id);
//                //获得时间差
//                Long time = getWatchTime(id);
//                //获得物品所有者
//                User user=getUser(request, response,thisEntity.getUserId());
//                //获得最大价格
//                float maxPrice=thisEntity.getMaxPrice();
//                System.out.println("maxPrice:"+maxPrice);
//                float price=thisEntity.getPrice();
////				System.out.println(user.getName());
//                if(thisEntity != null) {
//                    request.setAttribute("type", type);
//                    request.setAttribute("user", user);
//                    request.setAttribute("time", time);
//                    request.setAttribute("price", price);
//                    request.setAttribute("maxPrice", maxPrice);
//                    request.setAttribute("commodity", thisEntity);
//                    request.getRequestDispatcher("auction.jsp").forward(request, response);
//                }
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        //stamp
//        else if (type.equals("stamp")) {
//            try {
//                //根据id返回信息
//                Stamp thisEntity = getStamp(request, response, id);
//                //获得时间差
//                Long time = getStampTime(id);
//                //获得物品所有者
//                User user=getUser(request, response,thisEntity.getUserId());
//                //获得最大价格
//                float maxPrice=thisEntity.getMaxPrice();
//                System.out.println("maxPrice:"+maxPrice);
//                float price=thisEntity.getPrice();
////				System.out.println(user.getName());
//                if(thisEntity != null) {
//                    request.setAttribute("type", type);
//                    request.setAttribute("user", user);
//                    request.setAttribute("time", time);
//                    request.setAttribute("price", price);
//                    request.setAttribute("maxPrice", maxPrice);
//                    request.setAttribute("commodity", thisEntity);
//                    request.getRequestDispatcher("auction.jsp").forward(request, response);
//                }
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        // wine
//        else if (type.equals("wine")) {
//            try {
//                // 根据id返回信息
//                Wine thisEntity = getWine(request, response, id);
//                // 获得时间差
//                Long time = getWineTime(id);
//                // 获得物品所有者
//                User user = getUser(request, response, thisEntity.getUserId());
//                // 获得最大价格
//                float maxPrice = thisEntity.getMaxPrice();
//                System.out.println("maxPrice:" + maxPrice);
//                float price = thisEntity.getPrice();
////						System.out.println(user.getName());
//                if (thisEntity != null) {
//                    request.setAttribute("type", type);
//                    request.setAttribute("user", user);
//                    request.setAttribute("time", time);
//                    request.setAttribute("price", price);
//                    request.setAttribute("maxPrice", maxPrice);
//                    request.setAttribute("commodity", thisEntity);
//                    request.getRequestDispatcher("auction.jsp").forward(request, response);
//                }
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        }
    }

//    private Long getWatchTime(int id) {
//        // TODO Auto-generated method stub
//        Long time = null;
//        WatchDao thisDao=WatchDaoFactory.getWatchDaoInstance();
//        try {
//            time = thisDao.getSecond(id);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return time;
//    }

    //根据id获取Item信息
    public Item getItem(HttpServletRequest request, HttpServletResponse response,int id){
        Item item=null;
        ItemDAO itemDAO=new ItemDAO();
        item=itemDAO.getById(id);

        if(item==null){
            System.out.println("获取物品失败");
        }
        return item;
    }
    //获取物品所有者
    public Buyer getBuyer(HttpServletRequest request, HttpServletResponse response,int buyerId){
        Buyer buyer = null;
        BuyerDAO buyerDAO=new BuyerDAO();
        buyer=buyerDAO.getBuyerById(buyerId);
        return buyer;

    }

    //获取现在时间与结束时间的时间差
    private long getTime(int id){
        Long time = null;
        ItemDAO itemDAO=new ItemDAO();
        time=itemDAO.getSecond(id);
        return time;
    }

}
