package com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.Interaction;

import java.util.List;

public interface InteractionServiceInterface {


    List<Interaction> getAll();

    void Follow(String Iuserhandle, String Puserhandle);

    void Like(String Iuserhandle, String Puserhandle, String PsearchTitle);

    void Comment(String Iuserhandle, String Puserhandle, String Ppostname, String comment);

    List<Interaction> getAllBySearchTitle(String searchTitle);
}
