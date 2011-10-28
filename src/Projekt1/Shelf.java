package Projekt1;

import java.util.HashMap;
import java.util.Map;
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
		this.existedFilms.put(new Location(2, 0), new Film("The Limits of Control",
				"Jim Jarmusch", 2009));
		this.existedFilms.put(new Location(3, 0), new Film("Broken Flowers",
				"Jim Jarmusch", 2005));
		this.existedFilms.put(new Location(4, 0), new Film("Dom zły",
				"Wojciech Smarzowski", 2009));

	}
	
	public void put(Location location, Film film) {
		
		this.existedFilms.put(location, film);
		logger.info("Added film " + film + " in location " + location);
	}
		
	public Map<Location, Film> getExistedFilms() {
		return existedFilms;
	}

	public void setExistedFilms(Map<Location, Film> existedFilms) {
		this.existedFilms = existedFilms;
	}

	public void show() {

		System.out.println(this.existedFilms);
		                                                                                                                                                                            
	}                                            

	public void find(Location location){                                         
		
		Location l = new Location();
		l = location;
		System.out.println(getExistedFilms().get(l));
		logger.info("Find the film in location " + l);
	}
	
	public void clear(){
		existedFilms.clear();
		logger.info("All films is clear");
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
}
