package hr.foi.air.tictactoe;

import java.util.List;

import hr.foi.air.tictactoe.model.Igrac;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class IgraciFragment extends Fragment implements OnClickListener {
	
	public IgraciFragment() {} // konstruktor prazan prema uputi Fragment dokumentacije
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_igraci, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button btnIgracDodaj = (Button) getActivity().findViewById(R.id.btnIgracDodaj);
		btnIgracDodaj.setOnClickListener(this);
		
		osvjeziPopis();
	}
	
	/**
	 * Osvjezava ListView s popisom igraca
	 */
	private void osvjeziPopis() {
		List<String> igraciUBazi = Igrac.sortiraniPopisIgracaBodova();
		ArrayAdapter<String> adapter =
				new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, igraciUBazi);
		ListView lvListaIgraca = (ListView) getActivity().findViewById(R.id.lvListaIgraca);
		lvListaIgraca.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnIgracDodaj: // pritisnut gumb za dodavanje igraca
			EditText etIgrac = (EditText) getActivity().findViewById(R.id.etIgrac);
			String nazivIgraca = etIgrac.getText().toString();
			if(nazivIgraca.trim().isEmpty() == true) {
				return;
			}		
			if(Igrac.igracPostoji(nazivIgraca) == false) {
				Igrac igrac = new Igrac();
				igrac.naziv = nazivIgraca;
				igrac.bodovi = 0;
				igrac.save(); // pohranjuje igraca u tablicu Igraci
				etIgrac.setText(R.string.prazno);
			}
			osvjeziPopis();
			break;
		}
	}
}
