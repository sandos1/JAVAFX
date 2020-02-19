package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminViewControllers implements Initializable {
    @FXML
    private TextField registerMemberTxtF;

    @FXML
    private TextField LNameTxtF;

    @FXML
    private TextField FNameTxt;

    @FXML
    private TextField PhoneTxtF;

    @FXML
    private TextField EmailTxtF;

    @FXML
    private TextArea AddressTxtArea;

    @FXML
    private RadioButton maleRdiobtn;

    @FXML
    private RadioButton femaleRdiobtn;

    @FXML
    private DatePicker dateOFBirthTxtF;

    @FXML
    private Button createMemberbnt;

    @FXML
    private TextField rgisterNberup_deltxtF;

    @FXML
    private Button searchBtnUp_delt;

    @FXML
    private TextField lnametxtF;

    @FXML
    private TextField fnametxtF;

    @FXML
    private TextField DOBTxtF;

    @FXML
    private TextField phoneTxtF;


    @FXML
    private TextField genderTxtF;

    @FXML
    private TextField emailTxtF;

    @FXML
    private TextArea addressTxtArea;


    @FXML
    private TableView<Members> tableMemberView;

    @FXML
    private TableColumn<Members, Integer> regist_NumCol;

    @FXML
    private TableColumn<Members, String> lnameCol;

    @FXML
    private TableColumn<Members, String> fnameCol;

    @FXML
    private TableColumn<Members, String> DOB_Col;

    @FXML
    private TableColumn<Members, String> PhoneNum_Col;

    @FXML
    private TableColumn<Members, String> GenderCol;

    @FXML
    private TableColumn<Members, String> emailCol;

    @FXML
    private TableColumn<Members, String> addressCol;

    @FXML
    private TableView<Sessions> sessionTableView;

    @FXML
    private TableColumn<Sessions, Integer> session_ID_Col;

    @FXML
    private TableColumn<Sessions, Integer> Push_upCol;

    @FXML
    private TableColumn<Sessions, Integer> Pull_UpCol;

    @FXML
    private TableColumn<Sessions, Integer> Performance_RsltCol;

    @FXML
    private TextField rgisterNmberTxtF;

    @FXML
    private Button searchbutton;

    @FXML
    private Button logoutbtn;

    @FXML
    private Button clearinfobtn;

    Stage stage;
    static ResultSet rs;

    ObservableList<Members> Members_observeList = FXCollections.observableArrayList();
    ObservableList<Sessions> session_obserlist = FXCollections.observableArrayList();


    @FXML
    void createMemberHandler(ActionEvent event) throws SQLException {
        PreparedStatement mystate = null;
        ResultSet rs = null;



        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root", "D@o09712130");
        if (registerMemberTxtF.getText().isEmpty() || LNameTxtF.getText().isEmpty() || FNameTxt.getText().isEmpty()
                || PhoneTxtF.getText().isEmpty() || EmailTxtF.getText().isEmpty() || AddressTxtArea.getText().isEmpty()
                || dateOFBirthTxtF.getValue() == null || !(maleRdiobtn.isSelected() || femaleRdiobtn.isSelected())) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("fill out the form");
            alert.show();
        } else {
            try {
                boolean found=isAlpha(registerMemberTxtF.getText());
                if(found==false){
                    //insert a new employee
                    String query = "insert into members_table(member_RegistNumber,Last_Name,First_Name,Date_of_Birth,Phone_Number,Email,gender,address) " +
                            "values (?,?,?,?,?,?,?,?)";

                    // prepared statement
                    mystate = connection.prepareStatement(query);
                    // set the parameter

                    mystate.setString(1, registerMemberTxtF.getText());
                    mystate.setString(2, LNameTxtF.getText());
                    mystate.setString(3, FNameTxt.getText());
                    String DOB = dateOFBirthTxtF.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    mystate.setString(4, DOB);
                    mystate.setString(5, PhoneTxtF.getText());
                    mystate.setString(6, EmailTxtF.getText());
                    if (femaleRdiobtn.isSelected()) {
                        mystate.setString(7, "female");
                    }
                    if (maleRdiobtn.isSelected()) {
                        mystate.setString(7, "male");
                    }
                    mystate.setString(8, AddressTxtArea.getText());
                    mystate.execute();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("enter a number");
                    alert.show();
                }
                }
                catch (SQLException e) {
                e.printStackTrace();

            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        }
    }
    public boolean isAlpha(String name) {
        boolean Found = true;

        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                Found = false;
            }
        }

        return Found;
    }

    @FXML
    void PreviousAction(ActionEvent event) throws SQLException {
        if (!rs.isFirst()){
            rs.previous();
        }
        rgisterNberup_deltxtF.setText(rs.getString(1));
        lnametxtF.setText(rs.getString(2));
        fnametxtF.setText(rs.getString(3));
        DOBTxtF.setText(rs.getString(4));
        phoneTxtF.setText(rs.getString(5));
        emailTxtF.setText(rs.getString(6));
        genderTxtF.setText(rs.getString(7));
        addressTxtArea.setText(rs.getString(8));
    }

    @FXML
    void UpdateAction(ActionEvent event) {

        if(rgisterNberup_deltxtF.getText().isEmpty() ||lnametxtF.getText().isEmpty()|| fnametxtF.getText().isEmpty()|| DOBTxtF.getText().isEmpty()||
                phoneTxtF.getText().isEmpty()|| genderTxtF.getText().isEmpty()||emailTxtF.getText().isEmpty()||addressTxtArea.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please fill out the form");
            alert.show();
        }else{
            update(Integer.parseInt(rgisterNberup_deltxtF.getText()));

        }
    }
    @FXML
    void deleteAction(ActionEvent event) {

        if(rgisterNberup_deltxtF.getText().isEmpty() ||lnametxtF.getText().isEmpty()|| fnametxtF.getText().isEmpty()|| DOBTxtF.getText().isEmpty()||
                phoneTxtF.getText().isEmpty()|| genderTxtF.getText().isEmpty()||emailTxtF.getText().isEmpty()||addressTxtArea.getText().isEmpty()){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please fill out the form");
            alert.show();
        }else{
            delete(Integer.parseInt(rgisterNberup_deltxtF.getText()));

        }
    }

    @FXML
    void searchbuttonAction(ActionEvent event) throws SQLException {
        sessionTableView.getItems().clear();
        //rgisterNmberTxtF.clear();
        boolean found=isAlpha(rgisterNmberTxtF.getText());
        if(found==false){
            ResultSet rs = null;
            System.out.println("In Initialize");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection("root","D@o09712130");
            PreparedStatement st1 = connection.prepareStatement("select member_RegistNumber from members_table");
            rs= st1.executeQuery();
            ArrayList<Integer> member_regist_Nber = new ArrayList<Integer>();
            while (rs.next()){
                member_regist_Nber.add(rs.getInt("member_RegistNumber"));

            }
            if(rgisterNmberTxtF.getText().isEmpty()){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("enter your register_Number");
                alert.show();

            }
            else {
                boolean result = contains(member_regist_Nber, Integer.parseInt(rgisterNmberTxtF.getText()));
                if (result == true) {
                    search(rgisterNmberTxtF.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("user doesn't exist");
                    alert.show();
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("enter a number");
            alert.show();
        }



    }

    @FXML
    void nextAction(ActionEvent event) throws SQLException {
        if (!rs.isLast()){
            rs.next();
        }
        rgisterNberup_deltxtF.setText(rs.getString(1));
        lnametxtF.setText(rs.getString(2));
        fnametxtF.setText(rs.getString(3));
        DOBTxtF.setText(rs.getString(4));
        phoneTxtF.setText(rs.getString(5));
        emailTxtF.setText(rs.getString(6));
        genderTxtF.setText(rs.getString(7));
        addressTxtArea.setText(rs.getString(8));
    }
    @FXML
    void clearAction(ActionEvent event) {
        rgisterNberup_deltxtF.clear();
        lnametxtF.clear();
        fnametxtF.clear();
        DOBTxtF.clear();
        phoneTxtF.clear();
        genderTxtF.clear();
        emailTxtF.clear();

        addressTxtArea.clear();
    }
    @FXML
    void ClearButtonAction(ActionEvent event) {
        LNameTxtF.clear();
        FNameTxt.clear();
        registerMemberTxtF.clear();
        PhoneTxtF.clear();
        dateOFBirthTxtF.setValue(null);
        maleRdiobtn.setSelected(false);
        femaleRdiobtn.setSelected(false);
        EmailTxtF.clear();
        AddressTxtArea.clear();
    }

    @FXML
    void refreshtbaAction(ActionEvent event) throws SQLException {
        tableMemberView.getItems().clear();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        try (
                ResultSet rs =
                        connection.createStatement().executeQuery("select * from members_table"))
        {
            while(rs.next()){
                Members_observeList.add(new Members(rs.getInt("member_RegistNumber"),
                        rs.getString("Last_Name"),
                        rs.getString("First_Name"),
                        rs.getString("Date_of_Birth"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("gender"),
                        rs.getString("address")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        regist_NumCol.setCellValueFactory(new PropertyValueFactory<>("registerNumber"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        DOB_Col.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        PhoneNum_Col.setCellValueFactory(new PropertyValueFactory<>("phone_nmb"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        //tblView.setItems(obsList);
        tableMemberView.setItems(Members_observeList);


    }


    @FXML
    void searchTo_UpdateAction(ActionEvent event) throws SQLException {
        //searchRegist_numb (Integer.parseInt(rgisterNberup_deltxtF.getText()));
        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        boolean found=isAlpha(rgisterNberup_deltxtF.getText());
        if(found==false){
            //ResultSet rs = null;

            PreparedStatement st1 = connection.prepareStatement("select member_RegistNumber from members_table");
            ResultSet rs= st1.executeQuery();
            ArrayList<Integer> member_regist_Nber = new ArrayList<Integer>();
            while (rs.next()){
                member_regist_Nber.add(rs.getInt("member_RegistNumber"));

            }
            if(rgisterNberup_deltxtF.getText().isEmpty()){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("enter your register_Number");
                alert.show();

            }
            else {
                boolean result = contains(member_regist_Nber, Integer.parseInt(rgisterNberup_deltxtF.getText()));
                if (result == true) {
                    searchRegist_numb (Integer.parseInt(rgisterNberup_deltxtF.getText()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("user doesn't exist");
                    alert.show();
                }
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("enter a number");
            alert.show();
        }


    }

    public void searchRegist_numb (int nmber) {
        ResultSet rs = null;
        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        try{
            PreparedStatement st = connection.prepareStatement("select Last_Name,First_Name,Date_of_Birth,Phone_Number" +
                    ",Email,gender,address from members_table where " +
                    "member_RegistNumber=?");
            //int value = Integer.parseInt(rgistertxtField.getText());
            st.setString(1, String.valueOf(nmber));
            rs= st.executeQuery();
            while (rs.next()){
                lnametxtF.setText(rs.getString("Last_Name"));
                fnametxtF.setText(rs.getString("First_Name"));
                DOBTxtF.setText(rs.getString("Date_of_Birth"));
                phoneTxtF.setText(rs.getString("Phone_Number"));
                emailTxtF.setText(rs.getString("Email"));
                genderTxtF.setText(rs.getString("gender"));
                addressTxtArea.appendText(rs.getString("address"));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int rgistNmber){

        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        try{
            PreparedStatement st = connection.prepareStatement("delete from members_table where member_RegistNumber=?");
            //int value = Integer.parseInt(rgistertxtField.getText());
            st.setString(1, String.valueOf(rgistNmber));
            int row= st.executeUpdate();

            rgisterNberup_deltxtF.clear();
            lnametxtF.clear();
            fnametxtF.clear();
            DOBTxtF.clear();
            phoneTxtF.clear();
            genderTxtF.clear();
            emailTxtF.clear();
            addressTxtArea.clear();

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    public void update(int rgistNmber){

        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        try{
            PreparedStatement st = connection.prepareStatement("update members_table set" +
                    " Last_Name=?,First_Name=?,Date_of_Birth=?,Phone_Number=?,Email=?,gender=?,address=? " +
                    "where member_RegistNumber=?");

            st.setString(1,lnametxtF.getText());
            st.setString(2, fnametxtF.getText());
            st.setString(3, DOBTxtF.getText());
            st.setString(4, phoneTxtF.getText());
            st.setString(5, emailTxtF.getText());
            st.setString(6, genderTxtF.getText());
            st.setString(7, addressTxtArea.getText());
            st.setString(8, String.valueOf(rgistNmber));

            int row= st.executeUpdate();

            rgisterNberup_deltxtF.clear();
            lnametxtF.clear();
            fnametxtF.clear();
            DOBTxtF.clear();
            phoneTxtF.clear();
            genderTxtF.clear();
            emailTxtF.clear();
            addressTxtArea.clear();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        try{
            rs = connection.createStatement().executeQuery("select * from members_table");
            rs.first();

        }catch(SQLException e){
            e.printStackTrace();
        }

        try (
                ResultSet rs =
                        connection.createStatement().executeQuery("select * from members_table"))
        {
            while(rs.next()){
                Members_observeList.add(new Members(rs.getInt("member_RegistNumber"),
                        rs.getString("Last_Name"),
                        rs.getString("First_Name"),
                        rs.getString("Date_of_Birth"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("gender"),
                        rs.getString("address")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        regist_NumCol.setCellValueFactory(new PropertyValueFactory<>("registerNumber"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        DOB_Col.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        PhoneNum_Col.setCellValueFactory(new PropertyValueFactory<>("phone_nmb"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        //tblView.setItems(obsList);
        tableMemberView.setItems(Members_observeList);


        try{
            PreparedStatement st = connection.prepareStatement("select * from session_record");
            //int value = Integer.parseInt(rgistertxtField.getText());

             ResultSet rs= st.executeQuery();

            while(rs.next()){
                session_obserlist.add(new Sessions(
                        rs.getInt("Session_ID"),
                        rs.getInt("Push_Up"),
                        rs.getInt("Pull_Up"),
                        rs.getInt("Performance_result")));


            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        session_ID_Col.setCellValueFactory(new PropertyValueFactory<>("Session_ID"));
        Push_upCol.setCellValueFactory(new PropertyValueFactory<>("Push_Up"));
        Pull_UpCol.setCellValueFactory(new PropertyValueFactory<>("Pull_Up"));
        Performance_RsltCol.setCellValueFactory(new PropertyValueFactory<>("performance"));

        //tblView.setItems(obsList);
        sessionTableView.setItems(session_obserlist);

    }

    public void search (String nmber) {
        ResultSet rs = null;
        System.out.println("In Initialize");
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection("root","D@o09712130");
        try{
            PreparedStatement st = connection.prepareStatement("select Session_ID,Push_Up,Pull_Up,Performance_result from session_record where " +
                    "member_RegistNumber=?");
            //int value = Integer.parseInt(rgistertxtField.getText());
            st.setString(1,nmber);
            rs= st.executeQuery();

            while(rs.next()){
                session_obserlist.add(new Sessions(
                        rs.getInt("Session_ID"),
                        rs.getInt("Push_Up"),
                        rs.getInt("Pull_Up"),
                        rs.getInt("Performance_result")));
                System.out.println(rs.getInt("Session_ID")+" "+ rs.getInt("Push_Up")+" "+rs.getInt("Pull_Up")+" "+
                        rs.getInt("Performance_result"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static boolean contains(final ArrayList<Integer> array, final int v) {

        boolean result = false;

        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }

        return result;
    }

    @FXML
    void logoutbtnAction(ActionEvent event) throws IOException {
        if(event.getSource()==logoutbtn){
            stage = (Stage) logoutbtn.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginView.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void textHandler(ActionEvent event) {
        if(!registerMemberTxtF.getText().isEmpty() ||registerMemberTxtF.getText().isEmpty() ) {
            createMemberbnt.requestFocus();
            createMemberbnt.setDefaultButton(true);
        }
        if(!rgisterNberup_deltxtF.getText().isEmpty() ||rgisterNberup_deltxtF.getText().isEmpty()) {
            searchBtnUp_delt.requestFocus();
            searchBtnUp_delt.setDefaultButton(true);
        }
        if(!rgisterNmberTxtF.getText().isEmpty() || rgisterNmberTxtF.getText().isEmpty()) {
            searchbutton.requestFocus();
            searchbutton.setDefaultButton(true);
        }
    }
    @FXML
    void contentHandler(MouseEvent event) {
        if(registerMemberTxtF.getText().isEmpty()){
            registerMemberTxtF.requestFocus();
        }
        if(rgisterNberup_deltxtF.getText().isEmpty()){
            rgisterNberup_deltxtF.requestFocus();
        }
        if(rgisterNmberTxtF.getText().isEmpty()){
            rgisterNmberTxtF.requestFocus();
        }


    }

}
