package tech.francium.fifteeenpuzzle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import static android.view.View.Y;

public class MainActivity extends AppCompatActivity {

    Spinner mDifficultySelector;
    Button mGoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        mDifficultySelector = (Spinner) findViewById(R.id.difficultySelector);
        mGoButton = (Button) findViewById(R.id.goButton);

        ArrayAdapter<String> difficultyAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficulties));
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDifficultySelector.setAdapter(difficultyAdapter);

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("difficulty", mDifficultySelector.getSelectedItemId()+3);
                startActivity(intent);
            }
        });
    }
}
