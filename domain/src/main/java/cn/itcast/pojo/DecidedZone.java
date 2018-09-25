package cn.itcast.pojo;

public class DecidedZone {
    private String id;

    private String name;

    private String staffId;

    public DecidedZone(String id, String name, String staffId) {
        this.id = id;
        this.name = name;
        this.staffId = staffId;
    }

    public DecidedZone() {
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }
}