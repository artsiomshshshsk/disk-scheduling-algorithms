package utils;

public class Query {
    private int location;
    private int deadline;
    private int appearanceTime;
    private boolean realTime;

    public Query(int location, int deadline, int appearanceTime, boolean realTime) {
        this.location = location;
        this.deadline = deadline;
        this.appearanceTime = appearanceTime;
        if(!realTime){
            this.deadline = -1;
        }
        this.realTime = realTime;
    }

    public int getLocation() {
        return location;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getAppearanceTime() {
        return appearanceTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query = (Query) o;

        if (location != query.location) return false;
        if (deadline != query.deadline) return false;
        return appearanceTime == query.appearanceTime;
    }


    public boolean isRealTime() {
        return realTime;
    }

    public void setAppearanceTime(int appearanceTime) {
        this.appearanceTime = appearanceTime;
    }

    @Override
    public int hashCode() {
        int result = location;
        result = 31 * result + deadline;
        result = 31 * result + appearanceTime;
        return result;
    }

    @Override
    public String toString() {
        return "Query{" +
                "location=" + location +
                ", deadline=" + deadline +
                ", appearanceTime=" + appearanceTime +
                ", realTime=" + realTime +
                '}';
    }
}
