package events;

import statuses.AvailableFilm;

public class ReturnFilm implements InterfaceFilmListeners {

	@Override
	public void processFilm(FilmEvent film) {

		film.getFilm().setStatus(new AvailableFilm());

		System.out.println(film.getFilm() + " is available");
	}

}
