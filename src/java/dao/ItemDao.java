/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
/**
 *
 * @author DB1407
 */
public class ItemDao {
    private final String SELECT = "select * from item";
    private Koneksi kon;
    
    public ItemDao(){
        this.kon = new Koneksi();
    }
    
    public List<Item> getAll(){
        List<Item> items = new ArrayList<>();
        ResultSet rs;
        Statement statement;
        try {
            statement = kon.getConn().createStatement();
            rs = statement.executeQuery(SELECT);
            while(rs.next()){
                Item item = new Item(rs.getInt("id"), rs.getString("nama"), rs.getInt("stok"), rs.getInt("harga"));
                items.add(item);
            }
            rs.close();
            statement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
}
