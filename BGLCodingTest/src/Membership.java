
public class Membership implements Comparable<Membership>{

    private String name;
    private double proportion;
    private boolean isPension;
    public Membership(String name,double proportion) {
        this.name = name;
        this.proportion = proportion;
        isPension = false;
    }

    public Membership(String name,double proportion,boolean isPension) {
        this.name = name;
        this.proportion = proportion;
        this.isPension = isPension;
    }

    public boolean isPension() {
        return isPension;
    }

    public void setPension(boolean pension) {
        isPension = pension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    @Override
    public int compareTo(Membership o) {
        if(this.getProportion() - o.getProportion() > 0)
            return 1;
        else if(this.getProportion() - o.getProportion() == 0)
            return 0;
        else
            return -1;
    }
}
