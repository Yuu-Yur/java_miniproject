package signinpage;

import java.sql.Date;

public class MainDTO {
	int num;
	String id;
	String author;
	Date releaseDate;
	float rating;
	public MainDTO() {}
	public MainDTO(String id, String author, Date releaseDate, float rating) {
		super();
		this.id = id;
		this.author = author;
		this.releaseDate = releaseDate;
		this.rating = rating;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
}
