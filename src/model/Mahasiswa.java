package model;

public class Mahasiswa {
    private String nim;
    public String getNim() {
        return nim;
    }

    private String nama;
    public String getNama() {
        return nama;
    }

    private Prodi prodi;
    public Prodi getProdi() {
        return prodi;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setProdi(Prodi prodi) {this.prodi = prodi;}
    public Mahasiswa(String nim, String nama, Prodi prodi) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
    }
}
