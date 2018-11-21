package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2811 is a Querydsl query type for Pm2811
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2811 extends EntityPathBase<Pm2811> {

    private static final long serialVersionUID = -1909828457L;

    public static final QPm2811 pm2811 = new QPm2811("pm2811");

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idRef = createNumber("idRef", Integer.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath obsvdsg = createString("obsvdsg");

    public final StringPath obsvdst = createString("obsvdst");

    public final StringPath userId = createString("userId");

    public final NumberPath<java.math.BigDecimal> vdsg = createNumber("vdsg", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> vdst = createNumber("vdst", java.math.BigDecimal.class);

    public QPm2811(String variable) {
        super(Pm2811.class, forVariable(variable));
    }

    public QPm2811(Path<? extends Pm2811> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2811(PathMetadata<?> metadata) {
        super(Pm2811.class, metadata);
    }

}

