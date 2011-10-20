package Projekt1;

public class Start {

	private Shelf shelf;

	public Start() {
		this.shelf = new Shelf();
		init();
	}

	public void init() {
		this.shelf.put(new Location(0, 0), new Film("La Comunidad",
				"de la Iglesia", 2004));
		this.shelf.put(new Location(1, 0), new Film("Soul Kitchen",
				"Fatih Akin", 2010));
		this.shelf.put(new Location(2, 0), new Film("The Limits of Control",
				"Jim Jarmusch", 2009));
		this.shelf.put(new Location(3, 0), new Film("Broken Flowers",
				"Jim Jarmusch", 2005));
		this.shelf.put(new Location(4, 0), new Film("Dom zły",
				"Wojciech Smarzowski", 2009));

	}

	public void start() {

		System.out.println(this.shelf);

	}

	public void find(Location location){
		
		Location l = new Location();
		l = location;
		System.out.println(getShelf().getExistedFilms().get(l));
	
	}
	
	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

}
