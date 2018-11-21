package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0711 is a Querydsl query type for Pm0711
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0711 extends EntityPathBase<Pm0711> {

    private static final long serialVersionUID = -1909889000L;

    public static final QPm0711 pm0711 = new QPm0711("pm0711");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nespa = createNumber("nespa", Integer.class);

    public final StringPath obsnespa = createString("obsnespa");

    public final StringPath obstesp = createString("obstesp");

    public final NumberPath<Integer> tesp = createNumber("tesp", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0711(String variable) {
        super(Pm0711.class, forVariable(variable));
    }

    public QPm0711(Path<? extends Pm0711> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0711(PathMetadata<?> metadata) {
        super(Pm0711.class, metadata);
    }

}

