package com.example.bookstore1;

public class Book {
	 private String title;
	    private String author;
	    private String genre;
	    private double price;
	    private boolean inStock;
	    
		public Book(String title, String author, String genre, double price, boolean inStock) {
			super();
			this.title = title;
			this.author = author;
			this.genre = genre;
			this.price = price;
			this.inStock = inStock;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public boolean isInStock() {
			return inStock;
		}

		public void setInStock(boolean inStock) {
			this.inStock = inStock;
		}
		
	    
}
