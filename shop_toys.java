import java.util.List;

public class shop_toys {
    private Integer idToy;
    private String name;
    private Integer weight;
    public static int idCount = 1;

    public shop_toys(String name, Integer weight) {
        this.idToy = shop_toys.idCount++;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String toString()
    {
        return String.format(new StringBuilder().append("\tID: %d; ")
            .append("Вес: %d; ")
            .append("Наименование: %s")
            .toString(), idToy, weight, name);
    }

    public int getWeights(List<shop_toys> toys) {
        return this.weight;
    }

    public Integer getWeightToy() {
        return this.weight;
    }

    public int getID() {
        return this.idToy;
    }

    public boolean containsID(Integer value) {
        if (shop_toys.this.idToy == value) return true;
        else return false;
    }

    public void setWeight(int value) {
        this.weight = value;
    }
}