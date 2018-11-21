package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4311 is a Querydsl query type for Pm4311
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4311 extends EntityPathBase<Pm4311> {

    private static final long serialVersionUID = -1909773680L;

    public static final QPm4311 pm4311 = new QPm4311("pm4311");

    public final NumberPath<java.math.BigDecimal> acumsub = createNumber("acumsub", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acumtot = createNumber("acumtot", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final StringPath obssub = createString("obssub");

    public final StringPath obstot = createString("obstot");

    public final NumberPath<java.math.BigDecimal> subsi = createNumber("subsi", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toting = createNumber("toting", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm4311(String variable) {
        super(Pm4311.class, forVariable(variable));
    }

    public QPm4311(Path<? extends Pm4311> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4311(PathMetadata<?> metadata) {
        super(Pm4311.class, metadata);
    }

}

