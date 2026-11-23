package edu.kirkwood.repository;

import edu.kirkwood.model.ShoeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeModelRepository extends JpaRepository<ShoeModel, Integer> {
    List<ShoeModel> findByShoeIdOrderByReleaseYearDescIdAsc(Integer shoeId);
}
