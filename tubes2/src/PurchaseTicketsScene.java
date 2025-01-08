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

public class PurchaseTicketsScene {
    private final Scene scene;

    public PurchaseTicketsScene(Stage primaryStage, Scene menuScene) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(15);
        layout.setStyle("-fx-background-color: #F0F8FF;");

        Text header = new Text("Beli Tiket");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        header.setFill(Color.DARKBLUE);

        DufanTicketPaymentContent ticketPaymentContent = new DufanTicketPaymentContent();

        Button backToMenuButton = new Button("Kembali ke Menu");
        backToMenuButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        backToMenuButton.setOnAction(e -> primaryStage.setScene(menuScene));

        layout.getChildren().addAll(header, ticketPaymentContent.getContent(), backToMenuButton);
        layout.setAlignment(Pos.CENTER);

        this.scene = new Scene(layout, 600, 600);
    }

    public Scene getScene() {
        return scene;
    }
}