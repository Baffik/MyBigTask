package JSON;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Nika on 31.07.2017.
 */
public class Otvet extends JFrame{
    private JTextArea textArea1;
    private JLabel label1;
    private JPanel panel;

    public static  String URL = "https://en.wikipedia.org/wiki/";

    public Otvet(String name)
    {
        URL += name;
        setContentPane(panel);
        setPreferredSize(new Dimension(600, 600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        label1.setText(label1.getText() + " " + name);

        try {
            URL url = JSon.createUrl(URL);
            System.out.println("Полученный JSON:\n" + url);
            textArea1.setText(URL);

            //String resultJson = JSon.parseUrl(url);
           // textArea1.setText(resultJson);
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        pack();
        setVisible(true);
    }
}
