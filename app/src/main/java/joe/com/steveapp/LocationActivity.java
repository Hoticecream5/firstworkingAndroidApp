package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocationActivity extends AppCompatActivity {

    private Button localBtn, notLocalBtn;
    String prdctName,prdctPrce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        localBtn = findViewById(R.id.local_area_btn);
        notLocalBtn = findViewById(R.id.not_local_btn);


        Bundle bundle = getIntent().getExtras();

        prdctName = bundle.getString("name");
        prdctPrce = bundle.getString("price");



        localBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               localBtn();
            }
        });

        notLocalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                notLocalBtn();
            }
        });
    }

    public void localBtn(){
        Intent intent = new Intent(LocationActivity.this, ResidenceTypeActivity.class);
        intent.putExtra("name",prdctName );
        intent.putExtra("price",prdctPrce);

        startActivity(intent);
    }

    public void notLocalBtn(){
        Intent intent = new Intent(LocationActivity.this, HouseInformationActivity.class);
        intent.putExtra("name",prdctName );
        intent.putExtra("price",prdctPrce);
        startActivity(intent);
    }

}
