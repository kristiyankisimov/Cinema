package com.cinema.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	private static Calendar getDate(int when) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, when);
		return c;
	}

	private static List<Seat> generateSeats() {
		List<Seat> seats = new ArrayList<Seat>();
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 21; j++) {
				seats.add(new Seat(i, j, 0, null));
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

	private static Movie[] MOVIES = { new Movie("Tommorowland", "Tommorowland is a 2015 American science-fiction mystery. ", 60, 8.0),
			new Movie("San Andreas", "San Andreas is a 2015 American 3D disaster film directed by Brad Peyton. The film stars an ensemble cast.", 80, 10.0),
			new Movie("Harry Potter", "Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling.", 120, 16.0),
			new Movie("White Fang", "White Fang is a novel by American author Jack London (1876–1916) — and the name of the book's eponymous character, a wild wolfdog.", 100, 14.3)

	};
	private static Hall[] HALLS = {

	new Hall(1), new Hall(2) };


	private static List<Seat> seat1 = new ArrayList<>(generateSeats());
	private static List<Seat> seat2 = new ArrayList<>(generateSeats());
	private static List<Seat> seat3 = new ArrayList<>(generateSeats());
	private static List<Seat> seat4 = new ArrayList<>(generateSeats());


	private static Screening[] SCREENING = {
			new Screening(getDate(-1), MOVIES[0], HALLS[0], seat1),
			new Screening(getDate(0), MOVIES[1], HALLS[1], seat2),
			new Screening(getDate(-1), MOVIES[3], HALLS[0], seat3),
			new Screening(getDate(1), MOVIES[2], HALLS[0], seat4)

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

//	private void addTestMovies() {
//		for (Movie movie : MOVIES) {
//			movieDAO.addMovie(movie);
//		}
//	}

	private void addTestScreening() {
		for (Screening screening : SCREENING) {
			screeningDAO.addScreening(screening);
		}
	}

}