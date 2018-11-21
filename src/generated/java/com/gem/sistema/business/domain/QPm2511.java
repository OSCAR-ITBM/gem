package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2511 is a Querydsl query type for Pm2511
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2511 extends EntityPathBase<Pm2511> {

    private static final long serialVersionUID = -1909831340L;

    public static final QPm2511 pm2511 = new QPm2511("pm2511");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> acumagua = createNumber("acumagua", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> agua = createNumber("agua", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsagua = createString("obsagua");

    public final StringPath obspob = createString("obspob");

    public final NumberPath<Integer> poblacion = createNumber("poblacion", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2511(String variable) {
        super(Pm2511.class, forVariable(variable));
    }

    public QPm2511(Path<? extends Pm2511> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2511(PathMetadata<?> metadata) {
        super(Pm2511.class, metadata);
    }

}

