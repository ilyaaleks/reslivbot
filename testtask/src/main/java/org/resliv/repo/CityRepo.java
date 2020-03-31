package org.resliv.repo;

import org.resliv.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends CrudRepository<City, Long> {
    City findByName(String name);
    Page<City> findAll(Pageable pageable);
    Long deleteById(Integer id);
}
