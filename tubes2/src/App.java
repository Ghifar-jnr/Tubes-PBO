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

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "12345";
    
    // In-memory user store (for simplicity)
    private Map<String, String> userDatabase = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dufan");

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);

        Text header = new Text("Selamat Datang");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.setFill(Color.DARKBLUE);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        loginButton.setOnAction(e -> showLoginScreen(primaryStage));

        Button guestLoginButton = new Button("Login Sebagai Tamu");
        guestLoginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        guestLoginButton.setOnAction(e -> new LoginSebagaiTamu().showLoginScreen(primaryStage, layout.getScene()));

        Button registerButton = new Button("Daftar Akun Baru");
        registerButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        registerButton.setOnAction(e -> showRegistrationScreen(primaryStage));

        layout.getChildren().addAll(header, loginButton, guestLoginButton, registerButton);

        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showLoginScreen(Stage stage) {
        VBox loginLayout = new VBox();
        loginLayout.setPadding(new Insets(20));
        loginLayout.setSpacing(15);
        loginLayout.setAlignment(Pos.CENTER);
    
        Text loginHeader = new Text("Login");
        loginHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        loginHeader.setFill(Color.DARKBLUE);
    
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-padding: 10px; -fx-border-radius: 10px; -fx-border-color: #4682B4;");
    
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-padding: 10px; -fx-border-radius: 10px; -fx-border-color: #4682B4;");
    
        Button loginButton = new Button("Masuk");
        loginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (validateLogin(username, password)) {
                new DufanApp().showMenuScreen(stage);
            } else {
                showErrorAlert();
            }
        });
    
        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: #DC143C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        backButton.setOnAction(e -> showMainScreen(stage));  // Go back to the main screen
    
        loginLayout.getChildren().addAll(loginHeader, usernameField, passwordField, loginButton, backButton);
    
        Scene loginScene = new Scene(loginLayout, 500, 500);
        stage.setScene(loginScene);
    }
    
    private void showRegistrationScreen(Stage stage) {
        VBox registrationLayout = new VBox();
        registrationLayout.setPadding(new Insets(20));
        registrationLayout.setSpacing(15);
        registrationLayout.setAlignment(Pos.CENTER);
    
        Text registrationHeader = new Text("Daftar Akun Baru");
        registrationHeader.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        registrationHeader.setFill(Color.DARKBLUE);
    
        TextField newUsernameField = new TextField();
        newUsernameField.setPromptText("Username Baru");
        newUsernameField.setStyle("-fx-padding: 10px; -fx-border-radius: 10px; -fx-border-color: #4682B4;");
    
        PasswordField newPasswordField = new PasswordField();
        newPasswordField.setPromptText("Password Baru");
        newPasswordField.setStyle("-fx-padding: 10px; -fx-border-radius: 10px; -fx-border-color: #4682B4;");
    
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Konfirmasi Password");
        confirmPasswordField.setStyle("-fx-padding: 10px; -fx-border-radius: 10px; -fx-border-color: #4682B4;");
    
        Button registerButton = new Button("Daftar");
        registerButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        registerButton.setOnAction(e -> {
            String username = newUsernameField.getText();
            String password = newPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();
    
            if (password.equals(confirmPassword)) {
                if (registerNewUser(username, password)) {
                    showSuccessAlert("Akun berhasil didaftarkan!");
                    showLoginScreen(stage);
                } else {
                    showErrorAlert("Username sudah terdaftar.");
                }
            } else {
                showErrorAlert("Password tidak cocok.");
            }
        });
    
        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: #DC143C; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        backButton.setOnAction(e -> showMainScreen(stage));  // Go back to the login screen
    
        registrationLayout.getChildren().addAll(registrationHeader, newUsernameField, newPasswordField, confirmPasswordField, registerButton, backButton);
    
        Scene registrationScene = new Scene(registrationLayout, 500, 500);
        stage.setScene(registrationScene);
    }
    
    private void showMainScreen(Stage stage) {
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(15);
        layout.setAlignment(Pos.CENTER);
    
        Text header = new Text("Selamat Datang");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        header.setFill(Color.DARKBLUE);
    
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        loginButton.setOnAction(e -> showLoginScreen(stage));
    
        Button guestLoginButton = new Button("Login Sebagai Tamu");
        guestLoginButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        guestLoginButton.setOnAction(e -> new LoginSebagaiTamu().showLoginScreen(stage, layout.getScene()));
    
        Button registerButton = new Button("Daftar Akun Baru");
        registerButton.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-border-radius: 10px;");
        registerButton.setOnAction(e -> showRegistrationScreen(stage));
    
        layout.getChildren().addAll(header, loginButton, guestLoginButton, registerButton);
    
        Scene scene = new Scene(layout, 500, 500);
        stage.setScene(scene);
    }
    
    private boolean validateLogin(String username, String password) {
        // Check the entered credentials against the user database
        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    private boolean registerNewUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            return false;  // User already exists
        }
        userDatabase.put(username, password);
        return true;
    }

    private void showErrorAlert() {
        showErrorAlert("Username atau password salah.");
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Gagal");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}