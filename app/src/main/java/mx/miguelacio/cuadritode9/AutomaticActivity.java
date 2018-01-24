    package mx.miguelacio.cuadritode9;

    import android.os.AsyncTask;
    import android.os.Handler;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.GridView;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.PriorityQueue;

    import mx.miguelacio.cuadritode9.models.Board;
    import mx.miguelacio.cuadritode9.models.Cell;
    import mx.miguelacio.cuadritode9.models.Node;
    import mx.miguelacio.cuadritode9.utils.PuzzleAdapter;

    public class AutomaticActivity extends AppCompatActivity {

        Board board = new Board();
        GridView gridView;
        PuzzleAdapter gridAdapter;
        int movements = 0;
        TextView textViewMovements;
        ArrayList<ArrayList<Cell>> pasos;

        public int dimension = 3;
        int[] row = { 1, 0, -1, 0 };
        int[] col = { 0, -1, 0, 1 };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_automatic);

            gridView = findViewById(R.id.grid_view);
            textViewMovements= findViewById(R.id.text_view_movements);

            board.populateArray();
            /*

            ArrayList<Cell> cells = new ArrayList<>();

            cells.add(new Cell("1"));
            cells.add(new Cell("2"));
            cells.add(new Cell("3"));
            cells.add(new Cell("4"));
            cells.add(new Cell("5"));
            cells.add(new Cell("6"));
            cells.add(new Cell(" "));
            cells.add(new Cell("7"));
            cells.add(new Cell("8"));
            */


            gridAdapter = new PuzzleAdapter(board.getCellArrayList(), AutomaticActivity.this);

            gridView.setAdapter(gridAdapter);



            int zero [] = new int[3];
            int one [] = new int[3];
            int two [] =  new int[3];
            int index = -1;
            int newX =-1, newY =-1;

            for (int i = 0; i < 3; i++) {
                if (board.getCellArrayList().get(i).getValue().equals(" ")){
                    zero[i]= 0;
                } else {
                    int value = Integer.parseInt(board.getCellArrayList().get(i).getValue());
                    zero[i] = value;
                }
            }
            for (int j = 3; j < 6; j++) {
                if (board.getCellArrayList().get(j).getValue().equals(" ")){

                    one[j-3]= 0;
                } else {
                    int value = Integer.parseInt(board.getCellArrayList().get(j).getValue());
                    one[j-3] = value;
                }
            }
            for (int k = 6; k < 9; k++) {
                if (board.getCellArrayList().get(k).getValue().equals(" ")){
                    two[k-6]= 0;
                } else {
                    int value = Integer.parseInt(board.getCellArrayList().get(k).getValue());
                    two[k-6] = value;
                }
            }

            for (int w = 0; w < board.getCellArrayList().size(); w++) {
                if (board.getCellArrayList().get(w).getValue().equals(" ")){
                    index = w;
                }

            }
            switch (index){
                case 0:
                    newX = 0;
                    newY = 0;
                    break;
                case 1:
                    newX = 0;
                    newY = 1;
                    break;
                case 2:
                    newX = 0;
                    newY = 2;
                    break;
                case 3:
                    newX = 1;
                    newY = 0;
                    break;
                case 4:
                    newX = 1;
                    newY = 1;
                    break;
                case 5:
                    newX = 1;
                    newY = 2;
                    break;
                case 6:
                    newX = 2;
                    newY = 0;
                    break;
                case 7:
                    newX = 2;
                    newY = 1;
                    break;
                case 8:
                    newX = 2;
                    newY = 2;
                    break;

            }

            int [][] secondinital = {zero, one, two};
            int[][] goal = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };


            if (isSolvable(secondinital)) {
                solve(secondinital, goal, newX, newY);
            }
            else {
                Toast.makeText(this, "El puzzle no puede ser resuelto", Toast.LENGTH_SHORT).show();

            }

            pasos = board.getPasos();

            final Handler h = new Handler();
            final Runnable r = new Runnable() {
                public void run() {

            if (movements<pasos.size()) {
                gridAdapter = new PuzzleAdapter(pasos.get(movements), AutomaticActivity.this);
                gridView.setAdapter(gridAdapter);
                movements++;
                h.postDelayed(this, 500);
                    }
                }
            };

            h.postDelayed(r, 500);


        }

        public void solve(int[][] initial, int[][] goal, int x, int y) {
            PriorityQueue<Node> pq = new PriorityQueue<mx.miguelacio.cuadritode9.models.Node>(1000, (a, b) -> (a.cost + a.level) - (b.cost + b.level));
            mx.miguelacio.cuadritode9.models.Node root = new mx.miguelacio.cuadritode9.models.Node(initial, x, y, x, y, 0, null);
            root.cost = calculateCost(initial, goal);
            pq.add(root);


            while (!pq.isEmpty()) {

               Node min = pq.poll();
                if (min.cost == 0) {
                    printPath(min);
                    return;
                }

                for (int i = 0; i < 4; i++) {

                    if (isSafe(min.x + row[i], min.y + col[i])) {
                        Node child = new Node(min.matrix, min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min);
                        child.cost = calculateCost(child.matrix, goal);
                        pq.add(child);
                    }
                }

            }


        }



        public int calculateCost(int[][] initial, int[][] goal) {
            int count = 0;
            int n = initial.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {
                        count++;
                    }
                }
            }
            return count;
        }

        public void printMatrix(int[][] matrix) {

            for (int[] aMatrix : matrix) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(aMatrix[j] + " ");
                }
                System.out.println();
            }



        }

        public boolean isSafe(int x, int y) {
            return (x >= 0 && x < dimension && y >= 0 && y < dimension);
        }

        public void printPath(mx.miguelacio.cuadritode9.models.Node root) {
            if (root == null) {
                return;
            }
                    printMatrix(root.matrix);
                    printPath(root.parent);
                    System.out.println();

                    board.updateArrayWithArray(root.matrix);
        }

        public boolean isSolvable(int[][] matrix) {
            int count = 0;
            List<Integer> array = new ArrayList<Integer>();

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    array.add(matrix[i][j]);
                }
            }

            Integer[] anotherArray = new Integer[array.size()];
            array.toArray(anotherArray);

            for (int i = 0; i < anotherArray.length - 1; i++) {
                for (int j = i + 1; j < anotherArray.length; j++) {
                    if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
                        count++;
                    }
                }
            }

            return count % 2 == 0;
        }






    }
