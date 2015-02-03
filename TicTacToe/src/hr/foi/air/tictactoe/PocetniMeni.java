package hr.foi.air.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

public class PocetniMeni extends Activity implements OnClickListener, OnItemSelectedListener {

	Button btnStart, btnLista, btnIzlaz;
	Spinner sTema;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.pocetna);

		btnStart = (Button) findViewById(R.id.btnStart);
		btnLista = (Button) findViewById(R.id.btnListaIgraca);
		btnIzlaz = (Button) findViewById(R.id.btnIzlaz);
		
		btnStart.setOnClickListener(this);
		btnLista.setOnClickListener(this);
		btnIzlaz.setOnClickListener(this);		

		sTema = (Spinner) findViewById(R.id.sTema);
		sTema.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnStart:
				Intent i1 = new Intent("hr.foi.air.tictactoe.POSTAVKE");
				startActivity(i1);
				break;
				
			case R.id.btnListaIgraca:
				Intent i2 = new Intent("hr.foi.air.tictactoe.LISTAIGRACA");
				startActivity(i2);
				break;

			case R.id.btnIzlaz:
				finish();
				break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int idTeme, long arg3) {
		SharedPreferences podaci = this.getSharedPreferences("Postavke", 0);
		SharedPreferences.Editor editor = podaci.edit();
		editor.putInt("tema", idTeme);
		editor.commit();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) { }

}
