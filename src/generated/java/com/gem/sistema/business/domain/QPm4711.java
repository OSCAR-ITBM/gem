package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4711 is a Querydsl query type for Pm4711
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4711 extends EntityPathBase<Pm4711> {

    private static final long serialVersionUID = -1909769836L;

    public static final QPm4711 pm4711 = new QPm4711("pm4711");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> ntinsfin = createNumber("ntinsfin", Integer.class);

    public final NumberPath<Integer> ntinsini = createNumber("ntinsini", Integer.class);

    public final StringPath obsntinsfin = createString("obsntinsfin");

    public final StringPath obsntinsini = createString("obsntinsini");

    public final StringPath userid = createString("userid");

    public QPm4711(String variable) {
        super(Pm4711.class, forVariable(variable));
    }

    public QPm4711(Path<? extends Pm4711> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4711(PathMetadata<?> metadata) {
        super(Pm4711.class, metadata);
    }

}

