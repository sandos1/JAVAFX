package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.*;

public class MainController implements Initializable {
    @FXML
    private RadioButton yesRadio;

    @FXML
    private ToggleGroup YN;

    @FXML
    private RadioButton NOradio;
    @FXML
    private CheckBox emailCkbox;

    @FXML
    private CheckBox printChkbox;

    @FXML
    private Label customerNameLabel;
    @FXML
    private Slider qtySlider;

    @FXML
    private Label qtyLabel;


    @FXML
    private TextArea txtAreaOrderDetail;
    @FXML
    private Button addorder;

    @FXML
    private ImageView imageView;

    @FXML
    private ComboBox<String> choiceBox;

    @FXML
    private Label titleLbl;

    @FXML
    private Label authorLbl;
    @FXML
    private Label publisherLbl;

    @FXML
    private Label bookISBNLbl;

    @FXML
    private Label editionLbl;

    @FXML
    private Label publishDayeLbl;

    @FXML
    private Label pricelbl;
    @FXML
    private Button checkbtn;

    @FXML
    private TableView<Book> tbleView;

    @FXML
    private TableColumn<Book, String> BookISBNCol;

    @FXML
    private TableColumn<Book, String> AuthorNameCol;

    @FXML
    private TableColumn<Book, String> publish_DateCol;


    @FXML
    private Label Book_ISBNLbl;

    @FXML
    private Label AuthorNmeLbl;

    @FXML
    private Label TitleLbl;

    @FXML
    private Label EditionLbl;

    @FXML
    private Label PublisherLbl;

    @FXML
    private Label PublishDateLbl;

    @FXML
    private Label PriceLbl;

    @FXML
    private Button Addbtn;

    @FXML
    private Button editbtn;

    @FXML
    private Button deletebtn;

    private TextArea purchaseInfo;
    String output = " ";
    private Stage stage = new Stage();

    @FXML
    private Slider sliderRate;

    @FXML
    private Label slidLabel;

    Image image;
    JSONObject books;
    Map book;
    String BookName;
    String price;
    ObservableList<Book> Bookentry;
    String selectItem;


    String itembough;
    private static final int INIT_Value =1;


    // holding choicebox element to choice your book Name
     ObservableList<String> dbTypeList = FXCollections.observableArrayList();
     // getting the JSon file key name and store in dbTypeList observableList
    public MainController() throws IOException, ParseException {
        Object obj =  new JSONParser().parse(new FileReader("C:\\Users\\soro sandona\\Desktop\\JavaProjectFile\\PROJECT_2\\BookDatabase.json"));
         books = (JSONObject) obj;
         book = new LinkedHashMap(books);
        Set set = book.entrySet();
        Iterator itr = set.iterator();
        while (itr.hasNext()){
            Map.Entry entry = (Map.Entry)itr.next();
            dbTypeList.add((String) entry.getKey());

        }

    }

@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        qtySlider.setValue(INIT_Value);
        qtyLabel.setText(new Integer(INIT_Value).toString());
        qtyLabel.textProperty().bindBidirectional(qtySlider.valueProperty(), NumberFormat.getCompactNumberInstance());

        choiceBox.setItems(dbTypeList);
        BookISBNCol.setCellValueFactory(new PropertyValueFactory<>("BookISBN"));
        AuthorNameCol.setCellValueFactory(new PropertyValueFactory<>("Author"));
        publish_DateCol.setCellValueFactory(new PropertyValueFactory<>("publish_Date"));
        try {
            tbleView.setItems(populate());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tbleView.setEditable(true);
        BookISBNCol.setCellFactory(TextFieldTableCell.forTableColumn());
        AuthorNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        publish_DateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tbleView.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,NewValue)->showDetail(NewValue));

    }
    @FXML
    public void setuserNameLabel(String name){
        customerNameLabel.setText(name);
    }


    public void editAuthorISBN (TableColumn.CellEditEvent eCell){
        Book empSelected = tbleView.getSelectionModel().getSelectedItem();
        empSelected.setBookISBN(eCell.getNewValue().toString());
    }

    public void editAuthorNAme (TableColumn.CellEditEvent eCell){
        Book empSelected = tbleView.getSelectionModel().getSelectedItem();
        empSelected.setAuthor(eCell.getNewValue().toString());
    }
    public void editPublish_Date (TableColumn.CellEditEvent eCell){
        Book empSelected = tbleView.getSelectionModel().getSelectedItem();
        empSelected.setPublish_Date(eCell.getNewValue().toString());
    }

    @FXML
    public void handleerror(){
        if(NOradio.isSelected()){
            printChkbox.setDisable(true);
            emailCkbox.setDisable(true);
        }
        else if(yesRadio.isSelected()){
            printChkbox.setDisable(false);
            emailCkbox.setDisable(false);
        }

    }

    private void showDetail(Book BOOK) {
        PriceLbl.setText(BOOK.getPrice());
        Book_ISBNLbl.setText(BOOK.getBookISBN());
        AuthorNmeLbl.setText(BOOK.getAuthor());
        TitleLbl.setText(BOOK.getTitle());
        EditionLbl.setText(BOOK.getEdition());
        PublisherLbl.setText(BOOK.getPublisher());
        PublishDateLbl.setText(BOOK.getPublish_Date());
    }

    public ObservableList<Book> populate() throws IOException, ParseException {
             Bookentry = FXCollections.observableArrayList();
            Object obj =  new JSONParser().parse(new FileReader("C:\\Users\\soro sandona\\Desktop\\JavaProjectFile\\PROJECT_2\\BookDatabase.json"));
            JSONObject bookDBS = (JSONObject) obj;
            Map books = new LinkedHashMap(bookDBS);

            //this arrayList hold the string of each object key in the JSON File
            ArrayList<String> keys = new ArrayList<String>();
            Set set = books.entrySet();
            Iterator itr = set.iterator();
            while (itr.hasNext()){
                Map.Entry entry = (Map.Entry)itr.next();
                // add string value to keys arraylist variable.
                keys.add((String) entry.getKey());
            }

            // looping through the arrayList to get each key  to retrieve its value from the json file
           for (int i=0; i<keys.size();i++){
               String value = keys.get(i);
               JSONObject BOOKOject = (JSONObject) bookDBS.get(value);

               String price =(String)BOOKOject.get("Price");
               String book_ISBN =(String)BOOKOject.get("BookISBN");
               String author =(String)BOOKOject.get("Author");
               String title =(String)BOOKOject.get("title");

               String edition =(String)BOOKOject.get("edition");
               String publisher =(String)BOOKOject.get("Publisher");
               String publish_Date =(String)BOOKOject.get("publish_Date");
               // adding a new book instance to the observableArrayList
               Bookentry.add(new Book(price,book_ISBN,author,title,edition,publisher,publish_Date));
           }


        return Bookentry;
    }
    @FXML
    void choiceBoxHandler(ActionEvent event) throws IOException {
        // get the select item from the choiceBox
         selectItem = (String) choiceBox.getSelectionModel().getSelectedItem();
        if(selectItem.equals("Java")){
            image = new Image("file:java.jpg");
            imageView.setImage(image);
            Map java_book= (Map) book.get("Java");
             price =(String)java_book.get("Price");
            String book_ISBN =(String)java_book.get("BookISBN");
            String title =(String)java_book.get("title");
            String author =(String)java_book.get("Author");
            String edition =(String)java_book.get("edition");
            String publisher =(String)java_book.get("Publisher");
            String publish_Date =(String)java_book.get("publish_Date");
            BookName="JAva";
            output+= "JAva \n"+price;
            // display book information into label
            pricelbl.setText(price);
            bookISBNLbl.setText(book_ISBN);
            titleLbl.setText(title);
            authorLbl.setText(author);
            editionLbl.setText(edition);
            publisherLbl.setText(publisher);
            publishDayeLbl.setText(publish_Date);

        }
        if(selectItem.equals("python")) {
            image = new Image("file:python.jpg");
            imageView.setImage(image);
            Map java_book = (Map) book.get("python");
            price = (String) java_book.get("Price");
            String book_ISBN = (String) java_book.get("BookISBN");
            String title = (String) java_book.get("title");
            String author = (String) java_book.get("Author");
            String edition = (String) java_book.get("edition");
            String publisher = (String) java_book.get("Publisher");
            String publish_Date = (String) java_book.get("publish_Date");
            BookName = "Python";
            output+= "python \n"+price;
            // display book information into label
            pricelbl.setText(price);
            bookISBNLbl.setText(book_ISBN);
            titleLbl.setText(title);
            authorLbl.setText(author);
            editionLbl.setText(edition);
            publisherLbl.setText(publisher);
            publishDayeLbl.setText(publish_Date);
        }

            if(selectItem.equals("Bussiness")){
                image = new Image("file:bussiness.jpg");
                imageView.setImage(image);
                Map bussinessBook= (Map) book.get("Bussiness");
                price =(String)bussinessBook.get("Price");
                String book_ISBN =(String)bussinessBook.get("BookISBN");
                String title =(String)bussinessBook.get("title");
                String author =(String)bussinessBook.get("Author");
                String edition =(String)bussinessBook.get("edition");
                String publisher =(String)bussinessBook.get("Publisher");
                String publish_Date =(String)bussinessBook.get("publish_Date");
                BookName="Bussines";
                output+= "Bussines \n"+price;
                // display book information into label
                pricelbl.setText(price);
                bookISBNLbl.setText(book_ISBN);
                titleLbl.setText(title);
                authorLbl.setText(author);
                editionLbl.setText(edition);
                publisherLbl.setText(publisher);
                publishDayeLbl.setText(publish_Date);
        }
        System.out.println(output);
    }
    @FXML
    void addOrderButton(ActionEvent event) throws IOException {
        if(event.getSource()==addorder){
            if(yesRadio.isSelected()) {

                if (emailCkbox.isSelected() && printChkbox.isSelected() && (selectItem.equals("Java"))) {

                    txtAreaOrderDetail.appendText("I want my receipt: : email and print\n");
                    txtAreaOrderDetail.appendText("you Order:a Java book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $217");
                } else if (emailCkbox.isSelected() && printChkbox.isSelected() && (selectItem.equals("python"))) {
                    String result = "I want my receipt: ";
                    txtAreaOrderDetail.appendText(result + "email and print\n");
                    txtAreaOrderDetail.appendText("you Order: python book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $120");
                } else if (emailCkbox.isSelected() && printChkbox.isSelected() && (selectItem.equals("Bussiness"))) {
                    String result = "I want my receipt: ";
                    txtAreaOrderDetail.appendText(result + "email and print\n");
                    txtAreaOrderDetail.appendText("you Order: Bussiness book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $200");
                } else if (emailCkbox.isSelected() && (selectItem.equals("Java"))) {
                    String result = "I want my receipt:: ";
                    txtAreaOrderDetail.appendText(result + "email\n");
                    txtAreaOrderDetail.appendText("you Order: java book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $217");
                } else if (printChkbox.isSelected() && (selectItem.equals("Java"))) {
                    txtAreaOrderDetail.appendText("I want my receipt: : print\n");
                    txtAreaOrderDetail.appendText("you Order: java book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $217");
                } else if (printChkbox.isSelected() && (selectItem.equals("python"))) {
                    String result = "I want my receipt: ";
                    txtAreaOrderDetail.appendText(result + "print\n");
                    txtAreaOrderDetail.appendText("you Order: Python book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $120");
                } else if (emailCkbox.isSelected() && (selectItem.equals("python"))) {
                    txtAreaOrderDetail.appendText("I want my receipt: : email\n");
                    txtAreaOrderDetail.appendText("you Order: Python book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $120");
                } else if (printChkbox.isSelected() && (selectItem.equals("Business"))) {
                    String result = "I want my receipt: ";
                    txtAreaOrderDetail.appendText(result + "print\n");
                    txtAreaOrderDetail.appendText("you Order: Business book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $200");
                } else if (emailCkbox.isSelected() && (selectItem.equals("Business"))) {
                    txtAreaOrderDetail.appendText("I want my receipt: : emaail\n");
                    txtAreaOrderDetail.appendText("you Order: Bussiness book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $200");
                }
            }


            if(NOradio.isSelected()) {
                if (selectItem.equals("Java")) {
                    txtAreaOrderDetail.appendText("No receipt\n");
                    txtAreaOrderDetail.appendText("you Order: java book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $217");
                } else if (selectItem.equals("python")) {
                    txtAreaOrderDetail.appendText("No receipt\n");
                    txtAreaOrderDetail.appendText("you Order: python book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $120");

                } else if (selectItem.equals("Bussiness")) {
                    txtAreaOrderDetail.appendText("No receipt\n");
                    txtAreaOrderDetail.appendText("you Order: Business book\n");
                    txtAreaOrderDetail.appendText("qty: "+(String)qtyLabel.getText()+"\n");
                    txtAreaOrderDetail.appendText("Price: $200");

                }

            }

            if(selectItem==null){
                Alert alt = new Alert(Alert.AlertType.ERROR);
                alt.setContentText("select a book");
                alt.show();
            }


        }

        }




    // handling the checkout Button on the customer view and error
    @FXML
    void checkbtnHandler(ActionEvent event) throws IOException {
        if(event.getSource()==checkbtn) {
            if(selectItem==null){
                checkbtn.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("please make a purchase by selecting a book");
                alert.show();
                checkbtn.setDisable(false);
            }else if(!(selectItem==null)) {
                stage =(Stage)checkbtn.getScene().getWindow();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CheckoutView.fxml")));
                stage.setScene(scene);
                stage.show();
            }


        }


    }

    // open newBook.fxml view to create a new book and add to the json file of bookDatabase.json
    @FXML
    void AddbtnHandlerTbleV(ActionEvent event) throws IOException {
        if(event.getSource()==Addbtn) {
            stage =(Stage)Addbtn.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("newBook.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    //delete row selected in the table view
    @FXML
    void deletebtnHandlerTbleV(ActionEvent event) throws IOException, ParseException {
        TableView.TableViewSelectionModel tsm = tbleView.getSelectionModel();
        if (tsm.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please select a row to delete");
            alert.show();
            System.out.println("Please select a row to delete.");

        }
        else{

        int index = tbleView.getSelectionModel().getSelectedIndex();
        Bookentry.remove(index);

        }

    }
}
