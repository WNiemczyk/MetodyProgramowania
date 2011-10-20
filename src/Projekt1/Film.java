package Projekt1;

public class Film {
	private String type;
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

	public String toString() {

		String film = "";
		film = film + title + ", " + director + ", " + year;
		return film;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
