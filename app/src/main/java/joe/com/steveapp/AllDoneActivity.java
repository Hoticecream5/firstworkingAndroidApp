package joe.com.steveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllDoneActivity extends AppCompatActivity {

    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_done);
        finishButton = findViewById(R.id.finish_btn);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allDone();
            }
        });

    }

    public void allDone(){

        Intent intent = new Intent(AllDoneActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}
