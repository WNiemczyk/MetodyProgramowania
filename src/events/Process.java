package events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import films.Film;


public class Process {

	private static Logger logger = Logger.getLogger(Process.class);

	private Film film;
	private List<InterfaceFilmListeners> filmProcesses = new ArrayList<InterfaceFilmListeners>();
	
	public synchronized void addFilmProcess(InterfaceFilmListeners process) {

		filmProcesses.add(process);
	}

	public synchronized void removeFilmProcess(InterfaceFilmListeners process) {

		filmProcesses.remove(process);
	}

	public synchronized void executeFilmProcess() {

		FilmEvent filmEvent = new FilmEvent(this, film);

		Iterator i = filmProcesses.iterator();
		while (i.hasNext()) {
			((InterfaceFilmListeners) i.next()).processFilm(filmEvent);			
			logger.info(film.getTitle() + " : " + film.getStatus().getName());
		}
	}
	
	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public List<InterfaceFilmListeners> getFilmProcesses() {
		return filmProcesses;
	}

	public void setFilmProcesses(List<InterfaceFilmListeners> filmProcesses) {
		this.filmProcesses = filmProcesses;
	}
}
// atrybuty, które opisują stan obiektu i chcemy coś w nim zmienić

