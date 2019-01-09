package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCpd is a Querydsl query type for Cpd
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCpd extends EntityPathBase<Cpd> {

    private static final long serialVersionUID = 5690339L;

    public static final QCpd cpd = new QCpd("cpd");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath cvetemas = createString("cvetemas");

    public final StringPath descripcion = createString("descripcion");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nope = createString("nope");

    public final StringPath pe = createString("pe");

    public final StringPath subTema = createString("subTema");

    public final StringPath tema = createString("tema");

    public final StringPath usuario = createString("usuario");

    public QCpd(String variable) {
        super(Cpd.class, forVariable(variable));
    }

    public QCpd(Path<? extends Cpd> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCpd(PathMetadata<?> metadata) {
        super(Cpd.class, metadata);
    }

}

