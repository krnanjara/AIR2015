package hr.foi.air.tictactoe;

import hr.foi.air.tictactoe.logika.Igra;
import hr.foi.air.tictactoe.logika.Potez;
import hr.foi.air.tictactoe.logika.Racunalo;
import hr.foi.air.tictactoe.logika.RacunaloLagano;
import hr.foi.air.tictactoe.logika.RacunaloTesko;
import hr.foi.air.tictactoe.logika.StanjeIgre;
import hr.foi.air.tictactoe.model.Igrac;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class IgraFragment extends Fragment implements Igra, OnClickListener {
	
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
		
		TextView tvIgrac1 = (TextView) getActivity().findViewById(R.id.tvIgrac1);
		TextView tvIgrac2 = (TextView) getActivity().findViewById(R.id.tvIgrac2);
		tvIgrac1.setText(prviIgrac);
		tvIgrac2.setText(drugiIgrac);
		
		Button gumb;
		gumb = (Button) getActivity().findViewById(R.id.btnIgra1);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra2);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra3);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra4);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra5);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra6);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra7);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra8);
		gumb.setOnClickListener(this);
		gumb = (Button) getActivity().findViewById(R.id.btnIgra9);
		gumb.setOnClickListener(this);
		inicijalizirajIgru();
		
		if(tema.equals(getResources().getStringArray(R.array.teme)[1]) == true) {
			koristiSlikeZaGumbe = true;
		}
		else {
			koristiSlikeZaGumbe = false;
		}
		racunalo = null;
		if(prviIgrac.equals(getResources().getString(R.string.racunaloLagano))) {
			racunalo = new RacunaloLagano();
			racunalo.postaviPotez(Potez.igrac1);
			onClick(getActivity().findViewById(R.id.btnIgra1)); // umjesto null bezveze poslan gumb1
		}
		if(prviIgrac.equals(getResources().getString(R.string.racunaloTesko))) {
			racunalo = new RacunaloTesko();
			racunalo.postaviPotez(Potez.igrac1);
			onClick(getActivity().findViewById(R.id.btnIgra1)); // umjesto null bezveze poslan gumb1
		}
		if(drugiIgrac.equals(getResources().getString(R.string.racunaloLagano))) {
			racunalo = new RacunaloLagano();
			racunalo.postaviPotez(Potez.igrac2);
		}
		if(drugiIgrac.equals(getResources().getString(R.string.racunaloTesko))) {
			racunalo = new RacunaloTesko();
			racunalo.postaviPotez(Potez.igrac2);
		}
	}
	
	private boolean koristiSlikeZaGumbe;
	private Racunalo racunalo;
	private StanjeIgre stanjeIgre;
	private Potez[][] potezi;
	
	/**
	 * Zapocinje igru
	 */
	private void inicijalizirajIgru() {
		stanjeIgre = StanjeIgre.naReduJeIgrac1;
		potezi = new Potez[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				potezi[i][j] = Potez.neodigran;
			}
		}
	}

	@Override
	public void postaviVrijednost(int pozicija, Potez potez)
			throws IndexOutOfBoundsException {
		switch(pozicija) {
		case 1:
			if(potezi[0][0] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[0][0] = potez;
			break;
		case 2:
			if(potezi[0][1] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[0][1] = potez;
			break;
		case 3:
			if(potezi[0][2] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[0][2] = potez;
			break;
		case 4:
			if(potezi[1][0] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[1][0] = potez;
			break;
		case 5:
			if(potezi[1][1] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[1][1] = potez;
			break;
		case 6:
			if(potezi[1][2] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[1][2] = potez;
			break;
		case 7:
			if(potezi[2][0] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[2][0] = potez;
			break;
		case 8:
			if(potezi[2][1] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[2][1] = potez;
			break;
		case 9:
			if(potezi[2][2] != Potez.neodigran) {
				if(potez != Potez.neodigran) {
					throw new IndexOutOfBoundsException();
				}
			}
			potezi[2][2] = potez;
			break;
		default: // nije ni jedna prepoznata pozicija
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public StanjeIgre simulirajPostaviVrijednost(int pozicija, Potez potez)
			throws IndexOutOfBoundsException {
		StanjeIgre prethodnoStanje = stanjeIgre; // pohranimo trenutno stanje, da ga mozemo vratiti nakon simulacije
		StanjeIgre simuliranoStanje = StanjeIgre.nerijeseno;
		if(dohvatiVrijednost(pozicija) != Potez.neodigran){
			throw new IndexOutOfBoundsException();
		}
		postaviVrijednost(pozicija, potez);
		simuliranoStanje = dohvatiStanjeIgre();
		
		postaviVrijednost(pozicija, Potez.neodigran); // vratimo kako je bilo
		stanjeIgre = prethodnoStanje; // vratimo kako je bilo
		return simuliranoStanje;
	}

	@Override
	public Potez dohvatiVrijednost(int pozicija)
			throws IndexOutOfBoundsException {
		switch(pozicija) {
		case 1:
			return potezi[0][0];
		case 2:
			return potezi[0][1];
		case 3:
			return potezi[0][2];
		case 4:
			return potezi[1][0];
		case 5:
			return potezi[1][1];
		case 6:
			return potezi[1][2];
		case 7:
			return potezi[2][0];
		case 8:
			return potezi[2][1];
		case 9:
			return potezi[2][2];
		default: // nije ni jedna prepoznata pozicija
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public StanjeIgre dohvatiStanjeIgre() {
		if(stanjeIgre == StanjeIgre.nerijeseno || stanjeIgre == StanjeIgre.pobjedaIgrac1 || stanjeIgre == StanjeIgre.pobjedaIgrac2) {
			return stanjeIgre; // zadnje stanje, nema se sto promjeniti
		}
		if(stanjeIgre == StanjeIgre.naReduJeIgrac1) {
			Potez potez = Potez.igrac1;
			// provjera za redove
			if(potezi[0][0] == potez && potezi[0][1] == potez && potezi[0][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			if(potezi[1][0] == potez && potezi[1][1] == potez && potezi[1][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			if(potezi[2][0] == potez && potezi[2][1] == potez && potezi[2][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			// provjera za stupce
			if(potezi[0][0] == potez && potezi[1][0] == potez && potezi[2][0] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			if(potezi[0][1] == potez && potezi[1][1] == potez && potezi[2][1] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			if(potezi[0][2] == potez && potezi[1][2] == potez && potezi[2][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			// provjera za dijagonalu
			if(potezi[0][0] == potez && potezi[1][1] == potez && potezi[2][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
			if(potezi[0][2] == potez && potezi[1][1] == potez && potezi[2][0] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac1;
			}
		}
		if(stanjeIgre == StanjeIgre.naReduJeIgrac2) {
			Potez potez = Potez.igrac2;
			// provjera za redove
			if(potezi[0][0] == potez && potezi[0][1] == potez && potezi[0][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			if(potezi[1][0] == potez && potezi[1][1] == potez && potezi[1][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			if(potezi[2][0] == potez && potezi[2][1] == potez && potezi[2][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			// provjera za stupce
			if(potezi[0][0] == potez && potezi[1][0] == potez && potezi[2][0] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			if(potezi[0][1] == potez && potezi[1][1] == potez && potezi[2][1] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			if(potezi[0][2] == potez && potezi[1][2] == potez && potezi[2][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			// provjera za dijagonalu
			if(potezi[0][0] == potez && potezi[1][1] == potez && potezi[2][2] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
			if(potezi[0][2] == potez && potezi[1][1] == potez && potezi[2][0] == potez) {
				stanjeIgre = StanjeIgre.pobjedaIgrac2;
			}
		}
		if(stanjeIgre == StanjeIgre.naReduJeIgrac1 || stanjeIgre == StanjeIgre.naReduJeIgrac2) {
			// provjera za nerijeseno
			boolean svePopunjeno = true;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(potezi[i][j] == Potez.neodigran) {
						svePopunjeno = false;
						break;
					}
				}
			}
			if(svePopunjeno == true) {
				stanjeIgre = StanjeIgre.nerijeseno;
			}
		}
		
		return stanjeIgre;
	}
	
	/**
	 * Metoda azurira prikaz tako da odgovara stanju matrice potezi[][]
	 */
	private void osvjeziPrikaz() {
		Button gumb;
		if(koristiSlikeZaGumbe == true) {
			// prvi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra1);
			Drawable ikona1 = getActivity().getResources().getDrawable(R.drawable.krizic);
			Drawable ikona2 = getActivity().getResources().getDrawable(R.drawable.kruzic);
			switch(dohvatiVrijednost(1)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// drugi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra2);
			switch(dohvatiVrijednost(2)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// treci gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra3);
			switch(dohvatiVrijednost(3)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// cetvrti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra4);
			switch(dohvatiVrijednost(4)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// peti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra5);
			switch(dohvatiVrijednost(5)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// sesti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra6);
			switch(dohvatiVrijednost(6)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// sedmi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra7);
			switch(dohvatiVrijednost(7)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// osmi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra8);
			switch(dohvatiVrijednost(8)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}
			// deveti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra9);
			switch(dohvatiVrijednost(9)) {
			case neodigran: break;
			case igrac1:    gumb.setBackground(ikona1); break;
			case igrac2:    gumb.setBackground(ikona2); break;
			}		
		}
		else {
			// prvi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra1);
			switch(dohvatiVrijednost(1)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// drugi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra2);
			switch(dohvatiVrijednost(2)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// treci gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra3);
			switch(dohvatiVrijednost(3)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// cetvrti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra4);
			switch(dohvatiVrijednost(4)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// peti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra5);
			switch(dohvatiVrijednost(5)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// sesti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra6);
			switch(dohvatiVrijednost(6)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// sedmi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra7);
			switch(dohvatiVrijednost(7)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// osmi gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra8);
			switch(dohvatiVrijednost(8)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}
			// deveti gumb
			gumb = (Button) getActivity().findViewById(R.id.btnIgra9);
			switch(dohvatiVrijednost(9)) {
			case neodigran: gumb.setText(R.string.prazno); break;
			case igrac1:    gumb.setText(R.string.krizic); break;
			case igrac2:    gumb.setText(R.string.kruzic); break;
			}		
		}

	}
	
	@Override
	public void onClick(View v) {
		int pozicija = -1;
		switch(v.getId()) {
		case R.id.btnIgra1: pozicija = 1; break;
		case R.id.btnIgra2: pozicija = 2; break;
		case R.id.btnIgra3: pozicija = 3; break;
		case R.id.btnIgra4: pozicija = 4; break;
		case R.id.btnIgra5: pozicija = 5; break;
		case R.id.btnIgra6: pozicija = 6; break;
		case R.id.btnIgra7: pozicija = 7; break;
		case R.id.btnIgra8: pozicija = 8; break;
		case R.id.btnIgra9: pozicija = 9; break;
		}
		
		boolean nekaRacunaloOdigra = false;
		
		try {
			Potez potez = Potez.neodigran;
			if(dohvatiStanjeIgre() == StanjeIgre.naReduJeIgrac1) {
				potez = Potez.igrac1;
				
			}
			if(dohvatiStanjeIgre() == StanjeIgre.naReduJeIgrac2) {
				potez = Potez.igrac2;
			}
			
			if(racunalo != null && racunalo.dohvatiPotez() == potez) {
				postaviVrijednost(racunalo.odigrajPotez(this), racunalo.dohvatiPotez());
			}
			else {
				postaviVrijednost(pozicija, potez);
				nekaRacunaloOdigra = true;
			}
			osvjeziPrikaz();
			
			dohvatiStanjeIgre();
			
			// promjena igraca
			if(stanjeIgre == StanjeIgre.naReduJeIgrac1) {
				stanjeIgre = StanjeIgre.naReduJeIgrac2;
			}
			else if(stanjeIgre == StanjeIgre.naReduJeIgrac2) {
				stanjeIgre = StanjeIgre.naReduJeIgrac1;
			}
		}
		catch(IndexOutOfBoundsException iznimka) {
			// ignorira se slucaj nemoguceg poteza
			return;
		}
		
		TextView tvPobjeda = (TextView) getActivity().findViewById(R.id.tvPobjeda);
		if(dohvatiStanjeIgre() == StanjeIgre.pobjedaIgrac1) {
			String prviIgrac = getActivity().getIntent().getExtras().getString(getResources().getString(R.string.parametarIgracPrvi));
			tvPobjeda.setText(getResources().getString(R.string.pobjeda) + prviIgrac);
			Igrac.uvecajBodove(prviIgrac, getActivity());
			nekaRacunaloOdigra = false;
		}
		if(dohvatiStanjeIgre() == StanjeIgre.pobjedaIgrac2) {
			String drugiIgrac = getActivity().getIntent().getExtras().getString(getResources().getString(R.string.parametarIgracDrugi));
			tvPobjeda.setText(getResources().getString(R.string.pobjeda) + drugiIgrac);
			Igrac.uvecajBodove(drugiIgrac, getActivity());
			nekaRacunaloOdigra = false;
		}
		if(dohvatiStanjeIgre() == StanjeIgre.nerijeseno) {
			tvPobjeda.setText(R.string.nerijeseno);
			nekaRacunaloOdigra = false;
		}
		
		if(nekaRacunaloOdigra == true && racunalo != null) {
			onClick(v);
		}
	}
}
