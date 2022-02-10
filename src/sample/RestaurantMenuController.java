package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;

public class RestaurantMenuController {
    public ListView courseList;
    public ListView StarterList;
    public ListView MainList;
    public ListView DessertList;
    public ListView DrinksList;
    public TextField totalCalories;
    public TextField totalCost;
    public TextField textTableNo;
    public TextField textDiners;
    public TextArea textOrder;
    public Button btnSubmit;
    public Button btnRandom;
    public Button btnClear;
    public Button btnAdd;
    public ListView lvOrder;

    private ObservableList<MainCourse> listOfMain;
    private ObservableList<MainCourse> listOfStarter;
    private ObservableList<String> listOfCourse;
    private ObservableList<MainCourse> listOfDessert;
    private ObservableList<MainCourse> listOfDrinks;
    private ObservableList<MainCourse> listOfOrder = FXCollections.observableArrayList();

    public RestaurantMenuController() {

    }

    public void initialize() {
        lvOrder.setItems(listOfOrder);
        listOfCourse = FXCollections.observableArrayList();
        listOfCourse.add("1 Course");
        listOfCourse.add("2 Courses");
        listOfCourse.add("3 Courses");
        courseList.setItems(listOfCourse);

        listOfStarter = FXCollections.observableArrayList();
        listOfStarter.add(new MainCourse(100, "Chicken Soup", 4));
        listOfStarter.add(new MainCourse(150, "Cheese bites", 3));
        listOfStarter.add(new MainCourse(175, "Garlic Bread", 2));
        listOfStarter.add(new MainCourse(200, "Chicken Wings", 4));
        StarterList.setItems(listOfStarter);

        listOfMain = FXCollections.observableArrayList();
        listOfMain.add(new MainCourse(123, "Burger", 5));
        listOfMain.add(new MainCourse(333, "Pizza", 6));
        listOfMain.add(new MainCourse(423, "Fish and Chips", 8));
        listOfMain.add(new MainCourse(546, "Chicken Curry", 5));
        listOfMain.add(new MainCourse(634, "Pasta", 4));
        listOfMain.add(new MainCourse(500, "Steak Pie", 7));
        MainList.setItems(listOfMain);

        listOfDessert = FXCollections.observableArrayList();
        listOfDessert.add(new MainCourse(350,"Chocolate Brownie", 5));
        listOfDessert.add(new MainCourse(250,"Ice Cream", 4));
        listOfDessert.add(new MainCourse(300,"Sticky Toffee Pudding",5));
        listOfDessert.add(new MainCourse(400,"Cheesecake",4));
        DessertList.setItems(listOfDessert);

        listOfDrinks = FXCollections.observableArrayList();
        listOfDrinks.add(new MainCourse(75,"Coca Cola", 3));
        listOfDrinks.add(new MainCourse(50,"Fanta", 3));
        listOfDrinks.add(new MainCourse(25,"Juice",3));
        listOfDrinks.add(new MainCourse(50,"Sprite",3));
        DrinksList.setItems(listOfDrinks);

        // force the fields to be numeric only
        textTableNo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textTableNo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        textDiners.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textDiners.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });


        //Set the Random button to select random items in the menu
        btnRandom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Random random = new Random();

                int index = random.nextInt(listOfStarter.size());
                StarterList.getSelectionModel().select(index);

                int index2 = random.nextInt(listOfMain.size());
                MainList.getSelectionModel().select(index2);

                int index3 = random.nextInt(listOfDessert.size());
                DessertList.getSelectionModel().select(index3);

                int index4 = random.nextInt(listOfDrinks.size());
                DrinksList.getSelectionModel().select(index4);
            }
        });


        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                listOfOrder.clear();
                totalCost.clear();
                totalCalories.clear();
                textDiners.clear();
                textTableNo.clear();
                courseList.getSelectionModel().clearSelection();
                StarterList.getSelectionModel().clearSelection();
                MainList.getSelectionModel().clearSelection();
                DessertList.getSelectionModel().clearSelection();
                DrinksList.getSelectionModel().clearSelection();
            }
        });


        btnAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                //get the selected items from the listviews and add to the order if something is selected.
                MainCourse starter = (MainCourse) StarterList.getSelectionModel().getSelectedItem();
                if(starter != null){
                    listOfOrder.add(starter);
                }

                MainCourse main = (MainCourse) MainList.getSelectionModel().getSelectedItem();
                if(main != null){
                    listOfOrder.add(main);
                }

                MainCourse dessert = (MainCourse) DessertList.getSelectionModel().getSelectedItem();
                if(dessert != null){
                    listOfOrder.add(dessert);
                }

                MainCourse drink = (MainCourse) DrinksList.getSelectionModel().getSelectedItem();
                if(drink != null){
                    listOfOrder.add(drink);
                }

                //call the method below to get the total calories from the listOfOrder and put the result into the text field
                totalCalories.setText(String.valueOf(calculateCalories()));
                totalCost.setText(String.valueOf(calculateCost()));

            }
        });

        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();

                //Open Receipt Screen
                PopUp1 popup = new PopUp1();
                popup.initOwner(primaryStage);
                popup.show();
            }
        });



    }

    /**
     * Loops the selected items list and call each items getCalorific content method,
     * adds the value to totalCalories and return the value
     * @return int - totalCalories
     */
    private int calculateCalories(){
        int totalCalories = 0;
        for (MainCourse course : listOfOrder) {
            totalCalories += course.getCalorificValue();
        }
        return  totalCalories;
    }

    private int calculateCost(){
        int totalCost = 0;
        for (MainCourse course : listOfOrder) {
            totalCost += course.getCost();
        }
        return totalCost;
    }

}
