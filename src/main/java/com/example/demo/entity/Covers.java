package com.example.demo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Class that is to be fetched from the back-end DB
 */
@Document(collection = "covers")
public class Covers {

	@Id
	private ObjectId _id;
	private int book_id;
	private int ratingcount;
	private double ratingval;

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getRatingcount() {
		return ratingcount;
	}

	public void setRatingcount(int ratingcount) {
		this.ratingcount = ratingcount;
	}

	public double getRatingval() {
		return ratingval;
	}

	public void setRatingval(double ratingval) {
		this.ratingval = ratingval;
	}
	
	@Override
	public String toString() {
		return "Covers : {book_id=" + book_id + ", ratingcount=" + ratingcount + ", ratingval=" + ratingval + "}";
	}
	
}
