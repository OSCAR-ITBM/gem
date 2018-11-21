package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMaycta is a Querydsl query type for Maycta
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMaycta extends EntityPathBase<Maycta> {

    private static final long serialVersionUID = -2004639553L;

    public static final QMaycta maycta = new QMaycta("maycta");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvsti = createString("clvsti");

    public final StringPath clvtip = createString("clvtip");

    public final StringPath cuenta = createString("cuenta");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath natcta = createString("natcta");

    public final NumberPath<Integer> nivcta = createNumber("nivcta", Integer.class);

    public final StringPath nommay = createString("nommay");

    public final NumberPath<Integer> notcta = createNumber("notcta", Integer.class);

    public final StringPath stacta = createString("stacta");

    public QMaycta(String variable) {
        super(Maycta.class, forVariable(variable));
    }

    public QMaycta(Path<? extends Maycta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMaycta(PathMetadata<?> metadata) {
        super(Maycta.class, metadata);
    }

}

