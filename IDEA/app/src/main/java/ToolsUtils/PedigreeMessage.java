package ToolsUtils;

public class PedigreeMessage {
    private String sex = null;
    private String mothermother = null;
    private String motherfather = null;
    private String fathermother = null;
    private String fatherfather = null;
    private String mother = null;
    private String father = null;
    private String address = null;
    private String birthday = null;
    private String birthAddress = null;
    private String birthWeight = null;
    private String variety = null;
    private String coatColor = null;

    public void setSexMale() {
        this.sex = "1";
    }

    public void setSexFemale(){
        this.sex = "0";
    }

    public void setCoatColor(String s){
        this.coatColor = s;
    }

    public void setFatherfather(String fatherfather) {
        this.fatherfather = fatherfather;
    }

    public void setFathermother(String fathermother) {
        this.fathermother = fathermother;
    }

    public void setMotherfather(String motherfather) {
        this.motherfather = motherfather;
    }

    public void setMothermother(String mothermother) {
        this.mothermother = mothermother;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public void setBirthWeight(String birthWeight) {
        this.birthWeight = birthWeight;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBirthWeight() {
        return birthWeight;
    }

    public String getCoatColor() {
        return coatColor;
    }

    public String getFather() {
        return father;
    }

    public String getFatherfather() {
        return fatherfather;
    }

    public String getFathermother() {
        return fathermother;
    }

    public String getMother() {
        return mother;
    }

    public String getMotherfather() {
        return motherfather;
    }

    public String getMothermother() {
        return mothermother;
    }

    public String getSex() {
        return sex;
    }

    public String getVariety() {
        return variety;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setnull(){
        this.sex = "";
        this.mothermother = "";
        this.motherfather = "";
        this.fathermother = "";
        this.fatherfather = "";
        this.mother = "";
        this.father = "";
        this.address = "";
        this.birthday = "";
        this.birthAddress = "";
        this.birthWeight = "";
        this.variety = "";
        this.coatColor = "";
    }
}
