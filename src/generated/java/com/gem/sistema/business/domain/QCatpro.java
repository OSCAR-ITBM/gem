package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatpro is a Querydsl query type for Catpro
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatpro extends EntityPathBase<Catpro> {

    private static final long serialVersionUID = 2003899723L;

    public static final QCatpro catpro = new QCatpro("catpro");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath cappro = createString("cappro");

    public final StringPath clvpro = createString("clvpro");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nompro = createString("nompro");

    public final StringPath ultniv = createString("ultniv");

    public QCatpro(String variable) {
        super(Catpro.class, forVariable(variable));
    }

    public QCatpro(Path<? extends Catpro> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatpro(PathMetadata<?> metadata) {
        super(Catpro.class, metadata);
    }

}

