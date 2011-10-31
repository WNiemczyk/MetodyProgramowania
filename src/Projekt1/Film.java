package Projekt1;

import org.apache.log4j.Logger;

public class Film {

	private static Logger logger = Logger.getLogger(Film.class);
	private String title;
	private String director;
	private int year;
	
	public Film() {

	}

	public Film(String title, String director, int year) {

		this.title = title;
		this.director = director;
		this.year = year;
	}

	public void setYearEx(int year) throws MyException{
		if (year > 2012)
			throw new MyException("Year cannot be greater than 2011");
		else this.year = year;
	}
	
	public String toString() {

		String film = "";
		film = film + title + ", " + director + ", " + year;
		return film;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
