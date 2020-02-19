// Author: soro sandona
//Date: 9/27/2019
// Class: CSIS 335
//Instructor: Dr. Ficek
//Assignment: Project 1 : this project  processing restaurant order, display image of menu,print invoice of customer order


package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Controller {
    //option choice of dropdown list for delivery type
    ObservableList<String> deliveryType = FXCollections.observableArrayList("Pick up","Delivery");

    // nodes
    @FXML
    private ImageView imgViewBurger;

    @FXML
    private ImageView imgViewFried;

    @FXML
    private ImageView imgViewdrink;
    @FXML
    private CheckBox BurgerChckBox;

    @FXML
    private CheckBox FriedChckBox;

    @FXML
    private CheckBox DrinkChckBox;

    @FXML
    private RadioButton Burger_regularRdo;

    @FXML
    private ToggleGroup burger;

    @FXML
    private RadioButton bug_withCheeserdo;

    @FXML
    private RadioButton bug_withBaconrdo;

    @FXML
    private RadioButton bug_cheeseBaconrdo;

    @FXML
    private RadioButton fried_smallrdo;

    @FXML
    private ToggleGroup fried;

    @FXML
    private RadioButton fried_mediumrdo;

    @FXML
    private RadioButton fried_largerdo;

    @FXML
    private RadioButton drink_Cokerdo;

    @FXML
    private ToggleGroup drink;

    @FXML
    private RadioButton drink_fantardo;

    @FXML
    private RadioButton drink_Pepsirdo;

    @FXML
    private Button submitButton;

    @FXML
    private TextField Cost_TxtField;

    @FXML
    private Button printbutton;

    @FXML
    private TextArea txtArea_for_Inventory;

    @FXML
    private Button BillsBtn;

    @FXML
    private Button Cancel_order;


    @FXML
    private ChoiceBox<String> deliveryChoicebox;




    LinkedList<Product> orderList = new LinkedList<Product>();
    Image image;
    Product product;
    Product product_fried;
    Product product_drink;
    double total_Cost=0;

//add option choice to the choicebox

    @FXML
    void initialize() {
        deliveryChoicebox.setValue("Pick up");
        deliveryChoicebox.setItems(deliveryType);
    }


    // handler event of Burger check box
    @FXML
    void BurgerChckBoxHandlerEvent(ActionEvent event) {

        if(event.getSource()==BurgerChckBox) {
            product = new Product();

            if(BurgerChckBox.isSelected()){
                image = new Image("burger.jpg");
                imgViewBurger.setImage(image);
                product.setProductName("Burger");

            }
            else{
                if(!BurgerChckBox.isSelected()) {

                    imgViewBurger.setImage(null);
                    Burger_regularRdo.setSelected(false);
                    bug_withCheeserdo.setSelected(false);
                    bug_withBaconrdo.setSelected(false);
                    bug_cheeseBaconrdo.setSelected(false);
                    removeProduct(product.getProductName());
                    product.removeorder();
                }
            }
        }

        orderList.add(product);
    }

    // handler event of burger radio button
    @FXML
    void burgerRadioHandler(ActionEvent event) {
        if(!BurgerChckBox.isSelected()){
            Burger_regularRdo.setSelected(false);
            bug_withCheeserdo.setSelected(false);
            bug_withBaconrdo.setSelected(false);
            bug_cheeseBaconrdo.setSelected(false);

        }
        else {
            if (Burger_regularRdo.isSelected()) {
                double burger_price = Burger.REGULAR.getPrice();
                product.setProduct_Description("Regular Burger");
                product.setProduct_Price(burger_price);
                total_Cost += burger_price;
            } else if (bug_withCheeserdo.isSelected()) {
                double bug_withCheesePrice = Burger.WITHCHEESE.getPrice();
                product.setProduct_Description("Burger with Cheese");
                product.setProduct_Price(bug_withCheesePrice);
                total_Cost += bug_withCheesePrice;
            } else if (bug_withBaconrdo.isSelected()) {
                double bug_withBaconprice = Burger.WITHBACON.getPrice();
                product.setProduct_Description("Burger with bacon");
                product.setProduct_Price(bug_withBaconprice);
                total_Cost += Burger.WITHBACON.getPrice();
            } else if (bug_cheeseBaconrdo.isSelected()) {
                double bug_cheeseBaconPrice = Burger.CHEESEANDBACON.getPrice();
                product.setProduct_Description("Burger with Cheese and bacon");
                product.setProduct_Price(bug_cheeseBaconPrice);
                total_Cost += bug_cheeseBaconPrice;

            }
        }
    }

    // handler event of Fried check box
    @FXML
    void FriedChckBoxHandlerEvent(ActionEvent event) {
        if(event.getSource()==FriedChckBox) {
            product_fried = new Product();
            if(FriedChckBox.isSelected()){
                image = new Image("Fried.jpg");
                imgViewFried.setImage(image);
                product_fried.setProductName("Fried");
            }
            else{
                if(!FriedChckBox.isSelected()) {

                    imgViewFried.setImage(null);
                    fried_smallrdo.setSelected(false);
                    fried_mediumrdo.setSelected(false);
                    fried_largerdo.setSelected(false);
                    removeProduct(product_fried.getProductName());
                    product_fried.removeorder();

                }
            }
        }
        orderList.add(product_fried);
    }
    // handler event of fried radio button
    @FXML
    void fried_RadioHandler(ActionEvent event) {

        if(!FriedChckBox.isSelected()){
            fried_smallrdo.setSelected(false);
            fried_mediumrdo.setSelected(false);
            fried_largerdo.setSelected(false);

        }
        else {
            if (fried_smallrdo.isSelected()) {
                double fried_smallPrice=Fried.SMALL.getPrice();
                product_fried.setProduct_Description("Small size fried");
                product_fried.setProduct_Price(fried_smallPrice);
                total_Cost += fried_smallPrice;
            } else if (fried_mediumrdo.isSelected()) {
                double fried_mediumPrice = Fried.MEDIUM.getPrice();
                product_fried.setProduct_Description("Medium size fried");
                product_fried.setProduct_Price(fried_mediumPrice);
                total_Cost +=fried_mediumPrice;
            } else if (fried_largerdo.isSelected()) {
                double fried_largePrice=Fried.LARGE.getPrice();
                product_fried.setProduct_Description(" Large size fried");
                product_fried.setProduct_Price(fried_largePrice);
                total_Cost += fried_largePrice;
            }
        }
    }

    // handler event of Burger check box
    @FXML
    void DrinkChckBoxHandlerEvent(ActionEvent event) {
        if(event.getSource()==DrinkChckBox){
            product_drink = new Product();
            if(DrinkChckBox.isSelected()){
                image = new Image("drink.jpg");
                imgViewdrink.setImage(image);
                product_drink.setProductName("Beverage");
            }
            else{
                if(!DrinkChckBox.isSelected()) {

                    imgViewdrink.setImage(null);
                    drink_Cokerdo.setSelected(false);
                    drink_fantardo.setSelected(false);
                    drink_Pepsirdo.setSelected(false);
                    removeProduct(product_drink.getProductName());
                    product_drink.removeorder();
                }
            }
        }
        orderList.add(product_drink);
    }

    // handler event of Drink radio button
    @FXML
    void drink_RadioHandler(ActionEvent event) {
        if(!DrinkChckBox.isSelected()){
            drink_Cokerdo.setSelected(false);
            drink_fantardo.setSelected(false);
            drink_Pepsirdo.setSelected(false);

        }
        else {
            if (drink_Cokerdo.isSelected()) {
                double cokePrice = Drink.COKE.getPrice();
                product_drink.setProduct_Description("Can Coke");
                product_drink.setProduct_Price(cokePrice);
                total_Cost += cokePrice;
            } else if (drink_fantardo.isSelected()) {
                double fantaPrice = Drink.FANTA.getPrice();
                product_drink.setProduct_Description("Can Fanta");
                product_drink.setProduct_Price(fantaPrice);
                total_Cost += fantaPrice;
            } else if (drink_Pepsirdo.isSelected()) {
                double pepsiPrice = Drink.PEPSI.getPrice();
                product_drink.setProduct_Description("Can Pepsi");
                product_drink.setProduct_Price(pepsiPrice);
                total_Cost += pepsiPrice;
            }
        }

    }

    // handler event of submit button
    @FXML
    void SubmitHandlerEvent(ActionEvent event) {

        if(orderList.size()==0){
            System.out.println("no order, please place your order");
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("please place an order");
            al.show();

        }
        else {
            System.out.println(orderList);
        }


    }
    // handler event of Bill payment
    @FXML
    void BillsBtnHandlerEvent(ActionEvent event) {

        if(BurgerChckBox.isSelected() && !(bug_withBaconrdo.isSelected() || bug_withCheeserdo.isSelected() || Burger_regularRdo.isSelected()
                ||bug_cheeseBaconrdo.isSelected())){
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setContentText("please make a choice for your burger");
            infoAlert.show();
        }
        if(FriedChckBox.isSelected() && !(fried_smallrdo.isSelected() || fried_mediumrdo.isSelected() || fried_largerdo.isSelected())){
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setContentText("please make a choice for your Fried");
            infoAlert.show();
        }
        if(DrinkChckBox.isSelected() && !(drink_Cokerdo.isSelected() || drink_fantardo.isSelected() || drink_Pepsirdo.isSelected()
                ||bug_cheeseBaconrdo.isSelected())){
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setContentText("please make a choice for your Drink");
            infoAlert.show();
        }
        String delivertype=deliveryChoicebox.getValue();
        double deliver_charge= 5.00;
        if(DrinkChckBox.isSelected() && DrinkChckBox.isSelected() && FriedChckBox.isSelected()){
            double total_Cost_Coupon = total_Cost-(total_Cost*Product.getCoupon());
            String result = Double.toString(total_Cost_Coupon);

            if(delivertype==deliveryType.get(0)){
                Cost_TxtField.setText(result);
            }
            else{
                double result_deliveryCharge = total_Cost+deliver_charge;
                Cost_TxtField.setText(Double.toString(result_deliveryCharge));

            }

            //txtArea_for_Inventory.appendText(result);

        }
        else {
            if(delivertype==deliveryType.get(0)){
                Cost_TxtField.setText(Double.toString(total_Cost));
            }
            else{
                double result_deliver_Charge = total_Cost+deliver_charge;
                Cost_TxtField.setText(Double.toString(result_deliver_Charge));
            }

        }

    }
    @FXML
    void printInventory_HandlerEvent(ActionEvent event) {

        Collections.sort(orderList);

        String txtarea="";
        for(Product order:orderList){

            //System.out.printf("Product Description: %s\n Product Name: %s\n Product Price:%d",prod.getProduct_Description(),prod.getProductName()
                    //,prod.getProduct_Price());
            String str= Double.toString(order.getProduct_Price());
            String output = String.format("%-30s $%-19s %-36s \n\n",order.getProductName(),order.getProduct_Price(),order.getProduct_Description());
             txtarea += output;

        }
       String st = "product name             price             Product Description\n\n";
        txtArea_for_Inventory.appendText(st);

        //st += txtarea +"\n\n\n"+"Total cost: $"+ Double.toString(total_Cost);
        txtArea_for_Inventory.appendText(txtarea);

        String delivertype=deliveryChoicebox.getValue();
        double deliver_charge= 5.00;
        if(DrinkChckBox.isSelected() && DrinkChckBox.isSelected() && FriedChckBox.isSelected()){
            double total_Cost_Coupon = total_Cost-(total_Cost*Product.getCoupon());
            String result = Double.toString(total_Cost_Coupon);

            if(delivertype==deliveryType.get(0)){
                txtArea_for_Inventory.appendText("Delivery type: Pick Up\n\n");
                txtArea_for_Inventory.appendText("Total cost: $"+ result);
            }
           else{
              double result_deliveryCharge = total_Cost+deliver_charge;
                txtArea_for_Inventory.appendText("Delivery type: Pick Up\n\n");
                txtArea_for_Inventory.appendText("Total cost: $"+ result_deliveryCharge);
            }
            //txtArea_for_Inventory.appendText(result);

        }
        else {
            if(delivertype==deliveryType.get(0)){

            txtArea_for_Inventory.appendText("Delivery type: Pick Up\n\n");
            txtArea_for_Inventory.appendText("Total cost: $"+ total_Cost);
        }
        else{
            double result_deliver_Charge = total_Cost+deliver_charge;
            txtArea_for_Inventory.appendText("Delivery type: Pick Up\n\n");
            txtArea_for_Inventory.appendText("Total cost: $"+ result_deliver_Charge);
        }

        }
        if(Cancel_order.isCancelButton()){
            total_Cost=0.0;
            txtArea_for_Inventory.appendText("Delivery type: None\n\n");
            txtArea_for_Inventory.appendText("Total cost: $"+ total_Cost);
        }

    }

    private int search(String ProductName){
        for(int i = 0; i <= orderList.size() - 1; i++)
        {
            Product tmpProduct_object = orderList.get(i);
            String tmp_Product_Name = tmpProduct_object.getProductName();
            if(tmp_Product_Name.equals(ProductName)) {
                return i;
            }
        }
        return -999;
    }
    public void removeProduct(String ProductName)
    {
        int index = search(ProductName); // find index of product
        if(index != -999) // if product exists product
        {
            orderList.remove(index);

        }

    }

    @FXML
    void Cancel_orderHandler_Event(ActionEvent event) {
        if(event.getSource()==Cancel_order){
            total_Cost=0;
            Cost_TxtField.setText("");
            BurgerChckBox.setSelected(false);
            Burger_regularRdo.setSelected(false);
            bug_withCheeserdo.setSelected(false);
            bug_withBaconrdo.setSelected(false);
            bug_cheeseBaconrdo.setSelected(false);
            FriedChckBox.setSelected(false);
            fried_smallrdo.setSelected(false);
            fried_mediumrdo.setSelected(false);
            fried_largerdo.setSelected(false);
            DrinkChckBox.setSelected(false);
            drink_Cokerdo.setSelected(false);
            drink_fantardo.setSelected(false);
            drink_Pepsirdo.setSelected(false);
            txtArea_for_Inventory.setText("");
            imgViewBurger.setImage(null);
            imgViewFried.setImage(null);
            imgViewdrink.setImage(null);

            orderList.clear();
        }
    }


}
