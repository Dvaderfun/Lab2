package oop;

public class TimeSpan {

    private int hours;
    private int minutes;

    public TimeSpan(int hours, int minutes) {
        if (isValidTime(hours, minutes)) {
            this.hours = hours;
            this.minutes = minutes;
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getTotalHours() {
        double value = (double) minutes / 60;
        return hours + value;
    }

    public int getTotalMinutes() {
        int value = hours * 60;
        return value + minutes;
    }

    public void add(int hours, int minutes) {
        if (isValidTime(hours, minutes)) {
            this.hours = this.hours + hours;
            this.minutes = this.minutes + minutes;
            convertTimeCorrectly();
        }
    }

    public void addTimeSpan(TimeSpan span) {
        hours = hours + span.getHours();
        minutes = minutes + span.getMinutes();
        convertTimeCorrectly();
    }

    public void subtract(TimeSpan span) throws IllegalArgumentException {
        int subtractMinutes = span.getMinutes();
        int subtractHours = span.getHours();

        if (getTotalMinutes() >= span.getTotalMinutes()){
            hours = hours - subtractHours;
            minutes = minutes - subtractMinutes;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void scale(int factor) {
        if (factor >= 0){
            hours = hours * factor;
            minutes = minutes * factor;
            convertTimeCorrectly();
        }

    }

    private boolean isValidTime(int hours, int minutes) {
        return minutes >= 0 && minutes < 60 && hours >= 0;
    }

    private void convertTimeCorrectly() {
        hours = hours + minutes / 60;
        minutes = minutes % 60;
    }

}
