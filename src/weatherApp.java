import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import netscape.javascript.JSObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.print.attribute.standard.JobStateReasons;

public class weatherApp {
    @SuppressWarnings("unchecked")
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

            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time); //index of current time in array

            double currentTemp = (double) current.get("temperature_2m");
            double apparTemp = (double) current.get("apparent_temperature");
            String currentCode = convertWeatherCode((long) current.get("weather_code"));
            double currentPrecip = (double) current.get("precipitation");
            long currentHumid = (long) current.get("relative_humidity_2m");
            String currentTime = (String) current.get("time");
            double currentWind = (double) current.get("wind_speed_10m");


            JSONArray dailyTempHi = (JSONArray) daily.get("temperature_2m_max");
            double daily1Hi = (double) dailyTempHi.get(1);
            double daily2Hi = (double) dailyTempHi.get(2);
            double daily3Hi = (double) dailyTempHi.get(3);
            double daily4Hi = (double) dailyTempHi.get(4);
            double daily5Hi = (double) dailyTempHi.get(5);

            JSONArray dailyTempLo = (JSONArray) daily.get("temperature_2m_min");
            double daily1Lo = (double) dailyTempLo.get(1);
            double daily2Lo = (double) dailyTempLo.get(2);
            double daily3Lo = (double) dailyTempLo.get(3);
            double daily4Lo = (double) dailyTempLo.get(4);
            double daily5Lo = (double) dailyTempLo.get(5);

            JSONArray dailyCode = (JSONArray) daily.get("weather_code");
            String daily1Code = convertWeatherCode((long)dailyCode.get(1));
            String daily2Code = convertWeatherCode((long)dailyCode.get(2));
            String daily3Code = convertWeatherCode((long)dailyCode.get(3));
            String daily4Code = convertWeatherCode((long)dailyCode.get(4));
            String daily5Code = convertWeatherCode((long)dailyCode.get(5));

            JSONArray dailyTime = (JSONArray) daily.get("time");
            String day1 = (String) dailyTime.get(1);
            String day2 = (String) dailyTime.get(2);
            String day3 = (String) dailyTime.get(3);
            String day4 = (String) dailyTime.get(4);
            String day5 = (String) dailyTime.get(5);


            JSONArray hourlyTemp = (JSONArray) hourly.get("temperature_2m");
            double hourly1Temp = (double) hourlyTemp.get(index + 1);
            double hourly2Temp = (double) hourlyTemp.get(index + 2);
            double hourly3Temp = (double) hourlyTemp.get(index + 3);
            double hourly4Temp = (double) hourlyTemp.get(index + 4);
            double hourly5Temp = (double) hourlyTemp.get(index + 5);

            JSONArray hourlyCode = (JSONArray) hourly.get("weather_code");
            String hourly1Code = convertWeatherCode((long)hourlyCode.get(index + 1));
            String hourly2Code = convertWeatherCode((long)hourlyCode.get(index + 2));
            String hourly3Code = convertWeatherCode((long)hourlyCode.get(index + 3));
            String hourly4Code = convertWeatherCode((long)hourlyCode.get(index + 4));
            String hourly5Code = convertWeatherCode((long)hourlyCode.get(index + 5));

            JSONArray hourlyTime = (JSONArray) hourly.get("time");
            String time1 = (String) hourlyTime.get(index + 1);
            String time2 = (String) hourlyTime.get(index + 2);
            String time3 = (String) hourlyTime.get(index + 3);
            String time4 = (String) hourlyTime.get(index + 4);
            String time5 = (String) hourlyTime.get(index + 5);


            JSONObject weatherData = new JSONObject();
            weatherData.put("current_temp", currentTemp);
            weatherData.put("apparent_temp", apparTemp);
            weatherData.put("current_weather_code", currentCode);
            weatherData.put("current_precipitation", currentPrecip);
            weatherData.put("current_humidity", currentHumid);
            weatherData.put("current_time", currentTime);
            weatherData.put("current_wind", currentWind);
            
            weatherData.put("first_day_high", daily1Hi);
            weatherData.put("second_day_high", daily2Hi);
            weatherData.put("third_day_high", daily3Hi);
            weatherData.put("fourth_day_high", daily4Hi);
            weatherData.put("fifth_day_high", daily5Hi);
            
            weatherData.put("first_day_low", daily1Lo);
            weatherData.put("second_day_low", daily2Lo);
            weatherData.put("third_day_low", daily3Lo);
            weatherData.put("fourth_day_low", daily4Lo);
            weatherData.put("fifth_day_low", daily5Lo);

            weatherData.put("first_day_code", daily1Code);
            weatherData.put("second_day_code", daily2Code);
            weatherData.put("third_day_code", daily3Code);
            weatherData.put("fourth_day_code", daily4Code);
            weatherData.put("fifth_day_code", daily5Code);

            weatherData.put("first_day", day1);
            weatherData.put("second_day", day2);
            weatherData.put("third_day", day3);
            weatherData.put("fourth_day", day4);
            weatherData.put("fifth_day", day5);

            weatherData.put("first_hour_temp", hourly1Temp);
            weatherData.put("second_hour_temp", hourly2Temp);
            weatherData.put("third_hour_temp", hourly3Temp);
            weatherData.put("fourth_hour_temp", hourly4Temp);
            weatherData.put("fifth_hour_temp", hourly5Temp);

            weatherData.put("first_hour_code", hourly1Code);
            weatherData.put("second_hour_code", hourly2Code);
            weatherData.put("third_hour_code", hourly3Code);
            weatherData.put("fourth_hour_code", hourly4Code);
            weatherData.put("fifth_hour_code", hourly5Code);

            weatherData.put("first_hour", time1);
            weatherData.put("second_hour", time2);
            weatherData.put("third_hour", time3);
            weatherData.put("fourth_hour", time4);
            weatherData.put("fifth_hour", time5);

            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getLocationData(String city) {
        city = city.replaceAll(" ", "+"); 

        String URL = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + 
            "&count=1&language=en&format=json";

        try {
            HttpURLConnection conn = fetchApiResponse(URL);

            if(conn.getResponseCode() != 200) {
                System.out.print("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scnr = new Scanner(conn.getInputStream());

            while(scnr.hasNext()) {
                resultJson.append(scnr.nextLine());
            }

            scnr.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject resultsJSONObj = (JSONObject) parser.parse(String.valueOf(resultJson));

            JSONArray locationData = (JSONArray) resultsJSONObj.get("results");

            return locationData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; //could not find location
    }

    @SuppressWarnings("deprecation")
    public static HttpURLConnection fetchApiResponse(String URL) {
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //could not connect 
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

    private static String convertWeatherCode(long weathercode){
        String weatherCondition = "";
        if(weathercode == 0L){
            // clear
            weatherCondition = "Clear";
        }else if(weathercode > 0L && weathercode <= 3L){
            // cloudy
            weatherCondition = "Cloudy";
        }else if((weathercode >= 51L && weathercode <= 67L)
                    || (weathercode >= 80L && weathercode <= 99L)){
            // rain
            weatherCondition = "Rain";
        }else if(weathercode >= 71L && weathercode <= 77L){
            // snow
            weatherCondition = "Snow";
        }

        return weatherCondition;
    } 
}
