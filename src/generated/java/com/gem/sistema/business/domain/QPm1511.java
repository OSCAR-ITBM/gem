package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1511 is a Querydsl query type for Pm1511
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1511 extends EntityPathBase<Pm1511> {

    private static final long serialVersionUID = -1909861131L;

    public static final QPm1511 pm1511 = new QPm1511("pm1511");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> acunccm = createNumber("acunccm", Integer.class);

    public final NumberPath<Integer> acunccms = createNumber("acunccms", Integer.class);

    public final NumberPath<Integer> acutcpd = createNumber("acutcpd", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nccm = createNumber("nccm", Integer.class);

    public final NumberPath<Integer> nccms = createNumber("nccms", Integer.class);

    public final StringPath obsnccm = createString("obsnccm");

    public final StringPath obsnccms = createString("obsnccms");

    public final StringPath obstcpd = createString("obstcpd");

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final NumberPath<Integer> tcpd = createNumber("tcpd", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm1511(String variable) {
        super(Pm1511.class, forVariable(variable));
    }

    public QPm1511(Path<? extends Pm1511> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1511(PathMetadata<?> metadata) {
        super(Pm1511.class, metadata);
    }

}

