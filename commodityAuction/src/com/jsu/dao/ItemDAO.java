package com.jsu.dao;

import com.jsu.bean.*;
import com.jsu.utils.DBUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemDAO {
    // 增加操作
    public boolean insert(Item item) throws Exception {
        //String sql="insert into item(itemName,initPrice,info,addTime,endTime,type,img) values('a',100,'娃哈哈','2020-12-29','2020-12-30',1,'123456789')";
        String sql="insert into item(itemName,initPrice,maxPrice,info,addTime,endTime,type,img,sellerId,state) values(?,?,?,?,?,?,?)";
        return DBUtils.save(sql,item.getItemName(),item.getInitPrice(),item.getMaxPrice(),item.getInfo(),item.getAddTime(),item.getEndTime(),item.getType(),item.getImg(),item.getSellerId(),item.getState());
    }

    // 修改操作
    public boolean alter(Item item) throws Exception {
        //String sql="insert into item(itemName,initPrice,info,addTime,endTime,type,img) values('a',100,'娃哈哈','2020-12-29','2020-12-30',1,'123456789')";
        String sql="update item set maxPrice=?,buyerId=? where itemId=?";
        return DBUtils.update(sql,item.getMaxPrice(),item.getBuyerId(),item.getItemId());
    }

    // 修改操作(SubmitAlterCommodityServlet调用)
    public boolean alterByAdmin(Item item,Boolean flag) throws Exception {
        //String sql="insert into item(itemName,initPrice,info,addTime,endTime,type,img) values('a',100,'娃哈哈','2020-12-29','2020-12-30',1,'123456789')";
        if(flag){
            String sql="update item set itemName=?,initPrice=?,info=?,endTime=?,type=?,img=? where itemId=?";
            return DBUtils.update(sql,item.getItemName(),item.getInitPrice(),item.getInfo(),item.getEndTime(),item.getType(),item.getImg(),item.getItemId());
        }else{
            String sql="update item set itemName=?,initPrice=?,info=?,endTime=?,type=? where itemId=?";
            return DBUtils.update(sql,item.getItemName(),item.getInitPrice(),item.getInfo(),item.getEndTime(),item.getType(),item.getItemId());
        }

    }

    //分页查询
    public List<Item> getItemListByPage(String sql) {
        return DBUtils.getList(Item.class,sql);
    }

    //分页查询,带条件
    public List<Item> getItemListByPage2(String sql1,String sql2,String value) {
        StringBuffer sql=new StringBuffer("");
        sql.append(sql1).append("?").append(sql2);
        System.out.println(sql.toString());
        return DBUtils.getList(Item.class,sql.toString(),"%"+value+"%");
    }

    //查询一共有多少行
    public Integer getItemCount() {
        String sql="select count(*) from item";
        Integer count=DBUtils.getCount(sql);
        return count;
    }

    //查询所有
    public List getItemAll(){
        List<Item> itemAll = new ArrayList<Item>() ;
        String sql = "select * from item";
        return DBUtils.getList(Item.class,sql);
    }

    //查询所有book
    public List getBookAll(){
        List<Item> BookAll = new ArrayList<Item>() ;
        String sql = "select * from item where type='book'";
        return DBUtils.getList(Item.class,sql);
    }
    //查询所有watch
    public List getWatchAll(){
        List<Item> BookAll = new ArrayList<Item>() ;
        String sql = "select * from item where type='watch'";
        return DBUtils.getList(Item.class,sql);
    }
    //查询所有stamp
    public List getStampAll(){
        List<Item> BookAll = new ArrayList<Item>() ;
        String sql = "select * from item where type='stamp'";
        return DBUtils.getList(Item.class,sql);
    }
    //查询所有wine
    public List getWineAll(){
        List<Item> BookAll = new ArrayList<Item>() ;
        String sql = "select * from item where type='wine'";
        return DBUtils.getList(Item.class,sql);
    }

    // 获取特定商品
    public Item getById(int itemId) {
        String sql = "select * from item where itemId=" + itemId;
        return DBUtils.getSingleObj(Item.class,sql);
    }

    public long getSecond(int itemId) {

        long second = 0;
        Item item=getById(itemId);
        Date nowDate=new Date();
        //3天259200000毫秒
        second=item.getEndTime().getTime()+259200000-nowDate.getTime();
        // rs进行读取一次 判断是否有数据
        // rs默认指向第0条数据，next实际上为第一条数据
        // 第6行为图片url
        return second;
    }

    // 删除操作
    public boolean removeByAdmin(Integer id) throws Exception {
        String sql="delete from item where itemId=?";
        return DBUtils.delete(sql,id);
    }

}
