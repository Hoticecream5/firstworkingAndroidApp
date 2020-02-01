package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FoodOrderActivity extends AppCompatActivity {

    private EditText order,restaurantName,restaurantAddress,deliveryAddress;
    private Button proceed;
    String orderNumber,restName,restAdrs,delvAdrs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order);

        order = findViewById(R.id.order_number);
        proceed = findViewById(R.id.order_number_btn);
        restaurantAddress = findViewById(R.id.restaurant_address);
        restaurantName = findViewById(R.id.restaurant_name);
        deliveryAddress = findViewById(R.id.user_address);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOrder();
            }
        });
    }

    public  void processOrder(){
        orderNumber  = order.getText().toString();
        restName = restaurantName.getText().toString();
        restAdrs = restaurantAddress.getText().toString();
        delvAdrs = deliveryAddress.getText().toString();
        String foodPrice = "70",foodName = "food";

       if(TextUtils.isEmpty(order.getText().toString())){

            Toast.makeText(FoodOrderActivity.this,"Enter Order Number", Toast.LENGTH_SHORT).show();

        }
       else {
            Intent intent = new Intent(FoodOrderActivity.this, ConfirmFinalOrderActivity.class);
            intent.putExtra("OrderNum", orderNumber);
            intent.putExtra("rest_Name", restName);
            intent.putExtra("delv_address",delvAdrs);
            intent.putExtra("rest_Adrs",restAdrs);
            intent.putExtra("name",foodName);
            intent.putExtra("price",foodPrice);
            startActivity(intent);

        }
    }
}
