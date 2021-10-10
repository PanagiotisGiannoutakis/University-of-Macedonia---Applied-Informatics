
public class Book {
	
	private int isbn;
	private String title;
	private Writer myWriter;
	private int timesRented;
	private boolean borrowed;
	
	public Book() {
		isbn = 0000;
		title = "";
		timesRented = 0;
		borrowed = false;
	}
	
	public Book(int kodikos, String titlos, Writer suggrafeas) {
		isbn = kodikos;
		title = titlos;
		myWriter = suggrafeas;
		borrowed = false;
		timesRented = 0;
	}

	
	
	
	
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Writer getMyWriter() {
		return myWriter;
	}

	public void setMyWriter(Writer myWriter) {
		this.myWriter = myWriter;
	}

	public int getTimesRented() {
		return timesRented;
	}

	public void setTimesRented(int timesRented) {
		this.timesRented = timesRented;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	
	
}
