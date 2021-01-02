package com.jsu.servlet;

import com.jsu.bean.Item;
import com.jsu.bean.PageBean;
import com.jsu.dao.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/CommoditySerchServlet")
public class CommoditySerchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //1、每页多少行 pageSize
        String pageSizeStr=request.getParameter("pageSize");
        Integer pageSize=null;//每页多少行
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.valueOf(pageSizeStr);
        }else {
            pageSize=2;
        }

        //2、当前是第几页 currentPage
        String currentPageStr=request.getParameter("currentPage");
        Integer currentPage=null;//当前查询第几页
        if(currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.valueOf(currentPageStr);
        }else {
            currentPage=1;
        }

        //3、一共有多少行 totalRows
        Integer totalRows=0;
        ItemDAO itemDAO=new ItemDAO();
        totalRows=itemDAO.getItemCount();
        System.out.println("共行："+totalRows);

        //5、起始行 startRow
        Integer startRow=(currentPage-1)*pageSize;

        String value=request.getParameter("serchInput");
        System.out.println("搜索内容："+value);
        List<Item> itemList=null;
        StringBuffer sqlRow1=new StringBuffer("select * from item where itemName like ");
//        sqlRow.append("%"+"?"+"%");
        StringBuffer sqlRow2=new StringBuffer();
        sqlRow2.append(" limit ").append(startRow).append(",").append(pageSize);

        System.out.println("1:"+sqlRow1.toString());
        System.out.println("2:"+sqlRow2.toString());
        System.out.println("value:"+value);
        itemList= itemDAO.getItemListByPage2(sqlRow1.toString(),sqlRow2.toString(),value);
        System.out.println(itemList);

        PageBean pageBean=new PageBean( currentPage,  pageSize,    totalRows,   itemList);
        request.setAttribute("pageBean",pageBean);

        System.out.println("跳转到commodityManage.jsp");
        request.getRequestDispatcher("commodityManage.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
