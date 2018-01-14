package it.univaq.teamvisal.java.business.impl.dao;

import java.util.List;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;

public interface JDBCReviewManager {
	public void manageReview(Review review, boolean approved) throws DatabaseConnectionException, QueryException;
	public List<Review> getPendingReviews() throws DatabaseConnectionException, QueryException;
	public List<Review> getReviewsForGame(Game game) throws DatabaseConnectionException, QueryException;
	public void sendReview(Review review) throws DatabaseConnectionException, QueryException;
	public boolean hasUserSentReviewFor(Game game) throws DatabaseConnectionException, QueryException;
}
