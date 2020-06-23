package sdis.projet.app.etare.model;

public class Contact {
    private int id_contact;
    private String name_contact;
    private String job_contact;
    private String phone_contact;
    private String mobilephone_contact;
    private String email_contact;
    private int id_etare;
    private int urgence;

    public int getUrgence() {
        return urgence;
    }

    public void setUrgence(int urgence) {
        this.urgence = urgence;
    }

    public Contact(){

    }
    public Contact(String name_contact, String job_contact, String phone_contact, String mobilephone_contact, String email_contact) {
        this.name_contact = name_contact;
        this.job_contact = job_contact;
        this.phone_contact = phone_contact;
        this.mobilephone_contact = mobilephone_contact;
        this.email_contact = email_contact;
    }



    public Contact(String name_contact, String job_contact, String phone_contact, String mobilephone_contact, String email_contact, int id_etare,int urgence) {
        this.name_contact = name_contact;
        this.job_contact = job_contact;
        this.phone_contact = phone_contact;
        this.mobilephone_contact = mobilephone_contact;
        this.email_contact = email_contact;
        this.id_etare = id_etare;
        this.urgence = urgence;
    }

    public Contact(int id_contact, String name_contact, String job_contact, String phone_contact, String mobilephone_contact, String email_contact) {
        this.id_contact = id_contact;
        this.name_contact = name_contact;
        this.job_contact = job_contact;
        this.phone_contact = phone_contact;
        this.mobilephone_contact = mobilephone_contact;
        this.email_contact = email_contact;
    }

    public Contact(int id_contact, String name_contact, String job_contact, String phone_contact, String mobilephone_contact, String email_contact, int id_etare) {
        this.id_contact = id_contact;
        this.name_contact = name_contact;
        this.job_contact = job_contact;
        this.phone_contact = phone_contact;
        this.mobilephone_contact = mobilephone_contact;
        this.email_contact = email_contact;
        this.id_etare = id_etare;
    }

    public int getId_contact() {
        return id_contact;
    }

    public void setId_contact(int id_contact) {
        this.id_contact = id_contact;
    }

    public String getName_contact() {
        return name_contact;
    }

    public void setName_contact(String name_contact) {
        this.name_contact = name_contact;
    }

    public String getJob_contact() {
        return job_contact;
    }

    public void setJob_contact(String job_contact) {
        this.job_contact = job_contact;
    }

    public String getPhone_contact() {
        return phone_contact;
    }

    public void setPhone_contact(String phone_contact) {
        this.phone_contact = phone_contact;
    }

    public String getMobilephone_contact() {
        return mobilephone_contact;
    }

    public void setMobilephone_contact(String mobilephone_contact) {
        this.mobilephone_contact = mobilephone_contact;
    }

    public String getEmail_contact() {
        return email_contact;
    }

    public void setEmail_contact(String email_contact) {
        this.email_contact = email_contact;
    }

    public int getId_etare() {
        return id_etare;
    }

    public void setId_etare(int id_etare) {
        this.id_etare = id_etare;
    }
}
