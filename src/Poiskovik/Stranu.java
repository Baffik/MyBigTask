package JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static JSON.DBConnection.query;
import static JSON.DBConnection.rs;

/**
 * Created by Nika on 29.07.2017.
 */
public class Stranu  extends JFrame{
    private JComboBox comboBox1;
    private JButton Button;
    private JPanel panel;
    private JTable table2;
    private JLabel label1;

    Stranu(final String name) {
        setContentPane(panel);
        setPreferredSize(new Dimension(600, 600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        String names = name.replaceAll(" ", "_");

            table2.setVisible(false);
  //      ADDCity aCity = new ADDCity();
  //      aCity.Cities(name);
    //    table2.setModel(aCity);



        pack();
        setVisible(true);

        try {
            DBConnection.rs = query("SELECT * from " + names);
            while (rs.next()) {
                String City = rs.getString("City");
                comboBox1.addItem(City);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String city = (String)comboBox1.getSelectedItem();
                new Otvet(city);
            }
        });
    }
}
