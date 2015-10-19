package webviewbrowser;

import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
 
public class WebViewBrowser extends Application {
 
    @Override public void start(Stage primaryStage) throws Exception {
        Pane root = new WebViewPane();
        primaryStage.setScene(new Scene(root, 1366, 768));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public class WebViewPane extends Pane {

        public WebViewPane() {
            VBox.setVgrow(this, Priority.ALWAYS);
            setMaxWidth(Double.MAX_VALUE);
            setMaxHeight(Double.MAX_VALUE);

            WebView view = new WebView();
            view.setMinSize(500, 400);
            view.setPrefSize(500, 400);
            final WebEngine eng = view.getEngine();
            eng.load("http://localhost:5000");

            GridPane grid = new GridPane();
            grid.setVgap(5);
            grid.setHgap(5);
            GridPane.setConstraints(view, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
            grid.getColumnConstraints().addAll(
                    new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
                    new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
            );
            grid.getChildren().addAll(view);
            getChildren().add(grid);
        }

        @Override protected void layoutChildren() {
            List<Node> managed = getManagedChildren();
            double width = getWidth();
            double height = getHeight();
            double top = getInsets().getTop();
            double right = getInsets().getRight();
            double left = getInsets().getLeft();
            double bottom = getInsets().getBottom();
            for (int i = 0; i < managed.size(); i++) {
                Node child = managed.get(i);
                layoutInArea(child, left, top,
                               width - left - right, height - top - bottom,
                               0, Insets.EMPTY, true, true, HPos.CENTER, VPos.CENTER);
            }
        }
    }
}
