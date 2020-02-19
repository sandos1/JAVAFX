package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LoginController {

    @FXML
    private TextField UserNametxtField;

    @FXML
    private PasswordField PasswordTxtField;

    @FXML
    private Button registerbtn;

    @FXML
    private Button loginbtn;



    public Stage stage = new Stage();
    JSONObject credentialInfo;
    String customerName="";


    ArrayList<JSONObject> members= new ArrayList<>();
    public void setUser1() {
        JSONObject obj1 = new JSONObject();
        Map user1 = new LinkedHashMap();
        user1.put("username","soros1");
        user1.put("PassWord","058215");
        user1.put("date","2015-10-20");
        user1.put("lastName","soro");
        user1.put("FirstName","Sandona");
        obj1.put("member1",user1);
        members.add(obj1);
    }

    @FXML
    void loginbtnHandler(ActionEvent event) throws IOException, ParseException {
        UserNametxtField.requestFocus();
        for(int i=0;i<members.size();i++){
            JSONObject memb = members.get(i);
            if(memb.get("PassWord").equals(PasswordTxtField.getText()) && memb.get("username").equals(UserNametxtField.getText())){
                System.out.println("sucesful login");
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")));
                stage.setScene(scene);
                stage.show();

            }

        }

    }



    @FXML
    void registerbtnHandler(ActionEvent event) throws IOException {
        stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("RegisterNewMemberView.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}
