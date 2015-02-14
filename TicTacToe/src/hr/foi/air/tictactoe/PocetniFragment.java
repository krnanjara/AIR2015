package hr.foi.air.tictactoe;

import hr.foi.air.tictactoe.model.Igrac;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

/**
 * PocetniFragment sadrzi gumbe pocetnog izbornika
 */
public class PocetniFragment extends Fragment implements OnClickListener{
	
	public PocetniFragment() {} // konstruktor prazan prema uputi Fragment dokumentacije
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_pocetni, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button btnStart = (Button) getActivity().findViewById(R.id.btnStart);
		Button btnIgraci = (Button) getActivity().findViewById(R.id.btnIgraci);
		Button btnIzlaz = (Button) getActivity().findViewById(R.id.btnIzlaz);
		
		btnStart.setOnClickListener(this);
		btnIgraci.setOnClickListener(this);
		btnIzlaz.setOnClickListener(this);
		
		provjeriDodajRacunalneIgrace();
	}
	
	/**
	 * Metoda dodaje racunalne igrace ukoliko ne postoje vec u bazi podataka
	 */
	private void provjeriDodajRacunalneIgrace() {
		String naziv = getResources().getString(R.string.racunaloTesko);
		if(Igrac.igracPostoji(naziv, getActivity()) == false) {
			Igrac igrac = new Igrac();
			igrac.naziv = naziv;
			igrac.bodovi = 0;
			igrac.save();
		}
		naziv = getResources().getString(R.string.racunaloLagano);
		if(Igrac.igracPostoji(naziv, getActivity()) == false) {
			Igrac igrac = new Igrac();
			igrac.naziv = naziv;
			igrac.bodovi = 0;
			igrac.save();
		}
	}

	@Override
	public void onClick(View v) {
		FragmentTransaction fragmentTransakcija;
		switch(v.getId()) {
		case R.id.btnStart: // kliknut je gumb za pocetak igre
			// pohranjujemo odabir teme u radne parametre
			Spinner spTema = (Spinner) getActivity().findViewById(R.id.spTema);
			Bundle radniParametri = getActivity().getIntent().getExtras();
			radniParametri.putString(getResources().getString(R.string.parametarTema), 
					spTema.getSelectedItem().toString());
			getActivity().getIntent().putExtras(radniParametri);
			
			// radimo tranziciju fragmenta
			PostavkeFragment postavkeFragment = (PostavkeFragment)
				getFragmentManager().findFragmentById(R.layout.fragment_postavke);
			if(postavkeFragment == null) { // ako jos nije bio pozvan
				postavkeFragment = new PostavkeFragment();
			}
			fragmentTransakcija = getFragmentManager().beginTransaction();
			fragmentTransakcija.replace(R.id.container, postavkeFragment);
			fragmentTransakcija.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fragmentTransakcija.addToBackStack(null); // omogucava vracanje Back tipkom
			fragmentTransakcija.commit();
			break;
		case R.id.btnIgraci: // kliknut je gumb za upravljanje igracima
			IgraciFragment igraciFragment = (IgraciFragment)
				getFragmentManager().findFragmentById(R.layout.fragment_igraci);
			if(igraciFragment == null){ // ako jos nije bio pozvan
				igraciFragment = new IgraciFragment();
			}
			fragmentTransakcija = getFragmentManager().beginTransaction();
			fragmentTransakcija.replace(R.id.container, igraciFragment);
			fragmentTransakcija.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			fragmentTransakcija.addToBackStack(null); // omogucava vracanje Back tipkom
			fragmentTransakcija.commit();
			break;
		case R.id.btnIzlaz: // kliknut je gumb za izlaz iz programa
			Activity vlasnik = getActivity(); // vlasnik je PocetniActivity
			if(vlasnik != null) {
				vlasnik.finish();
			}
			break;
		}
	}
}
