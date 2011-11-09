package Projekt1;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class Shelf {

	private static Logger logger = Logger.getLogger(Shelf.class);

	private Map<Location, Film> existedFilms;

	public Shelf() {

		this.existedFilms = new HashMap<Location, Film>();
		init();
	}

	public void init() {
		this.existedFilms.put(new Location(0, 0), new Film("La Comunidad",
				"de la Iglesia", 2004));
		this.existedFilms.put(new Location(1, 0), new Film("Soul Kitchen",
				"Fatih Akin", 2010));
		this.existedFilms.put(new Location(2, 0), new Film(
				"The Limits of Control", "Jim Jarmusch", 2009));
		this.existedFilms.put(new Location(3, 0), new Film("Broken Flowers",
				"Jim Jarmusch", 2005));
		this.existedFilms.put(new Location(4, 0), new Film("Dom zły",
				"Wojciech Smarzowski", 2009));

	}

	public String toString() {

		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;

		String map = "";

		Location l = null;

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			l = e.getKey();

			if (minX > l.getX())
				minX = l.getX();
			if (maxX < l.getX())
				maxX = l.getX();
			if (minY > l.getY())
				minY = l.getY();
			if (maxY < l.getY())
				maxY = l.getY();

		}

		for (int j = maxY; j >= minY; j--) {
			for (int i = minX; i <= maxX; i++) {
				map = map + "(" + i + "," + j + ") " + "["
						+ existedFilms.get(new Location(i, j)) + "] ";
			}
			map = map + "\n";
		}

		return map;
	}

	public void show() {

		System.out.println(toString());

	}

	public void findByLocation(Location location) {

		System.out.println(getExistedFilms().get(location));
		logger.info("Found the film in location " + location);
	}

	public void put(Location location, Film film) {
		this.existedFilms.put(location, film);
		logger.info("Added film " + film + " in location " + location);
	}

	public void removeByLocation(Location location) {

		this.getExistedFilms().remove(location);
		logger.info("Removed film from location " + location);
	}

	public void setNewFilm(String t, Location l, Film f) {

		String title = "";

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			title = e.getValue().getTitle();

			if (title.equals(t))
				this.existedFilms.put(l, f);
		}

	}

	public void changeLocation(Location l1, Location l2) {

		Film location1 = existedFilms.get(l1);
		Film location2 = existedFilms.get(l2);
		this.existedFilms.put(l1, (Film) location2);
		this.existedFilms.put(l2, (Film) location1);
		logger.info("Changed films from location " + l1 + " into " + l2
				+ " and viceversa");
	}

	public void clearAll() {

		this.getExistedFilms().clear();
		logger.info("All the films are cleared");
	}

	public Map<Location, Film> findByYear(int y) throws FilmNotFoundException {

		// int year = 0;

		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			// year = e.getValue().getYear();

			if (e.getValue().getYear() == y)
				foundedFilms.put(e.getKey(), e.getValue());
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films from year: "
					+ y);

		logger.info("There are " + foundedFilms.size() + " films from year " + y);
 
		return foundedFilms;
	}

	public Map<Location, Film> findByDirector(String d)
			throws FilmNotFoundException {

		// String director = "";

		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			// director = e.getValue().getDirector();
			// film2 = e.getValue().getTitle();

			// equals potrzebuje pełnej nazwy
			if (e.getValue().getDirector().contains(d))
				foundedFilms.put(e.getKey(), e.getValue());
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films by director: "
					+ d);

		logger.info("There are " + foundedFilms.size() + " films made by " + d);

		return foundedFilms;

	}

	public Map<Location, Film> findLocationByDirector(String d) throws FilmNotFoundException{

		// String film = "";
		Location l = null;
		
		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			// film = e.getValue().getDirector();
			l = e.getKey();

			// equals potrzebuje pełnej nazwy
			if (e.getValue().getDirector().contains(d))
				foundedFilms.put(e.getKey(), e.getValue());
		
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films by director: "
					+ d);
		
		logger.info("\nFilms are made by director " + d
				+ " you can find in location: " + foundedFilms.get(l));
	
		return foundedFilms;
	}

	public Map<Location, Film> getExistedFilms() {
		return existedFilms;
	}

	public void setExistedFilms(Map<Location, Film> existedFilms) {
		this.existedFilms = existedFilms;
	}

}
