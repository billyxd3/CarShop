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
    private Long bodyTypeId;
//    private Long bodyTypeId;
//    private List<Long> bodyTypeIds;
    private Long driverTypeId;
//    private List<Long> driverTypeIds;
    private Long fuelId;
//    private List<Long> fuelIds;
    private Long gearboxId;
//    private List<Long> gearboxIds;
    private Long colorId;
//    private List<Long> colorIds;
    private Long modelId;
    private Long makeId;
    private Long countryId;
    private Long cityId;
    private List<Long> favoriteCarsId;

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
        this.bodyTypeId = carCriteria.getBodyTypeId();
        this.driverTypeId = carCriteria.getDriverTypeId();
        this.fuelId = carCriteria.getFuelId();
        this.gearboxId = carCriteria.getGearboxId();
        this.colorId = carCriteria.getColorId();
        this.modelId = carCriteria.getModelId();
        this.makeId = carCriteria.getMakeId();
        this.countryId = carCriteria.getCountryId();
        this.cityId = carCriteria.getCityId();
//        this.favoriteCarsIds = carCriteria.getFavoriteCarsIds();
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
//        predicates.add(findByCountryAndCity(root,cb));
//        predicates.add(findByCountry(root,cb));


        predicates.add(findByCity(root,cb));
        predicates.add(findByDriverType(root,cb));
        predicates.add(findByBodyTypeId(root,cb));
        predicates.add(findByDriverType(root,cb));
        predicates.add(findByFuelId(root,cb));
        predicates.add(findByColorId(root,cb));
        predicates.add(findByGearboxId(root,cb));


//        predicates.add(findByIds(root,cb,driverTypeIds,"driverType"));
//        predicates.add(findByIds(root,cb,driverTypeIds,"driverType"));
//        predicates.add(findByIds(root,cb,bodyTypeIds,"bodyType"));
//        predicates.add(findByIds(root,cb,fuelIds,"fuel"));
//        predicates.add(findByIds(root,cb,gearboxIds,"gearbox"));
//        predicates.add(findByIds(root,cb,colorIds,"color"));

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate findByCity(Root<Car> root, CriteriaBuilder cb) {
        if (cityId != null) {
            Join<Car,City> joinCity = root.join("city");
            return cb.equal(joinCity.get("id"),cityId);
        } else {
            return cb.conjunction();
        }
    }
    private Predicate findByBodyTypeId(Root<Car> root, CriteriaBuilder cb) {
        if (bodyTypeId != null) {
            Join<Car,BodyType> joinBodyType = root.join("bodyType");
            return cb.equal(joinBodyType.get("id"),bodyTypeId);
        } else {
            return cb.conjunction();
        }
    }
    private Predicate findByDriverType(Root<Car> root, CriteriaBuilder cb) {
        if (driverTypeId != null) {
            Join<Car,DriverType> joinDriverType = root.join("driverType");
            return cb.equal(joinDriverType.get("id"),driverTypeId);
        } else {
            return cb.conjunction();
        }
    }
    private Predicate findByFuelId(Root<Car> root, CriteriaBuilder cb) {
        if (fuelId != null) {
            Join<Car,Fuel> joinFuel = root.join("fuel");
            return cb.equal(joinFuel.get("id"),fuelId);
        } else {
            return cb.conjunction();
        }
    }
    private Predicate findByGearboxId(Root<Car> root, CriteriaBuilder cb) {
        if (gearboxId != null) {
            Join<Car,Gearbox> joinGearbox = root.join("gearbox");
            return cb.equal(joinGearbox.get("id"),gearboxId);
        } else {
            return cb.conjunction();
        }
    }
    private Predicate findByColorId(Root<Car> root, CriteriaBuilder cb) {
        if (colorId != null) {
            Join<Car,Color> joinColor = root.join("color");
            return cb.equal(joinColor.get("id"),colorId);
        } else {
            return cb.conjunction();
        }
    }

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
            return cb.between(root.get("volume"), minVolume, maxVolume);
        } else if (minVolume != null) {
            return cb.ge(root.get("volume"), minVolume);
        } else if (maxVolume != null) {
            return cb.le(root.get("volume"), maxVolume);
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

    //        private Predicate findFavoriteCarsByNameLike(Root<Car> root, CriteriaBuilder cb) {
//        if (favoriteCarsIds == null) {
//            return cb.conjunction();
//        } else {
//            Join<Car,User> joinUser = root.join("favoriteCars");
//            return cb.in(joinUser.get("id")).in(favoriteCarsIds);
//        }
//    }

//    private Predicate findByIds(Root<Car> root, CriteriaBuilder cb,List<Long> valueIds,String name) {
//        if (valueIds != null) {
//            return root.join(name).get("id").in(valueIds);
//        } else {
//           return cb.conjunction();
//        }
//    }

//    private Predicate findByColorIds(Root<Car> root, CriteriaBuilder cb) {
//        if (colorIds != null) {
//            return root.join("color").get("id").in(colorIds);
//        } else {
//            return cb.conjunction();
//        }
//    }
//
//
//    private Predicate findByCountry(Root<Car> root, CriteriaBuilder cb) {
//        if (countryId != null) {
//            Join<Car,Country> joinCountry = root.join("country");
//            return cb.equal(joinCountry.get("id"),countryId);
//        } else {
//            return cb.conjunction();
//        }
//    }

}
