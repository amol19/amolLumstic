package lumstic.example.com.lumstic.Utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import lumstic.example.com.lumstic.Models.Surveys;
import lumstic.example.com.lumstic.R;

public class JsonHelper {

    Context context;


    public JsonHelper(Context context){
        this.context= context;
    }

    public String getStringFromJson(){
        try{

            InputStream inputStream = context.
                    getResources().openRawResource(R.raw.json_data);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            return  new String(buffer);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }
    public List<Surveys> tryParsing(String rawJson){


        try {
            JSONArray jsonArray = new JSONArray(rawJson);
            JSONParser jsonParser= new JSONParser();
            List<Surveys> surveyses=  jsonParser.parseSurvey(jsonArray);

return  surveyses;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
