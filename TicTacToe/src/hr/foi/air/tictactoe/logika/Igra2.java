package hr.foi.air.tictactoe.logika;

import hr.foi.air.tictactoe.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Button;

/**
 * Predstavlja nacin prikaza igre kad se odabere tema2
 * @author Arwen
 *
 */
public class Igra2 extends AbstractLogikaIgre {

	public Igra2(Context _c, Button b1, Button b2, Button b3, Button b4,
			Button b5, Button b6, Button b7, Button b8, Button b9) {
		super(_c, b1, b2, b3, b4, b5, b6, b7, b8, b9);
	}
	
	@Override
	void postaviOznakuOdabira(Button b) {

		Drawable ikona;
		if (trenutniZnak.charAt(0) == 'x') {
			ikona = c.getResources().getDrawable(R.drawable.krizic);
		} else {
			ikona = c.getResources().getDrawable(R.drawable.kruzic);
		}
		b.setBackground(ikona);
	}

}
