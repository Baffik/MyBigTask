package JSON;

import java.io.IOException;
import java.net.URL;

import static JSON.DBConnection.Razdel;


/**
 * Created by Nika on 29.07.2017.
 */
public class Zapusk {


    public static void main(String[] args) throws IOException {


       DBConnection DC = new DBConnection("root", "", "Test");
       Pusk pusk = new Pusk(DC);
    //   GlavnoeOkno GO = new GlavnoeOkno(DC, resultJson);

    }
    }

