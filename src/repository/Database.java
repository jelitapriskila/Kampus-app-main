package repository;

import com.google.gson.Gson;
import model.DBTables;
import model.Prodi;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private Gson gson;
    private String namaFileDataMahasiswa = "db_kampusapp";
    private String namaFileDataProdi = "db_prodi";
    protected DBTables tables = new DBTables();
    public MahasiswaTbl mahasiswaTbl = new MahasiswaTbl(this);
    public ArrayList<Prodi> prodiList = new ArrayList<>();

    public Database() {
        gson = new Gson();
        readDBFile();
        prodiList.add(new Prodi(1,"Informatika"));
        prodiList.add(new Prodi(2, "Teknik Elektro"));
    }

    protected void commitProdi() {
        try (PrintWriter writer = new PrintWriter(new File(namaFileDataProdi))) {
            for (Prodi prodi : prodiList) {
                writer.println(gson.toJson(prodi));
            }
        } catch (IOException e) {

        }
    }

    private void readProdiFile() {
        try (Scanner scanner = new Scanner(new File(namaFileDataProdi))) {
            prodiList.clear(); // Menghapus data yang ada di prodiList sebelum membaca yang baru
            while (scanner.hasNextLine()) {
                String jsonString = scanner.nextLine();
                Prodi prodi = gson.fromJson(jsonString, Prodi.class);
                prodiList.add(prodi);
            }
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca data Prodi: " + e.getMessage());
        }
    }

    public void createProdi(int id, String namaProdi) {
        Prodi prodi = new Prodi(id, namaProdi);
        prodiList.add(prodi);
        commitProdi(); // Simpan perubahan ke dalam file
        System.out.println("Prodi dengan ID " + id + " berhasil ditambahkan.");
    }

    public void showProdiList() {
        System.out.println("Daftar Prodi yang tersedia:");
        for (Prodi prodi : prodiList) {
            System.out.println("ID: " + prodi.getId());
        }
    }
    protected void commit() {
        try (PrintWriter writer = new PrintWriter(new File(namaFileDataMahasiswa))) {
            writer.println(gson.toJson(tables));
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan ke file: " + e.getMessage());
        }
    }

    private void readDBFile() {
        try (Scanner scanner = new Scanner(new File(namaFileDataMahasiswa))) {
            while (scanner.hasNextLine()) {
                String jsonString = scanner.nextLine();
                tables = gson.fromJson(jsonString, DBTables.class);
            }
        } catch (IOException e) {
            
        }
    }

    public Prodi getProdiById(int id) {
        for (Prodi prodi : prodiList) {
            if (prodi.getId() == id) {
                return prodi;
            }
        }
        return null;
    }
}
