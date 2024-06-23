//import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class weatherGUI extends JFrame{
    private JSONObject weatherData;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                // display our weather app gui
                new weatherGUI().setVisible(true);

//                System.out.println(WeatherApp.getLocationData("Tokyo"));

//                System.out.println(WeatherApp.getCurrentTime());

            }
        });
    }


    public weatherGUI() {
        super("Weather Forecaster");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(800, 600);

        //setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setLocation(1100, 100);

        setLayout(null);

        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {


        JLabel text = new JLabel();
        text.setBounds(10, 20, 651, 50);
        text.setText("Enter Location: ");
        text.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(text);
        
        JTextField searchText = new JTextField();
        searchText.setBounds(200, 20, 530, 50);
        searchText.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchText);

        JButton search = new JButton(loadImage("javaWeatherForecast/src/assets/search.png"));
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search.setBounds(732, 22, 50, 45);
        add(search);

        JLabel weatherConditionImg = new JLabel(loadImage("javaWeatherForecast/src/assets/cloudy.png"));
        weatherConditionImg.setBounds(-60, 20, 450, 300);
        add(weatherConditionImg);

        JLabel tempText = new JLabel();
        tempText.setText("100°F");
        tempText.setFont(new Font("SansSerif", Font.BOLD, 50));
        tempText.setBounds(310, -30, 600, 300);
        add(tempText);

        JLabel weatherDesc = new JLabel("Cloudy", SwingConstants.CENTER);
        weatherDesc.setFont(new Font("SansSerif", Font.BOLD, 40));
        weatherDesc.setBounds(10, 108, 300, 300);
        weatherDesc.setHorizontalAlignment(JLabel.CENTER);
        add(weatherDesc);

        JLabel feelsLikeText = new JLabel("<html><b>Feels like</b> 95°F</html>");
        feelsLikeText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        feelsLikeText.setBounds(320, 15, 600, 300);
        add(feelsLikeText);

        JLabel precipText = new JLabel("<html><b>Preciptation</b> 2 in</html>");
        precipText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        precipText.setBounds(320,45, 600, 300);
        add(precipText);

        JLabel windText = new JLabel("<html><b>Wind Speed</b> 3mph</html>");
        windText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        windText.setBounds(320, 75, 600, 300);
        add(windText);

        JLabel humidText = new JLabel("<html><b>Humidity</b> 100%</html>");
        humidText.setFont(new Font("SansSerif", Font.PLAIN, 20));
        humidText.setBounds(320,105, 600, 300);
        add(humidText);

        JLabel locationText = new JLabel("New York");
        locationText.setFont(new Font("SansSerif", Font.BOLD, 30));
        locationText.setBounds(550, -105, 225, 400);
        locationText.setHorizontalAlignment(JLabel.CENTER);
        add(locationText);

        JLabel timeText = new JLabel("14:00 | 6/20/24");
        timeText.setFont(new Font("SansSerif", Font.PLAIN, 15));
        timeText.setBounds(550, -80, 225, 400);
        timeText.setHorizontalAlignment(JLabel.CENTER);
        add(timeText);

        JPanel daily = new JPanel();
        daily.setLayout(null);
        daily.setBounds(550, 140, 225, 410);
        daily.setBackground(new Color(198, 204, 207));
        add(daily);

        JLabel daily1cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/clear.png", 50, 40));
        daily1cond.setBounds(67, 45, 60, 50);
        JLabel daily1date = new JLabel("6/21");
        daily1date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily1date.setBounds(12, 45, 60, 50);
        JLabel daily1temp = new JLabel("<html><b>100°F</b>/88°F</html>");
        daily1temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily1temp.setBounds(135, 45, 100, 50);
        JSeparator daily1Sep = new JSeparator();
        daily1Sep.setBounds(5, 100, 215, 20);
        daily1Sep.setBackground(new Color(0, 0, 0));
        
        JLabel daily2cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/rain.png", 50, 40));
        daily2cond.setBounds(67, 115, 60, 50);
        JLabel daily2date = new JLabel("6/22");
        daily2date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily2date.setBounds(12, 115, 60, 50);
        JLabel daily2temp = new JLabel("<html><b>93°F</b>/81°F</html>");
        daily2temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily2temp.setBounds(135, 115, 100, 50);
        JSeparator daily2Sep = new JSeparator();
        daily2Sep.setBounds(5, 170, 215, 20);
        daily2Sep.setBackground(new Color(0, 0, 0));

        JLabel daily3cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/snow.png", 50, 40));
        daily3cond.setBounds(67, 190, 60, 50);
        JLabel daily3date = new JLabel("6/23");
        daily3date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily3date.setBounds(12, 190, 60, 50);
        JLabel daily3temp = new JLabel("<html><b>43°F</b>/21°F</html>");
        daily3temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily3temp.setBounds(135, 190, 100, 50);
        JSeparator daily3Sep = new JSeparator();
        daily3Sep.setBounds(5, 250, 215, 20);
        daily3Sep.setBackground(new Color(0, 0, 0));

        JLabel daily4cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/windspeed.png", 50, 40));
        daily4cond.setBounds(67, 270, 60, 50);
        JLabel daily4date = new JLabel("6/24");
        daily4date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily4date.setBounds(12, 270, 60, 50);
        JLabel daily4temp = new JLabel("<html><b>63°F</b>/51°F</html>");
        daily4temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily4temp.setBounds(135, 270, 100, 50);
        JSeparator daily4Sep = new JSeparator();
        daily4Sep.setBounds(5, 330, 215, 20);
        daily4Sep.setBackground(new Color(0, 0, 0));
        
        JLabel daily5cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/clear.png", 50, 40));
        daily5cond.setBounds(67, 350, 60, 50);
        JLabel daily5date = new JLabel("6/25");
        daily5date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily5date.setBounds(12, 350, 60, 50);
        JLabel daily5temp = new JLabel("<html><b>92°F</b>/71°F</html>");
        daily5temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily5temp.setBounds(135, 350, 100, 50);

        JLabel dailyTitle = new JLabel("5-Day Forecast");
        dailyTitle.setBounds(0, 0, 225, 50);
        dailyTitle.setFont(new Font("SansSerif", Font.BOLD, 21));
        dailyTitle.setHorizontalAlignment(SwingConstants.CENTER);

        daily.add(dailyTitle);
        daily.add(daily1cond);
        daily.add(daily1date);
        daily.add(daily1temp);
        daily.add(daily1Sep);
        daily.add(daily2cond);
        daily.add(daily2date);
        daily.add(daily2temp);
        daily.add(daily2Sep);
        daily.add(daily3cond);
        daily.add(daily3date);
        daily.add(daily3temp);
        daily.add(daily3Sep);
        daily.add(daily4cond);
        daily.add(daily4date);
        daily.add(daily4temp);
        daily.add(daily4Sep);
        daily.add(daily5cond);
        daily.add(daily5date);
        daily.add(daily5temp);

        JPanel hourly = new JPanel();
        hourly.setBounds(20, 320, 510, 230);
        hourly.setBackground(new Color(198, 204, 207));
        hourly.setLayout(null);
        add(hourly);

        JLabel hourly1time = new JLabel("15:00");
        hourly1time.setBounds(13, 15, 75, 70);
        hourly1time.setHorizontalAlignment(SwingConstants.CENTER);
        hourly1time.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly1cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/clear.png", 70, 60));
        hourly1cond.setBounds(13, 65, 75, 80);
        hourly1time.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hourly1desc = new JLabel("Clear");
        hourly1desc.setBounds(13, 120, 75, 70);
        hourly1desc.setHorizontalAlignment(SwingConstants.CENTER);
        hourly1desc.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly1temp = new JLabel("95°F");
        hourly1temp.setBounds(13, 150, 75, 70);
        hourly1temp.setHorizontalAlignment(SwingConstants.CENTER);
        hourly1temp.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JSeparator hourly1sep = new JSeparator(JSeparator.VERTICAL);
        hourly1sep.setBounds(95, 10, 10, 210);
        hourly1sep.setBackground(new Color(0, 0, 0));

        JLabel hourly2time = new JLabel("16:00");
        hourly2time.setBounds(113, 15, 75, 70);
        hourly2time.setHorizontalAlignment(SwingConstants.CENTER);
        hourly2time.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly2cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/cloudy.png", 70, 60));
        hourly2cond.setBounds(113, 65, 75, 80);
        hourly2time.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hourly2desc = new JLabel("Cloudy");
        hourly2desc.setBounds(113, 120, 75, 70);
        hourly2desc.setHorizontalAlignment(SwingConstants.CENTER);
        hourly2desc.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly2temp = new JLabel("90°F");
        hourly2temp.setBounds(113, 150, 75, 70);
        hourly2temp.setHorizontalAlignment(SwingConstants.CENTER);
        hourly2temp.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JSeparator hourly2sep = new JSeparator(JSeparator.VERTICAL);
        hourly2sep.setBounds(195, 10, 10, 210);
        hourly2sep.setBackground(new Color(0, 0, 0));

        JLabel hourly3time = new JLabel("17:00");
        hourly3time.setBounds(213, 15, 75, 70);
        hourly3time.setHorizontalAlignment(SwingConstants.CENTER);
        hourly3time.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly3cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/rain.png", 70, 60));
        hourly3cond.setBounds(213, 65, 75, 80);
        hourly3time.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hourly3desc = new JLabel("Rain");
        hourly3desc.setBounds(213, 120, 75, 70);
        hourly3desc.setHorizontalAlignment(SwingConstants.CENTER);
        hourly3desc.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly3temp = new JLabel("85°F");
        hourly3temp.setBounds(213, 150, 75, 70);
        hourly3temp.setHorizontalAlignment(SwingConstants.CENTER);
        hourly3temp.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JSeparator hourly3sep = new JSeparator(JSeparator.VERTICAL);
        hourly3sep.setBounds(295, 10, 10, 210);
        hourly3sep.setBackground(new Color(0, 0, 0));

        JLabel hourly4time = new JLabel("18:00");
        hourly4time.setBounds(313, 15, 75, 70);
        hourly4time.setHorizontalAlignment(SwingConstants.CENTER);
        hourly4time.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly4cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/snow.png", 70, 60));
        hourly4cond.setBounds(313, 65, 75, 80);
        hourly4time.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hourly4desc = new JLabel("Snow");
        hourly4desc.setBounds(313, 120, 75, 70);
        hourly4desc.setHorizontalAlignment(SwingConstants.CENTER);
        hourly4desc.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly4temp = new JLabel("35°F");
        hourly4temp.setBounds(313, 150, 75, 70);
        hourly4temp.setHorizontalAlignment(SwingConstants.CENTER);
        hourly4temp.setFont(new Font("SansSerif", Font.PLAIN, 20));
        JSeparator hourly4sep = new JSeparator(JSeparator.VERTICAL);
        hourly4sep.setBounds(395, 10, 10, 210);
        hourly4sep.setBackground(new Color(0, 0, 0));

        JLabel hourly5time = new JLabel("19:00");
        hourly5time.setBounds(413, 15, 75, 70);
        hourly5time.setHorizontalAlignment(SwingConstants.CENTER);
        hourly5time.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly5cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/windspeed.png", 70, 60));
        hourly5cond.setBounds(413, 65, 75, 80);
        hourly5time.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel hourly5desc = new JLabel("Windy");
        hourly5desc.setBounds(413, 120, 75, 70);
        hourly5desc.setHorizontalAlignment(SwingConstants.CENTER);
        hourly5desc.setFont(new Font("SansSerif", Font.BOLD,20));
        JLabel hourly5temp = new JLabel("65°F");
        hourly5temp.setBounds(413, 150, 75, 70);
        hourly5temp.setHorizontalAlignment(SwingConstants.CENTER);
        hourly5temp.setFont(new Font("SansSerif", Font.PLAIN, 20));

        hourly.add(hourly1time);
        hourly.add(hourly1cond);
        hourly.add(hourly1desc);
        hourly.add(hourly1temp);
        hourly.add(hourly1sep);
        hourly.add(hourly2time);
        hourly.add(hourly2cond);
        hourly.add(hourly2desc);
        hourly.add(hourly2temp);
        hourly.add(hourly2sep);
        hourly.add(hourly3time);
        hourly.add(hourly3cond);
        hourly.add(hourly3desc);
        hourly.add(hourly3temp);
        hourly.add(hourly3sep);
        hourly.add(hourly4time);
        hourly.add(hourly4cond);
        hourly.add(hourly4desc);
        hourly.add(hourly4temp);
        hourly.add(hourly4sep);
        hourly.add(hourly5time);
        hourly.add(hourly5cond);
        hourly.add(hourly5desc);
        hourly.add(hourly5temp);

        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = search.getText();

                // validate input - remove whitespace to ensure non-empty text
                if(userInput.replaceAll("\\s", "").length() <= 0){
                    return;
                }

                weatherData = weatherApp.getWeatherData(userInput);
                

            }
            
        });
    }   

    private ImageIcon loadImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));

            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("Could not find file");
        return null;
    }

    private ImageIcon resizeImage(String path, int width, int height) {
        ImageIcon ImageIcon = loadImage(path);
        Image image = ImageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        ImageIcon newImage = new ImageIcon(newimg);
        return newImage;
    }
}
