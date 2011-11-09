package events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Projekt1.Film;

public class Process {

	private Film film;
	private List filmProcesses = new ArrayList();

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
		}
	}
}
// atrybuty, które opisują stan obiektu i chcemy coś w nim zmienić

