package com.example.tic_tac_toe;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    int flag;
    Button[] buttons;
    String button_string[][] = new String[3][3];

    int count = 0;
    static String player = "X", opponent = "O";
    EditText player_, opponent_;

    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    String opponent_player, playing_player;

    private void init() {
        player_ = (EditText) findViewById(R.id.inputPlayerName);
        opponent_ = (EditText) findViewById(R.id.inputRivalName);

        button_1 = (Button) findViewById(R.id.button_1);
        button_1.setOnClickListener(this);

        button_2 = (Button) findViewById(R.id.button_2);
        button_2.setOnClickListener(this);

        button_3 = (Button) findViewById(R.id.button_3);
        button_3.setOnClickListener(this);

        button_4 = (Button) findViewById(R.id.button_4);
        button_4.setOnClickListener(this);

        button_5 = (Button) findViewById(R.id.button_5);
        button_5.setOnClickListener(this);

        button_6 = (Button) findViewById(R.id.button_6);
        button_6.setOnClickListener(this);

        button_7 = (Button) findViewById(R.id.button_7);
        button_7.setOnClickListener(this);

        button_8 = (Button) findViewById(R.id.button_8);
        button_8.setOnClickListener(this);

        button_9 = (Button) findViewById(R.id.button_9);
        button_9.setOnClickListener(this);

        buttons = new Button[]{button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9};
    }

    public void button_input() {
        opponent_player = opponent_.getText().toString();
        playing_player = player_.getText().toString();

        b1 = button_1.getText().toString();
        b2 = button_2.getText().toString();
        b3 = button_3.getText().toString();
        b4 = button_4.getText().toString();
        b5 = button_5.getText().toString();
        b6 = button_6.getText().toString();
        b7 = button_7.getText().toString();
        b8 = button_8.getText().toString();
        b9 = button_9.getText().toString();

        button_string = new String[][]{{b1, b2, b3}, {b4, b5, b6}, {b7, b8, b9}};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void play_machine() {
        button_input();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Log.d("msg",button_string[i][j]);
            }

        }
        Move move = findBestMove(button_string);
        int index = (move.row * 3) + move.col;
        Log.d("msg", String.valueOf(move.col));
        Log.d("msg", String.valueOf(move.row));
        if (buttons[index].getText().equals("")){
            buttons[index].setText("O");
        }
    }

    @Override
    public void onClick(View view) {
        try {
            button_input();
            Button btnCurrant = (Button) view;
            opponent_.setText(opponent_player.equals("") ? "Machine" : opponent_player);
            button_input();
            if (flag == 0 && !btnCurrant.getText().equals("O") && !playing_player.equals("")) {
                btnCurrant.setText("X");
                Toast.makeText(MainActivity.this, "Now Play " + opponent_player, Toast.LENGTH_SHORT).show();
                flag = 1;
                count = count + 1;
                if (opponent_player.equals("Machine")) {
                    play_machine();
                    Toast.makeText(MainActivity.this, "Now Play " + playing_player, Toast.LENGTH_SHORT).show();
                    flag = 0;
                    count = count + 1;
                }
            } else if (flag == 1 && !btnCurrant.getText().equals("X") && !opponent_player.equals("Machine")) {
                btnCurrant.setText("O");
                Toast.makeText(MainActivity.this, "Now Play " + playing_player, Toast.LENGTH_SHORT).show();
                flag = 0;
                count = count + 1;
            }
        } catch (Exception e) {
            // Handle exceptions here
        }
        if (count > 4) {
            button_input();
            if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")) {
                Toast.makeText(MainActivity.this, "winner is :" + (b1.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")) {
                Toast.makeText(this, "winner is :" + (b4.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")) {
                Toast.makeText(this, "winner is :" + (b7.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")) {
                Toast.makeText(this, "winner is :" + (b1.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")) {
                Toast.makeText(this, "winner is :" + (b2.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")) {
                Toast.makeText(this, "winner is :" + (b3.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b1.equals(b5) && b5.equals(b9) && !b5.equals("")) {
                Toast.makeText(this, "winner is :" + (b5.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")) {
                Toast.makeText(this, "winner is :" + (b3.equals("X") ? playing_player : opponent_player), Toast.LENGTH_SHORT).show();
                flag = 2;
            } else if (count == 9) {
                Toast.makeText(this, "Game got tie", Toast.LENGTH_SHORT).show();
            }
        }
    }


    static Boolean isMovesLeft(String board[][])
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j].equals(""))
                    return true;
        return false;
    }
    static int evaluate(String b[][])
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0].equals(b[row][1]) &&
                    b[row][1].equals(b[row][2]))
            {
                if (b[row][0].equals(player))
                    return +10;
                else if (b[row][0].equals(opponent))
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col].equals(b[1][col]) &&
                    b[1][col].equals(b[2][col]))
            {
                if (b[0][col].equals(player))
                    return +10;

                else if (b[0][col].equals(opponent))
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0].equals(b[1][1]) && b[1][1].equals( b[2][2]))
        {
            if (b[0][0].equals(player))
                return +10;
            else if (b[0][0].equals(opponent))
                return -10;
        }

        if (b[0][2].equals(b[1][1]) && b[1][1].equals(b[2][0]))
        {
            if (b[0][2].equals(player))
                return +10;
            else if (b[0][2].equals(opponent))
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    static int minimax(String board[][],
                       int depth, Boolean isMax)
    {
        int score = evaluate(board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie

        if (isMovesLeft(board).equals(false))
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j].equals(""))
                    {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "";
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    // Check if cell is empty
                    if (board[i][j].equals(""))
                    {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = "";
                    }
                }
            }
            return best;
        }
    }
    static class Move
    {
        int row, col;
    };

    static Move findBestMove(String board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                // Check if cell is empty
                if (board[i][j].equals(""))
                {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = "";

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);

        return bestMove;
    }




}