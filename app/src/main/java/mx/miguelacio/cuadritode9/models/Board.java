package mx.miguelacio.cuadritode9.models;

import java.util.ArrayList;

/**
 * Created by miguelacio on 19/01/18.
 */

public class Board {

    ArrayList<Cell> cellArrayList = new ArrayList<>();

    public ArrayList<Cell> getCellArrayList() {
        return cellArrayList;
    }

    public void populateArray(){

        cellArrayList.add(new Cell("1"));
        cellArrayList.add(new Cell("2"));
        cellArrayList.add(new Cell("3"));
        cellArrayList.add(new Cell("4"));
        cellArrayList.add(new Cell("5"));
        cellArrayList.add(new Cell("6"));
        cellArrayList.add(new Cell("7"));
        cellArrayList.add(new Cell(" "));
        cellArrayList.add(new Cell("8"));


    }

    public void updateArray(ArrayList<Cell> cellArrayListnew, String firstValue,
                            String secondValue){



        int firstindex = -1, secondIndex = -1;

        for (int i = 0; i < cellArrayListnew.size(); i++) {
            Cell cell = cellArrayListnew.get(i);
            if (cell.getValue().equals(firstValue)){
                firstindex = i;
            }
            if (cell.getValue().equals(secondValue)){
                secondIndex = i;
            }
        }

        Cell cellFirt = new Cell(firstValue);
        Cell cellSecond = new Cell(secondValue);

        cellArrayListnew.set(firstindex, cellSecond);
        cellArrayListnew.set(secondIndex, cellFirt);


        cellArrayList = cellArrayListnew;
    }
}
