package sdis.projet.app.etare.model;

public class Map {

    private int id_map;
    private String name_map;
    private String file_map;
    private String type_map;
    private String echelle_map;
    private int id_etare;

    public Map(){}

    public Map(String name_map, String file_map, String type_map,String echelle_map) {
        this.name_map = name_map;
        this.file_map = file_map;
        this.type_map = type_map;
        this.echelle_map = echelle_map;
    }

    public Map(int id_map, String name_map, String file_map, String type_map,String echelle_map) {
        this.id_map = id_map;
        this.name_map = name_map;
        this.file_map = file_map;
        this.type_map = type_map;
        this.echelle_map = echelle_map;

    }

    public Map(String name_map, String file_map, String type_map,String echelle_map, int id_etare) {
        this.name_map = name_map;
        this.file_map = file_map;
        this.type_map = type_map;
        this.echelle_map = echelle_map;
        this.id_etare = id_etare;
    }

    public Map(int id_map, String name_map, String file_map, String type_map,String echelle_map, int id_etare) {
        this.id_map = id_map;
        this.name_map = name_map;
        this.file_map = file_map;
        this.type_map = type_map;
        this.echelle_map = echelle_map;
        this.id_etare = id_etare;
    }

    public int getId_map() {
        return id_map;
    }

    public void setId_map(int id_map) {
        this.id_map = id_map;
    }

    public String getName_map() {
        return name_map;
    }

    public void setName_map(String name_map) {
        this.name_map = name_map;
    }

    public String getFile_map() {
        return file_map;
    }

    public void setFile_map(String file_map) {
        this.file_map = file_map;
    }

    public String getEchelle_map() {
        return echelle_map;
    }

    public void setEchelle_map(String echelle_map) {
        this.echelle_map = echelle_map;
    }

    public String getType_map() {
        return type_map;
    }

    public void setType_map(String type_map) {
        this.type_map = type_map;
    }

    public int getId_etare() {
        return id_etare;
    }

    public void setId_etare(int id_etare) {
        this.id_etare = id_etare;
    }
}
