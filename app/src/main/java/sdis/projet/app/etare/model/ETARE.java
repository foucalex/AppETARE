package sdis.projet.app.etare.model;

public class ETARE extends Location {

    private int id_etare;
    private String creation_date;
    private String update_date;
    private String status;
    private int id_level;
    private int id_town;
    private int id_location;

    public ETARE() {
    }

    public ETARE(String creation_date, String update_date, String status) {
        this.creation_date = creation_date;
        this.update_date = update_date;
        this.status = status;
    }

    public ETARE(int id_etare, String creation_date, String update_date, String status) {
        this.id_etare = id_etare;
        this.creation_date = creation_date;
        this.update_date = update_date;
        this.status = status;
    }

    public ETARE(String creation_date, String update_date, String status, int id_level, int id_town, int id_location) {
        this.creation_date = creation_date;
        this.update_date = update_date;
        this.status = status;
        this.id_level = id_level;
        this.id_town = id_town;
        this.id_location = id_location;
    }

    public ETARE(int id_etare, String creation_date, String update_date, String status, int id_level, int id_town, int id_location) {
        this.id_etare = id_etare;
        this.creation_date = creation_date;
        this.update_date = update_date;
        this.status = status;
        this.id_level = id_level;
        this.id_town = id_town;
        this.id_location = id_location;
    }

    public int getId_etare() {
        return id_etare;
    }

    public void setId_etare(int id_etare) {
        this.id_etare = id_etare;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_level() {
        return id_level;
    }

    public void setId_level(int id_level) {
        this.id_level = id_level;
    }

    public int getId_town() {
        return id_town;
    }

    public void setId_town(int id_town) {
        this.id_town = id_town;
    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }
}
