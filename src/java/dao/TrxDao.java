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
import model.Trx;

/**
 *
 * @author DB1407
 */
public class TrxDao {
    private final String SELECT = "select * from item";
    private Koneksi kon;
    
    public TrxDao(){
        this.kon = new Koneksi();
    }
    
    public List<Trx> getAll(){
        List<Trx> trxes = new ArrayList<>();
        ResultSet rs;
        Statement statement;
        try {
            statement = kon.getConn().createStatement();
            rs = statement.executeQuery(SELECT);
            while(rs.next()){
                Trx trx = new Trx(rs.getInt("id"), rs.getInt("kasir"), rs.getDate("tanggal"));
                trxes.add(trx);
            }
            rs.close();
            statement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trxes;
    }
}
