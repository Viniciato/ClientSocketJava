package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Controller {
    @FXML private TextField ipLabel;
    @FXML private TextField portLabel;
    @FXML private TextField nameLabel;

    @FXML void connectAction(ActionEvent event) throws IOException{
        Socket socket = new Socket(ipLabel.getText(), Integer.parseInt(portLabel.getText()));
        OutputStream outPutStream = socket.getOutputStream();
        OutputStreamWriter outStreamWriter = new OutputStreamWriter(outPutStream);
        BufferedWriter buffeWriter = new BufferedWriter(outStreamWriter);
        buffeWriter.write(nameLabel.getText()+"\r\n");
        buffeWriter.flush();
        System.out.println("Connected on " + socket.getInetAddress());
    }
}
