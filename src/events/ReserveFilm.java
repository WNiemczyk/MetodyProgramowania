package events;

import statuses.ReservedFilm;

public class ReserveFilm implements InterfaceFilmListeners {

	public void processFilm(FilmEvent film) {
	
		film.getFilm().setStatus(new ReservedFilm());

	}

}
