package model;

public class Prodi {
    private int id;
    private String nama;


    public Prodi(int id, String nama) {
        this.nama = nama;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
