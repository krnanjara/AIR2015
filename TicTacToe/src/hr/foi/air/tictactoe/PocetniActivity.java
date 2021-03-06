package hr.foi.air.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PocetniActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PocetniFragment())
                    .commit();
        }
        inicijalizirajPostavke();
    }
    
    /**
     * Inicijalizira postavke koje ce se koristiti u aplikaciji za
     * prosljedjivanje parametara medju fragmentima tokom rada
     * aplikacije
     */
    private void inicijalizirajPostavke() {
    	if(getIntent().getExtras() == null) { // ne postoje jos, pa ce biti kreirane
        	Bundle radniParametri = new Bundle();
            radniParametri.putString(
            		getResources().getString(R.string.parametarKreirano),
            		getResources().getString(R.string.parametarIstina));
            getIntent().putExtras(radniParametri);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pocetni, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
