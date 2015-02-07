package hr.foi.air.tictactoe;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

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
		
		Button btnIgraci = (Button) getActivity().findViewById(R.id.btnIgraci);
		Button btnIzlaz = (Button) getActivity().findViewById(R.id.btnIzlaz);
		
		btnIgraci.setOnClickListener(this);
		btnIzlaz.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnIgraci: // kliknut je gumb za upravljanje igracima
			IgraciFragment igraciFragment = (IgraciFragment) getFragmentManager().findFragmentById(R.layout.fragment_igraci);
			if(igraciFragment == null){ // ako jos nije bio pozvan
				igraciFragment = new IgraciFragment();
			}
			FragmentTransaction fragmentTransakcija = getFragmentManager().beginTransaction();
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
