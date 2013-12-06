/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 */
package org.searchisko.persistence.service;

import java.util.List;

import org.searchisko.persistence.jpa.model.Rating;

/**
 * Interface for service used to persistently store "Personalized Content Rating"s.
 * <p>
 * TODO _RATING create JPA implementation of this service
 * 
 * @author Vlastimil Elias (velias at redhat dot com)
 */
public interface RatingPersistenceService {

	/**
	 * Get ratings performed by given contributor for given content.
	 * 
	 * @param contributorId we want rating for
	 * @param contentId list of identifier of content we wants rating for
	 * @return list of ratings. Do not contain object for contentId if user didn't rated it yet.
	 */
	List<Rating> getRatings(String contributorId, String... contentId);

	/**
	 * @param contributorId who rated
	 * @param contentId rating is for
	 * @param rating value
	 */
	void rate(String contributorId, String contentId, int rating);

	/**
	 * Count rating statistics (average rating and number of ratings) for given content.
	 * 
	 * @param contentId to count statistics for
	 * @return rating statistics. Null if content not rated yet.
	 */
	RatingStats countRatingStats(String contentId);

	public static class RatingStats {
		private String contentId;
		private float average;
		private int number;

		public RatingStats(String contentId, float average, int number) {
			this.contentId = contentId;
			this.average = average;
			this.number = number;
		}

		public float getAverage() {
			return average;
		}

		public int getNumber() {
			return number;
		}

		public String getContentId() {
			return contentId;
		}

		@Override
		public String toString() {
			return "RatingStats [contentId=" + contentId + ", average=" + average + ", number=" + number + "]";
		}

	}

}