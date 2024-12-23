import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class KasirController implements Initializable {

    @FXML
    private TextField txtNama;

    @FXML
    private TextField txtJumlahTiket;

    @FXML
    private Button btnBeli;

    @FXML
    private Label lblHarga;

    private TiketModel tiketModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tiketModel = new TiketModel();
        btnBeli.setOnAction(event -> beliTiket());
    }

    private void beliTiket() {
        try {
            // Ambil nama dari input
            String nama = txtNama.getText();
            if (nama.isEmpty()) {
                lblHarga.setText("Nama tidak boleh kosong!");
                return;
            }

            // Ambil jumlah tiket dari input dan validasi
            int jumlahTiket = Integer.parseInt(txtJumlahTiket.getText());
            if (jumlahTiket <= 0) {
                lblHarga.setText("Jumlah tiket harus lebih dari 0.");
                return;
            }

            // Hitung harga tiket
            int harga = tiketModel.hitungHarga(jumlahTiket);
            lblHarga.setText("Harga: Rp " + harga);

            // Simpan data ke database
            Database.simpanData(nama, jumlahTiket, harga);
        } catch (NumberFormatException e) {
            lblHarga.setText("Jumlah tiket harus berupa angka valid.");
        } catch (Exception e) {
            lblHarga.setText("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
