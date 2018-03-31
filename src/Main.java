import javafx.application.Application;
import javafx.stage.Stage;

/*
* Start here!
*
* */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        new Display(primaryStage);
    }

    public static void main(String[] args){
        //todo  We will run this class to test your codes.
        Application.launch(args);
    }
}
