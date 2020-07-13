package pl.w_kowalczyk.mytraining.util.other;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader extends AsyncTask<Void, Bitmap, Bitmap> {

    private String avatarUrl;
    private InputStream in;

    public Downloader(String Url) {
        avatarUrl = Url;
    }

    @Override
    public Bitmap doInBackground(Void... arg0) {

        try {
            URL url = new URL(avatarUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            in = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Wystąpił błąd w wątku Downloadera");
        }
        return BitmapFactory.decodeStream(in);
    }

    @Override
    public void onPostExecute(Bitmap result) {
        //  Toast.makeText(context,"Koniec watku Downloadera. Pobrano obraz",Toast.LENGTH_SHORT).show();
    }
}