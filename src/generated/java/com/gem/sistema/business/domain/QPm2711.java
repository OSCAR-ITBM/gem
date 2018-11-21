package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2711 is a Querydsl query type for Pm2711
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2711 extends EntityPathBase<Pm2711> {

    private static final long serialVersionUID = -1909829418L;

    public static final QPm2711 pm2711 = new QPm2711("pm2711");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> aculap = createNumber("aculap", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> lap = createNumber("lap", Integer.class);

    public final StringPath obslap = createString("obslap");

    public final StringPath obstloc = createString("obstloc");

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final NumberPath<Integer> tloc = createNumber("tloc", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2711(String variable) {
        super(Pm2711.class, forVariable(variable));
    }

    public QPm2711(Path<? extends Pm2711> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2711(PathMetadata<?> metadata) {
        super(Pm2711.class, metadata);
    }

}

