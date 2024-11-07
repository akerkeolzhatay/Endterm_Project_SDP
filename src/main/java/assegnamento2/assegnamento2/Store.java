package assegnamento2.assegnamento2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Main application class for the Bakery Store
public class Store extends Application {

    // Method to set up and display the main stage (window)
    @Override
    public void start(Stage stage) throws IOException {

        // Load the FXML layout for the login screen
        FXMLLoader fxmlLoader = new FXMLLoader(Store.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Set application icon
        stage.getIcons().add(new Image(Objects.requireNonNull(Store.class.getResourceAsStream("/assegnamento2/assegnamento2/Pics/logo.png"))));

        // Make the window non-resizable
        stage.setResizable(false);

        // Set the window title
        stage.setTitle("Bakery Store");

        // Set the scene and show the window
        stage.setScene(scene);
        stage.show();
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}