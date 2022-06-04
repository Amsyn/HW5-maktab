package base.domain;

public abstract class BaseDomain <ID>{

    private ID id;

    public BaseDomain() {
    }

    public BaseDomain(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
