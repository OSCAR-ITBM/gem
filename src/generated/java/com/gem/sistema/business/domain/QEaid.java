package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QEaid is a Querydsl query type for Eaid
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEaid extends EntityPathBase<Eaid> {

    private static final long serialVersionUID = 176445931L;

    public static final QEaid eaid = new QEaid("eaid");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> ampliacionReduccion = createNumber("ampliacionReduccion", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final StringPath concepto = createString("concepto");

    public final NumberPath<Integer> consecutivo = createNumber("consecutivo", Integer.class);

    public final NumberPath<java.math.BigDecimal> devengado = createNumber("devengado", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> diferencia = createNumber("diferencia", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> estimado = createNumber("estimado", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> fechaCptura = createDate("fechaCptura", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idAnio = createNumber("idAnio", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final NumberPath<java.math.BigDecimal> modificado = createNumber("modificado", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> recaudado = createNumber("recaudado", java.math.BigDecimal.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public QEaid(String variable) {
        super(Eaid.class, forVariable(variable));
    }

    public QEaid(Path<? extends Eaid> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEaid(PathMetadata<?> metadata) {
        super(Eaid.class, metadata);
    }

}

