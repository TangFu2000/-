package com.jsu.servlet;

import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.dao.ItemDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/AddCommodityServlet")
public class AddCommodityServlet extends HttpServlet {
    //serialVersionUID作用是序列化时保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性,用于版本控制
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		response.getWriter().append("Served at: ").append(request.getContextPath());
        doPost(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        request.setAttribute("message", "");
        request.setAttribute("path", "");
        String filename = null;
        // 设置上传图片的保存路径
        String savePath = this.getServletContext().getRealPath("/imges");
        File file = new File(savePath);
        System.out.println("路径："+savePath);
        // 判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath + "目录不存在，自动创建目录");
            // 创建目录
            file.mkdir();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> list = null;
        try {
            list = upload.parseRequest(request);
        } catch (FileUploadException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // 3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 按照传统方式获取数据
            return;
        }
        System.out.println(list.toString());// 文件的路径 以及保存的路径
        for (FileItem item : list) {
            filename = item.getName();// 获得一个项的文件名称
            if (filename == null || filename.trim().equals("")) {// 如果為空則跳過
                continue;
            }

            // 報錯 需要過濾文件名稱 java.io.FileNotFoundException:
            // G:\测试02\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\FaceUp\WEB-INF\images\C:\Users\Ray\Pictures\2.jpeg
            // (文件名、目录名或卷标语法不正确。)

            filename = filename.substring(filename.lastIndexOf("\\") + 1);
//				System.out.print(filename);
            if (filename.substring(filename.lastIndexOf(".") + 1).equals("png")
                    || filename.substring(filename.lastIndexOf(".") + 1).equals("jpg")
                    || filename.substring(filename.lastIndexOf(".") + 1).equals("JPG")
                    || filename.substring(filename.lastIndexOf(".") + 1).equals("PNG")
                    || filename.substring(filename.lastIndexOf(".") + 1).equals("jpeg")) {
                InputStream in = item.getInputStream();// 獲得上傳的輸入流
                FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);// 指定web-inf目錄下的images文件
                request.setAttribute("path", "images" + "\\" + filename);

                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = in.read(buffer)) > 0)// 每次讀取
                {
                    out.write(buffer, 0, len);
                }
                in.close();
                out.close();
                item.delete();
            } else {
                // 必须是图片才能上传否则失败
                System.out.println("必须上传图片!");
                return;
            }
        }
        System.out.println(filename);
        String imgUrl = "imges/" + filename;
        System.out.println("完整路径:" + imgUrl);

//        // 获取用户id
//        int userId = Integer.valueOf(request.getParameter("userId"));
//        System.out.println("userId:" + userId);
        Float price = null;
        String introduce = null;
        String type = null;
        String name=null;
        String endTime=null;

        // 获取表中的数据
        Iterator<FileItem> thisItem = list.iterator();
        while (thisItem.hasNext()) {
            FileItem thisItem2 = thisItem.next();
            String thisItemName = thisItem2.getFieldName();
            if (thisItem2.isFormField()) {
                if(thisItemName.equals("name")){
                    name = thisItem2.getString("utf-8");
                    System.out.println(name);
                }else if (thisItemName.equals("price")) {
                    price = Float.parseFloat(thisItem2.getString("utf-8"));
                    System.out.println(price);
                } else if (thisItemName.equals("introduce")) {
                    introduce = thisItem2.getString("utf-8");
                    System.out.println(introduce);
                }else if (thisItemName.equals("endTime")) {
                    endTime = thisItem2.getString("utf-8");
                    System.out.println(endTime);
                }else if (thisItemName.equals("select")) {
                    type = thisItem2.getString("utf-8");
                    System.out.println(type);
                }
            }
        }

        Date addDate=new Date();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date endDate= null;
        try {
            endDate = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Integer buyerId= (Integer) request.getSession().getAttribute("buyerId");
        Item item=new Item(null,name,Double.valueOf(price),null,introduce,addDate,endDate,type,filename,null,buyerId,1);

        addCommodity(item);
        response.sendRedirect("/commodityAuction/NewIndex.jsp");
    }

    //存入数据库中
    public void addCommodity(Item item) {

        ItemDAO itemDAO=new ItemDAO();
        try {
            if (itemDAO.insert(item)) {
                System.out.println("新增拍品存入数据库成功");
            }else {
                System.out.println("新增拍品存入数据库失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}