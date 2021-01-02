package com.jsu.dao;

import com.jsu.bean.Admin;
import com.jsu.bean.Buyer;
import com.jsu.utils.DBUtils;

public class AdminDAO {
    //根据管理员名和密码查询用户是否存在
    public Integer selectAdminCount(Admin admin) {
        String sql="select count(*) from admin a where a.adminName=? and a.password=?";
        Integer count=DBUtils.getCount(sql,admin.getAdminName(),admin.getPassword());
        return count;
    }
}
