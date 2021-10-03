package com.example.csse_mobileapp.Prevalent;

public class Site {

    private String po,name,departmentName,vendorT,dDate,dLocation,oDate,phone,sEmail;

    public Site() {
    }

    public Site(String po, String name, String departmentName, String vendorT, String dDate, String dLocation, String oDate, String phone, String sEmail) {
        this.po = po;
        this.name = name;
        this.departmentName = departmentName;
        this.vendorT = vendorT;
        this.dDate = dDate;
        this.dLocation = dLocation;
        this.oDate = oDate;
        this.phone = phone;
        this.sEmail = sEmail;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getVendorT() {
        return vendorT;
    }

    public void setVendorT(String vendorT) {
        this.vendorT = vendorT;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate;
    }

    public String getdLocation() {
        return dLocation;
    }

    public void setdLocation(String dLocation) {
        this.dLocation = dLocation;
    }

    public String getoDate() {
        return oDate;
    }

    public void setoDate(String oDate) {
        this.oDate = oDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }
}
