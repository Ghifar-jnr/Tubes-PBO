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

public class LoginSebagaiTamu {

    public void showLoginScreen(Stage stage, Scene menuScene) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        Text header = new Text("Login Sebagai Tamu");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        header.setFill(Color.DARKBLUE);

        Button guestLoginButton = new Button("Masuk Sebagai Tamu");
        guestLoginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        guestLoginButton.setOnAction(e -> showGuestTicketView(stage, menuScene));

        layout.getChildren().addAll(header, guestLoginButton);

        Scene loginScene = new Scene(layout, 500, 500);
        stage.setScene(loginScene);
    }

    private void showGuestTicketView(Stage stage, Scene menuScene) {
        ViewTicketsScene viewTicketsScene = new ViewTicketsScene(stage, menuScene);
        stage.setScene(viewTicketsScene.getScene());
    }
}