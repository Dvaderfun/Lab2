package oop;

public class TimeSpan {

    private int minutes;

    public TimeSpan(int hours, int minutes) {
        this.add(hours, minutes);
    }

    public int getHours() {
        return this.minutes / 60;
    }

    public int getMinutes() {
        return this.minutes - (getHours() * 60);
    }

    public void add(int hours, int minutes) {
        if (hours > -1 && minutes > -1 && minutes < 60)
            this.minutes = hours * 60 + minutes;
    }

    public void addTimeSpan(TimeSpan span) {
        this.minutes += span.getTotalMinutes();
    }

    public double getTotalHours() {
        double result = 0;

        int minutes_left = this.minutes - getHours() * 60;
        result = getHours() + (minutes_left / 60.0);

        return result;
    }

    public int getTotalMinutes() {
        return this.minutes;
    }

    public void subtract(TimeSpan span) {
        if (this.minutes >= span.getTotalMinutes())
            this.minutes = this.minutes - span.getTotalMinutes();
    }

    public void scale(int factor) {
        if (factor >= 0)
            this.minutes = this.minutes * factor;
    }

}
