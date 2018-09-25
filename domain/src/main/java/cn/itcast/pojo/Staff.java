package cn.itcast.pojo;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

public class Staff {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "^1[345678][0-9]{9}$")
    private String telephone;

    private String haspda;

    private String deltag;

    @NotBlank
    private String station;
    @NotBlank
    private String standard;

    public Staff(String id, String name, String telephone, String haspda, String deltag, String station, String standard) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.haspda = haspda;
        this.deltag = deltag;
        this.station = station;
        this.standard = standard;
    }

    public Staff() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getHaspda() {
        return haspda;
    }

    public void setHaspda(String haspda) {
        this.haspda = haspda == null ? null : haspda.trim();
    }

    public String getDeltag() {
        return deltag;
    }

    public void setDeltag(String deltag) {
        this.deltag = deltag == null ? null : deltag.trim();
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station == null ? null : station.trim();
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard == null ? null : standard.trim();
    }
}