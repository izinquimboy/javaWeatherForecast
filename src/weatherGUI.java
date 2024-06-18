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

        setLocationRelativeTo(null);

        setLayout(null);

        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {
        JLabel text = new JLabel();
        text.setBounds(10, 20, 651, 50);
        text.setText("Enter\n" + "Location: ");
        text.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(text);
        
        JTextField searchText = new JTextField();
        searchText.setBounds(201, 20, 540, 50);
        searchText.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(searchText);

        JButton search = new JButton(loadImage("javaWeatherForecast/src/assets/search.png"));
        search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        search.setBounds(740, 20, 50, 50);
        add(search);

        JLabel weatherConditionImg = new JLabel(loadImage("javaWeatherForecast/src/assets/cloudy.png"));
        weatherConditionImg.setBounds(-50, 40, 450, 300);
        add(weatherConditionImg);

        JLabel weatherDesc = new JLabel();
        weatherDesc.setText("Cloudy");
        weatherDesc.setFont(new Font("SansSerif", Font.BOLD, 50));
        weatherDesc.setBounds(350, -20, 600, 300);
        add(weatherDesc);

        JLabel tempText = new JLabel("100°F");
        tempText.setFont(new Font("Monospaced", Font.PLAIN, 21));
        tempText.setBounds(400, -20, 600, 300);
        add(tempText);

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
