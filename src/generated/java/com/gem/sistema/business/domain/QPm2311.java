package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2311 is a Querydsl query type for Pm2311
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2311 extends EntityPathBase<Pm2311> {

    private static final long serialVersionUID = -1909833262L;

    public static final QPm2311 pm2311 = new QPm2311("pm2311");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nvap = createNumber("nvap", Integer.class);

    public final NumberPath<Integer> nvcp = createNumber("nvcp", Integer.class);

    public final NumberPath<Integer> nvdre = createNumber("nvdre", Integer.class);

    public final NumberPath<Integer> nvrb = createNumber("nvrb", Integer.class);

    public final StringPath obsgral = createString("obsgral");

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final NumberPath<Integer> tvm = createNumber("tvm", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2311(String variable) {
        super(Pm2311.class, forVariable(variable));
    }

    public QPm2311(Path<? extends Pm2311> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2311(PathMetadata<?> metadata) {
        super(Pm2311.class, metadata);
    }

}

