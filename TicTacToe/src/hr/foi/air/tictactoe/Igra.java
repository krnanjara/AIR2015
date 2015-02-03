package hr.foi.air.tictactoe;

import hr.foi.air.tictactoe.logika.AbstractLogikaIgre;
import hr.foi.air.tictactoe.logika.Igra1;
import hr.foi.air.tictactoe.logika.Igra2;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Igra extends Activity implements OnClickListener {

	Button btnIgra1, btnIgra2, btnIgra3, btnIgra4, btnIgra5, btnIgra6, btnIgra7, btnIgra8, btnIgra9;

	AbstractLogikaIgre igra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.igra);

		btnIgra1 = (Button) findViewById(R.id.btnIgra1);
		btnIgra2 = (Button) findViewById(R.id.btnIgra2);
		btnIgra3 = (Button) findViewById(R.id.btnIgra3);
		btnIgra4 = (Button) findViewById(R.id.btnIgra4);
		btnIgra5 = (Button) findViewById(R.id.btnIgra5);
		btnIgra6 = (Button) findViewById(R.id.btnIgra6);
		btnIgra7 = (Button) findViewById(R.id.btnIgra7);
		btnIgra8 = (Button) findViewById(R.id.btnIgra8);
		btnIgra9 = (Button) findViewById(R.id.btnIgra9);

		btnIgra1.setOnClickListener(this);
		btnIgra2.setOnClickListener(this);
		btnIgra3.setOnClickListener(this);
		btnIgra4.setOnClickListener(this);
		btnIgra5.setOnClickListener(this);
		btnIgra6.setOnClickListener(this);
		btnIgra7.setOnClickListener(this);
		btnIgra8.setOnClickListener(this);
		btnIgra9.setOnClickListener(this);

		SharedPreferences podaci = getSharedPreferences("Postavke", 0);
		int tema = podaci.getInt("tema", 0);

		if (tema == 0) {
			igra = new Igra1(this, btnIgra1, btnIgra2, btnIgra3, btnIgra4, btnIgra5, btnIgra6, btnIgra7, btnIgra8, btnIgra9);
		} else {
			igra = new Igra2(this, btnIgra1, btnIgra2, btnIgra3, btnIgra4, btnIgra5, btnIgra6, btnIgra7, btnIgra8, btnIgra9);
		}
	}

	@Override
	public void onClick(View v) {
		igra.onClick(v);
	}

	@Override
	public void onPause() {
		super.onPause();

		char[][] stanjeIgre = igra.getStanjeIgre();
		String trenuntiZnak = igra.getTrenutniZnak();
		
		String stanje = "";
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				stanje += stanjeIgre[i][j];

		SharedPreferences prefs = getSharedPreferences("backup", 0);
		Editor editor = prefs.edit();
		try {
			editor.putString("stanje", stanje);
			editor.putString("znak", trenuntiZnak);
		} catch (Exception e) {
			e.printStackTrace();
		}
		editor.commit();
	}

	@Override
	public void onResume() {
		super.onResume();
		
		char[][] stanjeIgre = new char[3][3];
		String trenuntiZnak = "";
		
		String stanje = "";		

		SharedPreferences prefs = getSharedPreferences("backup", 0);
		try {
			stanje = prefs.getString("stanje", "");
			trenuntiZnak = prefs.getString("znak", "");
			Editor editor = prefs.edit();
			editor.clear();
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (stanje.isEmpty() == false) {
			int index = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					stanjeIgre[i][j] = stanje.charAt(index);
					index++;
				}
			}
			igra.setStanjeIgre(stanjeIgre);
			igra.setTrenutniZnak(trenuntiZnak);
		}
	}	
}
