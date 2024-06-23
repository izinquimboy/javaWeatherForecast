import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.print.attribute.standard.JobStateReasons;

public class weatherApp {
    public static JSONObject getWeatherData(String locationName) {
        JSONArray locationData = getLocationData(locationName);

        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");
        String timezone = (String) location.get("timezone");

        String urlString = "https://api.open-meteo.com/v1/forecast?latitude="+ latitude + "&longitude="+ longitude + 
                                "&current=temperature_2m,relative_humidity_" +
                                "2m,apparent_temperature,precipitation,weather_code,wind_speed_10m&h" +
                                "ourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperatu" +
                                "re_2m_min,uv_index_max&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_uni" +
                                "t=inch&timezone" + timezone;
        

        try {
            HttpURLConnection conn = fetchApiResponse(urlString);

            if(conn.getResponseCode() != 200) {
                System.out.print("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJSon = new StringBuilder();
            Scanner scnr = new Scanner(conn.getInputStream());
            while(scnr.hasNext()) {
                resultJSon.append(scnr.nextLine());
            }

            scnr.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(String.valueOf(resultJSon));

            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONObject daily = (JSONObject) obj.get("daily");
            JSONObject current = (JSONObject) obj.get("current");

            int index = findIndexOFCurrentTime(hourly); //index of current time in array

            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static int findIndexOfCurrentTime(JSONArray timeList){
        String currentTime = getCurrentTime();

        // iterate through the time list and see which one matches our current time
        for(int i = 0; i < timeList.size(); i++){
            String time = (String) timeList.get(i);
            if(time.equalsIgnoreCase(currentTime)){
                // return the index
                return i;
            }
        }

        return 0;
    }

    private static String getCurrentTime(){
        // get current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // format date to be 2023-09-02T00:00 (this is how is is read in the API)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");

        // format and print the current date and time
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }
}
