package joe.com.steveapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Serializable;
import java.lang.reflect.Member;
import java.util.HashMap;

import joe.com.steveapp.Prevalent.Prevalent;

public class ConfirmFinalOrderActivity extends AppCompatActivity implements Serializable {
    static final long serialUID = 40L;
    private Button confirmOrderBtn;
    private EditText userNameTxt, userNumber;
    private String name, phone_Number, finalMessage, productPrice,
            orderNumbr, hseNum, addrs, blkNum, resName, roomNum, rest_Address, user_Address, rest_Name, productName;
    private int count;
    DatabaseReference reff;
    Customers customers;
    long maxId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        confirmOrderBtn = findViewById(R.id.confirm_final_order_btn);
        userNameTxt = findViewById(R.id.shippment_name);
        userNumber = findViewById(R.id.shippment_phone_number);
        customers = new Customers();
        reff = FirebaseDatabase.getInstance().getReference().child("Customers");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxId = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
        messageToSend();
            }
        });


       /* if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            }
            else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, My_PERMISSION_REQUEST_SEND_SMS);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case My_PERMISSION_REQUEST_SEND_SMS:
                {

                if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    confirmOrderBtn.setEnabled(true);
                } else {
                    Toast.makeText(this, "Permission is needed to send SMS to driver", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }*/
    }

    public void messageToSend() {
        count = count + 1;
        name = userNameTxt.getText().toString();
        phone_Number = userNumber.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone_Number)) {
            Toast.makeText(this, "Please enter name and phone number ", Toast.LENGTH_SHORT).show();
          /*  if (TextUtils.isEmpty(orderNumbr) && TextUtils.isEmpty(resName)) {
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
                        "Restaurant Name: " + rest_Name + "\n" +
                        "Product name: " + productName + "\n" +
                        "Product Price: " + productPrice + "\n" +
                        "Number Of Trips: " + count + "\n" +
                        "Order Number: " + orderNumbr;
            } else if (TextUtils.isEmpty(addrs) && TextUtils.isEmpty(hseNum)) {
                finalMessage = "Name: " + name + "\n" +
                        "Phone Number: " + phone_Number + "\n" +
                        "Delivery Address: " + user_Address + "\n" +
                        "Restaurant Address: " + rest_Address + "\n" +
                        "Restaurant Name: " + rest_Name + "\n" +
                        "Product name: " + productName + "\n" +
                        "Product Price: " + productPrice + "\n" +
                        "Number Of Trips: " + count + "\n" +
                        "Order Number " + orderNumbr;

            }
            //reff.child(String.valueOf(maxId + 1)).setValue(customers);
            //Intent intent = new Intent(this, AllDoneActivity.class);*/

            if (name == null) {
                customers.setName(" ");
            } else {
                customers.setName(name);
            }
            if (phone_Number == null) {
                customers.setPhone_Number(" ");
            } else {
                customers.setPhone_Number(phone_Number);
            }

            if ((productName == null)) {
                customers.setProductName(" ");
            } else {
                customers.setProductName(productName);
            }
            if (resName == null) {
                customers.setResName(" ");
            } else {
                customers.setResName(resName);
            }
            if (rest_Address == null) {
                customers.setRest_Address(" ");
            } else {
                customers.setRest_Address(rest_Address);
            }
            if (rest_Name == null) {
                customers.setResName(" ");
            } else {
                customers.setRest_Name(rest_Name);
            }

            if (roomNum == null) {
                customers.setRoomNum(" ");
            } else {
                customers.setRoomNum(roomNum);
            }
            if (blkNum == null) {
                customers.setBlkNum(" ");
            } else {
                customers.setBlkNum(blkNum);
            }
            if (productPrice == null) {
                customers.setProductPrice(" ");
            } else {
                customers.setProductPrice(productPrice);
            }
            if (orderNumbr == null) {
                customers.setOrderNumbr(" ");
            } else {
                customers.setOrderNumbr(orderNumbr);
            }

            if (addrs == null) {
                customers.setAddrs(" ");
            } else {
                customers.setAddrs(addrs);
            }

            if (hseNum == null) {
                customers.setHseNum(" ");
            } else {
                customers.setHseNum(hseNum);
            }
            if (String.valueOf(count) == null) {
                customers.setCount(" ");
            } else {
                customers.setCount(String.valueOf(count));
            }
            if (user_Address == null) {
                customers.setUser_Address(" ");
            } else {
                customers.setUser_Address(user_Address);
            }

            final DatabaseReference RootRef;
            RootRef = FirebaseDatabase.getInstance().getReference();

            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        maxId = (dataSnapshot.getChildrenCount());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (!(dataSnapshot.child("Customers").child(name).exists())) {
                        HashMap<String, Object> userdataMap = new HashMap<>();
                        userdataMap.put("Name", name);
                        userdataMap.put("Phone_Number", phone_Number);
                        userdataMap.put("Product_Name", productName);
                        userdataMap.put("Residnce Name", resName);
                        userdataMap.put("Room Number", roomNum);
                        userdataMap.put("Block Number", blkNum);
                        userdataMap.put("Order", orderNumbr);
                        userdataMap.put("Addrress", addrs);
                        userdataMap.put("House Number", hseNum);
                        userdataMap.put("User Address", user_Address);
                        userdataMap.put("Restaurant Address", rest_Address);
                        userdataMap.put("Restaurant Name", rest_Name);
                        userdataMap.put("Number of Rides", count);
                        userdataMap.put("Product Price", productPrice);


                        RootRef.child("Customers").child(name).updateChildren(userdataMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ConfirmFinalOrderActivity.this, "Driver Alerted.", Toast.LENGTH_SHORT).show();


                                            Intent intent = new Intent(ConfirmFinalOrderActivity.this, AllDoneActivity.class);
                                            startActivity(intent);

                                        } else {

                                            Toast.makeText(ConfirmFinalOrderActivity.this, "Network Error: Make sure you are connected to the internet...", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(ConfirmFinalOrderActivity.this, "This " + finalMessage + " already exists.", Toast.LENGTH_SHORT).show();

                        Toast.makeText(ConfirmFinalOrderActivity.this, "Please try again using another Email address.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ConfirmFinalOrderActivity.this, ConfirmFinalOrderActivity.class);
                        startActivity(intent);
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }
}




