package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCfddetails is a Querydsl query type for Cfddetails
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCfddetails extends EntityPathBase<Cfddetails> {

    private static final long serialVersionUID = 1505571573L;

    public static final QCfddetails cfddetails = new QCfddetails("cfddetails");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Long> cant = createNumber("cant", Long.class);

    public final StringPath cant2 = createString("cant2");

    public final StringPath concepto = createString("concepto");

    public final NumberPath<Long> folio = createNumber("folio", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Long> idSector = createNumber("idSector", Long.class);

    public final NumberPath<java.math.BigDecimal> importe = createNumber("importe", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> preciounit = createNumber("preciounit", java.math.BigDecimal.class);

    public final StringPath serie = createString("serie");

    public final StringPath userid = createString("userid");

    public QCfddetails(String variable) {
        super(Cfddetails.class, forVariable(variable));
    }

    public QCfddetails(Path<? extends Cfddetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCfddetails(PathMetadata<?> metadata) {
        super(Cfddetails.class, metadata);
    }

}

