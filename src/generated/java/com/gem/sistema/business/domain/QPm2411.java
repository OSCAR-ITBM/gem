package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2411 is a Querydsl query type for Pm2411
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2411 extends EntityPathBase<Pm2411> {

    private static final long serialVersionUID = -1909832301L;

    public static final QPm2411 pm2411 = new QPm2411("pm2411");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> npu = createNumber("npu", Integer.class);

    public final StringPath obsnpu = createString("obsnpu");

    public final StringPath obstpu = createString("obstpu");

    public final NumberPath<Integer> tpu = createNumber("tpu", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2411(String variable) {
        super(Pm2411.class, forVariable(variable));
    }

    public QPm2411(Path<? extends Pm2411> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2411(PathMetadata<?> metadata) {
        super(Pm2411.class, metadata);
    }

}

