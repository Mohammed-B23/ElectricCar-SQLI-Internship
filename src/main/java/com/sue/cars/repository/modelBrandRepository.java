package com.sue.cars.repository;

import com.sue.cars.entity.Brand;
import com.sue.cars.entity.ModelBrand;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface modelBrandRepository extends JpaRepository<ModelBrand,Long> {
    Optional<ModelBrand> findByName(String name);
    List<ModelBrand> findModelBrandByBrandAndModelYear(Brand brand, int modelYaer);
    @Query("SELECT distinct (m.modelYear) FROM ModelBrand m")
    List<Integer> getDistinctModelYear();
    // select distinct brand_id, count(model_brand.brand_id) from model_brand where model_year=2020 group by brand_id; /** give all brands which model year is 2020 **/
//    @Query("select distinct m.brand, count(m.brand) from ModelBrand m where m.modelYear =?1 group by m.brand")
//    Map<Brand,Integer> getDistinctByBrand(Integer modelYear);

//    select count(c.id), b.name from car c , model_brand m, brand b
//    where b.id = m.brand_id and c.model_brand_id = m.id and m.model_year=2020 group by b.name
     @Query("SELECT b.name, COUNT(c.id) FROM ModelBrand m, Brand b, Car c WHERE m.modelYear = ?1 and c.modelBrand.id = m.id and m.brand.id = b.id GROUP BY b.name")
     List<Object[]> getDistinctByModelYear(Integer modelYear);

    @Query("SELECT m.modelYear, COUNT(c.id) FROM ModelBrand m, Brand b, Car c WHERE b.name = ?1 and c.modelBrand.id = m.id and m.brand.id = b.id GROUP BY m.modelYear")
    List<Object[]> getDistinctByBrand(String brandName);

//    @Query("SELECT m.brand.name, COUNT(m) FROM ModelBrand m WHERE m.modelYear = ?1 GROUP BY m.brand.name")
//    List<Object[]> getDistinctByBrand(Integer modelYear);
    List<ModelBrand> findModelBrandByBrand(Brand brand);
}
