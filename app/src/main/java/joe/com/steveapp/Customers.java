package joe.com.steveapp;

public class Customers {
    public String name,phone_Number,resName ,
            blkNum,productName , productPrice , count, roomNum,
            user_Address ,rest_Address , rest_Name, orderNumbr
            ,hseNum,addrs;
    public boolean permission;

    public Customers() {
    }

    public Customers(String name, String phone_Number, String resName, String blkNum, String productName, String productPrice, String count, String roomNum, String user_Address, String rest_Address, String rest_Name, String orderNumbr, String hseNum, String addrs, boolean permission) {
        this.name = name;
        this.phone_Number = phone_Number;
        this.resName = resName;
        this.blkNum = blkNum;
        this.productName = productName;
        this.productPrice = productPrice;
        this.count = count;
        this.roomNum = roomNum;
        this.user_Address = user_Address;
        this.rest_Address = rest_Address;
        this.rest_Name = rest_Name;
        this.orderNumbr = orderNumbr;
        this.hseNum = hseNum;
        this.addrs = addrs;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getBlkNum() {
        return blkNum;
    }

    public void setBlkNum(String blkNum) {
        this.blkNum = blkNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getUser_Address() {
        return user_Address;
    }

    public void setUser_Address(String user_Address) {
        this.user_Address = user_Address;
    }

    public String getRest_Address() {
        return rest_Address;
    }

    public void setRest_Address(String rest_Address) {
        this.rest_Address = rest_Address;
    }

    public String getRest_Name() {
        return rest_Name;
    }

    public void setRest_Name(String rest_Name) {
        this.rest_Name = rest_Name;
    }

    public String getOrderNumbr() {
        return orderNumbr;
    }

    public void setOrderNumbr(String orderNumbr) {
        this.orderNumbr = orderNumbr;
    }

    public String getHseNum() {
        return hseNum;
    }

    public void setHseNum(String hseNum) {
        this.hseNum = hseNum;
    }

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }

    public boolean getPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }
}
