package com.example.puzzles_remed;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int SIZE = 4;

    private Button[][] buttons;
    private String[] arrHuruf = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", "N", "O", " "};


    private void populateButton(){
        buttons = new Button[SIZE][SIZE];
        buttons[0][0] = findViewById(R.id.button_a);
        buttons[0][1] = findViewById(R.id.button_b);
        buttons[0][2] = findViewById(R.id.button_c);
        buttons[0][3] = findViewById(R.id.button_d);
        buttons[1][0] = findViewById(R.id.button_e);
        buttons[1][1] = findViewById(R.id.button_f);
        buttons[1][2] = findViewById(R.id.button_g);
        buttons[1][3] = findViewById(R.id.button_h);
        buttons[2][0] = findViewById(R.id.button_i);
        buttons[2][1] = findViewById(R.id.button_j);
        buttons[2][2] = findViewById(R.id.button_k);
        buttons[2][3] = findViewById(R.id.button_l);
        buttons[3][0] = findViewById(R.id.button_m);
        buttons[3][1] = findViewById(R.id.button_n);
        buttons[3][2] = findViewById(R.id.button_o);
        buttons[3][3] = findViewById(R.id.button_p);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    private boolean cekBerhasil(){
        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                String current = buttons[i][j].getText().toString();
                String answer = arrHuruf[i * SIZE + j];
                if(!current.equalsIgnoreCase(answer)){
                    return false;
                }
            }
        }
        return true;
    }

    private void tampilkanStatus(){
        if(cekBerhasil()){
            Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show();
        }
    }

    private void shufflePos(){
        List<String> alHuruf = Arrays.asList(arrHuruf);
        Collections.shuffle(alHuruf);
        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                buttons[i][j].setText(alHuruf.get(i*SIZE+j));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_shuffle){
            shufflePos();
        } else {
            finish();
            System.exit(0);
        }
        return true;
    }

    private void swapKar(Point curPosition, Point destPosition, String kar){
        if(destPosition != null) {
            buttons[destPosition.x][destPosition.y].setText(kar);
            buttons[curPosition.x][curPosition.y].setText(" ");
        }
    }

    private Point getPosition(int id){
        for (int i = 0; i<SIZE; i++){
            for (int j = 0; j<SIZE; j++){
                if (buttons[i][j].getId() == id){
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public void klik(View view){
        Point point = getPosition(view.getId());
        if (point == null) return;

        String karCurrent = buttons[point.x][point.y].getText().toString();
        if (karCurrent.equals(" ")){
            return;
        }

        Point position = null;
        if (point.x-1 >= 0 && buttons[point.x-1][point.y].getText().equals(" "))
            position = new Point(point.x-1, point.y);
        else if (point.x+1 < SIZE && buttons[point.x+1][point.y].getText().equals(" "))
            position = new Point(point.x+1, point.y);
        else if (point.y-1 >= 0 && buttons[point.x][point.y-1].getText().equals(" "))
            position = new Point(point.x, point.y-1);
        else if (point.y+1 < SIZE && buttons[point.x][point.y+1].getText().equals(" "))
            position = new Point(point.x, point.y+1);
        swapKar(point, position, karCurrent);

        if(point.x == SIZE-1 && point.y == SIZE-1){
            tampilkanStatus();
        }

    }

}