package events;

import statuses.AvailableFilm;

public class CancelReservationFilm implements InterfaceFilmListeners{

	@Override
	public void processFilm(FilmEvent film) {
	
		film.getFilm().setStatus(new AvailableFilm());

	}
	
}
