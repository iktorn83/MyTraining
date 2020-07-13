package pl.w_kowalczyk.mytraining.ui.application.model;

public class HistoryModel {

    public final String activity;
    public final String date;
    public final String heartrate;
    public final String time;

    public HistoryModel(String activity, String date, String heartrate, String time) {
        this.activity = activity;
        this.date = date;
        this.heartrate = heartrate;
        this.time = time;
    }

    @Override
    public String toString() {
        return "HistoryModel{" +
                "activity='" + activity + '\'' +
                ", date='" + date + '\'' +
                ", heartrate='" + heartrate + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
