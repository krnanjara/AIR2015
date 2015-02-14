package hr.foi.air.tictactoe;

import hr.foi.air.tictactoe.model.Igrac;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PostavkeFragment extends Fragment implements OnClickListener{
	
	public PostavkeFragment() { } // konstruktor prazan prema uputi Fragment dokumentacije
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_postavke, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button btnZapocni = (Button) getActivity().findViewById(R.id.btnZapocni);
		btnZapocni.setOnClickListener(this);
		
		napuniSpinnereIgracima();
	}
	
	/**
	 * Popunjava spinnere s imenima igraca
	 */
	private void napuniSpinnereIgracima() {
		List<String> igraciUBazi = Igrac.popisIgraca();
		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, igraciUBazi);
		
		Spinner spPrviIgrac = (Spinner) getActivity().findViewById(R.id.spPrviIgrac);
		Spinner spDrugiIgrac = (Spinner) getActivity().findViewById(R.id.spDrugiIgrac);
		spPrviIgrac.setAdapter(adapter);
		spDrugiIgrac.setAdapter(adapter);
		
		ucitajOdaberiIgraceOdZadnjiPut();
	}
	
	/**
	 * Provjerava jesu li odabrani igraci dozvoljeni. Npr. nije dozvoljeno igrati sam protiv sebe
	 * @param prviIgrac koji je odabran (X)
	 * @param drugiIgrac koji je odabran (O)
	 * @return true ako je sve ok, inace false
	 */
	private boolean provjeriOdabraneIgrace(String prviIgrac, String drugiIgrac) {
		if(prviIgrac.equals(drugiIgrac) == true) {
			return false;
		}
		if(prviIgrac.equals(getResources().getString(R.string.racunaloLagano)) == true &&
				drugiIgrac.equals(getResources().getString(R.string.racunaloTesko)) == true) {
			return false;
		}
		if(prviIgrac.equals(getResources().getString(R.string.racunaloTesko)) == true &&
				drugiIgrac.equals(getResources().getString(R.string.racunaloLagano)) == true) {
			return false;
		}
		return true;
	}
	
	/**
	 * Odabire u spinnerima igrace od zadnje igre (ukoliko postoji zapis)
	 */
	private void ucitajOdaberiIgraceOdZadnjiPut() {
		SharedPreferences prefs = getActivity().getSharedPreferences(getResources().getString(R.string.sharedPrefRjecnikIgraci), 0);
		String prviIgrac = prefs.getString(getResources().getString(R.string.parametarIgracPrvi), getResources().getString(R.string.racunaloTesko));
		String drugiIgrac = prefs.getString(getResources().getString(R.string.parametarIgracDrugi), getResources().getString(R.string.racunaloTesko));
		Spinner spPrviIgrac = (Spinner) getActivity().findViewById(R.id.spPrviIgrac);
		Spinner spDrugiIgrac = (Spinner) getActivity().findViewById(R.id.spDrugiIgrac);
		for(int i = 0; i < spPrviIgrac.getCount(); i++) {
			if(spPrviIgrac.getItemAtPosition(i).toString().equals(prviIgrac) == true) {
				spPrviIgrac.setSelection(i);
				break;
			}
		}
		for(int i = 0; i < spDrugiIgrac.getCount(); i++) {
			if(spDrugiIgrac.getItemAtPosition(i).toString().equals(drugiIgrac) == true) {
				spDrugiIgrac.setSelection(i);
				break;
			}
		}
	}
	
	/**
	 * Pamti igrace za sljedeci put
	 * @param prviIgrac
	 * @param drugiIgrac
	 */
	private void zapamtiIgraceZaSljedeciPut(String prviIgrac, String drugiIgrac) {
		SharedPreferences prefs = getActivity().getSharedPreferences(getResources().getString(R.string.sharedPrefRjecnikIgraci), 0);
		Editor editor = prefs.edit();
		editor.putString(getResources().getString(R.string.parametarIgracPrvi), prviIgrac);
		editor.putString(getResources().getString(R.string.parametarIgracDrugi), drugiIgrac);
		editor.commit();
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnZapocni:
			// pohranjujemo odabrane igrace u radne parametre
			Spinner spPrviIgrac = (Spinner) getActivity().findViewById(R.id.spPrviIgrac);
			Spinner spDrugiIgrac = (Spinner) getActivity().findViewById(R.id.spDrugiIgrac);
			String prviIgrac = spPrviIgrac.getSelectedItem().toString();
			String drugiIgrac = spDrugiIgrac.getSelectedItem().toString();
			if(provjeriOdabraneIgrace(prviIgrac, drugiIgrac) == false) {
				return; // ignoriramo nedozvoljene odabire
			}
			zapamtiIgraceZaSljedeciPut(prviIgrac, drugiIgrac);
			
			Bundle radniParametri = getActivity().getIntent().getExtras();
			radniParametri.putString(getResources().getString(R.string.parametarIgracPrvi), prviIgrac);
			radniParametri.putString(getResources().getString(R.string.parametarIgracDrugi), drugiIgrac);
			getActivity().getIntent().putExtras(radniParametri);
			
			// radimo tranziciju fragmenta
			IgraFragment igraFragment = (IgraFragment)
				getFragmentManager().findFragmentById(R.layout.fragment_igra);
			if(igraFragment == null) {
				igraFragment = new IgraFragment();
			}
			FragmentTransaction fragmentTransakcija = getFragmentManager().beginTransaction();
			fragmentTransakcija.replace(R.id.container, igraFragment);
			fragmentTransakcija.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fragmentTransakcija.addToBackStack(null); // omogucava vracanje Back tipkom
			fragmentTransakcija.commit();
			break;
		}
	}
}
