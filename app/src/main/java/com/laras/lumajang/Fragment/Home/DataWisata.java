package com.laras.lumajang.Fragment.Home;

public class DataWisata {
    private  String nama ,kategori,alamat, Deskripsi , gambar ;
    private Double Lat, Long ;


    public DataWisata(String nama , String gambar ,String alamat){
        this.nama=nama;
        this.gambar=gambar;
        this.alamat=alamat;

    }
    public DataWisata(String nama, String gambar,String alamat , String Deskripsi){
        this.nama=nama;
        this.gambar=gambar;
        this.alamat=alamat;
        this.Deskripsi=Deskripsi;

    }
    public DataWisata(String nama, String gambar,String alamat , String Deskripsi,Double Lat,Double Long){
        this.nama=nama;
        this.gambar=gambar;
        this.alamat=alamat;
        this.Deskripsi=Deskripsi;
       this.Lat=Lat;
       this.Long=Long;

    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama=nama;
    }
    public String getGambar (){
        return gambar;
    }
    public void setGambar(String gambar){
        this.gambar=gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public Double getLat() {
        return Lat;
    }

    public void setLat(Double lat) {
        Lat = lat;
    }

    public Double getLong() {
        return Long;
    }

    public void setLong(Double aLong) {
        Long = aLong;
    }
}
