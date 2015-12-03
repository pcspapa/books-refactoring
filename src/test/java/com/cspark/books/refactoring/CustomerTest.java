package com.cspark.books.refactoring;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cspark on 2015. 12. 2..
 */
public class CustomerTest {

    private static final Movie REGULAR = new Movie("레귤러 영화", Movie.REGULAR);
    private static final Movie NEW_RELEASE = new Movie("신작 영화", Movie.NEW_RELEASE);
    private static final Movie CHILDRENS = new Movie("어린이 영화", Movie.CHILDRENS);

    private final Customer customer = new Customer("chan");

    @Test
    public void basicRegularRental() throws Exception {
        customer.addRental(new Rental(REGULAR, 2));
        assertThat(customer.statements(), is(expectedMessageFor("레귤러 영화", 2.0, 2.0, 1)));
    }

    @Test
    public void shouldDiscountRegularRentals() throws Exception {
        customer.addRental(new Rental(REGULAR, 3));
        assertThat(customer.statements(), is(expectedMessageFor("레귤러 영화", 3.5, 3.5, 1)));
    }

    @Test
    public void basicNewReleaseRental() throws Exception {
        customer.addRental(new Rental(NEW_RELEASE, 1));
        assertThat(customer.statements(), is(expectedMessageFor("신작 영화", 3.0, 3.0, 1)));
    }

    @Test
    public void shouldNotDiscountNewReleaseRentalsButBonusFrequentRenterPoints() throws Exception {
        customer.addRental(new Rental(NEW_RELEASE, 3));
        assertThat(customer.statements(), is(expectedMessageFor("신작 영화", 9.0, 9.0, 2)));
    }

    @Test
    public void basicChildensRental() throws Exception {
        customer.addRental(new Rental(CHILDRENS, 3));
        assertThat(customer.statements(), is(expectedMessageFor("어린이 영화", 1.5, 1.5, 1)));
    }

    @Test
    public void shouldDiscountChildrensRentals() throws Exception {
        customer.addRental(new Rental(CHILDRENS, 4));
        assertThat(customer.statements(), is(expectedMessageFor("어린이 영화", 3.0, 3.0, 1)));
    }

    private static String expectedMessageFor(String rental, double price, double total, int renterPointsEarned) {
        return "chan 고객님의 대여 기록\n\t" + rental + "\t" + price + "\n누적 대여료: " + total + "\n적립 포인트: " + renterPointsEarned;
    }
}