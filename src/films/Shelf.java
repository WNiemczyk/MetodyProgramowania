package films;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import exceptions.EndOfShelfException;
import exceptions.FilmNotFoundException;
import exceptions.LocationIsNullException;

import statuses.AvailableFilm;

public class Shelf {

	private static Logger logger = Logger.getLogger(Shelf.class);
	private final static int SHELF_HEIGHT = 15;
	private final static int SHELF_WIDTH = 5;

	private Map<Location, Film> existedFilms;

	public Shelf() {

		this.existedFilms = new HashMap<Location, Film>();
		init();
	}

	public void init() {
		this.existedFilms.put(new Location(0, 0), new Film("La Comunidad",
				"de la Iglesia", 2004, new AvailableFilm()));
		this.existedFilms.put(new Location(1, 0), new Film("Soul Kitchen",
				"Fatih Akin", 2010, new AvailableFilm()));
		this.existedFilms.put(new Location(2, 0), new Film(
				"The Limits of Control", "Jim Jarmusch", 2009,
				new AvailableFilm()));
		this.existedFilms.put(new Location(3, 0), new Film("Broken Flowers",
				"Jim Jarmusch", 2005, new AvailableFilm()));
		this.existedFilms.put(new Location(4, 0), new Film("Dom zły",
				"Wojciech Smarzowski", 2009, new AvailableFilm()));

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

		logger.info("Current films: " + this.toString());
	}

	public void put(Location l, Film f) {

		this.existedFilms.put(l, f);

	}


	public void putInFreeLocation(Location l, Film f)
			throws LocationIsNullException {

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if ((e.getKey() != null) && (e.getKey().equals(l))) {

				throw new LocationIsNullException(
						"Cannot put film in occupied location");
			}

			else if ((e.getKey() == null) && (e.getKey().equals(l))) {
				this.existedFilms.put(l, f);
			}

		}

		this.existedFilms.put(l, f);

		logger.info("Added film " + f + " in location " + l);

	}

	public Location getFreeLocation() throws EndOfShelfException{
		
		Location l = new Location(0, 0);
		Location rl = null;
		
		// TODO uzupełnić
		
		 for (Map.Entry<Location, Film> e : existedFilms.entrySet()) { 
			 
			 if (e.getKey().getY() == this.SHELF_HEIGHT){ 
				 throw new EndOfShelfException("Cannot exit the shelf"); } 
			 else if ((e.getKey().getX() == this.SHELF_WIDTH) && (e.getKey().getY() >= l.getY() &&
					 e.getKey().getY() < this.SHELF_HEIGHT)){ 
				 l.setY(e.getKey().getY()+1); }
			 else if ((e.getKey().getX() < this.SHELF_WIDTH) && (l.getX() <=
					 e.getKey().getX())){ 
				 l.setX(e.getKey().getX() + 1);
			 }
			 
			 rl = l;
		 }	 
		 
		if(rl != null) 
		logger.info("All locations are occupied");
		else	
		return rl;
		
		return rl;
	}
	
	public void put(Film film) throws LocationIsNullException, EndOfShelfException {
		Location l = this.getFreeLocation();
		this.put(l, film);
	}

	/*
	 * public void put(List<Film> films){ for(Film f: films){ put(f); }
	 * logger.info("Added new films " + films); }
	 */

	public void removeByLocation(Location location) {

		this.getExistedFilms().remove(location);
		logger.info("Removed film from location " + location);
		// TODO uwaga na wyznaczanie wolnej lokalizacji - przestawienie filmów
	}

	public Location findByLocation(Location l) throws LocationIsNullException {

		Film film = null;
		film = this.getExistedFilms().get(l);
		if (film == null)
			throw new LocationIsNullException("There is no film in location: "
					+ l);

		logger.info("Found film " + film + " in location" + l);
		
		return l;
	}

	public Map<Location, Film> setNewFilmOnOccupiedLocation(String t,
			Location l, Film f) throws FilmNotFoundException,
			LocationIsNullException, EndOfShelfException {

		Film film = null;

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getTitle().equals(t)) {
				this.existedFilms.put(e.getKey(), f);
				film = e.getValue();
				this.put(film);
				break;
			}

			if (film == null)
				throw new FilmNotFoundException("There is not film with title"
						+ film.getTitle());
		}

		logger.info("New film " + f + " is putted instead of film " + t);

		return existedFilms;
	}

	public Location changeLocation(Location l1, Location l2)
			throws LocationIsNullException {

		Film location1 = existedFilms.get(l1);
		Film location2 = existedFilms.get(l2);

		if ((location1 == null) || (location2 == null)) {
			throw new LocationIsNullException("Cannot give empty location");
		} else {
			this.existedFilms.put(l1, (Film) location2);
			this.existedFilms.put(l2, (Film) location1);
		}

		logger.info("Changed films from location " + l1 + " into " + l2
				+ " and viceversa");

		return l2;
	}

	public void clearAll() {

		this.getExistedFilms().clear();
		logger.info("All films are cleared");
	}

	public Map<Location, Film> findByYear(int y) throws FilmNotFoundException {

		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getYear() == y)
				foundedFilms.put(e.getKey(), e.getValue());
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films from year: "
					+ y);

		logger.info("There are " + foundedFilms.size() + " films from year "
				+ y);

		return foundedFilms;
	}

	public Map<Location, Film> findByDirector(String d)
			throws FilmNotFoundException {

		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getDirector().contains(d))
				foundedFilms.put(e.getKey(), e.getValue());
		}

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films by director: "
					+ d);

		logger.info("There are " + foundedFilms.size() + " films made by " + d);

		return foundedFilms;

	}

	public ArrayList<Location> findLocationByDirector(String d)
			throws LocationIsNullException {

		ArrayList<Location> locations = new ArrayList<Location>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getDirector().contains(d))
				locations.add(e.getKey());
		}

		if (locations.isEmpty())
			throw new LocationIsNullException(
					"There are not films by director: " + d);

		logger.info("Films made by " + d + " you can find in locations:");

		return locations;

	}

	public Map<Location, Film> getExistedFilms() {
		return existedFilms;
	}

	public void setExistedFilms(Map<Location, Film> existedFilms) {
		this.existedFilms = existedFilms;
	}

}
