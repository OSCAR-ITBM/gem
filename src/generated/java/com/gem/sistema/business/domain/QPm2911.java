package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2911 is a Querydsl query type for Pm2911
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2911 extends EntityPathBase<Pm2911> {

    private static final long serialVersionUID = -1909827496L;

    public static final QPm2911 pm2911 = new QPm2911("pm2911");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> acumgas = createNumber("acumgas", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acumtot = createNumber("acumtot", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> gastot = createNumber("gastot", java.math.BigDecimal.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsgas = createString("obsgas");

    public final StringPath obstot = createString("obstot");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final NumberPath<java.math.BigDecimal> totton = createNumber("totton", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm2911(String variable) {
        super(Pm2911.class, forVariable(variable));
    }

    public QPm2911(Path<? extends Pm2911> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2911(PathMetadata<?> metadata) {
        super(Pm2911.class, metadata);
    }

}

