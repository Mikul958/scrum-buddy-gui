package model;

/**
 * An enumeration containing all categories that a project can select.
 * @author Michael Pikula
 */
public enum Category
{
    BUSINESS("Business"),
    DEVELOPMENT("Development"),
    EDUCATION("Education"),
    PERSONAL("Personal"),
    OTHER("Miscellaneous");

    public final String description;

    /**
     * Sets the description of the category equal to its corresponding string.
     * @param description The string corresponding to a given category.
     */
    private Category(String description)
    {
        this.description = description;
    }

    /**
     * Returns the description of this category.
     */
    public String toString()
    {
        return description;
    }
}
