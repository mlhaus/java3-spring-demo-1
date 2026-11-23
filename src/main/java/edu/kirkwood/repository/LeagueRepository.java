package edu.kirkwood.repository;

import edu.kirkwood.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    List<League> findAllByOrderByAbbreviationAsc();
}
