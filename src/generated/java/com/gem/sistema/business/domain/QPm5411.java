package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5411 is a Querydsl query type for Pm5411
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5411 extends EntityPathBase<Pm5411> {

    private static final long serialVersionUID = -1909742928L;

    public static final QPm5411 pm5411 = new QPm5411("pm5411");

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> masimun = createNumber("masimun", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mautob = createNumber("mautob", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mautobyac = createNumber("mautobyac", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> mejemes = createNumber("mejemes", java.math.BigDecimal.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final NumberPath<java.math.BigDecimal> mminmes = createNumber("mminmes", java.math.BigDecimal.class);

    public final StringPath observa = createString("observa");

    public final NumberPath<Integer> obyacmes = createNumber("obyacmes", Integer.class);

    public final StringPath partciu = createString("partciu");

    public final NumberPath<Integer> sesiones = createNumber("sesiones", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm5411(String variable) {
        super(Pm5411.class, forVariable(variable));
    }

    public QPm5411(Path<? extends Pm5411> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5411(PathMetadata<?> metadata) {
        super(Pm5411.class, metadata);
    }

}

