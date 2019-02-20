package com.inther.services.mappers;

public interface Mapper<A, B> {

    // this is just to not forget to add all methods to every mapper
    // (it's called a "contract" maybe, I don't really know all this java slang)

    A toEntity(B dto);

    B toDto(A entity);

    void patchEntity(A source, A destination);

    void patchDto(B source, B destination);
}
