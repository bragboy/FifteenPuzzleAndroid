package tech.francium.fifteeenpuzzle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;

public class GameActivity extends AppCompatActivity {


    GridLayout mGameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUp();
    }

    private void setUp(){
        mGameBoard = (GridLayout)findViewById(R.id.gameBoard);

        Bundle extras = getIntent().getExtras();
        int difficulty = (int)extras.getLong("difficulty");

        mGameBoard.setRowCount(difficulty);
        mGameBoard.setColumnCount(difficulty);

        for(int i=0;i<difficulty*difficulty-1;i++){
            Button button = new Button(GameActivity.this);
            button.setLayoutParams(((Button)findViewById(R.id.modelButton)).getLayoutParams());
            button.setText(Integer.toString(i+1));
            mGameBoard.addView(button);
        }
    }


}
