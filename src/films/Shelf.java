package films;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import statuses.AvailableFilm;

public class Shelf {

	private static Logger logger = Logger.getLogger(Shelf.class);
	private final static int SHELF_HEIGHT = 15;
	private final static int SHELF_SZEROKOSC = 5;

	private Map<Location, Film> existedFilms;

	public Shelf() {

		this.existedFilms = new HashMap<Location, Film>();
		init();
	}

	public void init() {
		this.existedFilms.put(new Location(0, 0), new Film("La Comunidad",
				"de la Iglesia", 2004, new AvailableFilm()));
		this.existedFilms.put(new Location(1, 0), new Film("Soul Kitchen",
				"Fatih Akin", 2010,new AvailableFilm()));
		this.existedFilms.put(new Location(2, 0), new Film(
				"The Limits of Control", "Jim Jarmusch", 2009, new AvailableFilm()));
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

	public void put(Location location, Film film) {
		this.existedFilms.put(location, film);
		logger.info("Added film " + film + " in location " + location);
	}

	private Location getFreeLocation(){
		Location l = new Location(0,0);
		Location rl = null;
		//TODO uzupełnić
/*		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {
			if (e.getKey().getY() == this.SHELF_HEIGHT){
				throw new EndOfShelfException; //TODO dopisac wyjatek
			} else if ((e.getKey().getX() == this.SHELF_SZEROKOSC) && (e.getKey().getY()<this.SHELF_HEIGHT)){
				l.setY(e.getKey().getY()+1);
			} else if ((e.getKey().getX() < this.SHELF_SZEROKOSC) && (l.getX() <= e.getKey().getX())){
				l.setX(e.getKey().getX() + 1);
			} else if ()
			
		}*/
		
		return l;
	}
	
	//TODO stworzyć osobny pakiet na wyjątki
	
	public void put(Film film){
		Location l = this.getFreeLocation();
		this.put(l,  film);
	}
	
	/*
	public void put(List<Film> films){
		for(Film f: films){
			put(f);
		}
	logger.info("Added new films " + films);
	}
	*/
	
	public void removeByLocation(Location location) {

		this.getExistedFilms().remove(location);
		logger.info("Removed film from location " + location);
		//TODO uwaga na wyznaczanie wolnej lokalizacji - przestawienie filmów
	}


	public void findByLocation(Location location) {
		
		this.getExistedFilms().get(location);
		logger.info("Found film " + getExistedFilms().get(location) + " in location " + location);
	}
	
	public void setNewFilm(String t, Location l, Film f) {

		String title = "";
		Film ftm = null;
		
		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			title = e.getValue().getTitle();

			if (title.equals(t)){
				this.existedFilms.put(e.getKey(), f);
				ftm = e.getValue();		
				this.put(ftm);
				break;
			}
			
		}

		logger.info("New film " + f + " is putted instead film " + t);
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


		Map<Location, Film> foundedFilms = new HashMap<Location, Film>();

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

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

	

	public ArrayList<Location> findLocationByDirector(String d) throws FilmNotFoundException{

		ArrayList<Location> locations = new ArrayList<Location>();
		
		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			if (e.getValue().getDirector().contains(d))
				locations.add(e.getKey());
		}
		
		if (locations.isEmpty()) throw new FilmNotFoundException("There are not films by director: "
				+ d);
		
		logger.info("Films made by " + d + " you can find in locations:");
		
		return locations;
		
	}
		/*
		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			location = e.getKey();

			// equals potrzebuje pełnej nazwy
			if (e.getValue().getDirector().contains(d))
				foundedFilms.put(e.getKey(), e.getValue());
		
		}
	

		if (foundedFilms.size() == 0)
			throw new FilmNotFoundException("There are not films by director: "
					+ f);
		
		logger.info("\nFilms are made by director " + f
				+ " you can find in location: " + foundedFilms.toString()); // jak wyświetlić tylko location?	
		return foundedFilms;
	}
	*/
		
	public Map<Location, Film> getExistedFilms() {
		return existedFilms;
	}

	public void setExistedFilms(Map<Location, Film> existedFilms) {
		this.existedFilms = existedFilms;
	}

}
