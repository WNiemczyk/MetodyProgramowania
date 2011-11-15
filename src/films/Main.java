package films;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import exceptions.EndOfShelfException;
import exceptions.FilmNotFoundException;
import exceptions.LocationIsNullException;
import statuses.AvailableFilm;
import statuses.BorrowedFilm;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		PropertyConfigurator.configure("Log4J.properties");

		Shelf shelf = new Shelf();
		
		shelf.show();
		
		try {
			shelf.getFreeLocation();
		} catch (EndOfShelfException e) {
			logger.warn(e.toString());
		}
		
		try {
			shelf.putInFreeLocation(new Location(5, 0), new Film("Bracia", "Susanne Bier", 2004, new AvailableFilm()));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}
		
		shelf.show();

		try {
			shelf.findByLocation(new Location(1, 0));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}

		shelf.removeByLocation(new Location(1, 0));

		try {
			logger.info(shelf.findByYear(2045));
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}

		try {
			logger.info(shelf.findByDirector("Jim Jarmusch"));
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}

		try {
			logger.info(shelf.findLocationByDirector("Jarmusch"));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}

		
		try {
			try {
				try {
					logger.info(shelf.setNewFilmOnOccupiedLocation("La Comunidad", new Location(0, 0), new Film("Róża",
							"Wojciech Smarzowski", 2010, new AvailableFilm())));
				} catch (EndOfShelfException e) {
					
					logger.info(e.toString());
				}
			} catch (LocationIsNullException e) {
				logger.warn(e.toString());
			}
		} catch (FilmNotFoundException e) {
			logger.warn(e.toString());
		}
		

		try {
			logger.info(shelf.changeLocation(new Location(2, 0), new Location(3, 0)));
		} catch (LocationIsNullException e) {
			logger.warn(e.toString());
		}
		
		shelf.show();

		shelf.clearAll();
		shelf.show();
		
		/*
		
		List<Film> newFilms = new ArrayList<Film>();
		newFilms.add(new Film("Chinatown", "Roman Polański", 1974, new AvailableFilm()));
		newFilms.add(new Film("Taxi Driver", "Martin Scorsese", 1976, new AvailableFilm()));
		newFilms.add(new Film("Snatch", "Guy Ritchie", 2000, new AvailableFilm()));
		newFilms.add(new Film("Nóż w wodzie", "Roman Polański", 1961, new AvailableFilm()));
		                                           
		shelf.putListOfFilms(newFilms);
		shelf.show();
		
		*/
	}

}
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
// klasa która dziedziczy po eventObject - coś się wydarzyło, skończył
// się olej i chcemy change oil, rotate wheel, wash (obiekty)
// interfejs, który implementuje klasy rotate, wash, change oil
//
// jUnit zaimportować tak jak Log4J
// testy klas Shelf, Film, Location - Eclipse wygeneruje wszystkie
// metody klas, Before i After
// vogella.de - 2 ostatnie tabele -- asercje
// assertTrue(p.getCars().size()>0);
// assertNotNull(p.getCars());
// assertSame(c, p.getCars().get(0)) - sprawdzamy referencje
// assertEquals(p.toString(), "Adam has 0 cars.")
// testujemy metody, które mają wyrzucić wyjątek
// sprawdzić w projekcie, czy metody działają
// do wtorku

// metoda static nie potrzebuje tworzenia obiektów, dostajemy się do
// metody nie tworząc jej obiektu


// enumy wrzucić