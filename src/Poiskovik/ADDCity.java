package JSON;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.ArrayList;

import static JSON.DBConnection.query;
import static JSON.DBConnection.rs;

/**
 * Created by Nika on 30.07.2017.
 */
public class ADDCity extends AbstractTableModel {
    public int columnCount = 1;
    private static ArrayList<String[]> list;

    public ADDCity() {
        list = new ArrayList<String[]>();

        for (int i = 0; i < list.size(); i++) {
            list.add(new String[getColumnCount()]);
        }
    }


    @Override
    public int getRowCount() {

        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) // poluchaet znachenie s tablici

    {
        String[] rows = list.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "predmet";
            // case 1:
            // return "predmetu";
        }
        return "";
    }

    public void addData(String[] row) {
        int i = getColumnCount();
        String[] rowsTable = new String[i];
        rowsTable = row;
        list.add(rowsTable);
    }

    //делаем запрос на таблицу и заносим в тайбл все значения из таблици
    public void Cities(String nametable)
    {

        try {
           nametable = nametable.replaceAll(" ", "_");
            rs = query("SELECT * from "+nametable);
            while (rs.next()) {

                String city = rs.getString("City");

                String [] row = {city};
                addData(row);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
