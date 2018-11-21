package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1111 is a Querydsl query type for Pm1111
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1111 extends EntityPathBase<Pm1111> {

    private static final long serialVersionUID = -1909864975L;

    public static final QPm1111 pm1111 = new QPm1111("pm1111");

    public final NumberPath<java.math.BigDecimal> acuitev = createNumber("acuitev", java.math.BigDecimal.class);

    public final NumberPath<Integer> acunatev = createNumber("acunatev", Integer.class);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idRef = createNumber("idRef", Integer.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final NumberPath<java.math.BigDecimal> itev = createNumber("itev", java.math.BigDecimal.class);

    public final NumberPath<Integer> natev = createNumber("natev", Integer.class);

    public final StringPath obsitev = createString("obsitev");

    public final StringPath obsnatev = createString("obsnatev");

    public final StringPath userId = createString("userId");

    public QPm1111(String variable) {
        super(Pm1111.class, forVariable(variable));
    }

    public QPm1111(Path<? extends Pm1111> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1111(PathMetadata<?> metadata) {
        super(Pm1111.class, metadata);
    }

}

