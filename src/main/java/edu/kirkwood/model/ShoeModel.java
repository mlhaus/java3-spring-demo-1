package edu.kirkwood.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** One release within a shoe line (for example "Adidas D.O.N. Issue #7"). */
@Entity
@Table(name = "shoe_model")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShoeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shoe_id")
    private Shoe shoe;

    @Column(name = "model_name")
    private String name;

    private Integer releaseYear;

    private String imageUrl;
}
