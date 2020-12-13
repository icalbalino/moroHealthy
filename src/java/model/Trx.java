/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author DB1407
 */
public class Trx {
    private int id;
    private int kasirId;
    private Date tanggal;

    public Trx(int id, int kasirId, Date tanggal) {
        this.id = id;
        this.kasirId = kasirId;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKasirId() {
        return kasirId;
    }

    public void setKasirId(int kasirId) {
        this.kasirId = kasirId;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
}
