/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Koneksi;
import dao.ItemDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import model.User;

/**
 *
 * @author DB1407
 */
public class UserDao {
    private final String SELECT = "select * from user";
    private final String SELECT_BY_USERNAME_AND_PASSWORD = "select * from user where username=? and password=?";
    private Koneksi kon;
    
    public UserDao(){
        this.kon = new Koneksi();
    }
    
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        ResultSet rs;
        PreparedStatement statement;
        try {
            statement = kon.getConn().prepareStatement(SELECT);
            rs = statement.executeQuery(SELECT);
            while(rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("nama"), rs.getString("role"), rs.getString("username"), rs.getString("password")));
            }
            rs.close();
            statement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    public User getByUsernameAndPassword(String username, String password){
        ResultSet rs;
        PreparedStatement statement;
        try {
            statement = kon.getConn().prepareStatement(SELECT_BY_USERNAME_AND_PASSWORD);
            rs = statement.executeQuery(SELECT_BY_USERNAME_AND_PASSWORD);
            while(rs.next()){
                return new User(rs.getInt("id"), rs.getString("nama"), rs.getString("role"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            statement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
