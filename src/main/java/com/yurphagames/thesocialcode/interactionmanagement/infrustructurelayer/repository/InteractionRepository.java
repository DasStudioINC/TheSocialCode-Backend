package com.yurphagames.thesocialcode.interactionmanagement.infrustructurelayer.repository;

import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.Interaction;
import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.InteractionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InteractionRepository extends JpaRepository<Interaction, Integer> {
    boolean existsByUserHandleInteractedAndUserHandlePosterAndType(String interactedUserHandle, String posterUserHandle, InteractionType type);
    Interaction findByUserHandleInteractedAndUserHandlePosterAndType(String userHandleInteracted, String userHandlePoster, InteractionType type);

    boolean getAllById(Integer id);
}
