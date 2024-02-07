package com.zybooks.tictactoe;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //  Megan Lesch
    //  Don Parker
    //  ISYS 221 - VL1
    //  Pair Programming Assignment #2 - Tic, Tac, Toe Application
    //  Due: 10/8/2023

    private TicTacToe mGame;
    private GridLayout mGameGrid;
    ImageView TurnKeeper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameGrid = findViewById(R.id.game_grid);
        TurnKeeper = findViewById(R.id.imageViewTurn);

        // Add the same click handler to all grid buttons
        for (int buttonIndex = 0; buttonIndex < mGameGrid.getChildCount(); buttonIndex++) {
            ImageButton gridButton = (ImageButton) mGameGrid.getChildAt(buttonIndex);
            gridButton.setOnClickListener(this::onChooseSquareClick);
        }

        mGame = new TicTacToe();
        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Determine which menu option was selected
        if (item.getItemId() == R.id.action_newGame) {
            startGame();

        }
        else if (item.getItemId() == R.id.action_quit) {
            showQuitConfirmationDialog(); // Show the quit confirmation dialog
        }

        return super.onOptionsItemSelected(item);
    }

    private void startGame() {
        mGame.newGame();
        setSymbol();
    }

    private void onChooseSquareClick(View view) {

        // Find the button's row and col
        int buttonIndex = mGameGrid.indexOfChild(view);
        int row = buttonIndex / TicTacToe.GRID_SIZE;
        int col = buttonIndex % TicTacToe.GRID_SIZE;

        ImageButton gridButton = (ImageButton) mGameGrid.getChildAt(buttonIndex);

        if (mGame.isSquare(row, col) == 0 && mGame.mTurn() < 10) {

            mGame.selectSquare(row, col);

            if (mGame.isSquare(row, col) % 2 == 1) {
                gridButton.setImageResource(R.drawable.x_symbol);
                if (!mGame.isGameOver()){
                    TurnKeeper.setImageResource(R.drawable.o_symbol);
                }
            }
            if (mGame.isSquare(row, col) % 2 == 0) {
                gridButton.setImageResource(R.drawable.o_symbol);
                if (!mGame.isGameOver()){
                    TurnKeeper.setImageResource(R.drawable.x_symbol);
                }
            }
            // Congratulate the user if the game is over
            if (mGame.isGameOver()) {
                mGame.noTurns();
                if (mGame.did_xWin()){
                    Toast.makeText(this, "Congratulations X Wins!", Toast.LENGTH_LONG).show();
                }
                else if (mGame.did_oWin()){
                    Toast.makeText(this, "Congratulations O Wins!", Toast.LENGTH_LONG).show();
                }
                else if (mGame.didCatGame()){
                    Toast.makeText(this, "CAT GAME!", Toast.LENGTH_LONG).show();
                }
            }
            mGame.nextTurn();

        }

    }

    private void setSymbol() {

        for (int buttonIndex = 0; buttonIndex < mGameGrid.getChildCount(); buttonIndex++) {
            ImageButton gridButton = (ImageButton) mGameGrid.getChildAt(buttonIndex);

            // Find the button's row and col
            int row = buttonIndex / TicTacToe.GRID_SIZE;
            int col = buttonIndex % TicTacToe.GRID_SIZE;

            if (mGame.isSquare(row, col) == 0) {
                gridButton.setImageResource(R.drawable.empty_square);
            }
            else if (mGame.isSquare(row, col) % 2 == 1 ) {
                gridButton.setImageResource(R.drawable.x_symbol);
            }
            else if (mGame.isSquare(row, col) % 2 == 0 ) {
                gridButton.setImageResource(R.drawable.o_symbol);
            }

        }
        TurnKeeper.setImageResource(R.drawable.x_symbol);
    }

    private void showQuitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to quit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // This will close the application
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing, just close the dialog
            }
        });
        builder.create().show();
    }

}