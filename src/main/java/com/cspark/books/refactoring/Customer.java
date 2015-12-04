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
        Enumeration rentals = _rentals.elements();
        String result = getName() + " 고객님의 대여 기록\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            // 이번에 대여하는 비디오 정보와 대여료 출력
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        // 푸터 행 추가
        result += "누적 대여료: " + String.valueOf(getTotalAmount()) + "\n";
        result += "적립 포인트: " + String.valueOf(getFrequentRenterPoints());
        return result;
    }

    public String htmlStatements() {
        Enumeration rentals = _rentals.elements();
        String result = "<P><H1><EM>" + getName() + " 고객님의 대여 기록</EM></H1></P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            // 이번에 대여하는 비디오 정보와 대여료 출력
            result += each.getMovie().getTitle() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
        }

        // 푸터 행 추가
        result += "<P>누적 대여료: <EM>" + String.valueOf(getTotalAmount()) + "</EM></P>\n";
        result += "<P>적립 포인트: <EM>" + String.valueOf(getFrequentRenterPoints()) + "</EM></P>";
        return result;
    }

    private int getFrequentRenterPoints() {
        int result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            // 경우에 따른 적립 포인트 지급 함수를 호출
            result += each.getFrequentRenterPoints();
        }
        return result;
    }

    private double getTotalAmount() {
        double result = 0;
        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            // 현재까지 누적된 총 대여료
            result += each.getCharge();
        }

        return result;
    }

}
