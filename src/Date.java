import java.io.Serializable;
//date class to track month, day, and year

public class Date implements Serializable {
    int month, day, year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;

    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }



    public int getYear() {
        return year;
    }
}


