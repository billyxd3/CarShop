package com.bren.carshop.service;

import com.bren.carshop.dto.request.ModelRequest;
import com.bren.carshop.dto.response.ModelResponse;
import com.bren.carshop.entity.Model;
import com.bren.carshop.exception.HasDependenciesException;
import com.bren.carshop.exception.NoMatchesException;
import com.bren.carshop.repository.ModelRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    private final MakeService makeService;

    public ModelService(ModelRepository modelRepository, MakeService makeService) {
        this.modelRepository = modelRepository;
        this.makeService = makeService;
    }

    public void save(ModelRequest request) {
        modelRepository.save(modelRequestToModel(null, request));
    }

    public void update(ModelRequest request, Long id) {
        modelRepository.save(modelRequestToModel(findOne(id),request));

    }

    public void delete(Long id) {
        Model model = findOne(id);
        if (model.getCars().isEmpty()) {
            modelRepository.delete(model);
        } else {
            throw new HasDependenciesException("Can`t delete model with id " + id + " because it has dependencies");
        }
    }

    public List<ModelResponse> findAllByMakeId(Long makeId) {
        return modelRepository.findAllByMakeId(makeId).stream().map(ModelResponse::new).collect(Collectors.toList());
    }

    public List<ModelResponse> findAll(String fieldName) {
        return modelRepository.findAll(Sort.by(fieldName)).stream()
                .map(ModelResponse::new).collect(Collectors.toList());
    }

    public Model findOne(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new NoMatchesException("Model with id " + id + " doesn`t exist"));
    }

    private Model modelRequestToModel(Model model, ModelRequest request) {
        if (model == null) {
            model = new Model();
        }
        model.setName(request.getName());
        model.setMake(makeService.findOne(request.getMakeId()));
        return model;
    }

    public ModelResponse findOneResponse(Long id) {
        return new ModelResponse(findOne(id));
    }
}
