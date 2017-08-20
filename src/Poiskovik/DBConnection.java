package JSON;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nika on 29.07.2017.
 */
public class DBConnection {

    public static Properties propeties = new Properties();
    private static Connection mysqlConnect;

    public String root;
    public String password;
    public String nameDB;
    public static String url;
    public static ResultSet rs;
    public static Statement stat;


    public DBConnection(String root, String password, String nameDB) // polya classa
    {
        this.root = root;
        this.password = password;
        this.nameDB = nameDB;

        url = "jdbc:mysql://localhost:3306/" + this.nameDB;
        init();
    }


    public void init() {

        if (mysqlConnect == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");  // нужен для подключения драйвера
                mysqlConnect = DriverManager.getConnection(url, this.root, this.password); // возвращает наш юрл, что бы создать соединение
                stat = mysqlConnect.createStatement();
                System.out.println("Connect!");

                String createTableSQL = "CREATE TABLE IF NOT EXISTS Country(id int(100) NOT NULL auto_increment, Country varchar(100) NOT NULL, PRIMARY KEY (id))";
                stat.execute(createTableSQL);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // подпограмма для получения результата с таблици
    public static ResultSet query(String query) {
        // ResultSet rs=null;
        try {
            rs = stat.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    // подпограмма для операций с данными в таблице (Delete, Insert)
    public static void updateQuery(String query) {
        try {
            //Statement stmt = mysqlConnect.createStatement();
            stat.executeUpdate(query);
            //stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // создание таблиц стран
    public static void CreateTableRB(String tablename) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tablename + "(id int(100) NOT NULL auto_increment, City varchar(100) NOT NULL, PRIMARY KEY (id))";
        try {
            //  Statement stmt = mysqlConnect.createStatement();
            stat.execute(createTableSQL);
            // stat.close();
            System.out.println("Table " + tablename + " is created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // разбиение строк на страны и города

    static List<String> list3 = new ArrayList<String>();
    static List<String> spisokstran = new ArrayList<String>();
    static String slovo = "";

    //разделеить данные в файле
    public static void Razdel(StringBuilder stringbuilder) {

        String s = stringbuilder.toString();
       // s = s.replaceAll("\'","5");
        s = s.replaceAll("-","6");
        String[] raz = s.split(":");
        spisokstran.add(raz[0].replaceAll("[\\p{Punct}|\\n]", ""));

        for (int i = 0; i < raz.length; i++)  // суть в том что бы считывать по :, заменять все символы на !
        // и потом возвращать последнее значение, которое будет следующим названием таблици.
        {

            raz[i] = raz[i].replaceAll("[\\p{Punct}|\\n]", "!");
            String stroka = raz[i];
            // System.out.println(stroka);
            char[] ch = stroka.toCharArray();
            for (int j = 0; j < ch.length; j++) {
                if (!(ch[j] == '!')) {
                    slovo += String.valueOf(ch[j]);
                } else {
                    list3.add(slovo);
                    slovo = "";
                }
            }

            for (int iii = 0; iii < list3.size(); iii++) {
                if (list3.get(iii).equals("")) {
                    list3.remove(iii);
                    iii--;
                }
            }

            String table = spisokstran.get(0);
            updateQuery("INSERT INTO Country(Country)VALUES('" + table + "')");
            table = table.replaceAll(" ", "_");
            CreateTableRB(table);

            if (!(list3.size() == 0)) {
                spisokstran.set(0, list3.get(list3.size() - 1));
                list3.remove(list3.size() - 1);
            } else break;


            //  Collections.sort(list3);
            for (int h = 0; h < list3.size(); h++) {

                    String city = list3.get(h);
                    city = city.replaceAll("6","-");
                    updateQuery("INSERT INTO " + table + "(City)VALUES('" + city + "')");
                }


                list3.clear();
            }
        }
    }





