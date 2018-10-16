package studio.smartters.jewellary.pojo;

public class DbItem {
    private String id,url,gos,name,originalName,sold;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGos() {
        return gos;
    }

    public void setGos(String gos) {
        this.gos = gos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public DbItem(String id, String url, String gos, String name, String originalName, String sold) {
        this.id = id;
        this.url = url;
        this.gos = gos;
        this.name = name;
        this.originalName = originalName;
        this.sold = sold;

    }
}
