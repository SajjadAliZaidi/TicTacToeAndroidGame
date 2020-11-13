package com.smsaz.class9_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button[] buttonsGameCells = new Button[9];
    int numberOfCells = 9;
    char playerTurn = 'X';
    boolean gameOver = false;
    TextView textViewPlayerTurn;
    TextView textViewGameStatus;
    Button buttonResetGame;
    boolean gameWon = false;
    int numberOfOccupiedCells = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerTurn = findViewById(R.id.textViewPlayerTurn);
        textViewGameStatus = findViewById(R.id.textViewGameStatus);
        buttonResetGame = findViewById(R.id.buttonResetGame);

        buttonResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // change textViews
                textViewPlayerTurn.setText("Turn: X");
                textViewGameStatus.setText("IN PROGRESS");

                // change cells
                for(int i = 0; i < numberOfCells; i++){
                    buttonsGameCells[i].setText("");
                }

                // reset booleans to false
                gameOver = gameWon = false;

                // reset helping variables
                playerTurn = 'X';
                numberOfOccupiedCells = 0;
            }
        });

        // id example: buttonCell4
        for (int i = 0; i < 9; i++) {
            int resId = getResources().getIdentifier("buttonCell" + i, "id", getPackageName());
            final Button button = findViewById(resId);
            buttonsGameCells[i] = button;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (button.getText().equals("") && !gameOver) {
                        button.setText(Character.toString(playerTurn));
                        numberOfOccupiedCells++;
                        gameWon = checkPlayerWin();
                        if (gameWon){
                            gameOver = true;
                            Toast.makeText(MainActivity.this, "YOU WON Player: " + playerTurn,
                                    Toast.LENGTH_SHORT).show();
                            textViewGameStatus.setText("GAME OVER!");
                            textViewPlayerTurn.setText("\'" + Character.toString(playerTurn) + "\' has won!");
                        }
                        else if(!gameOver)
                            changePlayer();
                    }
                }
            });
        }

    }

    private boolean checkPlayerWin() {
        // returns true if someone has won or game is a tie
        // TODO: 11/11/2020 distinguish b/w tie and game win

        // Checking horizontal cells / rows for a match
        for (int i = 0; i < numberOfCells; i += 3) {
            boolean matched = true;
            if (!buttonsGameCells[i].getText().equals("")) {
                for (int j = i + 1; j < i + 3 && matched; j++) {
                    if (!buttonsGameCells[i].getText().equals(buttonsGameCells[j].getText()) ||
                            buttonsGameCells[j].getText().equals("")) {
                        matched = false;
                    }
                }
                if (matched) {
                    gameWon = true;
                    return true;
                }
            }
        }

        // Checking vertical cells / columns for a match
        for (int i = 0; i < 3; i++) {
            boolean matched = true;
            if (!buttonsGameCells[i].getText().equals("")) {
                for (int j = i + 3; j < numberOfCells && matched; j += 3) {
                    if (!buttonsGameCells[i].getText().equals(buttonsGameCells[j].getText()) ||
                            buttonsGameCells[j].getText().equals("")) {
                        matched = false;
                    }
                }
                if (matched) {
                    gameWon = true;
                    return true;
                }
            }
        }

        // Checking diagonals for a match
        if (!buttonsGameCells[0].getText().equals("")) {
            boolean matched = true;
            for (int j = 4; j < numberOfCells && matched; j += 4) {
                if (!buttonsGameCells[j].getText().equals(buttonsGameCells[0].getText()) ||
                        buttonsGameCells[j].getText().equals(""))
                    matched = false;
            }
            if(matched){
                gameWon = true;
                return true;
            }
        }

        if (!buttonsGameCells[2].getText().equals("")) {
            boolean matched = true;
            for (int j = 4; j < (numberOfCells-1) && matched; j += 2) {
                if (!buttonsGameCells[j].getText().equals(buttonsGameCells[2].getText()) ||
                        buttonsGameCells[j].getText().equals(""))
                    matched = false;
            }
            if(matched){
                gameWon = true;
                return true;
            }
        }

        if(numberOfOccupiedCells == numberOfCells){
            textViewGameStatus.setText("GAME OVER");
            textViewPlayerTurn.setText("It's a TIE");
            gameOver = true;
            Toast.makeText(MainActivity.this, "It's a TIE !!",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void changePlayer() {
        if (playerTurn == 'X'){
            playerTurn = 'O';
            textViewPlayerTurn.setText("Turn: O");
        }
        else{
            playerTurn = 'X';
            textViewPlayerTurn.setText("Turn: X");
        }
    }
}