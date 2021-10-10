
public class Main {

	public static void main(String[] args) {
		
		Movie m1 = new DayMovie("Angry Men", 1, 5, 1.00);
		Movie m2 = new DayMovie("Fight Club", 2, 4, 2.00);
		Movie m3 = new DayMovie("Inception", 3, 6, 1.50);
		Movie m4 = new DayMovie("The Matrix", 4, 2, 0.50);
		Movie m5 = new DayMovie("Forrest Gump", 5, 1, 1.00);
		Movie m6 = new DayMovie("Seven", 6, 7, 1.00);
		Movie m7 = new DayMovie("Casablanca", 7, 3, 2.50);
		Movie m8 = new DayMovie("Rear Window", 8, 7, 1.50);
		Movie m9 = new DayMovie("Leon", 9, 9, 0.50);
		Movie m10 = new DayMovie("Memento", 10, 3, 1.00);
		Movie m11 = new DayMovie("Alien", 11, 4, 1.50);
		Movie m12 = new DayMovie("Vertigo", 12, 5, 2.00);
		Movie m13 = new DayMovie("American Beauty", 13, 6, 3.00);
		Movie m14 = new RegularMovie("Toy Story 3", 14, 1.00);
		Movie m15 = new RegularMovie("Amelie", 15, 2.00);
		Movie m16 = new RegularMovie("The Prestige", 16, 3.00);
		Movie m17 = new RegularMovie("The Third Man", 17, 1.50);
		Movie m18 = new RegularMovie("ChinaTown", 18, 1.50);
		Movie m19 = new RegularMovie("Full Metal Jacket", 19, 1.00);
		Movie m20 = new RegularMovie("BraveHeart", 20, 2.50);
		Movie m21 = new RegularMovie("Oldboy", 21, 0.50);
		Movie m22 = new RegularMovie("Metropolis", 22, 1.50);
		Movie m23 = new RegularMovie("The Sting", 23, 2.00);
		Movie m24 = new RegularMovie("Batman Begins", 24, 0.50);
		Movie m25 = new RegularMovie("The Elephant Man", 25, 3.00);
		
		Rental r1 = new Rental();
		Rental r2 = new Rental();
		Rental r3 = new Rental();
		Rental r4 = new Rental();
		Rental r5 = new Rental();
		Rental r6 = new Rental();
		Rental r7 = new Rental();
		Rental r8 = new Rental();
		Rental r9 = new Rental();
		Rental r10 = new Rental();
		Rental r11 = new Rental();
		Rental r12 = new Rental();
		Rental r13 = new Rental();
		Rental r14 = new Rental();
		Rental r15 = new Rental();
		Rental r16 = new Rental();
		Rental r17 = new Rental();
		Rental r18 = new Rental();
		Rental r19 = new Rental();
		
		r1.addMovies(m1);
		r1.addMovies(m2);
		r1.addMovies(m21);
		r1.addMovies(m19);
		r2.addMovies(m14);
		r2.addMovies(m17);
		r3.addMovies(m4);
		r3.addMovies(m20);
		r3.addMovies(m25);
		r3.addMovies(m9);
		r4.addMovies(m18);
		r4.addMovies(m24);
		r5.addMovies(m1);
		r5.addMovies(m22);
		r5.addMovies(m11);
		r6.addMovies(m21);
		r7.addMovies(m14);
		r7.addMovies(m19);
		r8.addMovies(m16);
		r8.addMovies(m13);
		r9.addMovies(m15);
		r10.addMovies(m14);
		r11.addMovies(m25);
		r11.addMovies(m4);
		r11.addMovies(m8);
		r12.addMovies(m9);
		r13.addMovies(m3);
		r13.addMovies(m18);
		r14.addMovies(m1);
		r15.addMovies(m12);
		r15.addMovies(m22);
		r16.addMovies(m23);
		r17.addMovies(m7);
		r17.addMovies(m11);
		r18.addMovies(m9);
		r18.addMovies(m17);
		r19.addMovies(m18);
		r19.addMovies(m5);
		r19.addMovies(m6);
		r19.addMovies(m10);
		r19.addMovies(m3);
		r19.addMovies(m9);
		r19.addMovies(m2);
		
		Customer c1 = new Customer(1, "Giorgos Papadopoulos");
		Customer c2 = new Customer(2, "Dimitris Antoniadis");
		Customer c3 = new Customer(3, "Georgia Mosxou");
		Customer c4 = new Customer(4, "Panagiotis Chalkias");
		Customer c5 = new Customer(5, "Antigoni Giarenni");
		Customer c6 = new Customer(6, "Viorel Culiuk");
		Customer c7 = new Customer(7, "Christos Sidiropoulos");
		Customer c8 = new Customer(8, "Eleftheria Gkioura");
		Customer c9 = new Customer(9, "Aggela Xristodoulaki");
		Customer c10 = new Customer(10, "Panagiotis Giannoutakis");
		
		c1.addRentals(r1);
		c1.addRentals(r2);
		c1.addRentals(r3);
		c2.addRentals(r4);
		c2.addRentals(r5);
		c3.addRentals(r6);
		c4.addRentals(r7);
		c4.addRentals(r8);
		c5.addRentals(r9);
		c6.addRentals(r10);
		c6.addRentals(r11);
		c6.addRentals(r12);
		c7.addRentals(r13);
		c7.addRentals(r14);
		c8.addRentals(r15);
		c9.addRentals(r16);
		c9.addRentals(r17);
		c9.addRentals(r18);
		c10.addRentals(r19);
		
		VideoClub v1 = new VideoClub();
		
		v1.addCustomer(c1);
		v1.addCustomer(c2);
		v1.addCustomer(c3);
		v1.addCustomer(c4);
		v1.addCustomer(c5);
		v1.addCustomer(c6);
		v1.addCustomer(c7);
		v1.addCustomer(c8);
		v1.addCustomer(c9);
		v1.addCustomer(c10);
		
		v1.sort();
		v1.PrintPerissoteresTainies(3);
		
		v1.sort1();
		v1.PrintPerissoteraXrimata(3);
		
	}

}
