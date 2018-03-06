package pflb.entity.master;

public class masterFile {

    private String Name;
    private String URL;

    public masterFile() {
    }

    public masterFile(String name, String URL) {
        Name = name;
        this.URL = URL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
