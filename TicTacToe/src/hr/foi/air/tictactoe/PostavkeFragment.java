package hr.foi.air.tictactoe;

import hr.foi.air.tictactoe.model.Igrac;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentTransaction;
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
