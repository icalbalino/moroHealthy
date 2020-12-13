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
import model.DetailTrx;
import model.Trx;

/**
 *
 * @author DB1407
 */
public class DetailTrxDao {
    private final String SELECT = "select * from detail_trx";
    private Koneksi kon;
    
    public DetailTrxDao(){
        this.kon = new Koneksi();
    }
    
    public List<DetailTrx> getAll(){
        List<DetailTrx> details = new ArrayList<>();
        ResultSet rs;
        Statement statement;
        try {
            statement = kon.getConn().createStatement();
            rs = statement.executeQuery(SELECT);
            while(rs.next()){
                DetailTrx detail = new DetailTrx(rs.getInt("id"), rs.getInt("trx_id"), rs.getInt("item_id"), rs.getInt("subtotal"));
                details.add(detail);
            }
            rs.close();
            statement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return details;
    }
}
