import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DufanApp {

    public void showMenuScreen(Stage primaryStage) {
        VBox menuLayout = new VBox();
        menuLayout.setPadding(new Insets(20));
        menuLayout.setSpacing(20);
        menuLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #87CEEB, #ffffff);");

        Text header = new Text("Selamat Datang");
        Text header2 = new Text("Di");
        Text header3 = new Text("Pembelian Tiket Dufan");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.setFill(Color.DARKBLUE);
        header2.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header2.setFill(Color.DARKBLUE);
        header3.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header3.setFill(Color.DARKBLUE);
        menuLayout.getChildren().add(header);
        menuLayout.getChildren().add(header2);
        menuLayout.getChildren().add(header3);

        Button viewTicketsButton = new Button("Tiket Tersedia");
        viewTicketsButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        Button purchaseTicketsButton = new Button("Beli Tiket");
        purchaseTicketsButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        menuLayout.getChildren().addAll(viewTicketsButton, purchaseTicketsButton);
        menuLayout.setAlignment(Pos.CENTER);

        Scene menuScene = new Scene(menuLayout, 550, 300);

        ViewTicketsScene viewTicketsScene = new ViewTicketsScene(primaryStage, menuScene);
        PurchaseTicketsScene purchaseTicketsScene = new PurchaseTicketsScene(primaryStage, menuScene);

        viewTicketsButton.setOnAction(e -> primaryStage.setScene(viewTicketsScene.getScene()));
        purchaseTicketsButton.setOnAction(e -> primaryStage.setScene(purchaseTicketsScene.getScene()));

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
}