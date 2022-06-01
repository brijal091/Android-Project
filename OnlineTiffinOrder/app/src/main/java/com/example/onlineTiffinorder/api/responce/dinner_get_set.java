package com.example.onlineTiffinorder.api.responce;

public class dinner_get_set {
    int id;
    String custid,rotii,sabji,mongdal,buttermilk,salad,papad,name,mobno,qty,corona,  stdate,enddate,address,pincode,spreq;

    public dinner_get_set(int id, String custid, String rotii, String sabji, String mongdal, String buttermilk, String salad, String papad, String name, String mobno, String qty, String corona, String stdate, String enddate, String address, String pincode, String spreq) {
        this.id = id;
        this.custid = custid;
        this.rotii = rotii;
        this.sabji = sabji;
        this.mongdal = mongdal;
        this.buttermilk = buttermilk;
        this.salad = salad;
        this.papad = papad;
        this.name = name;
        this.mobno = mobno;
        this.qty = qty;
        this.corona = corona;
        this.stdate = stdate;
        this.enddate = enddate;
        this.address = address;
        this.pincode = pincode;
        this.spreq = spreq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getRotii() {
        return rotii;
    }

    public void setRotii(String rotii) {
        this.rotii = rotii;
    }

    public String getSabji() {
        return sabji;
    }

    public void setSabji(String sabji) {
        this.sabji = sabji;
    }

    public String getMongdal() {
        return mongdal;
    }

    public void setMongdal(String mongdal) {
        this.mongdal = mongdal;
    }

    public String getButtermilk() {
        return buttermilk;
    }

    public void setButtermilk(String buttermilk) {
        this.buttermilk = buttermilk;
    }

    public String getSalad() {
        return salad;
    }

    public void setSalad(String salad) {
        this.salad = salad;
    }

    public String getPapad() {
        return papad;
    }

    public void setPapad(String papad) {
        this.papad = papad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getCorona() {
        return corona;
    }

    public void setCorona(String corona) {
        this.corona = corona;
    }

    public String getStdate() {
        return stdate;
    }

    public void setStdate(String stdate) {
        this.stdate = stdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getSpreq() {
        return spreq;
    }

    public void setSpreq(String spreq) {
        this.spreq = spreq;
    }
}
