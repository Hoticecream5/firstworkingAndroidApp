package joe.com.steveapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import joe.com.steveapp.Prevalent.Prevalent;

public class ConfirmFinalOrderActivity extends AppCompatActivity {
    private Button confirmOrderBtn;
    private EditText userNameTxt,userNumber;

    private String name,phone_Number,finalMessage, productPrice,orderNumbr,hseNum,
            addrs,blkNum,resName,roomNum,rest_Address,user_Address, rest_Name, productName;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        confirmOrderBtn =  findViewById(R.id.confirm_final_order_btn);
        userNameTxt = findViewById(R.id.shippment_name);
        userNumber = findViewById(R.id.shippment_phone_number);


        Bundle bundle = getIntent().getExtras();

        orderNumbr = bundle.getString("OrderNum");
        hseNum = bundle.getString("house_Number");
        addrs = bundle.getString("address");
        blkNum = bundle.getString("blockNumber");
        resName = bundle.getString("res_Name");
        roomNum = bundle.getString("room_Num");
        rest_Name = bundle.getString("rest_Name");
        rest_Address = bundle.getString("rest_Adrs");
        user_Address = bundle.getString("delv_address");
        productName = bundle.getString("name");
        productPrice = bundle.getString("price");


        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConfirmFinalOrderActivity.this, "click send to send sms to driver ",Toast.LENGTH_LONG).show();
              smsMessage();
            }
        });
    }

    public void messageToSend(){
    count = count+1;

        if (TextUtils.isEmpty(orderNumbr) && TextUtils.isEmpty(resName)) {
            finalMessage = "Name: " + name + "\n" +
                    "Phone Number: " + phone_Number + "\n" +
                    "Address: " + addrs + "\n" +
                    "Product name: " + productName + "\n" +
                    "Product Price: " + productPrice + "\n" +
                    "Number Of Trips: " + count + "\n" +
                    "House Number: " + hseNum + "\n";

        } else if (TextUtils.isEmpty(orderNumbr) && TextUtils.isEmpty(hseNum)) {

            finalMessage = "Name: " + name + "\n" +
                    "Phone Number: " + phone_Number + "\n" +
                    "Residence Name: " + resName + "\n" +
                    "Block Number: " + blkNum + "\n" +
                    "Product name: " + productName + "\n" +
                    "Product Price: " + productPrice + "\n" +
                    "Number Of Trips: " + count + "\n" +
                    "Room Number: " + roomNum + "\n";


        } else if (TextUtils.isEmpty(resName)) {
            finalMessage = "Name: " + name + "\n" +
                    "Phone Number: " + phone_Number + "\n" +
                    "Delivery Address: " + user_Address + "\n" +
                    "Restaurant Address: " + rest_Address + "\n" +
                    "Restaurant Name: " +rest_Name + "\n" +
                    "Product name: " + productName + "\n" +
                    "Product Price: " + productPrice + "\n" +
                    "Number Of Trips: " + count + "\n" +
                    "Order Number: " + orderNumbr;
        } else if (TextUtils.isEmpty(addrs) && TextUtils.isEmpty(hseNum)) {
            finalMessage = "Name: " + name + "\n" +
                    "Phone Number: " + phone_Number + "\n" +
                    "Delivery Address: " + user_Address + "\n" +
                    "Restaurant Address: " + rest_Address + "\n" +
                    "Restaurant Name: " +rest_Name + "\n" +
                    "Product name: " + productName + "\n" +
                    "Product Price: " + productPrice + "\n" +
                    "Number Of Trips: " + count + "\n" +
                    "Order Number " + orderNumbr;
        }
    }


    private void smsMessage() {
        name = userNameTxt.getText().toString();
        phone_Number = userNumber.getText().toString();
        if ((TextUtils.isEmpty(name)) || (TextUtils.isEmpty(phone_Number))) {
            Toast.makeText(ConfirmFinalOrderActivity.this, "Fill in your name and phone number ", Toast.LENGTH_LONG).show();
        } else {

            messageToSend();

            String phoneNumber = "+27732921905";

            Uri sms_uri = Uri.parse("smsto:" + phoneNumber);
            Intent sms_intent = new Intent(Intent.ACTION_VIEW, sms_uri);
            sms_intent.setData(sms_uri);
            sms_intent.putExtra("sms_body", finalMessage);
            startActivity(sms_intent);
            emptyCart();
        }
    }
    public void emptyCart(){

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        cartListRef.child("User View")
                .child(Prevalent.currentOnlineUser.getPhone())
                .child("Products")
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                        }
                    }
                });
    }

}

