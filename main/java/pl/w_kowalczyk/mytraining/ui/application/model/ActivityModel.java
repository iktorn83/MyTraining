package pl.w_kowalczyk.mytraining.ui.application.model;

public class ActivityModel {

    private String activity;
    private String date;
    private String heartrate;
    private String time;

    public ActivityModel(String activity, String date, String heartrate, String time) {

        this.activity = activity;
        this.date = date;
        this.heartrate = heartrate;
        this.time = time;

    }

    public ActivityModel() {

    }

    @Override
    public String toString() {
        return "ActivityModel{" +
                "activity='" + activity + '\'' +
                ", date='" + date + '\'' +
                ", heartrate='" + heartrate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getActivity() {
        return activity;
    }

    public String getDate() {
        return date;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public String getTime() {
        return time;
    }
}
