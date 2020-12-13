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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import model.Trx;
import model.Cart;
import model.DetailTrx;
public class TrxDao {
    private final String SELECT = "select * from trx order by tanggal desc";
    private final String INSERT = "insert into trx (kasir_id, tanggal) values(?, ?)";
    private static final String COUNT_TRX = "SELECT count(*) FROM trx where DATE_FORMAT(tanggal, '%Y-%m-%d') = curdate()";
    private static final String COUNT_SUBTOTAL = "select sum(s.subtotal) from\n" +
"(SELECT sum(dt.subtotal) as subtotal FROM trx t JOIN detail_trx dt where t.id=dt.trx_id and DATE_FORMAT(t.tanggal, '%Y-%m-%d') = curdate() group by t.id) as s";
    

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
                Trx trx = new Trx(rs.getInt("id"), rs.getInt("kasir_id"), rs.getDate("tanggal"));
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
    
    public boolean insert(Trx trx, List<Cart> carts) {
        if (kon.getConn() == null) {
            return false;
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                PreparedStatement statement = kon.getConn().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, trx.getKasirId());
                statement.setString(2, format.format(new Date()));
                int res = statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                
                if (res > 0) {
                    int trxId = 0;
                    while(rs.next()){
                        trxId = rs.getInt(1);
                    }
                    for(Cart cart:carts){
                        System.out.println(cart.toString());
                        DetailTrx detailTrx = new DetailTrx(0, trxId, cart.getItem().getId(), cart.getQty()*cart.getItem().getHarga(), cart.getQty());
                        new DetailTrxDao().insert(detailTrx);
                    }
                    rs.close();
                    statement.close();
                    kon.getConn().close();

                    return true;
                } else {
                    System.out.println("fail");
                    rs.close();
                    statement.close();
                    kon.getConn().close();
                    return false;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }
    
    public int countTrx(){
        ResultSet rs;
        Statement statement;
        try {
            statement = kon.getConn().createStatement();
            rs = statement.executeQuery(COUNT_TRX);
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
    public int countSubtotal(){
        ResultSet rs;
        Statement statement;
        try {
            statement = kon.getConn().createStatement();
            rs = statement.executeQuery(COUNT_SUBTOTAL);
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
