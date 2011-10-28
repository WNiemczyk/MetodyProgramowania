package Projekt1;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		shelf.show();

		shelf.put(new Location(-1, 0), new Film("Bracia", "Susanne Bier", 2004));

		Film f = new Film("Aki Kaurismaki", "La Havre", 2011);

		try {
			f.setYearEx(2014);
		} catch (MyException e) {

			logger.error(e.getMessage());
			// logger.fatal(e.getMessage());
			// logger.warn(e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
		}

		shelf.show();

		shelf.getExistedFilms().remove(new Location(1, 0));
		Location location = new Location(1, 0);
		logger.info("Remove film from location " + location);
	
		shelf.show();

		shelf.find(new Location(-1, 0));

		shelf.clear();

		shelf.show();

	}

}
