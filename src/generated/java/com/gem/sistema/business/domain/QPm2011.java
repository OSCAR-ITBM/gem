package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2011 is a Querydsl query type for Pm2011
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2011 extends EntityPathBase<Pm2011> {

    private static final long serialVersionUID = -1909836145L;

    public static final QPm2011 pm2011 = new QPm2011("pm2011");

    public final NumberPath<Integer> amb = createNumber("amb", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsamb = createString("obsamb");

    public final StringPath obstamp = createString("obstamp");

    public final NumberPath<Integer> tamp = createNumber("tamp", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2011(String variable) {
        super(Pm2011.class, forVariable(variable));
    }

    public QPm2011(Path<? extends Pm2011> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2011(PathMetadata<?> metadata) {
        super(Pm2011.class, metadata);
    }

}

