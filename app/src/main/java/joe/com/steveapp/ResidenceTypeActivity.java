package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResidenceTypeActivity extends AppCompatActivity {

    private Button universityBtn, otherBtn;
    String prdctName,prdctPrce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence_type);

        universityBtn = findViewById(R.id.university_btn);
        otherBtn = findViewById(R.id.not_university_house);

        Bundle bundle = getIntent().getExtras();

        prdctName = bundle.getString("name");
        prdctPrce = bundle.getString("price");


        universityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ResidenceTypeActivity.this, ResidenceInformationActivity.class);
                intent.putExtra("name",prdctName );
                intent.putExtra("price",prdctPrce);
                startActivity(intent);
            }
        });

       otherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ResidenceTypeActivity.this, HouseInformationActivity.class);
                intent.putExtra("name",prdctName );
                intent.putExtra("price",prdctPrce);
                startActivity(intent);
            }
        });
    }

}
