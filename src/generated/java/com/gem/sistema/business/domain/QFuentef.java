package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFuentef is a Querydsl query type for Fuentef
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFuentef extends EntityPathBase<Fuentef> {

    private static final long serialVersionUID = 917565353L;

    public static final QFuentef fuentef = new QFuentef("fuentef");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath camf1 = createString("camf1");

    public final StringPath camf2 = createString("camf2");

    public final StringPath camf3 = createString("camf3");

    public final StringPath camf4 = createString("camf4");

    public final StringPath camf5 = createString("camf5");

    public final StringPath capfuen = createString("capfuen");

    public final StringPath clvfte = createString("clvfte");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath liga = createString("liga");

    public final NumberPath<Integer> nivfte = createNumber("nivfte", Integer.class);

    public final StringPath nombref = createString("nombref");

    public final StringPath userid = createString("userid");

    public QFuentef(String variable) {
        super(Fuentef.class, forVariable(variable));
    }

    public QFuentef(Path<? extends Fuentef> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFuentef(PathMetadata<?> metadata) {
        super(Fuentef.class, metadata);
    }

}

