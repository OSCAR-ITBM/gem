package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4011 is a Querydsl query type for Pm4011
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4011 extends EntityPathBase<Pm4011> {

    private static final long serialVersionUID = -1909776563L;

    public static final QPm4011 pm4011 = new QPm4011("pm4011");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> clvreq = createNumber("clvreq", Integer.class);

    public final StringPath correo = createString("correo");

    public final StringPath cumple = createString("cumple");

    public final StringPath evidencia = createString("evidencia");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obs = createString("obs");

    public final StringPath requer = createString("requer");

    public final StringPath userid = createString("userid");

    public QPm4011(String variable) {
        super(Pm4011.class, forVariable(variable));
    }

    public QPm4011(Path<? extends Pm4011> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4011(PathMetadata<?> metadata) {
        super(Pm4011.class, metadata);
    }

}

