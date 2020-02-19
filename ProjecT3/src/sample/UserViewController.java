package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserViewController implements Initializable {
    @FXML
    private TextField registNumberUserViewTxtF;

    @FXML
    private TextField nberOfPush_upTxtF;

    @FXML
    private TextField Nber_Of_Pull_UpTxtF;

    @FXML
    private TextField performance_rsltTxtF;

    @FXML
    private Button regist_sessionBtn;

    @FXML
    private Button clearbtn;

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
    private TextField rgistertxtField;
    @FXML
    private Button clearTablebtn;
    @FXML
    private Button logoutbtn;

    @FXML
    private BarChart<?, ?> PerformanceChart;

    @FXML
    private CategoryAxis X_axis;

    @FXML
    private NumberAxis Y_axis;

    Stage stage = new Stage();
    XYChart.Series set1;



    @FXML
    private Button searchsessionbtn;

    ObservableList<Sessions> session_obserlist = FXCollections.observableArrayList();

    @FXML
    private void handleTextField(ActionEvent event) {

        if(!performance_rsltTxtF.getText().isEmpty() ||performance_rsltTxtF.getText().isEmpty()) {
            regist_sessionBtn.requestFocus();
            regist_sessionBtn.setDefaultButton(true);
        }
        if(!rgistertxtField.getText().isEmpty() || rgistertxtField.getText().isEmpty()) {
            searchsessionbtn.requestFocus();
            searchsessionbtn.setDefaultButton(true);
        }

    }


    @FXML
    void clearbtnHandler(ActionEvent event) {
        registNumberUserViewTxtF.clear();
        nberOfPush_upTxtF.clear();
        Nber_Of_Pull_UpTxtF.clear();
        performance_rsltTxtF.clear();
        registNumberUserViewTxtF.requestFocus();

    }
    @FXML
    void logoutHandler(ActionEvent event) throws IOException {
        if(event.getSource()==logoutbtn){
        stage = (Stage) logoutbtn.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("LoginView.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    }

    @FXML
    void regist_sessionBtnHandler(ActionEvent event) throws SQLException {

        PreparedStatement mystate=null;
        ResultSet rs = null;


        boolean found=isAlpha(registNumberUserViewTxtF.getText());
        if(found==false){
            System.out.println("In Initialize");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection("root","D@o09712130");
            if (nberOfPush_upTxtF.getText().isEmpty() || Nber_Of_Pull_UpTxtF.getText().isEmpty() || registNumberUserViewTxtF.getText().isEmpty()
                    || performance_rsltTxtF.getText().isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("fill out the form");
                alert.show();
            }
            else if(Integer.parseInt(nberOfPush_upTxtF.getText())>50 || Integer.parseInt(Nber_Of_Pull_UpTxtF.getText())>50 ||
                    Integer.parseInt(performance_rsltTxtF.getText())>100){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("enter number correctly");
                alert.show();

            }
            else {
                PreparedStatement st1 = connection.prepareStatement("select member_RegistNumber from members_table");
                rs= st1.executeQuery();
                ArrayList<Integer> member_regist_Nber = new ArrayList<Integer>();
                while (rs.next()){
                    member_regist_Nber.add(rs.getInt("member_RegistNumber"));

                }
                boolean result = contains(member_regist_Nber, Integer.parseInt(registNumberUserViewTxtF.getText()));
                if (result == true) {
                    try {

                        //insert a new employee
                        String query1 = "insert into session_record(Push_Up,Pull_Up,member_RegistNumber,Performance_result) values (?,?,?,?)";

                        // prepared statement
                        mystate = connection.prepareStatement(query1);
                        // set the parameter

                        mystate.setString(1, nberOfPush_upTxtF.getText());
                        mystate.setString(2, Nber_Of_Pull_UpTxtF.getText());
                        mystate.setString(3, registNumberUserViewTxtF.getText());
                        mystate.setString(4, performance_rsltTxtF.getText());
                        mystate.execute();
                    } catch (SQLException e) {
                        e.printStackTrace();

                    } finally {
                        if (rs != null) {
                            rs.close();
                        }
                    }


                }

                else {
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

        registNumberUserViewTxtF.requestFocus();
    }


    @FXML
    void searchsessionbtnHandler_Event(ActionEvent event) throws SQLException {

        ResultSet rs = null;
        System.out.println("In Initialize");
        boolean found=isAlpha(rgistertxtField.getText());
        if(found==false){
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection("root","D@o09712130");
            PreparedStatement st1 = connection.prepareStatement("select member_RegistNumber from members_table");
            rs= st1.executeQuery();
            ArrayList<Integer> member_regist_Nber = new ArrayList<Integer>();
            while (rs.next()){
                member_regist_Nber.add(rs.getInt("member_RegistNumber"));

            }
            if(rgistertxtField.getText().isEmpty()){
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("enter your register_Number");
                alert.show();

            }
            else {

                boolean result = contains(member_regist_Nber, Integer.parseInt(rgistertxtField.getText()));
                if (result == true) {
                    search(rgistertxtField.getText());
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
    void clearTablebtnhandler(ActionEvent event) {
        sessionTableView.getItems().clear();
        rgistertxtField.clear();
        PerformanceChart.setAnimated(false);
        set1.getData().clear();
        PerformanceChart.setAnimated(true);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registNumberUserViewTxtF.requestFocus();
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
         set1 = new XYChart.Series<>();


        try{
            PreparedStatement st = connection.prepareStatement("select Session_ID,Push_Up,Pull_Up,Performance_result from session_record where " +
                    "member_RegistNumber=?");
            //int value = Integer.parseInt(rgistertxtField.getText());
            st.setString(1,nmber);
            rs= st.executeQuery();

            while(rs.next()){
                Sessions seriesOfSessions = new Sessions(
                        rs.getInt("Session_ID"),
                        rs.getInt("Push_Up"),
                        rs.getInt("Pull_Up"),
                        rs.getInt("Performance_result"));

                session_obserlist.add(seriesOfSessions);
                set1.getData().add(new XYChart.Data("session"+ seriesOfSessions.getSession_ID(),seriesOfSessions.getPerformance()));


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        PerformanceChart.getData().addAll(set1);
    }
    @FXML
    void contentHandler(MouseEvent event) {
        if(registNumberUserViewTxtF.getText().isEmpty()){
            registNumberUserViewTxtF.requestFocus();
        }
        if(rgistertxtField.getText().isEmpty()){
            rgistertxtField.requestFocus();
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

}
