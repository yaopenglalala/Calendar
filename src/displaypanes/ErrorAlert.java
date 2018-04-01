package displaypanes;

import javafx.scene.control.Alert;

public class ErrorAlert extends Alert{
    public ErrorAlert(int errorCode){
        super(Alert.AlertType.ERROR, getErrorMassage(errorCode));
    }

    private static String getErrorMassage(int errorCode){
        String[] errorMessages = {"It is not formatted ","The date you input is out of bound."};
        return errorMessages[errorCode - 1];
    }
}
