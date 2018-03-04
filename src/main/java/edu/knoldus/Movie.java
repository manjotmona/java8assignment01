package edu.knoldus;

/**
 * Created by pallavi on 4/3/18.
 */
public class Movie {

  String name;
  int releaseYear;
  int rating;
  Genre genre;
  public Movie(String name, int releaseYear, int rating, Genre genre) {
    this.name = name;
    this.releaseYear = releaseYear;
    this.rating = rating;
    this.genre = genre;
  }

  public String toString() {
    return this.name;
  }
}
