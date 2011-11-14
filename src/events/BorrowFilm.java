package events;

import statuses.BorrowedFilm;

public class BorrowFilm implements InterfaceFilmListeners {

	public void processFilm(FilmEvent film) {
	
		film.getFilm().setStatus(new BorrowedFilm());
		
	}

}
