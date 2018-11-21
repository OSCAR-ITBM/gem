package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMir is a Querydsl query type for Mir
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMir extends EntityPathBase<Mir> {

    private static final long serialVersionUID = 5699746L;

    public static final QMir mir = new QMir("mir");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final StringPath codigo = createString("codigo");

    public final NumberPath<Integer> consec = createNumber("consec", Integer.class);

    public final DateTimePath<java.util.Date> fecha = createDateTime("fecha", java.util.Date.class);

    public final StringPath frecuencia = createString("frecuencia");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nivel = createString("nivel");

    public final StringPath nomind = createString("nomind");

    public final StringPath programa = createString("programa");

    public final StringPath tipo = createString("tipo");

    public QMir(String variable) {
        super(Mir.class, forVariable(variable));
    }

    public QMir(Path<? extends Mir> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMir(PathMetadata<?> metadata) {
        super(Mir.class, metadata);
    }

}

