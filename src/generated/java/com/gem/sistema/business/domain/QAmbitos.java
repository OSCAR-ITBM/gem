package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAmbitos is a Querydsl query type for Ambitos
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAmbitos extends EntityPathBase<Ambitos> {

    private static final long serialVersionUID = 543061841L;

    public static final QAmbitos ambitos = new QAmbitos("ambitos");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath campo1 = createString("campo1");

    public final NumberPath<Float> campo2 = createNumber("campo2", Float.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> cveAmbito = createNumber("cveAmbito", Integer.class);

    public final StringPath descrip = createString("descrip");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QAmbitos(String variable) {
        super(Ambitos.class, forVariable(variable));
    }

    public QAmbitos(Path<? extends Ambitos> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmbitos(PathMetadata<?> metadata) {
        super(Ambitos.class, metadata);
    }

}

