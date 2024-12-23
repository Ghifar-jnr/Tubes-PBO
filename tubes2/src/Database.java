import java.util.ArrayList;
import java.util.List;

public class Database {

    private static List<Pembelian> dataPembelian = new ArrayList<>();

    public static void simpanData(String nama, int jumlahTiket, int harga) {
        Pembelian pembelian = new Pembelian(nama, jumlahTiket, harga);
        dataPembelian.add(pembelian);
    }

    public static List<Pembelian> getDataPembelian() {
        return dataPembelian;
    }

    public static class Pembelian {
        private String nama;
        private int jumlahTiket;
        private int harga;

        public Pembelian(String nama, int jumlahTiket, int harga) {
            this.nama = nama;
            this.jumlahTiket = jumlahTiket;
            this.harga = harga;
        }

        // Getter dan Setter
    }
}
