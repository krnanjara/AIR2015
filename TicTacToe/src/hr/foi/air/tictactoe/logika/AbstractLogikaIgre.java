package hr.foi.air.tictactoe.logika;

import hr.foi.air.tictactoe.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * sve sto je potrebno da se igra normalno odvija
 * @author Arwen
 *
 */
public abstract class AbstractLogikaIgre {

	Context c;
	Activity a;

	Button btnIgra1, btnIgra2, btnIgra3, btnIgra4, btnIgra5, btnIgra6, btnIgra7, btnIgra8, btnIgra9;
	TextView igrac1, igrac2;

	String trenutniZnak;
	char[][] stanjeIgre;
	boolean imaLiPobjede;
	boolean nerijeseno;

	int vrstaIgre; // ako je 0 onda igrac vs igrac a ako je 1 onda igrac vs pc lagani; 2 igrac vs pc teski
	AbstractRacunalo racunalo;

	public AbstractLogikaIgre(Context _c, Button b1, Button b2, Button b3,
			Button b4, Button b5, Button b6, Button b7, Button b8, Button b9) {
		
		c = _c;
		a = (Activity) c;

		btnIgra1 = b1;
		btnIgra2 = b2;
		btnIgra3 = b3;
		btnIgra4 = b4;
		btnIgra5 = b5;
		btnIgra6 = b6;
		btnIgra7 = b7;
		btnIgra8 = b8;
		btnIgra9 = b9;

		trenutniZnak = "x";
		imaLiPobjede = false;
		nerijeseno = false;
		stanjeIgre = new char[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				stanjeIgre[i][j] = 'N'; //Inicijalizacija da su sva polja prazna
			}
		}

		SharedPreferences podaci = c.getSharedPreferences("Postavke", 0);

		vrstaIgre = podaci.getInt("tipIgre", 0);
		igrac1 = (TextView) a.findViewById(R.id.tvIgrac1);
		igrac2 = (TextView) a.findViewById(R.id.tvIgrac2);

		igrac1.setText(podaci.getString("ime1", ""));
		igrac2.setText(podaci.getString("ime2", ""));

		if (vrstaIgre == 1)
			racunalo = new RacunaloLagano();
		else if (vrstaIgre == 2)
			racunalo = new RacunaloTesko();

	}

	public void onClick(View v) {

		if (imaLiPobjede == true || nerijeseno == true) {
			return;
		}

		boolean promjena;
		promjena = zapisiOdabir(v.getId());

		if (promjena == true) {

			imaLiPobjede = detektirajPobjedu(trenutniZnak.charAt(0));
			if (imaLiPobjede == true) {
				return;
			}

			nerijeseno = detektirajNerijeseno();
			if (nerijeseno == true) {
				return;
			}

			promijeniZnak();

			if (vrstaIgre == 1 || vrstaIgre == 2) {

				int odabir = racunalo.biraj(stanjeIgre);
				zapisiOdabir(odabir);

				imaLiPobjede = detektirajPobjedu(trenutniZnak.charAt(0));
				if (imaLiPobjede == true) {
					return;
				}

				nerijeseno = detektirajNerijeseno();
				if (nerijeseno == true) {
					return;
				}

				promijeniZnak();
			}
		}
	}

	private void promijeniZnak() {
		if (trenutniZnak.equals("x")) {
			trenutniZnak = "o";
		} else if (trenutniZnak.equals("o")) {
			trenutniZnak = "x";
		}
	}

	boolean zapisiOdabir(int brojGumba) {

		boolean promjena = false;

		switch (brojGumba) {
			case 1:
			case R.id.btnIgra1:
				
				if (stanjeIgre[0][0] == 'N') {
					postaviOznakuOdabira(btnIgra1);
					stanjeIgre[0][0] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 2:
			case R.id.btnIgra2:

				if (stanjeIgre[0][1] == 'N') {
					postaviOznakuOdabira(btnIgra2);
					stanjeIgre[0][1] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 3:
			case R.id.btnIgra3:
				if (stanjeIgre[0][2] == 'N') {
					postaviOznakuOdabira(btnIgra3);
					stanjeIgre[0][2] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 4:
			case R.id.btnIgra4:

				if (stanjeIgre[1][0] == 'N') {
					postaviOznakuOdabira(btnIgra4);
					stanjeIgre[1][0] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 5:
			case R.id.btnIgra5:

				if (stanjeIgre[1][1] == 'N') {
					postaviOznakuOdabira(btnIgra5);
					stanjeIgre[1][1] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 6:
			case R.id.btnIgra6:

				if (stanjeIgre[1][2] == 'N') {
					postaviOznakuOdabira(btnIgra6);
					stanjeIgre[1][2] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 7:
			case R.id.btnIgra7:

				if (stanjeIgre[2][0] == 'N') {
					postaviOznakuOdabira(btnIgra7);
					stanjeIgre[2][0] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 8:
			case R.id.btnIgra8:

				if (stanjeIgre[2][1] == 'N') {
					postaviOznakuOdabira(btnIgra8);
					stanjeIgre[2][1] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
			case 9:
			case R.id.btnIgra9:

				if (stanjeIgre[2][2] == 'N') {
					postaviOznakuOdabira(btnIgra9);
					stanjeIgre[2][2] = trenutniZnak.charAt(0);
					promjena = true;
				} else {
					promjena = false;
				}

				break;
		}
		return promjena;
	}

	abstract void postaviOznakuOdabira(Button b);

	boolean detektirajPobjedu(char znakZaProvjeru) {

		boolean pobjeda = false;

		// provjera za redove
		if (stanjeIgre[0][0] == znakZaProvjeru && stanjeIgre[0][1] == znakZaProvjeru && stanjeIgre[0][2] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[1][0] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[1][2] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[2][0] == znakZaProvjeru && stanjeIgre[2][1] == znakZaProvjeru && stanjeIgre[2][2] == znakZaProvjeru)
			pobjeda = true;
		// provjera za stupce
		else if (stanjeIgre[0][0] == znakZaProvjeru && stanjeIgre[1][0] == znakZaProvjeru && stanjeIgre[2][0] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[0][1] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[2][1] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[0][2] == znakZaProvjeru && stanjeIgre[1][2] == znakZaProvjeru && stanjeIgre[2][2] == znakZaProvjeru)
			pobjeda = true;
		// provjera za dijagonalu
		else if (stanjeIgre[0][0] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[2][2] == znakZaProvjeru)
			pobjeda = true;
		else if (stanjeIgre[0][2] == znakZaProvjeru && stanjeIgre[1][1] == znakZaProvjeru && stanjeIgre[2][0] == znakZaProvjeru)
			pobjeda = true;
		else
			pobjeda = false;

		if (pobjeda) {
			String imePobjednika = "";
			if (trenutniZnak.charAt(0) == 'x') {
				imePobjednika = igrac1.getText().toString();
			} else {
				imePobjednika = igrac2.getText().toString();
			}								
			TextView text = (TextView) a.findViewById(R.id.tvPobjeda);
			text.setText("Pobjeda: " + imePobjednika);
			
			SharedPreferences igraci = c.getSharedPreferences("Lista", 0);						
			int trenutniRezultat = igraci.getInt(imePobjednika, 0);			
			trenutniRezultat = trenutniRezultat + 1;			
			Editor editor = igraci.edit();
			editor.putInt(imePobjednika, trenutniRezultat);
			editor.commit();			
		}
		return pobjeda;
	}

	boolean detektirajNerijeseno() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (stanjeIgre[i][j] == 'N') {
					return false;
				}
			}
		}
		TextView text = (TextView) a.findViewById(R.id.tvPobjeda);
		text.setText("Igra je nerijesena!");
		return true;
	}
	
	//Metode za dohvat i povrat stanja igre prilikom prekida

	public void setStanjeIgre(char[][] _stanjeIgre) {
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				index++;
				if (_stanjeIgre[i][j] != 'N') {
					setTrenutniZnak(String.valueOf(_stanjeIgre[i][j]));
					zapisiOdabir(index);
				}
			}
		}
		imaLiPobjede = detektirajPobjedu(trenutniZnak.charAt(0));
		nerijeseno = detektirajNerijeseno();
	}

	public char[][] getStanjeIgre() {
		return stanjeIgre;
	}

	public void setTrenutniZnak(String _trenutniZnak) {
		trenutniZnak = _trenutniZnak;
	}

	public String getTrenutniZnak() {
		return trenutniZnak;
	}

}
