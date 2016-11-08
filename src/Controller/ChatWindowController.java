package Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Nadin on 10/7/16.
 */
public class ChatWindowController {
    @FXML private TextField textLabel;
    @FXML private TextArea messagesLabel;
    private BufferedWriter buffWriter;
    private String nome;
    private Socket socket;

    public void setParams(BufferedWriter buff, String nome, Socket socket){
        this.buffWriter = buff;
        this.nome = nome;
        this.socket = socket;
        escutar();
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

    public void escutar(){

        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                try {
                    InputStream in = socket.getInputStream();
                    InputStreamReader inr = new InputStreamReader(in);
                    BufferedReader bfr = new BufferedReader(inr);
                    String msg = "";
                    while(!"Sair".equalsIgnoreCase(msg)){
                        if(bfr.ready()){
                            msg = bfr.readLine();
                            msg = msg.replace("[", "");
                            msg = msg.replace("]", "");
                            String[] ary = msg.split(",");

                            if(msg.equals("Sair"))
                                messagesLabel.appendText("Servidor caiu");
                            else
                                messagesLabel.appendText(ary[0]+"\r\n");
                        }
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
                return null;
            }
        };

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
    }

    @FXML void finishChat(ActionEvent event) {

    }
}
