package joe.com.steveapp;

public class Customers {
    public String finalMessage;

    public  Customers(){

    }

    public Customers(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }
}
