import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class CopyBruh extends Application {
    private final TextArea[] uiBoxes = new TextArea[25];
    private final TextField newDataEntry = new TextField();
    private final ListView<String> dataList = new ListView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CopyBruh 2.0");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label newDataLabel = new Label("Enter new data:");
        grid.add(newDataLabel, 0, 0);
        grid.add(newDataEntry, 1, 0);

        Label existingDataLabel = new Label("Existing data:");
        grid.add(existingDataLabel, 0, 1);
        grid.add(dataList, 1, 1);

        for (int i = 0; i < 25; i++) {
            uiBoxes[i] = new TextArea();
            uiBoxes[i].setPrefRowCount(1);
            uiBoxes[i].setEditable(false);
            final int index = i;
            uiBoxes[i].setOnMouseClicked(event -> copyData(uiBoxes[index].getText()));
            grid.add(uiBoxes[i], 0, i + 2, 2, 1);
        }

        newDataEntry.setOnAction(event -> saveData());

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(windowEvent -> {
            // Perform any cleanup or finalization before closing the application
            // For example, stop background tasks or save application state
            Platform.exit();
            System.exit(0);
        });

        primaryStage.show();
    }

    private void saveData() {
        String newData = newDataEntry.getText();
        dataList.getItems().add(newData);

        for (int i = 0; i < dataList.getItems().size() && i < uiBoxes.length; i++) {
            uiBoxes[i].setText(dataList.getItems().get(i));
        }

        newDataEntry.clear();
    }

    private void copyData(String data) {
        Platform.runLater(() -> {
            try {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(data);
                clipboard.setContent(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
