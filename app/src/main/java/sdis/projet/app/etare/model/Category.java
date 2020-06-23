package sdis.projet.app.etare.model;

public class Category {

    private int Id_category;
    private String Name_category;
    private String Commentary_category;
    private int Id_etare;

    public Category(){}

    public Category(String name_category, String commentary_category) {
        this.Name_category = name_category;
        this.Commentary_category = commentary_category;
    }

    public Category(String name_category, String commentary_category, int id_etare) {
        this.Name_category = name_category;
        this.Commentary_category = commentary_category;
        this.Id_etare = id_etare;
    }

    public Category(int id_category, String name_category, String commentary_category) {
        this.Id_category = id_category;
        this.Name_category = name_category;
        this.Commentary_category = commentary_category;
    }

    public Category(int id_category, String name_category, String commentary_category, int id_etare) {
        this.Id_category = id_category;
        this.Name_category = name_category;
        this.Commentary_category = commentary_category;
        this.Id_etare = id_etare;
    }

    public int getId_category() {
        return Id_category;
    }

    public void setId_category(int id_category) {
        this.Id_category = id_category;
    }

    public String getName_category() {
        return Name_category;
    }

    public void setName_category(String name_category) {
        this.Name_category = name_category;
    }

    public String getCommentary_category() {
        return Commentary_category;
    }

    public void setCommentary_category(String commentary_category) {
        this.Commentary_category = commentary_category;
    }

    public int getId_etare() {
        return Id_etare;
    }

    public void setId_etare(int id_etare) {
        this.Id_etare = id_etare;
    }
}
