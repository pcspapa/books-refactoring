package com.cspark.books.refactoring;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by cspark on 2015. 11. 26..
 */
public class Customer {
    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        this._name = name;
    }

    public void addRental(Rental rental) {
        _rentals.add(rental);
    }

    public String getName() {
        return _name;
    }

    public String statements() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = getName() + " 고객님의 대여 기록\n";
        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            // 비디오 종류별 대여료 계산
            thisAmount = amountFor(each);

            // 적립 포인트를 1로 포인트 증가
            frequentRenterPoints ++;

            // 최신물을 이틀 이상 대여하면 보너스 포인트 지급
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints ++;

            // 이번에 대여하는 비디오 정보와 대여료 출력
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";

            // 현재까지 누적된 총 대여료
            totalAmount += thisAmount;
        }

        // 푸터 행 추가
        result += "누적 대여료: " + String.valueOf(totalAmount) + "\n";
        result += "적립 포인트: " + String.valueOf(frequentRenterPoints);
        return result;
    }

    private double amountFor(Rental aRental) {
        double thisAmount = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (aRental.getDaysRented() > 2)
                    thisAmount += (aRental.getDaysRented() - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                thisAmount += aRental.getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (aRental.getDaysRented() > 3)
                    thisAmount += (aRental.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

}
