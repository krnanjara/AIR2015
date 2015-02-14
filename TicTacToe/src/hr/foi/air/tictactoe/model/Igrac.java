package hr.foi.air.tictactoe.model;

import hr.foi.air.tictactoe.R;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "Igraci")
public class Igrac extends Model {

	@Column(name = "Naziv")
	public String naziv;
	
	@Column(name = "Bodovi")
	public Integer bodovi;
	
	public Igrac() { super(); } // prema uputi iz ActiveAndroid
		
	/**
	 * Provjerava postoji li igrac sa zadanim nazivom u bazi
	 * @param naziv igraca
	 * @return false ako ne postoji, true inace
	 */
	public static boolean igracPostoji(String naziv, Activity kontekst) {
		Resources resursi = kontekst.getResources();
		String where_kriterij = resursi.getString(R.string.where_kriterij);
		Igrac igrac = new Select()
			.from(Igrac.class)
			.where(where_kriterij, naziv)
			.executeSingle();
		if(igrac == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Priprema high score listu
	 * @return lista stringova oblika: poredak. [igrac] bodovi
	 */
	public static ArrayList<String> sortiraniPopisIgracaBodova(Activity kontekst) {
		Resources resursi = kontekst.getResources();
		ArrayList<String> popis = new ArrayList<String>();
		ArrayList<Igrac> igraci = new Select()
			.from(Igrac.class)
			.orderBy(resursi.getString(R.string.sortiranje_kriterij))
			.execute();
		int i = 0;
		for(Igrac igrac : igraci) {
			i += 1;
			popis.add(String.format(
					resursi.getString(R.string.format_popis), i, igrac.naziv, igrac.bodovi));
		}
		return popis;
	}
	
	/**
	 * Priprema listu igraca
	 * @return lista stringova oblika: igrac
	 */
	public static ArrayList<String> popisIgraca() {
		ArrayList<String> popis = new ArrayList<String>();
		ArrayList<Igrac> igraci = new Select()
			.from(Igrac.class)
			.execute();
		for(Igrac igrac : igraci) {
			popis.add(igrac.naziv);
		}
		return popis;	
	}
	
	/**
	 * Uvecava bodove (za 1) zadanom igracu
	 * @param naziv igraca kojem ce se povecati bodovi
	 */
	public static void uvecajBodove(String naziv, Activity kontekst) {
		Resources resursi = kontekst.getResources();
		String where_kriterij = resursi.getString(R.string.where_kriterij);
		Igrac igrac = new Select()
			.from(Igrac.class)
			.where(where_kriterij, naziv)
			.executeSingle();
		igrac.bodovi = igrac.bodovi + 1;
		igrac.save();
	}
}
