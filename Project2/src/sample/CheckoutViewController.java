package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CheckoutViewController {
    @FXML
    private DatePicker expire_DateTxtF;

    @FXML
    private ChoiceBox<String> paymentTypeRdiobtn;

    @FXML
    private PasswordField CVVInfoTxtF;

    @FXML
    private TextField CardNberTxtF;

    @FXML
    private TextField cardHolderNmeTxtF;

    @FXML
    private TextArea purchaseInfoTxtArea;

    @FXML
    private Button paybnt;
    @FXML
    private Button Homebutton;

    String itembough;

    Stage stage;

    //option choice of dropdown list for delivery type
    ObservableList<String> paymentType = FXCollections.observableArrayList("Credit","Debit");
    Map customerOrder = new HashMap<String,String>();

    //initialize value of payment type choice box in the checkout view
    @FXML
    void initialize() {
        paymentTypeRdiobtn.setValue("Credit");
        paymentTypeRdiobtn.setItems(paymentType);
    }

    @FXML
    void paybtnHandler(ActionEvent event) {
        String selectItem = (String) paymentTypeRdiobtn.getSelectionModel().getSelectedItem();
        if(expire_DateTxtF.getValue()==null ||cardHolderNmeTxtF.getText().isEmpty()||CardNberTxtF.getText().isEmpty()
                ||CVVInfoTxtF.getText().isEmpty()||selectItem==null){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please fill out the form");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("you are about to proceed the checkout ");
            alert.show();
            if(selectItem.equals("Credit")){
                customerOrder.put("payment Type","Credit");
            }
            else{
                customerOrder.put("payment Type","Debit");
            }
            customerOrder.put("CardHolderName",cardHolderNmeTxtF.getText());
            customerOrder.put("Card_Number",CardNberTxtF.getText());
            String date = expire_DateTxtF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            customerOrder.put("Expiry_Date",date);
            customerOrder.put("CVV",CVVInfoTxtF.getText());


        }

        //System.out.println(itembough);

    }
    public void setItembough(String value){
        this.itembough=value;
    }

    @FXML
    void HomebuttonHandler(ActionEvent event) throws IOException {
        if(event.getSource()==Homebutton) {
            stage =(Stage)Homebutton.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

}
