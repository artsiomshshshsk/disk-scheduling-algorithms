package DataVizualization;

public class HeadMovement {
    private int time;
    private int location;

    public HeadMovement(int time, int location) {
        this.time = time;
        this.location = location;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HeadMovement{" +
                "time=" + time +
                ", location=" + location +
                '}';
    }
}
