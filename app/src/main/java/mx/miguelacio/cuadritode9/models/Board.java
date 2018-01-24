package mx.miguelacio.cuadritode9.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by miguelacio on 19/01/18.
 */

public class Board {

    ArrayList<Cell> cellArrayList = new ArrayList<>();

    ArrayList<ArrayList<Cell>> pasos = new ArrayList<>();

    public ArrayList<ArrayList<Cell>> getPasos() {
        return pasos;
    }

    public ArrayList<Cell> getCellArrayList() {
        return cellArrayList;
    }

    public void populateArray(){

        Random randy = new Random();
        int[] readArray = new int[9];
        for (int i = 0; i < readArray.length; i++) {
            int temp;
            boolean isExists;
            do {
                isExists = false;
                temp = randy.nextInt(9);

                for (int j = 0; j < i; j++)
                {
                    if (readArray[j] == temp)
                    {
                        isExists = true;
                        break;
                    }
                }
            } while (isExists);
            readArray[i] = temp;
        }

        for (int aReadArray : readArray) {
            if (aReadArray == 0) {
                cellArrayList.add(new Cell(" "));
            } else {
                cellArrayList.add(new Cell(String.valueOf(aReadArray)));
            }
        }



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

    public void updateArrayWithArray(int [][] matrix){

        ArrayList<Cell> helperCellArrayList = new ArrayList<>();

        int[] zero = matrix[0];
        int[] one = matrix[1];
        int[] two = matrix[2];

        for (int i = 0; i < zero.length; i++) {
            if (zero[i] == 0){
                helperCellArrayList.add(new Cell(" "));
            } else {
                helperCellArrayList.add(new Cell(String.valueOf(zero[i])));
            }

        }
        for (int i = 0; i < one.length; i++) {
            if (one[i] == 0){
                helperCellArrayList.add( new Cell(" "));
            } else {
                helperCellArrayList.add( new Cell(String.valueOf(one[i])));
            }

        }
        for (int i = 0; i < two.length; i++) {
            if (two[i] == 0){
                helperCellArrayList.add( new Cell(" "));
            } else {
                helperCellArrayList.add( new Cell(String.valueOf(two[i])));
            }

        }

        cellArrayList = helperCellArrayList;
        pasos.add(helperCellArrayList);

    };
}
