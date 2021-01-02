package com.jsu.utils;

import com.jsu.bean.Buyer;
import com.jsu.bean.Item;
import com.jsu.dao.BuyerDAO;
import com.jsu.dao.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        ItemDAO itemDAO=new ItemDAO();

        List<Item> list=new ArrayList<>();
        list=itemDAO.getBookAll();
        System.out.println("book"+list.toString());

        list=itemDAO.getWatchAll();
        System.out.println("watch"+list.toString());

        list=itemDAO.getStampAll();
        System.out.println("stamp"+list.toString());

        list=itemDAO.getWineAll();
        System.out.println("wine"+list.toString());

    }

}
