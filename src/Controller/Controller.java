package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/chatWindow.fxml"));
        Parent home_page_parent = loader.load();

        ChatWindowController controller = loader.getController();
        controller.setParams(buffeWriter, nameLabel.getText(), socket);

        Scene home_page_scene = new Scene(home_page_parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.close();
        main_stage.setScene(home_page_scene);
        main_stage.setResizable(false);
        main_stage.setMaximized(false);
        main_stage.show();
    }
}
