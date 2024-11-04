package signinpage;

public class ReviewDTO {
	int id = 0;
	String bookName;
	String userName;
	String review;
	
	public ReviewDTO() {}
	
	// 리뷰 받아오기용
	public ReviewDTO(String userName, String review) {
		super();
		this.userName = userName;
		this.review = review;
	}


	public ReviewDTO(String bookName, String userName, String review) {
		super();
		this.bookName = bookName;
		this.userName = userName;
		this.review = review;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	
}
