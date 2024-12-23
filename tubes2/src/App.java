import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dufan");

        // Create main menu layout
        VBox menuLayout = new VBox();
        menuLayout.setPadding(new Insets(20));
        menuLayout.setSpacing(20);
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEEB, #ffffff);");

        // Header text
        Text header = new Text("Selamat Datang, Di Pembelian Tiket Dufan");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.setFill(Color.DARKBLUE);
        menuLayout.getChildren().add(header);

        // Buttons for options
        Button viewTicketsButton = new Button("Tiket Tersedia");
        viewTicketsButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        Button purchaseTicketsButton = new Button("Beli Tiket");
        purchaseTicketsButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        menuLayout.getChildren().addAll(viewTicketsButton, purchaseTicketsButton);

        // Main scene
        Scene menuScene = new Scene(menuLayout, 550, 300);

        // Set up view tickets scene
        VBox viewTicketsLayout = new VBox();
        viewTicketsLayout.setPadding(new Insets(20));
        viewTicketsLayout.setSpacing(15);
        viewTicketsLayout.setStyle("-fx-background-color: #F0F8FF;");

        Text viewTicketsHeader = new Text("Tipe Tiket Yang Tersedia");
        viewTicketsHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        viewTicketsHeader.setFill(Color.DARKBLUE);

        Label ticketTypesLabel = new Label("1. Regular : Rp 100,000\n2. Fast Track : Rp 200,000\n3. Premium : Rp 500,000");
        ticketTypesLabel.setFont(Font.font("Arial", 14));

        Button backToMenuButton1 = new Button("Kembali ke Menu");
        backToMenuButton1.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        viewTicketsLayout.getChildren().addAll(viewTicketsHeader, ticketTypesLabel, backToMenuButton1);

        Scene viewTicketsScene = new Scene(viewTicketsLayout, 500, 300);

        // Set up purchase tickets scene
        VBox purchaseTicketsLayout = new VBox();
        purchaseTicketsLayout.setPadding(new Insets(20));
        purchaseTicketsLayout.setSpacing(15);
        purchaseTicketsLayout.setStyle("-fx-background-color: #F0F8FF;");

        Text purchaseHeader = new Text("Beli Tiket");
        purchaseHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        purchaseHeader.setFill(Color.DARKBLUE);

        Button backToMenuButton2 = new Button("Kembali ke Menu");
        backToMenuButton2.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        // Integrate existing ticket purchase layout
        DufanTicketPaymentContent ticketPaymentContent = new DufanTicketPaymentContent();
        purchaseTicketsLayout.getChildren().addAll(purchaseHeader, ticketPaymentContent.getContent(), backToMenuButton2);

        Scene purchaseTicketsScene = new Scene(purchaseTicketsLayout, 500, 500);

        // Button actions
        viewTicketsButton.setOnAction(e -> primaryStage.setScene(viewTicketsScene));
        purchaseTicketsButton.setOnAction(e -> primaryStage.setScene(purchaseTicketsScene));
        backToMenuButton1.setOnAction(e -> primaryStage.setScene(menuScene));
        backToMenuButton2.setOnAction(e -> primaryStage.setScene(menuScene));

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// Separate class for ticket payment content
class DufanTicketPaymentContent {
    private final TextField nameField = new TextField();
    private final ComboBox<String> ticketTypeCombo = new ComboBox<>();
    private final Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
    private final Label totalPriceValue = new Label("0");

    public VBox getContent() {
        VBox content = new VBox();
        content.setSpacing(15);

        // Nama
        Label nameLabel = new Label("Nama :");
        nameField.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Tipe Tiket
        Label ticketTypeLabel = new Label("Tipe Tiket :");
        ticketTypeCombo.getItems().addAll("Regular", "Fast Track", "Premium");
        ticketTypeCombo.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Jumlah Tiket
        Label quantityLabel = new Label("Jumlah Tiket :");
        quantitySpinner.setStyle("-fx-background-color: #E8F0FE; -fx-border-color: #4682B4;");

        // Total Harga label
        Label totalPriceLabel = new Label("Total Harga :");
        totalPriceValue.setTextFill(Color.DARKGREEN);

        // button Bayar
        Button payButton = new Button("Bayar");
        payButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        // Set Harga Tiket
        final int regularPrice = 100000;
        final int vipPrice = 200000;
        final int familyPackagePrice = 500000;

        ticketTypeCombo.setOnAction(e -> updateTotalPrice(regularPrice, vipPrice, familyPackagePrice));
        quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> updateTotalPrice(regularPrice, vipPrice, familyPackagePrice));

        // Handle payment button
        payButton.setOnAction(e -> handlePayment());

        content.getChildren().addAll(nameLabel, nameField, ticketTypeLabel, ticketTypeCombo, quantityLabel, quantitySpinner, totalPriceLabel, totalPriceValue, payButton);
        return content;
    }

    private void updateTotalPrice(int regularPrice, int vipPrice, int familyPackagePrice) {
        String ticketType = ticketTypeCombo.getValue();
        int quantity = quantitySpinner.getValue();
        int price = 0;

        if ("Regular".equals(ticketType)) {
            price = regularPrice * quantity;
        } else if ("Fast Track".equals(ticketType)) {
            price = vipPrice * quantity;
        } else if ("Premium".equals(ticketType)) {
            price = familyPackagePrice * quantity;
        }

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
