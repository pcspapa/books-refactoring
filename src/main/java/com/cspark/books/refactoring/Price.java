package com.cspark.books.refactoring;

/**
 * Created by cspark on 2015. 12. 4..
 */
public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }

}
