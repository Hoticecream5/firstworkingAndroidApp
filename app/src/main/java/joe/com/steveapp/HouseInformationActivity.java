package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class HouseInformationActivity extends AppCompatActivity {

    private Button proceed;
    private EditText inputPhoneNumber, inputAddress, inputHouseNumber;
    private ProgressDialog loadingBar;
    String prdctName,prdctPrce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_information);

        proceed = findViewById(R.id.house_info_btn);
        inputAddress =  findViewById(R.id.house_address_input);
        inputHouseNumber =  findViewById(R.id.house_number_input);
        inputPhoneNumber =  findViewById(R.id.house_phone_input);
        loadingBar = new ProgressDialog(this);

        Bundle bundle = getIntent().getExtras();

        prdctName = bundle.getString("name");
        prdctPrce = bundle.getString("price");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                confirmHouseInform();
            }
        });

    }

    private void confirmHouseInform()
    {
        String address = inputAddress.getText().toString();
        String houseNumber = inputHouseNumber.getText().toString();

        if (TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(houseNumber))
        {
            Toast.makeText(this, "Please write your house number...", Toast.LENGTH_SHORT).show();
        }
         else
        {
            loadingBar.setTitle("Processing");
            loadingBar.setMessage("Please wait, validating information.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Intent intent = new Intent(HouseInformationActivity.this, ConfirmFinalOrderActivity.class);
            intent.putExtra("address",address);
            intent.putExtra("house_Number",houseNumber);
            intent.putExtra("name",prdctName );
            intent.putExtra("price",prdctPrce);
            startActivity(intent);

        }
    }

}
