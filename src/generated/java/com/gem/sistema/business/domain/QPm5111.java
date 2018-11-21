package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5111 is a Querydsl query type for Pm5111
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5111 extends EntityPathBase<Pm5111> {

    private static final long serialVersionUID = -1909745811L;

    public static final QPm5111 pm5111 = new QPm5111("pm5111");

    public final NumberPath<java.math.BigDecimal> acuitsa = createNumber("acuitsa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuteca = createNumber("acuteca", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> itsa = createNumber("itsa", java.math.BigDecimal.class);

    public final StringPath obsitsa = createString("obsitsa");

    public final StringPath obsteca = createString("obsteca");

    public final NumberPath<java.math.BigDecimal> teca = createNumber("teca", java.math.BigDecimal.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm5111(String variable) {
        super(Pm5111.class, forVariable(variable));
    }

    public QPm5111(Path<? extends Pm5111> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5111(PathMetadata<?> metadata) {
        super(Pm5111.class, metadata);
    }

}

