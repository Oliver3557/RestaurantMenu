package sample;

public class MainCourse {
    private int CalorificValue;
    private String FoodItem;
    private double Cost;


    public MainCourse(int calorificValue, String foodItem, double cost) {
        CalorificValue = calorificValue;
        FoodItem = foodItem;
        Cost = cost;
    }

    public int getCalorificValue() {
        return CalorificValue;
    }

    public void setCalorificValue(int calorificValue) {
        CalorificValue = calorificValue;
    }

    public String getFoodItem() {
        return FoodItem;
    }

    public void setFoodItem(String foodItem) {
        FoodItem = foodItem;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }


    @Override
    public String toString() {
        return CalorificValue + ": " + FoodItem + ": " + Cost;
    }

}
