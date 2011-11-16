package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import films.*;
import exceptions.*;

import statuses.AvailableFilm;

public class ShelfTest {

	Shelf s = new Shelf();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShelf() {

		
	}

	@Test
	public void testInit() {
		
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testShow() {
		
		s.put(new Location(0, 0), new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		assertEquals(s.getExistedFilms().get(new Location(0, 0)).toString(), "Dzień świra, Marek Koterski, 2002");
		
	}

	@Test
	public void testPutLocationFilm() {
		fail("Not yet implemented");
	}

	@Test
	public void testPutInFreeLocation() {
		
		s.put(new Location(0, 0), new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		assertTrue(s.getExistedFilms().size() > 0);
	}

	@Test
	public void testGetFreeLocation() {
		
	}

	@Test
	public void testPutFilm() throws LocationIsNullException, EndOfShelfException{
		
		s.put(new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));		
		assertTrue(s.getExistedFilms().size() > 0);
		
	}

	@Test
	public void testRemoveByLocation() {

		Location l = new Location(0, 0);
		s.put(l, new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		s.removeByLocation(l);
		assertNull(s.getExistedFilms().get(l));
		
	}

	@Test
	public void testFindByLocation() throws LocationIsNullException{
		
		Location l = new Location(0, 0);
		s.put(l, new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		assertSame(s.findByLocation(l), s.getExistedFilms().get(l));

	}

	@Test
	public void testSetNewFilmOnOccupiedLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeLocation() {
		
		Location l1 = new Location(0, 0);
		Location l2 = new Location(0, 1);
		s.put(l1, new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		s.put(l2, new Film("Wszyscy jesteśmy Chrystusami", "Marek Koterski", 2007, new AvailableFilm()));
		Film f1 = s.getExistedFilms().get(l1);
		Film f2 = s.getExistedFilms().get(l2);
		
		s.getExistedFilms().put(l1, (Film) f2);
		s.getExistedFilms().put(l2, (Film) f1);
		
		assertSame(s.getExistedFilms().get(l2), f1);
	}

	@Test
	public void testClearAll() {
		
		s.put(new Location(0, 0), new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		s.getExistedFilms().clear();
		assertTrue(s.getExistedFilms().size() == 0);
	}

	@Test
	public void testFindByYear() throws FilmNotFoundException{
		
		Location l = new Location(0, 0);
		s.put(l, new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		assertSame(s.findByYear(2002).get(l), s.getExistedFilms().get(l));
	}

	@Test
	public void testFindByDirector() throws FilmNotFoundException{
		
		Location l = new Location(0, 0);
		s.put(l, new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		assertSame(s.findByDirector("Koterski"), s.getExistedFilms().get(l));
	}

	@Test
	public void testFindLocationByDirector() throws LocationIsNullException{
		
		Location l = new Location(0, 0);
		Film f = new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm());
		s.put(l, f);
	
		assertEquals(s.findLocationByDirector(f.getDirector()), l);
		
	}

	@Test
	public void testGetExistedFilms() {
		
		s.put(new Location(0, 0), new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		assertTrue(s.getExistedFilms().size() > 0);
		
	}

	@Test
	public void testSetExistedFilms() {
		
		s.put(new Location(0, 0), new Film("Wszyscy jesteśmy Chrystusami", "Marek Koterski", 2007, new AvailableFilm()));
		Map<Location, Film> newFilm = new HashMap<Location, Film>();
		newFilm.put(new Location(0, 0), new Film("Dzień świra", "Marek Koterski", 2002, new AvailableFilm()));
		s.setExistedFilms(newFilm);
		
		assertSame(s.getExistedFilms().get(new Location(0, 0)), newFilm.get(new Location(0, 0)));
	}

}
