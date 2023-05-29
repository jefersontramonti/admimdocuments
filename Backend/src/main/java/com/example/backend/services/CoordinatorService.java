package com.example.backend.services;

import com.example.backend.dto.CoordinatorDTO;
import com.example.backend.entities.Coordinator;
import com.example.backend.repositories.CoordinatorRepository;
import com.example.backend.util.CoordinatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoordinatorService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    private CoordinatorUtil coordinatorUtil;
    public List<CoordinatorDTO> findAll() {
        return coordinatorRepository.findAll().stream()
                .map(CoordinatorUtil::toCoordinatorDTO)
                .collect(Collectors.toList());
    }

    public CoordinatorDTO findById(Long id) {
        return coordinatorRepository.findById(id)
                .map(CoordinatorUtil::toCoordinatorDTO)
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));
    }

    public CoordinatorDTO save(CoordinatorDTO coordinatorDTO) {
        Coordinator coordinator = CoordinatorUtil.toCoordinator(coordinatorDTO);
        coordinator = coordinatorRepository.save(coordinator);
        return CoordinatorUtil.toCoordinatorDTO(coordinator);
    }

    public CoordinatorDTO update(Long id, CoordinatorDTO coordinatorDTO) {
        Coordinator coordinator = coordinatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found"));
        CoordinatorUtil.updateCoordinator(coordinator, coordinatorDTO);
        Coordinator updatedCoordinator = coordinatorRepository.save(coordinator);
        return CoordinatorUtil.toCoordinatorDTO(updatedCoordinator);
    }

    public void delete(Long id) {
        coordinatorRepository.deleteById(id);
    }
}
