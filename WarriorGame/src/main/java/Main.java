import controlers.gameControler;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.gameLogic;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import static  logic.Coordinates.getX;
import static logic.Coordinates.getY;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        gameLogic logic=new gameLogic();
        gameControler controler=new gameControler();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Paths.get("src", "main", "resources", "fxml", "mainScreen.fxml").toUri().toURL());
        Scene scene=new Scene(loader.load());
        stage.setTitle("Warrior");
        stage.getIcons().add(new Image(Paths.get("src", "main", "resources", "textures/icon.jpeg").toUri().toString()));
        stage.setMinHeight(680);
        stage.setMinWidth(680);
        stage.setScene(scene);
        stage.widthProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            stage.setHeight(t1.doubleValue());
        });
        stage.heightProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            stage.setWidth(t1.doubleValue());
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if(e.getCode().toString()=="W"){
                    try {
                        controler.getLogic().moveUp();
                        controler.move();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if(e.getCode().toString()=="S"){
                    try {
                        controler.getLogic().moveDown();
                        controler.move();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if(e.getCode().toString()=="A"){
                    try {
                        controler.getLogic().moveLeft();
                        controler.move();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                } else if(e.getCode().toString()=="D"){
                    try {
                        controler.getLogic().moveRight();
                        controler.move();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });
        stage.show();
    }
}
