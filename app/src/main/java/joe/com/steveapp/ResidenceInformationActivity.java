package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResidenceInformationActivity extends AppCompatActivity {


private EditText residenceName, blockNumber, inputRoomNumber;
private Button proceed;
private ProgressDialog loadingBar;
    String prdctName,prdctPrce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_information);

        proceed = findViewById(R.id.res_info_btn);
        residenceName =  findViewById(R.id.residence_name);
        blockNumber =  findViewById(R.id.block_number);
        inputRoomNumber =  findViewById(R.id.room_number_input);
        loadingBar = new ProgressDialog(this);

        Bundle bundle = getIntent().getExtras();

        prdctName = bundle.getString("name");
        prdctPrce = bundle.getString("price");

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                confirmResidenceInform();
            }
        });

    }

    private void confirmResidenceInform()
    {
        String resName = residenceName.getText().toString();
        String blckNmber = blockNumber.getText().toString();
        String roomNumber = inputRoomNumber.getText().toString();

        if (TextUtils.isEmpty(resName))
        {
            Toast.makeText(this, "Please write your address...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(blckNmber))
        {
            Toast.makeText(this, "Please write your house number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(roomNumber))
        {
            Toast.makeText(this, "Please write your room number...", Toast.LENGTH_SHORT).show();
        }

        else
        {
            loadingBar.setTitle("Processing");
            loadingBar.setMessage("Please wait, validating information.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Intent intent = new Intent(ResidenceInformationActivity.this, ConfirmFinalOrderActivity.class);
            intent.putExtra("blockNumber",blckNmber);
            intent.putExtra("res_Name",resName);
            intent.putExtra("room_Num",roomNumber);
            intent.putExtra("name",prdctName );
            intent.putExtra("price",prdctPrce);
            startActivity(intent);

        }
    }
}
