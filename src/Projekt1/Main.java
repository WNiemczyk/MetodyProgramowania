package Projekt1;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		// dopisać wyjątek - błąd, który nie został obsłużony
		// zaimportowaliśmy bibliotekę log4j - tworzymy katalog lib, import,
		// logi - zapisywanie do pliku, skorzystać z vaannila.com

		// PropertyComfigurator.configure("Log4J.properties");

		// w logach informacja, że coś dodano, usunięto
		// 1. logi do wszystkiego (raport - w metodach dopisać
		// logger.info("Dodano " + cos)) i 2. logi tylko z błędami

		// Półka z filmami (HashMap <KEY = lokalizacja (x, y)> <VALUE = film
		// (tytuł, reżyser, rok produkcji)>)

		// BasicConfigurator.configure();

		PropertyConfigurator.configure("Log4J.properties");

		Shelf shelf = new Shelf();
		logger.info(shelf.toString());
		shelf.show();

		shelf.put(new Location(-1, 0), new Film("Bracia", "Susanne Bier", 2004));
		shelf.show();
		
		shelf.findByLocation(new Location(1, 0));

		shelf.removeByLocation(new Location(1, 0));
		
		
		try {
			logger.info(shelf.findByYear(2045));
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}

		
		try {
			shelf.findByDirector("Jim J");
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}
		
		
		try {
			shelf.findLocationByDirector("Jim Jarmusch");
		} catch (FilmNotFoundException e) {
		
			logger.warn(e.toString());	
		}
		
		shelf.show();
		shelf.setNewFilm("La Comunidad", new Location(0,0), new Film("Wesele", "Wojciech Smarzowski", 2004));
		shelf.show();
		
		shelf.changeLocation(new Location(2, 0), new Location (3, 0));
		shelf.show();
		
		shelf.clearAll();
		shelf.show();

	}

}
