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
    //private JSONObject weatherData;
    
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

        JLabel daily1cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/clear.png", 60, 50));
        daily1cond.setBounds(67, 10, 60, 50);
        daily.add(daily1cond);
        
        JLabel daily1date = new JLabel("6/21");
        daily1date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily1date.setBounds(12, 10, 60, 50);
        daily.add(daily1date);

        JLabel daily1temp = new JLabel("<html><b>100°F</b>/88°F</html>");
        daily1temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily1temp.setBounds(135, 10, 100, 50);
        daily.add(daily1temp);

        JSeparator daily1Sep = new JSeparator();
        daily1Sep.setBounds(5, 65, 215, 20);
        daily1Sep.setBackground(new Color(0, 0, 0));
        daily.add(daily1Sep);



        JLabel daily2cond = new JLabel(resizeImage("javaWeatherForecast/src/assets/rain.png", 60, 50));
        daily2cond.setBounds(67, 85, 60, 50);
        daily.add(daily2cond);
        
        JLabel daily2date = new JLabel("6/22");
        daily2date.setFont(new Font("SansSerif", Font.BOLD, 20));
        daily2date.setBounds(12, 85, 60, 50);
        daily.add(daily2date);

        JLabel daily2temp = new JLabel("<html><b>93°F</b>/81°F</html>");
        daily2temp.setFont(new Font("SansSerif", Font.PLAIN, 15));
        daily2temp.setBounds(135, 85, 100, 50);
        daily.add(daily2temp);

        JSeparator daily2Sep = new JSeparator();
        daily2Sep.setBounds(5, 140, 215, 20);
        daily2Sep.setBackground(new Color(0, 0, 0));
        daily.add(daily2Sep);

        JPanel hourly = new JPanel();
        hourly.setBounds(20, 320, 510, 230);
        hourly.setBackground(new Color(198, 204, 207));
        add(hourly);



        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
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
