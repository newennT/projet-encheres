package fr.projet.enchere.projet_grp7_enchere.bo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the withdrawal information for an article.
 */
public class Retrait implements Serializable {
    private long no_retrait;
    @NotBlank(message = "{validation.rue.notBlank}")
    @Size(max = 30, message = "{validation.rue.size}")
    @Pattern(regexp = "^[a-zA-Z0-9À-ÿ\\- ]+$", message = "{validation.rue.pattern}")
    private String rue;

    @NotBlank(message = "{validation.code_postal.notBlank}")
    @Size(max = 10, message = "{validation.code_postal.size}")
    @Pattern(regexp = "^\\d{5}$", message = "{validation.code_postal.pattern}")
    private String code_postal;

    @NotBlank(message = "{validation.ville.notBlank}")
    @Size(max = 50, message = "{validation.ville.size}")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\- ]+$", message = "{validation.ville.pattern}")
    private String ville;



    /**
     * Default constructor for the Retrait class.
     */
    public Retrait() {
    }

    /**
     * Constructor for creating a Retrait object with all parameters.
     *
     * @param no_retrait  The unique identifier for the withdrawal.
     * @param rue         The street address for the withdrawal.
     * @param code_postal The postal code for the withdrawal.
     * @param ville       The city for the withdrawal.
     */
    public Retrait(long no_retrait, String rue, String code_postal, String ville) {
        this.no_retrait = no_retrait;
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    /**
     * Constructor for creating a Retrait object with all parameters.
     *
     * @param rue         The street address for the withdrawal.
     * @param code_postal The postal code for the withdrawal.
     * @param ville       The city for the withdrawal.
     */
    public Retrait(String rue, String code_postal, String ville) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
    }

    // Getter and setters

    public long getNo_retrait() {
        return no_retrait;
    }

    public void setNo_retrait(long no_retrait) {
        this.no_retrait = no_retrait;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Indicates whether some other object is "equal to" this one based on their withdrawal details.
     *
     * @param o The reference object with which to compare.
     * @return True if this withdrawal is the same as the object argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Retrait retrait)) return false;
        return Objects.equals(getNo_retrait(), retrait.getNo_retrait()) && Objects.equals(getRue(),
                retrait.getRue()) && Objects.equals(getCode_postal(),
                retrait.getCode_postal()) && Objects.equals(getVille(), retrait.getVille());
    }

    /**
     * Computes a hash code for this withdrawal based on its associated article, street address, postal code, and city.
     *
     * @return The hash code value for this withdrawal.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNo_retrait(), getRue(), getCode_postal(), getVille());
    }
}
