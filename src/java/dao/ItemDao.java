/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Koneksi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
public class ItemDao {
    private final String SELECT = "select * from item";
    private final String SELECT_BY_ID = "select * from item where id=?";
    private final String INSERT = "INSERT into item(nama, stok, harga) values(?, ?, ?)";
    private final String UPDATE = "update item set nama=?, stok=?, harga=? where id=? ";
    
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
    
    public Item findById(int id){
        System.out.println("item find by id "+id);
        ResultSet rs;
        try {
            PreparedStatement preparedStatement = kon.getConn().prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                Item item = new Item(rs.getInt("id"), rs.getString("nama"), rs.getInt("stok"), rs.getInt("harga"));
                System.out.println(item.toString());
                return item;
            }
            rs.close();
            preparedStatement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
        
    
    public boolean insert(Item item) {
        if (kon.getConn() == null) {
            return false;
        } else {
            try {
                PreparedStatement statement = kon.getConn().prepareStatement(INSERT);
                statement.setString(1, item.getNama());
                statement.setInt(2, item.getStok());
                statement.setInt(3, item.getHarga());
                int res = statement.executeUpdate();
                statement.close();
                kon.getConn().close();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
    
    public boolean update(Item item) {
        if (kon.getConn() == null) {
            return false;
        } else {
            try {
                PreparedStatement statement = kon.getConn().prepareStatement(UPDATE);
                statement.setString(1, item.getNama());
                statement.setInt(2, item.getStok());
                statement.setInt(3, item.getHarga());
                statement.setInt(4, item.getId());
                int res = statement.executeUpdate();
                statement.close();
                kon.getConn().close();
                if (res > 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                return false;
            }
        }
    }
}
