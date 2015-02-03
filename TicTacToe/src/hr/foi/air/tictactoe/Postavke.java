package hr.foi.air.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Postavke extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.postavke);
		
		Button b = (Button) findViewById(R.id.btnZapocni);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		Spinner tipIgre = (Spinner) findViewById(R.id.sTipIgre);
		EditText ime1 = (EditText) findViewById(R.id.etIgrac1);
		EditText ime2 = (EditText) findViewById(R.id.etIgrac2);
		
		String ime1_tekst = ime1.getText().toString();
		String ime2_tekst = ime2.getText().toString();
		
		if(ime1_tekst.isEmpty() || ime2_tekst.isEmpty()){
			Toast.makeText(this, "Niste upisali imena igraca", Toast.LENGTH_SHORT).show();
		}
		else {			
			SharedPreferences podaci = this.getSharedPreferences("Postavke", 0);
			SharedPreferences.Editor editor = podaci.edit();
			editor.putInt("tipIgre", tipIgre.getSelectedItemPosition());
			editor.putString("ime1", ime1_tekst);
			editor.putString("ime2", ime2_tekst);
			editor.commit();
			
			SharedPreferences prefs = getSharedPreferences("backup", 0);
			editor = prefs.edit();
			editor.clear();
			editor.commit();
			
			Intent i = new Intent("hr.foi.air.tictactoe.IGRA");
			startActivity(i);					
		}	
	}	
}
