package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private RadioButton adminRdio;

    @FXML
    private ToggleGroup members;

    @FXML
    private RadioButton UserRdio;

    @FXML
    private TextField UserNameTxtF;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordTxtF;

    @FXML
    private Label errorLable;

    @FXML
    private Button createAccountbtn;
    public Stage stage;
    //= new Stage();

    @FXML
    void CreateNewAccountHandlerbtn(ActionEvent event) {

    }

    @FXML
    void LoginbtnHandler(ActionEvent event) throws IOException, SQLException {
        if(event.getSource()==loginBtn ) {

            if(adminRdio.isSelected()) {
                UserNameTxtF.requestFocus();
            if (UserNameTxtF.getText().equals("root") && passwordTxtF.getText().equals("D@o09712130") ) {
                    ConnectionClass connectionClass = new ConnectionClass();
                    Connection connection = connectionClass.getConnection(UserNameTxtF.getText(), passwordTxtF.getText());
                     stage = (Stage) loginBtn.getScene().getWindow();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("AdminView.fxml")));
                    stage.setScene(scene);
                    stage.show();
                }
            else if(UserNameTxtF.getText().isEmpty()|| passwordTxtF.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("fill out form");
                alert.show();
            }

            else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("not an Admin");
                alert.show();
            }
            }
            if(UserRdio.isSelected()){

                PreparedStatement mystate=null;
                ResultSet rs = null;
                System.out.println("In Initialize");
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection("root","D@o09712130");
                PreparedStatement st1 = connection.prepareStatement("select Password,UserName from user_login_info");
                rs= st1.executeQuery();
                ArrayList<Integer> member_Password = new ArrayList<Integer>();
                while (rs.next()){
                    if (passwordTxtF.getText().equals(String.valueOf((rs.getInt("Password")))) &&
                            UserNameTxtF.getText().equals((rs.getString("UserName"))) ) {
                        stage = (Stage) loginBtn.getScene().getWindow();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UserView.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        errorLable.setText("Wrong PassWord ");
                    }

                }
            }
        }
    }
    @FXML
    void radioHandler(ActionEvent event) {
        if(adminRdio.isSelected()) {
            UserNameTxtF.requestFocus();

        }
        if(UserRdio.isSelected()) {
            UserNameTxtF.requestFocus();

        }

    }


    @FXML
    void textFieldHandler(ActionEvent event) {
        if(!UserNameTxtF.getText().isEmpty()){
            passwordTxtF.requestFocus();
        }
        if(!passwordTxtF.getText().isEmpty()){
            loginBtn.requestFocus();
            loginBtn.setDefaultButton( true );
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
