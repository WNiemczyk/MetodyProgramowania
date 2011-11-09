package events;

public class BorrowFilm implements InterfaceFilmListeners {

	@Override
	public void processFilm(FilmEvent film) {
	
		System.out.println(film.getFilm() + " is borrowed");
	}

}
