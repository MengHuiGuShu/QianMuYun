package ToolsUtils;

public class MedicineMessage {

    private String name;
    private String vaccinationMethod;
    private String dose;
    private String Immuneperiod;
    private String id;
    private String time;
    private String noVaccination;
    private String noVaccinationCause;

    public String getTime() {
        return time;
    }

    public String getNoVaccinationCause() {
        return noVaccinationCause;
    }

    public String getNoVaccination() {
        return noVaccination;
    }

    public String getId() {
        return id;
    }

    public String getDose() {
        return dose;
    }

    public String getImmuneperiod() {
        return Immuneperiod;
    }

    public String getName() {
        return name;
    }

    public String getVaccinationMethod() {
        return vaccinationMethod;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public void setImmuneperiod(String immuneperiod) {
        Immuneperiod = immuneperiod;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVaccinationMethod(String vaccinationMethod) {
        this.vaccinationMethod = vaccinationMethod;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNoVaccination(String noVaccination) {
        this.noVaccination = noVaccination;
    }

    public void setNoVaccinationCause(String noVaccinationCause) {
        this.noVaccinationCause = noVaccinationCause;
    }
}
