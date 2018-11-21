package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QXcatpro is a Querydsl query type for Xcatpro
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QXcatpro extends EntityPathBase<Xcatpro> {

    private static final long serialVersionUID = -806081061L;

    public static final QXcatpro xcatpro = new QXcatpro("xcatpro");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath cappro = createString("cappro");

    public final StringPath clvdep = createString("clvdep");

    public final StringPath clvfin = createString("clvfin");

    public final StringPath clvfun = createString("clvfun");

    public final StringPath clvpro = createString("clvpro");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath ini = createString("ini");

    public final StringPath nompro = createString("nompro");

    public final NumberPath<Integer> sectorid = createNumber("sectorid", Integer.class);

    public final StringPath ultniv = createString("ultniv");

    public final StringPath userid = createString("userid");

    public QXcatpro(String variable) {
        super(Xcatpro.class, forVariable(variable));
    }

    public QXcatpro(Path<? extends Xcatpro> path) {
        super(path.getType(), path.getMetadata());
    }

    public QXcatpro(PathMetadata<?> metadata) {
        super(Xcatpro.class, metadata);
    }

}

