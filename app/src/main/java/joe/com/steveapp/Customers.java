package joe.com.steveapp;

public class Customers {
    public String Name,Block_Numner,Phone_Number,Product_Price,Residence_Name
            ,Room_Number,Number_Of_Rides,Order_Number,User_Address,Restaurant_Name,Restaurant_Address,
            House_Number,Product_Name,Addresss;
    public boolean permission;

    public Customers() {
    }

    public Customers(String name, String block_Numner, String phone_Number, String product_Price, String residence_Name, String room_Number, String number_Of_Rides, String order_Number, String user_Address, String restaurant_Name, String restaurant_Address, String house_Number, String product_Name, String addresss, boolean permission) {
        Name = name;
        Block_Numner = block_Numner;
        Phone_Number = phone_Number;
        Product_Price = product_Price;
        Residence_Name = residence_Name;
        Room_Number = room_Number;
        Number_Of_Rides = number_Of_Rides;
        Order_Number = order_Number;
        User_Address = user_Address;
        Restaurant_Name = restaurant_Name;
        Restaurant_Address = restaurant_Address;
        House_Number = house_Number;
        Product_Name = product_Name;
        Addresss = addresss;
        this.permission = permission;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBlock_Numner() {
        return Block_Numner;
    }

    public void setBlock_Numner(String block_Numner) {
        Block_Numner = block_Numner;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public void setProduct_Price(String product_Price) {
        Product_Price = product_Price;
    }

    public String getResidence_Name() {
        return Residence_Name;
    }

    public void setResidence_Name(String residence_Name) {
        Residence_Name = residence_Name;
    }

    public String getRoom_Number() {
        return Room_Number;
    }

    public void setRoom_Number(String room_Number) {
        Room_Number = room_Number;
    }

    public String getNumber_Of_Rides() {
        return Number_Of_Rides;
    }

    public void setNumber_Of_Rides(String number_Of_Rides) {
        Number_Of_Rides = number_Of_Rides;
    }

    public String getOrder_Number() {
        return Order_Number;
    }

    public void setOrder_Number(String order_Number) {
        Order_Number = order_Number;
    }

    public String getUser_Address() {
        return User_Address;
    }

    public void setUser_Address(String user_Address) {
        User_Address = user_Address;
    }

    public String getRestaurant_Name() {
        return Restaurant_Name;
    }

    public void setRestaurant_Name(String restaurant_Name) {
        Restaurant_Name = restaurant_Name;
    }

    public String getRestaurant_Address() {
        return Restaurant_Address;
    }

    public void setRestaurant_Address(String restaurant_Address) {
        Restaurant_Address = restaurant_Address;
    }

    public String getHouse_Number() {
        return House_Number;
    }

    public void setHouse_Number(String house_Number) {
        House_Number = house_Number;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getAddress() {
        return Addresss;
    }

    public void setAddress(String addresss) {
        Addresss = addresss;
    }

    public boolean getPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
