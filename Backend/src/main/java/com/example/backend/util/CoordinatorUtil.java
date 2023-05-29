package com.example.backend.util;

import com.example.backend.dto.CoordinatorDTO;
import com.example.backend.entities.Coordinator;

public class CoordinatorUtil {

    public static CoordinatorDTO toCoordinatorDTO(Coordinator coordinator) {
        CoordinatorDTO coordinatorDTO = new CoordinatorDTO();
        coordinatorDTO.setId(coordinator.getId());
        coordinatorDTO.setName(coordinator.getName());
        coordinatorDTO.setUsername(coordinator.getUsername());
        coordinatorDTO.setPassword(coordinator.getPassword());

        return coordinatorDTO;
    }

    public static Coordinator toCoordinator(CoordinatorDTO coordinatorDTO) {
        Coordinator coordinator = new Coordinator();
        coordinator.setId(coordinatorDTO.getId());
        coordinator.setName(coordinatorDTO.getName());
        coordinator.setUsername(coordinatorDTO.getUsername());
        coordinator.setPassword(coordinatorDTO.getPassword());

        return coordinator;
    }

    public static void updateCoordinator(Coordinator coordinator, CoordinatorDTO coordinatorDTO) {
        coordinator.setName(coordinatorDTO.getName());
        coordinator.setUsername(coordinatorDTO.getUsername());
        coordinator.setPassword(coordinatorDTO.getPassword());
    }
}
