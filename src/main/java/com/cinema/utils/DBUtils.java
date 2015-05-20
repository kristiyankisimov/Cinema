package com.cinema.utils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cinema.dao.MovieDAO;
import com.cinema.dao.UserDAO;
import com.cinema.model.Movie;
import com.cinema.model.User;


@Stateless
public class DBUtils {
    
    private static User[] USERS = {
	            new User("test1", "Ivan1", "ivan1@haker.bg", Boolean.TRUE, "123456"),
	            new User("test2", "Ivan2", "ivan2@haker.bg", Boolean.FALSE, "123456"),
	            new User("test3", "Ivan3", "ivan3@haker.bg", Boolean.FALSE, "123456"),
	            new User("test4", "Ivan4", "ivan4@haker.bg", Boolean.FALSE, "123456")
            };
    
    private static Movie[] MOVIES = {
    	new Movie("aaa", "bdfgdfgdfg", 12, 1.0),
    	new Movie("kdfjg", "bd", 12, 5.0),
    	new Movie(".lghk;fh", "dgff", 12, 6.0),
    	new Movie("a;odfgd;gkaa", "sdf", 12, 4.3)
    	
    };

//    private static Book[] BOOKS = {
//            new Book("The Old Man and the Sea", "Ernest Hemingway",
//                    "978-3-16-148410-0", 5),
//            new Book("Tom Sawyer", "Mark Twain", "978-4-16-241512-0", 0)};

    @PersistenceContext
    private EntityManager em;

//    @EJB
//    private BookDAO bookDAO;
    
    @EJB
    private UserDAO userDAO;
    
    @EJB
    private MovieDAO movieDAO;
    
    public void addTestDataToDB() {
        deleteData();
        addTestUsers();
        addTestMovies();
//        addTestBooks();
    }
//
    private void deleteData() {
//        em.createQuery("DELETE FROM Book").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
        em.createQuery("DELETE FROM Movie").executeUpdate();
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

//    private void addTestBooks() {
//        for (Book book : BOOKS) {
//            bookDAO.addBook(book);
//        }
//    }
}