import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class DufanTicketPaymentContent {
    private final TextField nameField = new TextField();
    private final TextField phoneField = new TextField(); // Tambahkan kolom untuk nomor telepon
    private final ComboBox<String> ticketTypeCombo = new ComboBox<>();
    private final Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
    private final Label totalPriceValue = new Label("0");
    private Button viewReceiptButton; // Declare the view receipt button

    private String receiptContent; // Store the receipt content

    public VBox getContent() {
        VBox content = new VBox();
        content.setSpacing(15);

        // Kolom Nama
        Label nameLabel = new Label("Nama :");
        nameField.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Kolom Nomor Telepon
        Label phoneLabel = new Label("No Telepon :");
        phoneField.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Kolom Tipe Tiket
        Label ticketTypeLabel = new Label("Tipe Tiket :");
        ticketTypeCombo.getItems().addAll("Regular", "Fast Track", "Premium", "Anual 6 Bulan", "Anual 12 Bulan");
        ticketTypeCombo.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Kolom Jumlah Tiket
        Label quantityLabel = new Label("Jumlah Tiket :");
        quantitySpinner.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Total Harga
        Label totalPriceLabel = new Label("Total Harga :");
        totalPriceValue.setTextFill(Color.DARKGREEN);

        // Tombol Bayar
        Button payButton = new Button("Bayar");
        payButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        final int regularPrice = 100000;
        final int vipPrice = 200000;
        final int familyPackagePrice = 500000;
        final int anual6Price = 250000;
        final int anual12Price = 365000;

        ticketTypeCombo.setOnAction(e -> updateTotalPrice(regularPrice, vipPrice, familyPackagePrice, anual6Price, anual12Price));
        quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> updateTotalPrice(regularPrice, vipPrice, familyPackagePrice, anual6Price, anual12Price));

        payButton.setOnAction(e -> handlePayment());

        content.getChildren().addAll(
            nameLabel, nameField, 
            phoneLabel, phoneField, // Tambahkan kolom nomor telepon ke antarmuka
            ticketTypeLabel, ticketTypeCombo, 
            quantityLabel, quantitySpinner, 
            totalPriceLabel, totalPriceValue, 
            payButton
        );

        // Tombol "Lihat Struk"
        viewReceiptButton = new Button("Lihat Struk");
        viewReceiptButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        viewReceiptButton.setOnAction(e -> showReceipt());
        viewReceiptButton.setVisible(false); // Sembunyikan awalnya
        content.getChildren().add(viewReceiptButton);

        return content;
    }

    private void updateTotalPrice(int regularPrice, int vipPrice, int familyPackagePrice, int anual6Price, int anual12Price) {
        String ticketType = ticketTypeCombo.getValue();
        int quantity = quantitySpinner.getValue();
        int price = 0;

        if ("Regular".equals(ticketType)) {
            price = regularPrice * quantity;
        } else if ("Fast Track".equals(ticketType)) {
            price = vipPrice * quantity;
        } else if ("Premium".equals(ticketType)) {
            price = familyPackagePrice * quantity;
        } else if ("Anual 6 Bulan".equals(ticketType)) {
            price = anual6Price * quantity;
        } else if ("Anual 12 Bulan".equals(ticketType)) {
            price = anual12Price * quantity;
        }

        totalPriceValue.setText(String.format("Rp %,d", price));
    }

    private void handlePayment() {
        String name = nameField.getText();
        String phone = phoneField.getText(); // Ambil nomor telepon
        String ticketType = ticketTypeCombo.getValue();
        int quantity = quantitySpinner.getValue();
        String totalPrice = totalPriceValue.getText();
    
        if (name.isEmpty() || phone.isEmpty() || ticketType == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Tolong Isi Semua Kolom.");
            return;
        }
    
        // Simpan struk pembelian ke variabel receiptContent
        receiptContent = String.format(
            "----------------------------------------\n" +
            "         DUFAN - STRUK PEMBELIAN         \n" +
            "----------------------------------------\n" +
            "Nama Pembeli    :   %s\n" +
            "No Telepon          :   %s\n" +
            "Tanggal                 :   %s\n" +
            "Item                         :   Tiket Masuk Dufan\n" +
            "Jumlah Tiket       :   %d\n" +
            "Harga Per Tiket  :   Rp %,d\n" +
            "----------------------------------------\n" +
            "Total Pembayaran   :   %s\n" +
            "----------------------------------------\n" +
            "   Terima Kasih!\n" +
            "   Selamat Menikmati Wahana!\n",
            name,
            phone, // Tambahkan nomor telepon ke struk
            java.time.LocalDate.now(),
            quantity,
            calculatePricePerTicket(),
            totalPrice
        );
    
        // Tampilkan alert untuk pembayaran berhasil
        showAlert(Alert.AlertType.INFORMATION, "Pembayaran Berhasil", receiptContent);
    
        // Tampilkan tombol "Lihat Struk"
        viewReceiptButton.setVisible(true);
    
        // Reset form untuk input berikutnya
        resetFields();
    }
    
    private void showReceipt() {
        if (receiptContent != null) {
            Alert receiptAlert = new Alert(Alert.AlertType.INFORMATION);
            receiptAlert.setTitle("Struk Pembelian");
            receiptAlert.setHeaderText("Detail Pembelian Tiket Dufan");
            receiptAlert.setContentText(receiptContent);
            receiptAlert.showAndWait();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Belum ada struk untuk ditampilkan.");
        }
    }
                
    private int calculatePricePerTicket() {
        String ticketType = ticketTypeCombo.getValue();
        if ("Regular".equals(ticketType)) {
            return 100000;
        } else if ("Fast Track".equals(ticketType)) {
            return 200000;
        } else if ("Premium".equals(ticketType)) {
            return 500000;
        } else if ("Anual 6 Bulan".equals(ticketType)) {
            return 250000;
        } else if ("Anual 12 Bulan".equals(ticketType)) {
            return 365000;
        }
        return 0; // Default if no ticket type is selected
    }
    
    private void resetFields() {
        nameField.clear();
        phoneField.clear(); // Reset nomor telepon
        ticketTypeCombo.setValue(null);
        quantitySpinner.getValueFactory().setValue(1);
        totalPriceValue.setText("0");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}