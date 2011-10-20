package Projekt1;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Półka z filmami (HashMap <KEY = lokalizacja (x, y)> <VALUE = film (tytuł, reżyser, rok produkcji)>)
				
		Start s = new Start();

		// pokaż półkę z filmami
		s.start();
		
		// dodaj film 
		s.getShelf().put(new Location(-1, 0),
				new Film("Bracia", "Susanne Bier", 2004));
		
		// pokaż aktualny stan półki
		s.start();
		
		// usuń film z lokalizacji (1, 0)
		s.getShelf().getExistedFilms().remove(new Location(1, 0));
		
		// update półki
		s.start();
		
		// wyszukiwanie wg lokalizacji - podajemy współrzędne
		s.find(new Location(-1,0));
		
	}

}

