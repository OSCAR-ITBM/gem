package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3011 is a Querydsl query type for Pm3011
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3011 extends EntityPathBase<Pm3011> {

    private static final long serialVersionUID = -1909806354L;

    public static final QPm3011 pm3011 = new QPm3011("pm3011");

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idRef = createNumber("idRef", Integer.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final NumberPath<java.math.BigDecimal> lknopav = createNumber("lknopav", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> lkpav = createNumber("lkpav", java.math.BigDecimal.class);

    public final StringPath obslknopav = createString("obslknopav");

    public final StringPath obslkpav = createString("obslkpav");

    public final StringPath userId = createString("userId");

    public QPm3011(String variable) {
        super(Pm3011.class, forVariable(variable));
    }

    public QPm3011(Path<? extends Pm3011> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3011(PathMetadata<?> metadata) {
        super(Pm3011.class, metadata);
    }

}

