package Projekt1;

public class Location {

	private int x;
	private int y;

	public Location() {

	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (!(obj instanceof Location))
			return false;

		Location other = (Location) obj;

		if ((this.x == other.x) && (this.y == other.y))
			return true;

		return false;

	}

	public int hashCode() {
		return x + y;
	}

	public String toString() {

		String location;
		location = " [" + this.x + "]" + " [" + this.y + "]";

		return location;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}

