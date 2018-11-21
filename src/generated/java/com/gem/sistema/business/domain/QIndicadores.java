package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QIndicadores is a Querydsl query type for Indicadores
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QIndicadores extends EntityPathBase<Indicadores> {

    private static final long serialVersionUID = 2062242105L;

    public static final QIndicadores indicadores = new QIndicadores("indicadores");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvDep = createString("clvDep");

    public final StringPath clvFin = createString("clvFin");

    public final StringPath clvFun = createString("clvFun");

    public final NumberPath<Integer> cveind = createNumber("cveind", Integer.class);

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath nomind = createString("nomind");

    public final StringPath periodo = createString("periodo");

    public final StringPath userid = createString("userid");

    public QIndicadores(String variable) {
        super(Indicadores.class, forVariable(variable));
    }

    public QIndicadores(Path<? extends Indicadores> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIndicadores(PathMetadata<?> metadata) {
        super(Indicadores.class, metadata);
    }

}

