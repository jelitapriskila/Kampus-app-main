package repository;

import model.Mahasiswa;
import model.Prodi;

public class MahasiswaTbl {
    private Database db;
    public MahasiswaTbl(Database db) {
        this.db = db;
    }

    public void create(Mahasiswa mahasiswa) {
        db.tables.dataMahasiswa.add(mahasiswa);
        db.commit();
    }

    public void create(Mahasiswa mahasiswa, int prodiId) {
        Prodi prodi = db.getProdiById(prodiId);
        if (prodi != null) {
            mahasiswa.setProdi(prodi); // Mengatur Prodi ke Mahasiswa
            db.tables.dataMahasiswa.add(mahasiswa);
            db.commit();
            db.commitProdi();
        } else {
            System.out.println("Prodi dengan ID " + prodiId + " tidak ditemukan.");
        }
    }


    public void update(String nim, Mahasiswa mahasiswa, int prodiId) {
        Prodi prodi = db.getProdiById(prodiId);
        if (prodi != null) {
            for(int i = 0; i < db.tables.dataMahasiswa.size(); i++) {
                if(db.tables.dataMahasiswa.get(i).getNim().equals(nim)) {
                    db.tables.dataMahasiswa.set(i, mahasiswa);
                    mahasiswa.setProdi(prodi);
                    db.commit();
                    db.commitProdi();
                    break;
                }
            }
        } else {
            System.out.println("Prodi dengan ID " + prodiId + " tidak ditemukan.");
        }
    }

    public Mahasiswa getMahasiswaByNim(String nim) {
        for(int i = 0; i < db.tables.dataMahasiswa.size(); i++) {
            if(db.tables.dataMahasiswa.get(i).getNim().equals(nim)) {
                return db.tables.dataMahasiswa.get(i);
            }
        }
        System.out.println("Data tidak ditemukan untuk NIM: " + nim);
        return null;
    }

    public void delete(String nim) {
        for(int i = 0; i < db.tables.dataMahasiswa.size(); i++) {
            if(db.tables.dataMahasiswa.get(i).getNim().equals(nim)) {
                db.tables.dataMahasiswa.remove(i);
                break;
            }
        }
        db.commit();
    }
}
