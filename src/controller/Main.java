package controller;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application{
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/Login_view.fxml"));
            String css = Main.class.getResource("/view/Login_styles.css").toExternalForm();
            
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);
            scene.getStylesheets().add(css);
            
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
    
    public static void main (String[] args){
        launch(args);
    }
}
