package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QConctb is a Querydsl query type for Conctb
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConctb extends EntityPathBase<Conctb> {

    private static final long serialVersionUID = 2016637827L;

    public static final QConctb conctb = new QConctb("conctb");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anoemp = createNumber("anoemp", Integer.class);

    public final StringPath clave = createString("clave");

    public final StringPath clave1 = createString("clave1");

    public final StringPath clvemp = createString("clvemp");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mescie = createNumber("mescie", Integer.class);

    public final NumberPath<Integer> mesemp = createNumber("mesemp", Integer.class);

    public final NumberPath<Integer> nivel1 = createNumber("nivel1", Integer.class);

    public final NumberPath<Integer> nivel2 = createNumber("nivel2", Integer.class);

    public final NumberPath<Integer> nivel3 = createNumber("nivel3", Integer.class);

    public final NumberPath<Integer> nivel4 = createNumber("nivel4", Integer.class);

    public final NumberPath<Integer> nivel5 = createNumber("nivel5", Integer.class);

    public final NumberPath<Integer> nivel6 = createNumber("nivel6", Integer.class);

    public final NumberPath<java.math.BigDecimal> numdet = createNumber("numdet", java.math.BigDecimal.class);

    public final NumberPath<Integer> numvac = createNumber("numvac", Integer.class);

    public final NumberPath<java.math.BigDecimal> ordpol = createNumber("ordpol", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QConctb(String variable) {
        super(Conctb.class, forVariable(variable));
    }

    public QConctb(Path<? extends Conctb> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConctb(PathMetadata<?> metadata) {
        super(Conctb.class, metadata);
    }

}

