package com.bren.carshop.specification;

import com.bren.carshop.dto.request.CarCriteriaRequest;
import com.bren.carshop.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CarSpecification implements Specification<Car> {

    private Integer minYear;
    private Integer maxYear;
    private Integer minPrice;
    private Integer maxPrice;
    private Double minVolume;
    private Double maxVolume;
    private Boolean carConditionNew;
    private Boolean abs;
    private Boolean leatherSeats;
    private Integer minPower;
    private Integer maxPower;
    private List<Long> bodyTypeIds;
    private List<Long> driverTypeIds;
    private List<Long> fuelIds;
    private List<Long> gearboxIds;
    private List<Long> colorIds;
//    private Long colorId;
    private Long modelId;
    private Long makeId;
    private Long countryId;
    private Long cityId;
    private List<Long> favoriteCarsIds;

    public CarSpecification(CarCriteriaRequest carCriteria) {
        this.minYear = carCriteria.getMinYear();
        this.maxYear = carCriteria.getMaxYear();
        this.minPrice = carCriteria.getMinPrice();
        this.maxPrice = carCriteria.getMaxPrice();
        this.minVolume = carCriteria.getMinVolume();
        this.maxVolume = carCriteria.getMaxVolume();
        this.carConditionNew = carCriteria.getCarConditionNew();
        this.abs = carCriteria.getAbs();
        this.leatherSeats = carCriteria.getLeatherSeats();
        this.minPower = carCriteria.getMinPower();
        this.maxPower = carCriteria.getMaxPower();
        this.bodyTypeIds = carCriteria.getBodyTypeIds();
        this.driverTypeIds = carCriteria.getDriverTypeIds();
        this.fuelIds = carCriteria.getFuelIds();
        this.gearboxIds = carCriteria.getGearboxIds();
        this.colorIds = carCriteria.getColorIds();
        this.modelId = carCriteria.getModelId();
        this.makeId = carCriteria.getMakeId();
        this.countryId = carCriteria.getCountryId();
        this.cityId = carCriteria.getCityId();
        this.favoriteCarsIds = carCriteria.getFavoriteCarsIds();
    }

    @Override
    public Predicate toPredicate(Root<Car> root,
                                 CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(findByMinMaxValue(root,cb,minYear,maxYear,"year"));
        predicates.add(findByMinMaxValue(root,cb,minPower,maxPower,"power"));
        predicates.add(findByMinMaxValue(root,cb,minPrice,maxPrice,"price"));
        predicates.add(findByVolume(root,cb));
        predicates.add(findByCondition(root,cb,carConditionNew,"carConditionNew"));
        predicates.add(findByCondition(root,cb,abs,"abs"));
        predicates.add(findByCondition(root,cb,leatherSeats,"leatherSeats"));
        predicates.add(findByCountryAndMakeAndModel(root,cb));
        predicates.add(findByCountryAndCity(root,cb));
        predicates.add(findByIds(root,cb,driverTypeIds,"driverType"));
        predicates.add(findByIds(root,cb,bodyTypeIds,"bodyType"));
        predicates.add(findByIds(root,cb,fuelIds,"fuel"));
        predicates.add(findByIds(root,cb,gearboxIds,"gearbox"));
        predicates.add(findByIds(root,cb,colorIds,"color"));

        return cb.and(predicates.toArray(new Predicate[0]));
    }
//        private Predicate findFavoriteCarsByNameLike(Root<Car> root, CriteriaBuilder cb) {
//        if (favoriteCarsIds == null) {
//            return cb.conjunction();
//        } else {
//            Join<Car,User> joinUser = root.join("favoriteCars");
//            return cb.in(joinUser.get("id")).in(favoriteCarsIds);
//        }
//    }

    private Predicate findByIds(Root<Car> root, CriteriaBuilder cb,List<Long> valueIds,String name) {
        if (valueIds != null) {
            return root.join(name).get("id").in(valueIds);
        } else {
           return cb.conjunction();
        }
    }

//    private Predicate findByColorIds(Root<Car> root, CriteriaBuilder cb) {
//        if (colorIds != null) {
//            return root.join("color").get("id").in(colorIds);
//        } else {
//            return cb.conjunction();
//        }
//    }
//
//
//    private Predicate findByDriverType(Root<Car> root, CriteriaBuilder cb) {
//        if (driverTypeId != null) {
//            Join<Car,DriverType> joinDriverType = root.join("driverType");
//            return cb.equal(joinDriverType.get("id"),driverTypeId);
//        } else {
//            return cb.conjunction();
//        }
//    }


    private Predicate findByCountryAndCity(Root<Car> root,CriteriaBuilder cb) {
        if (cityId != null) {
            Join<Car, City> joinCity = root.join("city");
            return cb.equal(joinCity.get("id"), cityId);
        } else if (countryId != null) {
            Join<Car, City> joinCity = root.join("city");
            Join<City, Country> joinCountry = joinCity.join("country");
            return cb.equal(joinCountry.get("id"), countryId);
        } else {
            return cb.conjunction();
        }
    }

    private Predicate findByCountryAndMakeAndModel(Root<Car> root,CriteriaBuilder cb) {
        if (modelId != null) {
            Join<Car, Model> joinModel = root.join("model");
            return cb.equal(joinModel.get("id"), modelId);
        } else if (makeId != null) {
            Join<Car, Model> joinModel = root.join("model");

            Join<Model, Make> joinMake = joinModel.join("make");
            return cb.equal(joinMake.get("id"), makeId);
        } else if (countryId != null) {
            Join<Car, Model> joinModel = root.join("model");
            Join<Model, Make> joinMake = joinModel.join("make");
            Join<Make,Country> joinCountry = joinMake.join("country");
            return cb.equal(joinCountry.get("id"), countryId);
        } else {
            return cb.conjunction();
        }
    }

    private Predicate findByVolume(Root<Car> root, CriteriaBuilder cb) {
        if (minVolume != null && maxVolume != null) {
            return cb.between(root.get("price"), minVolume, maxVolume);
        } else if (minVolume != null) {
            return cb.ge(root.get("price"), minVolume);
        } else if (maxVolume != null) {
            return cb.le(root.get("price"), maxVolume);
        } else {
            return cb.conjunction();
        }
    }

    private Predicate findByMinMaxValue(Root<Car> root, CriteriaBuilder cb, Integer minCr, Integer maxCr,String value) {
        if (minCr != null && maxCr != null) {
            return cb.between(root.get(value), minCr,maxCr);
        } else if (minCr != null) {
            return cb.ge(root.get(value), minCr);
        } else if (maxCr != null) {
            return cb.le(root.get(value), maxCr);
        } else {
            return cb.conjunction();
        }
    }

    private Predicate findByCondition(Root<Car> root, CriteriaBuilder cb, Boolean condition, String name) {
        if (condition == null) {
            return cb.conjunction();
        } else {
            if (condition) {
                return cb.isTrue(root.get(name));
            } else {
                return cb.isFalse(root.get(name));
            }
        }
    }

}
