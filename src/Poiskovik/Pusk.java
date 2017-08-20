package JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import static JSON.DBConnection.Razdel;

/**
 * Created by Nika on 31.07.2017.
 */
public class Pusk extends JFrame {
    private JButton LoadButton;
    private JTextField textField1;
    private JButton RunButton;
    private JPanel panel;

    public static final String URL = "https://raw.githubusercontent.com/David-Haim/CountriesToCitiesJSON/master/countriesToCities.json";

    public Pusk (final DBConnection DC) {
        setContentPane(panel);
        setPreferredSize(new Dimension(600, 600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);


        pack();
        setVisible(true);


        LoadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    URL url = JSon.createUrl(URL);
                    System.out.println("Полученный JSON:\n" + url);

                    String resultJson = JSon.parseUrl(url);
                    StringBuilder sb = new StringBuilder(resultJson);
                    Razdel(sb);

                    textField1.setText("Dannue zahrujennu!");

                } catch (IOException r) {
                    r.printStackTrace();
                }
            }
        });

        RunButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GlavnoeOkno GO = new GlavnoeOkno(DC);
                setVisible(false);

            }
        });
    }



}
