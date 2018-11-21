package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatfuen is a Querydsl query type for Catfuen
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatfuen extends EntityPathBase<Catfuen> {

    private static final long serialVersionUID = 1991054042L;

    public static final QCatfuen catfuen = new QCatfuen("catfuen");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvfuen = createString("clvfuen");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public QCatfuen(String variable) {
        super(Catfuen.class, forVariable(variable));
    }

    public QCatfuen(Path<? extends Catfuen> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatfuen(PathMetadata<?> metadata) {
        super(Catfuen.class, metadata);
    }

}

