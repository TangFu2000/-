package com.jsu.dao;

import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.utils.DBUtils;

import java.util.List;

public class BuyerDAO {
    //保存注册对象
    public boolean saveBuyer(Buyer buyer) {
        String sql="insert into buyer(buyerName,email,password,balance) values(?,?,?,?)";
        return DBUtils.save(sql,buyer.getBuyerName(),buyer.getEmail(),buyer.getPassword(),buyer.getBalance());
    }

    //根据email和密码查询用户
    public Buyer getBuyerByEmailAndPass(String email,String password) {
        String sql="select buyerId, buyerName, email, password, balance from buyer where email= ? and password= ?";
        return DBUtils.getSingleObj(Buyer.class,sql,email,password);
    }

    //根据id查询用户
    public Buyer getBuyerById(Integer id) {
        String sql="select buyerId, buyerName, email, password, balance from buyer where buyerId=?";
        return DBUtils.getSingleObj(Buyer.class,sql,id);
    }

    //根据email查询用户是否存在
    public Integer selectBuyerEmailCount(String email) {
        String sql="select count(*) from buyer a where a.email=?";
        Integer count=DBUtils.getCount(sql,email);
        return count;
    }

    //查询所有用户
    public List<Buyer> getBuyerAll() {
        String sql="select buyerId, buyerName, email, password, balance from buyer";
        return DBUtils.getList(Buyer.class,sql);
    }

    //查询一共有多少行
    public Integer getBuyerCount() {
        String sql="select count(*) from buyer";
        Integer count=DBUtils.getCount(sql);
        return count;
    }

    //分页查询
    public List<Buyer> getCompanyListByPage(String sql) {
        return DBUtils.getList(Buyer.class,sql);
    }

    // 修改操作(AlterCommodityManageServlet调用)
    public boolean alterByAdmin(Buyer buyer) throws Exception {
        //String sql="insert into item(itemName,initPrice,info,addTime,endTime,type,img) values('a',100,'娃哈哈','2020-12-29','2020-12-30',1,'123456789')";

        String sql="update buyer set buyerName=?,email=?,password=?,balance=? where buyerId=?";
        return DBUtils.update(sql,buyer.getBuyerName(),buyer.getBuyerName(),buyer.getPassword(),buyer.getBalance(),buyer.getBuyerId());
    }


}
