package com.bren.carshop.service;

import com.bren.carshop.dto.request.ColorRequest;
import com.bren.carshop.dto.response.ColorResponse;
import com.bren.carshop.entity.Color;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public void save(ColorRequest request) {
        colorRepository.save(colorRequestToColor(null,request));
    }

    public void update(ColorRequest request, Long id) {
        colorRepository.save(colorRequestToColor(findOne(id),request));
    }

    public List<ColorResponse> findAll(String fieldName) {
        return colorRepository.findAll(Sort.by(fieldName)).stream()
                    .map(ColorResponse::new).collect(Collectors.toList());
    }

    public void delete(Long id) {
        Color color  = findOne(id);
        if (color.getCars().isEmpty()) {
            colorRepository.delete(color);
        } else {
            throw new HasDependenciesException("Can`t delete color with id " + id + " because it has dependencies");
        }
    }

    public Color findOne(Long id) {
        return colorRepository.findById(id).orElseThrow(() -> new NoMatchesException("Color with id" + id + "doesn`t exists"));
    }

    public Color colorRequestToColor(Color color, ColorRequest request) {
        if (color == null) {
            color = new Color();
        }
        color.setName(request.getName());
        return color;
    }

    public List<ColorResponse> findAllByName(String value) {
        return colorRepository.findAllByNameLike('%' + value + '%', Sort.by("name")).stream()
                .map(ColorResponse::new).collect(Collectors.toList());
    }

    public ColorResponse findOneResponse(Long id) {
        return new ColorResponse(findOne(id));
    }
}
