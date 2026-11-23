package edu.kirkwood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Formula;

/**
 * A signature shoe line (for example "Adidas D.O.N. Issue"), which groups one or more
 * {@link ShoeModel models}.
 */
@Entity
@Table(name = "shoe")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoe_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "athlete_id")
    private Athlete athlete;

    private String name;

    private String featuredImageUrl;

    private Integer introYear;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shoe_tag",
            joinColumns = @JoinColumn(name = "shoe_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @OrderBy("label")
    @BatchSize(size = 50)
    private Set<Tag> tags = new LinkedHashSet<>();

    /** Earliest model release year, or the intro year of the line when it has no models. */
    @Formula("(select coalesce(min(m.release_year), intro_year) from shoe_model m where m.shoe_id = shoe_id)")
    private Integer firstYear;

    /** Most recent model release year, or the intro year of the line when it has no models. */
    @Formula("(select coalesce(max(m.release_year), intro_year) from shoe_model m where m.shoe_id = shoe_id)")
    private Integer latestYear;

    /** Number of models in the line. */
    @Formula("shoe_model_count(shoe_id)")
    private long modelCount;

}
