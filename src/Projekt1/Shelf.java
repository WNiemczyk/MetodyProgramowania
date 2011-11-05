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
		
		try{
			
		existedFilms.get(new Location(0,0)).setYearEx(2014);
			
		} catch (MyException e) {

			//logger.error(e.getMessage());
			//logger.fatal(e.getMessage());
			logger.warn(e.getMessage());
			// e.printStackTrace();
		} catch (Exception e) {
		}

		
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

		System.out.println("\n" + this.existedFilms);

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
	
	public void setNewFilm(String title, Location l, Film f){
		
		String film = "";
		
		for(Map.Entry<Location, Film> e : existedFilms.entrySet()){
			
			film = e.getValue().getTitle();
			
			if(film.equals(title))
				this.existedFilms.put(l, f);
		}
		
	}
	

	public void changeLocation(Location l1, Location l2) {

		Film location1 = existedFilms.get(l1);
		Film location2 = existedFilms.get(l2);
		this.existedFilms.put(l1, (Film) location2);
		this.existedFilms.put(l2, (Film) location1);
		logger.info("Changed films from location " + l1 + " into " + l2 + " and viceversa");
	}

	public void clearAll() {

		this.getExistedFilms().clear();
		logger.info("All the films are cleared");
	}

	public void findByYear(int y) {

		int year = 0;
		String title = "";
		System.out.println("\nFilms made in " + y + " are:");

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			year = e.getValue().getYear();
			title = e.getValue().getTitle();

			if (year == y)
				System.out.println(title);
		}
		
		logger.info("Found all the films from year" + y);
	}

	public void findByDirector(String director) {

		String film = "";
		String film2 = "";
		System.out.println("\nFilms made by director " + director + " are: ");

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			film = e.getValue().getDirector();
			film2 = e.getValue().getTitle();

			// equals potrzebuje pełnej nazwy
			if (film.contains(director))
				System.out.println(film2);
		}
		
		logger.info("Found films made by " + director);
	}

	public void findLocationByDirector(String director) {

		String film = "";
		Location l = null;
		System.out.println("\nFilms made by director " + director + " you can find in location: ");

		for (Map.Entry<Location, Film> e : existedFilms.entrySet()) {

			film = e.getValue().getDirector();
			l = e.getKey();

			// equals potrzebuje pełnej nazwy
			if (film.contains(director))
				System.out.println(l);
		}
		
		logger.info("Found films made by " + director + " in location " + l);
	}

	
	
	
	public Map<Location, Film> getExistedFilms() {
		return existedFilms;
	}

	public void setExistedFilms(Map<Location, Film> existedFilms) {
		this.existedFilms = existedFilms;
	}

}


