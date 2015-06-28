package com.cinema.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cinema.dao.MovieDAO;
import com.cinema.dao.ScreeningDAO;
import com.cinema.dao.UserDAO;
import com.cinema.model.Hall;
import com.cinema.model.Movie;
import com.cinema.model.Screening;
import com.cinema.model.Seat;
import com.cinema.model.User;

@Stateless
public class DBUtils {

	private static Date getDate(int when) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, when);
		return c.getTime();
	}

	private static List<Seat> generateSeats() {
		List<Seat> seats = new ArrayList<Seat>();
		for (int i = 1; i < 21; i++) {
			for (int j = 1; j < 11; j++) {
				seats.add(new Seat(i, j, false, new Date()));
			}
		}
		return seats;
	}

	private static User[] USERS = {
			new User("test1", "Ivan1", "ivan1@haker.bg", Boolean.TRUE, "123456"),
			new User("test2", "Ivan2", "ivan2@haker.bg", Boolean.FALSE,
					"123456"),
			new User("test3", "Ivan3", "ivan3@haker.bg", Boolean.FALSE,
					"123456"),
			new User("test4", "Ivan4", "ivan4@haker.bg", Boolean.FALSE,
					"123456") };

	private static Movie[] MOVIES = { new Movie("aaa", "bdfgdfgdfg", 12, 1.0),
			new Movie("kdfjg", "bd", 12, 5.0),
			new Movie(".lghk;fh", "dgff", 12, 6.0),
			new Movie("a;odfgd;gkaa", "sdf", 12, 4.3)

	};
	private static Hall[] HALLS = {

	new Hall(1), new Hall(2) };

	private static List<Seat> seat2 = new ArrayList<>(generateSeats());
	// static {
	// seat2.add(new Seat(1, 1, true, new Date()));
	// seat2.add(new Seat(1, 2, false, new Date()));
	// seat2.add(new Seat(3, 20, true, new Date()));
	// seat2.add(new Seat(10, 20, false, new Date()));
	// seat2.add(new Seat(9, 18, true, new Date()));
	// }

	private static List<Seat> seat1 = new ArrayList<>(generateSeats());
	// static {
	// seat1.add(new Seat(1, true, new Date()));
	// seat1.add(new Seat(2, false, new Date()));
	// seat1.add(new Seat(3, true, new Date()));
	// seat1.add(new Seat(4, false, new Date()));
	// seat1.add(new Seat(5, true, new Date()));
	// }

	static {

		HALLS[0].setSeats(seat1);
		HALLS[1].setSeats(seat2);
	}

	private static Screening[] SCREENING = {
			new Screening(getDate(-1), MOVIES[0], HALLS[0]),
			new Screening(getDate(0), MOVIES[1], HALLS[1]),
			new Screening(getDate(-1), MOVIES[3], HALLS[0]),
			new Screening(getDate(1), MOVIES[2], HALLS[0])

	};

	@PersistenceContext
	private EntityManager em;

	@EJB
	private UserDAO userDAO;

	@EJB
	private MovieDAO movieDAO;

	@EJB
	private ScreeningDAO screeningDAO;

	public void addTestDataToDB() {
		deleteData();
		addTestUsers();
		// addTestMovies();
		addTestScreening();
	}

	private void deleteData() {
		em.createQuery("DELETE FROM User").executeUpdate();
		em.createQuery("DELETE FROM Movie").executeUpdate();
		em.createQuery("DELETE FROM Seat").executeUpdate();
		em.createQuery("DELETE FROM Hall").executeUpdate();
		em.createQuery("DELETE FROM Screening").executeUpdate();

	}

	private void addTestUsers() {
		for (User user : USERS) {
			userDAO.addUser(user);
		}
	}

	private void addTestMovies() {
		for (Movie movie : MOVIES) {
			movieDAO.addMovie(movie);
		}
	}

	private void addTestScreening() {
		for (Screening screening : SCREENING) {
			screeningDAO.addScreening(screening);
		}
	}

}