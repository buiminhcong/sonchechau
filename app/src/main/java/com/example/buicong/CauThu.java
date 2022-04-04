package com.example.buicong;

public class CauThu {
    private int img;
    private String name, ngaySinh;
    private String viTri;

    public CauThu() {
    }

    public CauThu(int img, String name, String ngaySinh, String viTri) {
        this.img = img;
        this.name = name;
        this.ngaySinh = ngaySinh;
        this.viTri = viTri;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }
}
