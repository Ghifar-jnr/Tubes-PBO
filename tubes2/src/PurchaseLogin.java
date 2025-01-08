import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PurchaseLogin {

    public void showLoginScreen(Stage stage) {
        VBox loginLayout = new VBox();
        loginLayout.setPadding(new Insets(20));
        loginLayout.setSpacing(15);
        loginLayout.setAlignment(Pos.CENTER);

        Text header = new Text("Login Pembelian");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("user") && password.equals("user123")) {
                new DufanApp().start(stage);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Username / Password Salah");
            }
        });

        loginLayout.getChildren().addAll(header, usernameField, passwordField, loginButton);

        Scene purchaseLoginScene = new Scene(loginLayout, 600, 600);
        stage.setScene(purchaseLoginScene);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}