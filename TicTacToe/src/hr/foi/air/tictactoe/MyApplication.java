package hr.foi.air.tictactoe;

import android.app.Application;
import com.activeandroid.ActiveAndroid;

public class MyApplication extends Application {
	public static final String TAG = "TicTacToe";
	
	@Override
	public void onCreate() {
		super.onCreate();
		/* 
		 * Ovdje inicijaliziramo ActiveAndroid ORM jer se radi o
		 * inicijalnom pokretanju Android aplikacije
		 */
		ActiveAndroid.initialize(this);
	}
}
