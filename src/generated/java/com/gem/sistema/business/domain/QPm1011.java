package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1011 is a Querydsl query type for Pm1011
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1011 extends EntityPathBase<Pm1011> {

    private static final long serialVersionUID = -1909865936L;

    public static final QPm1011 pm1011 = new QPm1011("pm1011");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> ipm = createNumber("ipm", Integer.class);

    public final StringPath obsipm = createString("obsipm");

    public final StringPath obstdr = createString("obstdr");

    public final NumberPath<Integer> tdr = createNumber("tdr", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm1011(String variable) {
        super(Pm1011.class, forVariable(variable));
    }

    public QPm1011(Path<? extends Pm1011> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1011(PathMetadata<?> metadata) {
        super(Pm1011.class, metadata);
    }

}

