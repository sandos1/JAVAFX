// Author: soro sandona
//Date: 9/27/2019
// Class: CSIS 335
//Instructor: Dr. Ficek
//Assignment: Project 2 :this project contains a login page that allow user to login if they have their userName and password into the member.JSon file
//and take them to a customer view page where their select a type of book to buy and display all book information from the bookDatabase.json file into the table view that i consider
// as administrator view where he can add new book to the database and update the code for the new book add into  choiceBoxHandler in the MainController.

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        primaryStage.setTitle("Online BookStore");
        primaryStage.setScene(new Scene(root, 500, 375));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
