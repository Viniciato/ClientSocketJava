package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nadin on 10/7/16.
 */
public class ChatWindowController {
    @FXML private TextField textLabel;
    @FXML private TextArea messagesLabel;
    private BufferedWriter buffWriter;
    private String nome;

    public void setParams(BufferedWriter buff, String nome){
        this.buffWriter = buff;
        this.nome = nome;
    }

    @FXML void sendMessage(ActionEvent event) throws IOException{
        ArrayList<String> array = new ArrayList<>();
        array.add(textLabel.getText());
        array.add(nome);
        buffWriter.write(array.toString()+"\r\n");
        messagesLabel.appendText( nome + " -> " + textLabel.getText()+"\r\n");
        buffWriter.flush();
        textLabel.setText("");
    }

    @FXML void finishChat(ActionEvent event) {

    }
}
