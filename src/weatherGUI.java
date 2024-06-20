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
        text.setText("Enter\n " + "Location: ");
        text.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(text);
        
        JTextField searchText = new JTextField();
        searchText.setBounds(190, 20, 540, 50);
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

        JPanel daily = new JPanel();
        daily.setBounds(550, 150, 225, 400);
        daily.setBackground(new Color(198, 204, 207));
        add(daily);

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
}
