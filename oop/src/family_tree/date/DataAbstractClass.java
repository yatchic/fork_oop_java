package family_tree.date;

public abstract class DataAbstractClass {
    abstract  protected int raznitsaVremeni(String birthDateStr, String deathDateStr);

    protected abstract int calculateAge(String birthDateStr);
}
