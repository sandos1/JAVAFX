package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CreateNewBookController {
    @FXML
    private TextField BookISBNtxtF;

    @FXML
    private TextField AuthorTxtF;

    @FXML
    private TextField TitleTxtF;

    @FXML
    private TextField editionTxtF;

    @FXML
    private TextField PublisherTxtF;

    @FXML
    private DatePicker PublishDateTxtF;

    @FXML
    private TextField PriceTxtF;

    @FXML
    private TextField book_NmeTxtF;

    @FXML
    private Button addbtn;

    @FXML
    private Button homebtn;
    Stage stage = new Stage();

    @FXML
    void AddbtnHandler(ActionEvent event) throws IOException, ParseException {


        Object obj =  new JSONParser().parse(new FileReader("C:\\Users\\soro sandona\\Desktop\\JavaProjectFile\\PROJECT_2\\BookDatabase.json"));
        JSONObject allbooks = (JSONObject) obj;

        JSONObject bookInstance = new JSONObject();





        //LocalDate value = PublishDateTxtF.getValue();
        if(PublishDateTxtF.getValue()==null||PriceTxtF.getText().isEmpty()||BookISBNtxtF.getText().isEmpty()||AuthorTxtF.getText().isEmpty()|| TitleTxtF.getText().isEmpty()||editionTxtF.getText().isEmpty()||
                PublisherTxtF.getText().isEmpty() ){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please fill out the form");
            alert.show();
        }
        else{
            bookInstance.put("Price",PriceTxtF.getText());
            bookInstance.put("BookISBN",BookISBNtxtF.getText());
            bookInstance.put("Author",AuthorTxtF.getText());
            bookInstance.put("title",TitleTxtF.getText());
            bookInstance.put("edition",editionTxtF.getText());
            String date = PublishDateTxtF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            bookInstance.put("publish_Date",date);
            allbooks.put(book_NmeTxtF.getText(),bookInstance);
            PriceTxtF.clear();
            BookISBNtxtF.clear();
            AuthorTxtF.clear();
            TitleTxtF.clear();
            editionTxtF.clear();
            PublishDateTxtF.setValue(null);
            book_NmeTxtF.clear();
            PublisherTxtF.clear();
        }

        try {

            FileWriter jsonFileWriter = new FileWriter("C:\\Users\\soro sandona\\Desktop\\JavaProjectFile\\PROJECT_2\\BookDatabase.json");
            jsonFileWriter.write(allbooks.toJSONString());
            jsonFileWriter.flush();
            jsonFileWriter.close();
            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void HomebntHandler(ActionEvent event) throws IOException {
        if(event.getSource()==homebtn) {
            stage =(Stage)homebtn.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    public void fillbookDetails(Book book){

    }
}
