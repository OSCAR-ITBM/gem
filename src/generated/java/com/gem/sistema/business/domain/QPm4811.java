package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4811 is a Querydsl query type for Pm4811
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4811 extends EntityPathBase<Pm4811> {

    private static final long serialVersionUID = -1909768875L;

    public static final QPm4811 pm4811 = new QPm4811("pm4811");

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idRef = createNumber("idRef", Integer.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath obsrta = createString("obsrta");

    public final StringPath obsrtaa = createString("obsrtaa");

    public final NumberPath<java.math.BigDecimal> rta = createNumber("rta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rtaa = createNumber("rtaa", java.math.BigDecimal.class);

    public final StringPath userId = createString("userId");

    public QPm4811(String variable) {
        super(Pm4811.class, forVariable(variable));
    }

    public QPm4811(Path<? extends Pm4811> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4811(PathMetadata<?> metadata) {
        super(Pm4811.class, metadata);
    }

}

