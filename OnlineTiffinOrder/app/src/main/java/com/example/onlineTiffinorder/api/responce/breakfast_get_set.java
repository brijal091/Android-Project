package com.example.onlineTiffinorder.api.responce;

public class breakfast_get_set {
    int id;
    String custid,thepla,batakapuva,achar,tea,name,mobno,qty,corona,stdate,enddate, address,pincode,spreq;

    public breakfast_get_set(int id, String custid, String thepla, String batakapuva, String achar, String tea, String name, String mobno, String qty, String corona, String stdate, String enddate, String address, String pincode, String spreq) {
        this.id = id;
        this.custid = custid;
        this.thepla = thepla;
        this.batakapuva = batakapuva;
        this.achar = achar;
        this.tea = tea;
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

    public String getThepla() {
        return thepla;
    }

    public void setThepla(String thepla) {
        this.thepla = thepla;
    }

    public String getBatakapuva() {
        return batakapuva;
    }

    public void setBatakapuva(String batakapuva) {
        this.batakapuva = batakapuva;
    }

    public String getAchar() {
        return achar;
    }

    public void setAchar(String achar) {
        this.achar = achar;
    }

    public String getTea() {
        return tea;
    }

    public void setTea(String tea) {
        this.tea = tea;
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
