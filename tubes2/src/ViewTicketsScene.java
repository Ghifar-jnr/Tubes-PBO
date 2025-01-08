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

import java.util.HashMap;

public class ViewTicketsScene {
    private final Scene scene;

    public ViewTicketsScene(Stage primaryStage, Scene menuScene) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(15);
        layout.setStyle("-fx-background-color: #F0F8FF;");

        Text header = new Text("Tipe Tiket Yang Tersedia");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        header.setFill(Color.DARKBLUE);

        HBox topRow = new HBox();
        topRow.setSpacing(20);
        topRow.setPadding(new Insets(10, 0, 10, 0));
        topRow.setAlignment(Pos.CENTER);

        HBox bottomRow = new HBox();
        bottomRow.setSpacing(20);
        bottomRow.setPadding(new Insets(10, 0, 10, 0));
        bottomRow.setAlignment(Pos.CENTER);

        TicketBoxCreator ticketBoxCreator = new TicketBoxCreator();

        // Create ticket boxes without stock information
        VBox regularBox = ticketBoxCreator.createTicketBoxWithoutStock(
                "Regular",
                "Rp 100,000",
                "Akses reguler untuk menikmati wahana."
        );

        VBox fastTrackBox = ticketBoxCreator.createTicketBoxWithoutStock(
                "Fast Track",
                "Rp 200,000",
                "Antrian lebih cepat."
        );

        VBox premiumBox = ticketBoxCreator.createTicketBoxWithoutStock(
                "Premium",
                "Rp 500,000",
                "Akses eksklusif tanpa antri."
        );

        VBox anual6Box = ticketBoxCreator.createTicketBoxWithoutStock(
                "Anual 6 Bulan",
                "Rp 250.000",
                "Nikmati ke Dufan setiap hari selama 6 bulan."
        );

        VBox anual12Box = ticketBoxCreator.createTicketBoxWithoutStock(
                "Anual 12 Bulan",
                "Rp 365.000",
                "Nikmati ke Dufan setiap hari selama 12 bulan."
        );

        topRow.getChildren().addAll(regularBox, fastTrackBox, premiumBox);
        bottomRow.getChildren().addAll(anual6Box, anual12Box);

        VBox ticketLayout = new VBox();
        ticketLayout.setSpacing(20);
        ticketLayout.setPadding(new Insets(20));
        ticketLayout.setAlignment(Pos.CENTER);
        ticketLayout.getChildren().addAll(topRow, bottomRow);

        Button backToMenuButton = new Button("Kembali ke Menu");
        backToMenuButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        backToMenuButton.setOnAction(e -> primaryStage.setScene(menuScene));

        layout.getChildren().addAll(header, ticketLayout, backToMenuButton);
        layout.setAlignment(Pos.CENTER);

        this.scene = new Scene(layout, 550, 500);
    }

    public Scene getScene() {
        return scene;
    }
}