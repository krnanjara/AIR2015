package hr.foi.air.tictactoe.logika;

public abstract class AbstractRacunalo {

	abstract int biraj(char[][] stanjeIgre);

	public boolean daLiJePrazno(char[][] stanjeIgre, int brojGumba) {
		switch (brojGumba) {
			case 1:
				if (stanjeIgre[0][0] == 'N') {
					return true;
				}
				break;
			case 2:
				if (stanjeIgre[0][1] == 'N') {
					return true;
				}
				break;
			case 3:
				if (stanjeIgre[0][2] == 'N') {
					return true;
				}
				break;
			case 4:
				if (stanjeIgre[1][0] == 'N') {
					return true;
				}
				break;
			case 5:
				if (stanjeIgre[1][1] == 'N') {
					return true;
				}
				break;
			case 6:
				if (stanjeIgre[1][2] == 'N') {
					return true;
				}
				break;
			case 7:
				if (stanjeIgre[2][0] == 'N') {
					return true;
				}
				break;
			case 8:
				if (stanjeIgre[2][1] == 'N') {
					return true;
				}
				break;
			case 9:
				if (stanjeIgre[2][2] == 'N') {
					return true;
				}
				break;
		}
		return false;
	}

}
