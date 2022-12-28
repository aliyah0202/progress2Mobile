package com.example.project;

public class ModelClass {
    String namaMontir,numMontir, namaPelanggan, numPelanggan;
    int img;

    public String getNamaMontir() {
        return namaMontir;
    }
    public String getNamaPelanggan() { return namaPelanggan;}

    public void setNamaMontir(String namaMontir) {
        this.namaMontir = namaMontir;
    }
    public void setNamaPelanggan(String namaPelanggan) {this.namaPelanggan = namaPelanggan;}

    public String getNumMontir() {
        return numMontir;
    }
    public String getNumPelanggan() {return numPelanggan;}

    public void setNumMontir(String numMontir) {
        this.numMontir = numMontir;
    }
    public void setNumPelanggan(String numPelanggan) {this.numPelanggan = numPelanggan;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
