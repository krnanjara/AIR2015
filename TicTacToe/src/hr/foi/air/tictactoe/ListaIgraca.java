package hr.foi.air.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaIgraca extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.lista_igraca);

		SharedPreferences igraci = getSharedPreferences("Lista", 0);

		Map<String, ?> svaImena = igraci.getAll();
		
		ArrayList<Pair<String, Integer>> topListaIgraca = new ArrayList<Pair<String, Integer>>();
		
		//Dohvati sve zapise iz spremnika
		for (Map.Entry<String, ?> jedanIgrac : svaImena.entrySet()) {						
			topListaIgraca.add(new Pair<String, Integer>(jedanIgrac.getKey(), (Integer)jedanIgrac.getValue()));			
		}
		
		//Sortiranje
		Collections.sort(topListaIgraca,new praviloUsporedbe());
		
		//Priprema za ispis
		ArrayList<String> ispis = new ArrayList<String>();
		int i = 1;
		for(Pair<String, Integer> p : topListaIgraca){
			ispis.add(i + ": ["+p.second+"] "+p.first);
			i++;
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ispis );
		
		ListView lv= (ListView) findViewById(R.id.lvListaIgraca);
		lv.setAdapter(adapter);	
	}
	
	class praviloUsporedbe implements Comparator<Pair<String, Integer>> {

		@Override
		public int compare(Pair<String, Integer> a, Pair<String, Integer> b) {			
			if(a.second > b.second)
				return -1;
			else if (a.second<b.second)
				return 1;
			else
				return 0;
		}
		
	}
}
