package com.jsu.servlet;

import com.jsu.bean.Item;
import com.jsu.dao.ItemDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/RemoveCommodityServlet")
public class RemoveCommodityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String id=request.getParameter("itemId");

        ItemDAO itemDAO=new ItemDAO();
        try {
            itemDAO.removeByAdmin(Integer.valueOf(id));
            System.out.println("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("跳转");
        request.getRequestDispatcher("/CommodityManageServlet").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
