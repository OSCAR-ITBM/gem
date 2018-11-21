package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCopome is a Querydsl query type for Copome
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCopome extends EntityPathBase<Copome> {

    private static final long serialVersionUID = 2016708727L;

    public static final QCopome copome = new QCopome("copome");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopme = createNumber("anopme", Integer.class);

    public final NumberPath<Integer> cocpme = createNumber("cocpme", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idRef = createNumber("idRef", Integer.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mecpme = createNumber("mecpme", Integer.class);

    public final StringPath tpcpme = createString("tpcpme");

    public final StringPath userid = createString("userid");

    public QCopome(String variable) {
        super(Copome.class, forVariable(variable));
    }

    public QCopome(Path<? extends Copome> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCopome(PathMetadata<?> metadata) {
        super(Copome.class, metadata);
    }

}

