package JSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static JSON.DBConnection.*;


/**
 * Created by Nika on 29.07.2017.
 */
public class GlavnoeOkno extends JFrame {
    private JComboBox comboBox1;
    private JButton Button;
    private JPanel panel;
    private JTable table1;

    public GlavnoeOkno(DBConnection DC)
    {

        //размещение по середине экрана
        setContentPane(panel);
        setPreferredSize(new Dimension(600, 600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 600;
        int sizeHeight = 600;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        table1.setVisible(false);
      //  ADDCountry AC = new ADDCountry();
      //  AC.ADDCountry();
      //  table1.setModel(AC);

        pack();


        try {
            DBConnection.rs = query("SELECT * from Country");
            while (rs.next()) {
                String country = rs.getString("Country");
                comboBox1.addItem(country);

            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


        setVisible(true);
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = (String)comboBox1.getSelectedItem();
                new Stranu(name);

            }
        });


    }





}
