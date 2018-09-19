package com.renovavision.videosearch.domain.model;

/**
 * Created by Alexandr Golovach on 15.06.2018.
 */
public interface ModelMapper<E, M> {

    M fromEntity(E entity);

    E toEntity(M model);
}
