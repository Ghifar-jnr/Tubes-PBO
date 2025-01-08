import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class TicketBoxCreator {

    public VBox createTicketBoxWithoutStock(String title, String price, String description) {
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
}