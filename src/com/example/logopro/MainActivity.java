package com.example.logopro;

import java.util.ArrayList;
import java.util.Collections;

import android.os.AsyncTask;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


@SuppressLint("ParserError")
public class MainActivity extends Activity implements OnClickListener{

	String[] company_name = new String[20];
	TypedArray company_picture;
	String actual_company;
	
	int selectedCompanyButton = 0;
	Button mainCompanyButton;
	
	//Liste mit den zufällig gemischten zahlen von 0-19
	ArrayList<Integer> randomArray = new ArrayList<Integer>();
	int zaehler = 0;					//geht die Liste pro Spiel um 4 hoch, dadurch immer andere Firmen
	int zaehler_picture_company = 0;	//wählt eine der 4 angezeigten Firmen aus und zeigt diese an
	int punkte = 0;		
	int runde = 1;
	
	ImageView imageView1;
		
	//4 Auswhl Buttons am Bildschirmboden
	Button[] selectButton = new Button[4];
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView1 = (ImageView)findViewById(R.id.imageView_1);                   
        company_name = getResources().getStringArray(R.array.company_name);
        company_picture = getResources().obtainTypedArray(R.array.company_picture);
     
        selectButton[0] = (Button)findViewById(R.id.button_1);
        selectButton[1] = (Button)findViewById(R.id.button_2);
        selectButton[2] = (Button)findViewById(R.id.button_3);        
        selectButton[3] = (Button)findViewById(R.id.button_4);       		
		
        selectButton[0].setOnClickListener(this);
        selectButton[1].setOnClickListener(this);
        selectButton[2].setOnClickListener(this);
        selectButton[3].setOnClickListener(this);	
		
        //Zufallsarray erzeugen
        for (int i=0; i<20; i++) {
        	randomArray.add(i);
        }
        Collections.shuffle(randomArray);
        
        updateView();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);       
        return true;
    }

	public void onClick(View v) {
		new ImageChange().execute(v, imageView1);		
	}
	
	public void updateView () {	
		Toast.makeText(this, "Du hast aktuell " + Integer.toString(punkte) + " Punkte.", Toast.LENGTH_SHORT).show();
		selectedCompanyButton = (int)((Math.random()*4)+0.2);
		for (int i=0; i<4; i++) {
			if (selectedCompanyButton == i) {
				imageView1.setImageDrawable(company_picture.getDrawable(randomArray.get(zaehler)));
			}
			selectButton[i].setText(company_name[randomArray.get(zaehler)]);
			zaehler++;
		}	
	
	}

	class ImageChange extends AsyncTask<View, Void, String> {
		Button button;
		ImageView imageView;
		@Override
		protected String doInBackground(View... v) {
			button = (Button)v[0];
			imageView = (ImageView)v[1];
			switch (v[0].getId()){		
			case R.id.button_1:
				if(selectedCompanyButton == 0)
					return "richtig";	
				else
					return "falsch";
			case R.id.button_2:
				if(selectedCompanyButton == 1)
					return "richtig";	
				else
					return "falsch";	
			case R.id.button_3:
				if(selectedCompanyButton == 2)
					return "richtig";	
				else
					return "falsch";	
			case R.id.button_4:
				if(selectedCompanyButton == 3)
					return "richtig";	
				else
					return "falsch";	
			default:
				return null;
			}	
		}

		@Override
		protected void onPostExecute(String result) {			
			if(result == "richtig")
				punkte++;
			if(runde != 5) {
				runde++;
				updateView();
			}
			
			super.onPostExecute(result);
		}
	}
}
