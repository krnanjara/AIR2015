package hr.foi.air.tictactoe.model;

import java.util.ArrayList;

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
	public static boolean igracPostoji(String naziv) {
		Igrac igrac = new Select()
			.from(Igrac.class)
			.where("Naziv = ?", naziv)
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
	public static ArrayList<String> sortiraniPopisIgracaBodova() {
		ArrayList<String> popis = new ArrayList<String>();
		ArrayList<Igrac> igraci = new Select()
			.from(Igrac.class)
			.orderBy("Bodovi ASC")
			.execute();
		int i = 0;
		for(Igrac igrac : igraci) {
			i += 1;
			popis.add(i + ": [" + igrac.naziv + "] " + igrac.bodovi);
		}
		return popis;
	}
	
}
