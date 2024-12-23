import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

public class TiketController implements Initializable {

    @FXML
    private TableView<Pembelian> tblPembelian;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Pembelian> dataPembelian = Database.getDataPembelian();
        tblPembelian.getItems().addAll(dataPembelian);
    }
}
