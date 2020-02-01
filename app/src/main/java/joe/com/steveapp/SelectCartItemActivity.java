package joe.com.steveapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectCartItemActivity extends AppCompatActivity {

    private Button medicineBtn,foodBtn,transBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cart_item);

        medicineBtn = findViewById(R.id.medication_btn);
        foodBtn = findViewById(R.id.food_order_btn);
        transBtn = findViewById(R.id.transport_btn);



        medicineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectCartItemActivity.this, "Coming Soon",Toast.LENGTH_SHORT).show();
            }
        });

        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNumber();
            }
        });

        transBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }


    public void orderNumber(){
        Intent intent = new Intent(SelectCartItemActivity.this, FoodOrderActivity.class);
        startActivity(intent);
    }

    public void goToHome(){
        Intent intent = new Intent(SelectCartItemActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
