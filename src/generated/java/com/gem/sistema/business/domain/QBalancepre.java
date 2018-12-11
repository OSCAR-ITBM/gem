package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBalancepre is a Querydsl query type for Balancepre
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBalancepre extends EntityPathBase<Balancepre> {

    private static final long serialVersionUID = 786581115L;

    public static final QBalancepre balancepre = new QBalancepre("balancepre");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final StringPath concepto = createString("concepto");

    public final NumberPath<Long> consecutivo = createNumber("consecutivo", Long.class);

    public final NumberPath<java.math.BigDecimal> devengado = createNumber("devengado", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ea = createNumber("ea", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idAnio = createNumber("idAnio", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final NumberPath<java.math.BigDecimal> rp = createNumber("rp", java.math.BigDecimal.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public QBalancepre(String variable) {
        super(Balancepre.class, forVariable(variable));
    }

    public QBalancepre(Path<? extends Balancepre> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBalancepre(PathMetadata<?> metadata) {
        super(Balancepre.class, metadata);
    }

}

