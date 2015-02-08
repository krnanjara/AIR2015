package hr.foi.air.tictactoe;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IgraFragment extends Fragment {
	
	public IgraFragment() { } // konstruktor prazan prema uputi Fragment dokumentacije
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_igra, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String tema = getActivity().getIntent().getExtras().getString(getResources().getString(R.string.parametarTema));
		String prviIgrac = getActivity().getIntent().getExtras().getString(getResources().getString(R.string.parametarIgracPrvi));
		String drugiIgrac = getActivity().getIntent().getExtras().getString(getResources().getString(R.string.parametarIgracDrugi));
		
		if(tema != null && prviIgrac != null && drugiIgrac != null) {
			Log.i("PRIVREMENOTEST", tema + ", " + prviIgrac + ", " + drugiIgrac);
		}
		else {
			Log.i("PRIVREMENOTEST", "null");
		}
	}
}
