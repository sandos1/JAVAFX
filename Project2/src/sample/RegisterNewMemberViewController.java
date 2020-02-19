package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterNewMemberViewController {
    @FXML
    private TextField userNameTxtF;

    @FXML
    private PasswordField PassswordField;

    @FXML
    private DatePicker registDate;
    @FXML
    private TextField lNameTxtF;

    @FXML
    private TextField FNameTxtF;


    @FXML
    private Button addbtn;

    @FXML
    private Button gobacklogpagebtn;

    Stage stage;

    ArrayList<JSONObject> members=new ArrayList<JSONObject>();




    public void setUser1() {
        JSONObject obj1 = new JSONObject();
        Map user1 = new LinkedHashMap();

        user1.put("PassWord","058215");
        user1.put("date","2015-10-20");
        user1.put("lastName","soro");
        user1.put("FirstName","Sandona");
        obj1.put("soros1",user1);
        members.add(obj1);
    }

    @FXML
    void addbtnHandler(ActionEvent event) {
        members.add( addnew());

    }
    public JSONObject addnew(){
        JSONObject obj = new JSONObject();
        Map users = new LinkedHashMap();

        users.put("PassWord",PassswordField.getText());
        users.put("date",registDate.getValue());
        users.put("lastName",lNameTxtF.getText());
        users.put("FirstName",FNameTxtF.getText());
        obj.put(userNameTxtF.getText(),users);

        return obj;

    }

    @FXML
    void gobacklogpagebtnHandler(ActionEvent event) throws IOException {
        if(event.getSource()==gobacklogpagebtn)
        stage =(Stage)gobacklogpagebtn.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("loginView.fxml")));
        stage.setScene(scene);
        stage.show();
    }







}
