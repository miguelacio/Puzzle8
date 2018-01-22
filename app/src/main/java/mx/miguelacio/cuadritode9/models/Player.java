package mx.miguelacio.cuadritode9.models;

/**
 * Created by miguelacio on 19/01/18.
 */

public class Player {

    private String name;
    private String config;
    private String movements;
    private String time;
    private int id;

    public Player() {
        this.name = "";
        this.config = "";
        this.movements = "";
        this.time = "";
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
