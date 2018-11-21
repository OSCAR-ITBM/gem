package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1211 is a Querydsl query type for Pm1211
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1211 extends EntityPathBase<Pm1211> {

    private static final long serialVersionUID = -1909864014L;

    public static final QPm1211 pm1211 = new QPm1211("pm1211");

    public final NumberPath<java.math.BigDecimal> acumpre = createNumber("acumpre", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acumtransfe = createNumber("acumtransfe", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obspre = createString("obspre");

    public final StringPath obstrans = createString("obstrans");

    public final NumberPath<java.math.BigDecimal> preegre = createNumber("preegre", java.math.BigDecimal.class);

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final NumberPath<java.math.BigDecimal> transfe = createNumber("transfe", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm1211(String variable) {
        super(Pm1211.class, forVariable(variable));
    }

    public QPm1211(Path<? extends Pm1211> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1211(PathMetadata<?> metadata) {
        super(Pm1211.class, metadata);
    }

}

