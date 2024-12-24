import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dufan");

        VBox menuLayout = new VBox();
        menuLayout.setPadding(new Insets(20));
        menuLayout.setSpacing(20);
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEEB, #ffffff);");

        Text header = new Text("Selamat Datang, Di Pembelian Tiket Dufan");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.setFill(Color.DARKBLUE);
        menuLayout.getChildren().add(header);

        Button viewTicketsButton = new Button("Tiket Tersedia");
        viewTicketsButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        Button purchaseTicketsButton = new Button("Beli Tiket");
        purchaseTicketsButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        menuLayout.getChildren().addAll(viewTicketsButton, purchaseTicketsButton);
        menuLayout.setAlignment(Pos.CENTER);

        Scene menuScene = new Scene(menuLayout, 550, 300);

        VBox viewTicketsLayout = new VBox();
        viewTicketsLayout.setPadding(new Insets(20));
        viewTicketsLayout.setSpacing(15);
        viewTicketsLayout.setStyle("-fx-background-color: #F0F8FF;");

        Text viewTicketsHeader = new Text("Tipe Tiket Yang Tersedia");
        viewTicketsHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        viewTicketsHeader.setFill(Color.DARKBLUE);

        HBox topRow = new HBox();
        topRow.setSpacing(20);
        topRow.setPadding(new Insets(10, 0, 10, 0));
        topRow.setAlignment(Pos.CENTER);

        HBox bottomRow = new HBox();
        bottomRow.setSpacing(20);
        bottomRow.setPadding(new Insets(10, 0, 10, 0));
        bottomRow.setAlignment(Pos.CENTER);

        // Membuat daftar tiket
        VBox regularBox = createTicketBox("Regular", "Rp 100,000", "Akses reguler untuk menikmati wahana.");
        VBox fastTrackBox = createTicketBox("Fast Track", "Rp 200,000", "Antrian lebih cepat.");
        VBox premiumBox = createTicketBox("Premium", "Rp 500,000", "Akses eksklusif tanpa antri.");
        VBox anual6Box = createTicketBox("Anual 6 Bulan", "Rp 250.000", "Nikmati ke Dufan setiap hari selama 6 bulan.");
        VBox anual12Box = createTicketBox("Anual 12 Bulan", "Rp 365.000", "Nikmati ke Dufan setiap hari selama 12 bulan.");

        topRow.getChildren().addAll(regularBox, fastTrackBox, premiumBox);
        bottomRow.getChildren().addAll(anual6Box, anual12Box);

        VBox ticketLayout = new VBox();
        ticketLayout.setSpacing(20);
        ticketLayout.setPadding(new Insets(20));
        ticketLayout.setAlignment(Pos.CENTER);
        ticketLayout.getChildren().addAll(topRow, bottomRow);


        Button backToMenuButton1 = new Button("Kembali ke Menu");
        backToMenuButton1.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        viewTicketsLayout.getChildren().addAll(viewTicketsHeader, ticketLayout, backToMenuButton1);
        viewTicketsLayout.setAlignment(Pos.CENTER);

        Scene viewTicketsScene = new Scene(viewTicketsLayout, 550, 400);

        VBox purchaseTicketsLayout = new VBox();
        purchaseTicketsLayout.setPadding(new Insets(20));
        purchaseTicketsLayout.setSpacing(15);
        purchaseTicketsLayout.setStyle("-fx-background-color: #F0F8FF;");

        Text purchaseHeader = new Text("Beli Tiket");
        purchaseHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        purchaseHeader.setFill(Color.DARKBLUE);

        Button backToMenuButton2 = new Button("Kembali ke Menu");
        backToMenuButton2.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        DufanTicketPaymentContent ticketPaymentContent = new DufanTicketPaymentContent();
        purchaseTicketsLayout.getChildren().addAll(purchaseHeader, ticketPaymentContent.getContent(), backToMenuButton2);

        Scene purchaseTicketsScene = new Scene(purchaseTicketsLayout, 500, 500);

        viewTicketsButton.setOnAction(e -> primaryStage.setScene(viewTicketsScene));
        purchaseTicketsButton.setOnAction(e -> primaryStage.setScene(purchaseTicketsScene));
        backToMenuButton1.setOnAction(e -> primaryStage.setScene(menuScene));
        backToMenuButton2.setOnAction(e -> primaryStage.setScene(menuScene));

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private VBox createTicketBox(String title, String price, String description) {
        VBox ticketBox = new VBox();
        ticketBox.setPadding(new Insets(10));
        ticketBox.setSpacing(10);
        ticketBox.setAlignment(Pos.CENTER);
        ticketBox.setStyle("-fx-border-color: #4682B4; -fx-border-width: 2px; -fx-background-color: #E8F0FE; -fx-border-radius: 5px; -fx-background-radius: 5px;");
    
        Text titleText = new Text(title);
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleText.setFill(Color.DARKBLUE);
    
        Text priceText = new Text(price);
        priceText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        priceText.setFill(Color.DARKGREEN);
    
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setFont(Font.font("Arial", 12));
        descriptionLabel.setStyle("-fx-text-fill: #333333;");
    
        ticketBox.getChildren().addAll(titleText, priceText, descriptionLabel);
        return ticketBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// payment tiket dufan
class DufanTicketPaymentContent {
    private final TextField nameField = new TextField();
    private final ComboBox<String> ticketTypeCombo = new ComboBox<>();
    private final Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
    private final Label totalPriceValue = new Label("0");

    public VBox getContent() {
        VBox content = new VBox();
        content.setSpacing(15);

        Label nameLabel = new Label("Nama :");
        nameField.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        Label ticketTypeLabel = new Label("Tipe Tiket :");
        ticketTypeCombo.getItems().addAll("Regular", "Fast Track", "Premium", "Anual 6 Bulan", "Anual 12 Bulan");
        ticketTypeCombo.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        Label quantityLabel = new Label("Jumlah Tiket :");
        quantitySpinner.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        Label totalPriceLabel = new Label("Total Harga :");
        totalPriceValue.setTextFill(Color.DARKGREEN);

        Button payButton = new Button("Bayar");
        payButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        // Set Harga Tiket
        final int regularPrice = 100000;
        final int vipPrice = 200000;
        final int familyPackagePrice = 500000;
        final int anual6Price = 250000;
        final int anual12Price = 365000;

        ticketTypeCombo.setOnAction(e -> updateTotalPrice(regularPrice, vipPrice, familyPackagePrice, anual6Price, anual12Price));
        quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> updateTotalPrice(regularPrice, vipPrice, familyPackagePrice, anual6Price, anual12Price));

        payButton.setOnAction(e -> handlePayment());

        content.getChildren().addAll(nameLabel, nameField, ticketTypeLabel, ticketTypeCombo, quantityLabel, quantitySpinner, totalPriceLabel, totalPriceValue, payButton);
        return content;
    }

    private void updateTotalPrice(int regularPrice, int vipPrice, int familyPackagePrice, int anual6Price, int anual12Price) {
        String ticketType = ticketTypeCombo.getValue();
        int quantity = quantitySpinner.getValue();
        int price = 0;

        // Logika penghitungan harga berdasarkan tipe tiket
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

        // Format dan tampilkan total harga
        totalPriceValue.setText(String.format("Rp %,d", price));
    }

    private void handlePayment() {
        String name = nameField.getText();
        String ticketType = ticketTypeCombo.getValue();
        int quantity = quantitySpinner.getValue();
        String totalPrice = totalPriceValue.getText();

        if (name.isEmpty() || ticketType == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Tolong Isi Semua Kolom.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Pembayaran Berhasil", String.format(
                "Nama : %s\nTipe Tiket : %s\nJumlah Tiket : %d\nTotal Harga : %s", name, ticketType, quantity, totalPrice));

        resetFields();
    }

    private void resetFields() {
        nameField.clear();
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
