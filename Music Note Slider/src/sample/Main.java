package sample;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import jm.JMC;
import jm.music.data.*;
public class Main extends Application {
    public static Note noteNew = new Note(JMC.D4, JMC.TN);
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        var controller = new DataController();
        var serialPort = SerialPortService.getSerialPort("COM3");
        var outputStream = serialPort.getOutputStream();
        serialPort.addDataListener(controller);
        var window = new BorderPane();
        var slider = new Slider(0,100,10);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(10);
        var label = new Label();
        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            label.setText(String.valueOf(newValue.intValue()));
            int sliderValue = (int)slider.getValue();
            Note note = null;
            String noteStr = null;
            if(sliderValue <= 10)
            {
                note = new Note(JMC.D4, JMC.TN);
                noteStr = "D4";

            }
            else if(sliderValue <= 20)
            {
                note = new Note(JMC.E4, JMC.TN);
                noteStr = "E4";
            }
            else if(sliderValue <= 30)
            {
                note = new Note(JMC.F4, JMC.TN);
                noteStr = "F4";
            }
            else if(sliderValue <= 40)
            {
                note = new Note(JMC.G4, JMC.TN);
                noteStr = "G4";
            }
            else if(sliderValue <= 50)
            {
                note = new Note(JMC.A5, JMC.TN);
                noteStr = "A5";
            }
            else if(sliderValue <= 60)
            {
                note = new Note(JMC.B5, JMC.TN);
                noteStr = "B5";
            }
            else if(sliderValue <= 70)
            {
                note = new Note(JMC.C5, JMC.TN);
                noteStr = "C5";
            }
            else if(sliderValue <= 80)
            {
                note = new Note(JMC.D5, JMC.TN);
                noteStr = "D5";
            }
            else if(sliderValue <= 90)
            {
                note = new Note(JMC.E5, JMC.TN);
                noteStr = "E5";
            }
            else if(sliderValue <= 100)
            {
                note = new Note(JMC.F5, JMC.TN);
                noteStr = "F5";
            }
            try {
                outputStream.write(noteStr.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(sliderValue);
            noteNew = note;
        });
        window.setCenter(slider);
        window.setRight(label);
        window.setPadding(new Insets(0, 20, 0, 20));
        var scene = new Scene(window, 1200, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }//end main
}//end class