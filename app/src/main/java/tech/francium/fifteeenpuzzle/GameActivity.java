package tech.francium.fifteeenpuzzle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;
import static tech.francium.fifteeenpuzzle.R.string.difficulty;

public class GameActivity extends AppCompatActivity {
    GridLayout mGameBoard;
    Button[][] buttonArray;
    int difficulty;

    Button left, up, right, down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setUp();
    }

    private void setUp(){
        Bundle extras = getIntent().getExtras();
        difficulty = (int)extras.getLong("difficulty");

        mGameBoard = (GridLayout)findViewById(R.id.gameBoard);
        buttonArray = new Button[difficulty][difficulty];

        mGameBoard.setRowCount(difficulty);
        mGameBoard.setColumnCount(difficulty);

        initButtonsAndBoard(difficulty);

        initControlButtons();


//        handleKeyPress(3);
//        handleKeyPress(3);
    }

    private void doSwap(int x,int y,int x1,int y1){
        CharSequence text1, text2;
        int visibility1, visibility2;

        text1 = buttonArray[x][y].getText();
        visibility1 = buttonArray[x][y].getVisibility();

        text2 = buttonArray[x1][y1].getText();
        visibility2 = buttonArray[x1][y1].getVisibility();

        buttonArray[x][y].setText(text2);
        buttonArray[x][y].setVisibility(visibility2);

        buttonArray[x1][y1].setText(text1);
        buttonArray[x1][y1].setVisibility(visibility1);
    }

    private int findEmptyIndex() {
        for(int i=0;i<difficulty;i++)
            for (int j=0;j<difficulty;j++)
                if(!(buttonArray[i][j].getVisibility() == Button.VISIBLE))
                    return i*difficulty + j;
        return 0;
    }

    private boolean areThingsInPlace(){
        for(int i=0;i<difficulty*difficulty-1;i++){
            String name = (buttonArray[i/difficulty][i%difficulty]).getText().toString();
            if(name!=null && !(name.equals(""+(i+1))))
                return false;
        }
        return true;
    }

    private void handleKeyPress(int keyCode) {
        int emptyIndex = findEmptyIndex();
        int x = emptyIndex/difficulty;
        int y = emptyIndex%difficulty;

        switch (keyCode) {
            case 1://LEFT KEY
                if(y==difficulty-1) return;
                doSwap(x,y,x,y+1);
                break;
            case 2://UP KEY
                if(x==difficulty-1) return;
                doSwap(x,y,x+1,y);
                break;
            case 3://RIGHT KEY
                if(y==0) return;
                doSwap(x,y,x,y-1);
                break;
            case 4://DOWN KEY
                if(x==0) return;
                doSwap(x,y,x-1,y);
                break;
        }

        if(areThingsInPlace()){
            Toast.makeText(GameActivity.this, "WIN", Toast.LENGTH_SHORT).show();
        }
    }


    private void initButtonsAndBoard(int difficulty) {
        List<Button> buttonList = new ArrayList<Button>();
        for(int i=0;i<difficulty*difficulty-1;i++){
            Button button = createButton(Integer.toString(i+1), Button.VISIBLE);
            buttonList.add(button);
        }
        Button button = createButton("", Button.INVISIBLE);
        buttonList.add(button); //Create an invisible button

        int index = 0;
        for(int i=0;i<difficulty;i++){
            buttonArray[i] = new Button[difficulty];
            for(int j=0;j<difficulty;j++){
                buttonArray[i][j] = buttonList.get(index++);
                mGameBoard.addView(buttonArray[i][j]);
            }
        }
    }

    private Button createButton(String name, int visibility){
        Button button = new Button(GameActivity.this);
        button.setLayoutParams(((Button)findViewById(R.id.modelButton)).getLayoutParams());
        button.setText(name);
        button.setVisibility(visibility);
        return button;
    }

    private void initControlButtons(){
        left = (Button) findViewById(R.id.buttonLeft);
        up = (Button) findViewById(R.id.buttonUp);
        right = (Button) findViewById(R.id.buttonRight);
        down = (Button) findViewById(R.id.buttonDown);

        ButtonListener listener = new ButtonListener();

        left.setOnClickListener(listener);
        up.setOnClickListener(listener);
        right.setOnClickListener(listener);
        down.setOnClickListener(listener);
    }

    class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v == left){
                Log.d("SOME TAG", "left pressed");
                handleKeyPress(1);
            }
            if(v == up){
                Log.d("SOME TAG", "up pressed");
                handleKeyPress(2);
            }
            if(v == right){
                Log.d("SOME TAG", "right pressed");
                handleKeyPress(3);
            }
            if(v == down){
                Log.d("SOME TAG", "down pressed");
                handleKeyPress(4);
            }
        }
    }
}
