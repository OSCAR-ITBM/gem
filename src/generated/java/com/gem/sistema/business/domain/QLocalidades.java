package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QLocalidades is a Querydsl query type for Localidades
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QLocalidades extends EntityPathBase<Localidades> {

    private static final long serialVersionUID = 1145916707L;

    public static final QLocalidades localidades = new QLocalidades("localidades");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> cveLoc = createNumber("cveLoc", Integer.class);

    public final NumberPath<Integer> cveMun = createNumber("cveMun", Integer.class);

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nomLoc = createString("nomLoc");

    public final StringPath nomMun = createString("nomMun");

    public final StringPath xcveloc = createString("xcveloc");

    public final StringPath xcvemun = createString("xcvemun");

    public QLocalidades(String variable) {
        super(Localidades.class, forVariable(variable));
    }

    public QLocalidades(Path<? extends Localidades> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocalidades(PathMetadata<?> metadata) {
        super(Localidades.class, metadata);
    }

}

