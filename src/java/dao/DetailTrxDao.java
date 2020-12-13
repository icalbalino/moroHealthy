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
import model.Cart;
import model.DetailTrx;
import model.Trx;

/**
 *
 * @author DB1407
 */
public class DetailTrxDao {
    private final String SELECT = "select * from detail_trx";
    private final String INSERT = "insert into detail_trx(trx_id, item_id, subtotal, qty) values(?,?,?,?)";
    private final String COUNT_DETAIL_TRX = "select count(*) from trx t join detail_trx dt where t.id=dt.trx_id and DATE_FORMAT(t.tanggal, '%Y-%m-%d') = curdate()";
    
    
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
                DetailTrx detail = new DetailTrx(rs.getInt("id"), rs.getInt("trx_id"), rs.getInt("item_id"), rs.getInt("subtotal"), rs.getInt("qty"));
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
    
    public boolean insert(DetailTrx detail) {
        System.out.println("insert detail");
        if (kon.getConn() == null) {
            return false;
        } else {
            try {
                PreparedStatement statement = kon.getConn().prepareStatement(INSERT);
                statement.setInt(1, detail.getTrxId());
                statement.setInt(2, detail.getItemId());
                statement.setInt(3, detail.getSubtotal());
                statement.setInt(4, detail.getQty());
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
    
    public int countDetailTrx(){
        ResultSet rs;
        Statement statement;
        try {
            statement = kon.getConn().createStatement();
            rs = statement.executeQuery(COUNT_DETAIL_TRX);
            while(rs.next()){
                return rs.getInt(1);
            }
            rs.close();
            statement.close();
            kon.getConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
