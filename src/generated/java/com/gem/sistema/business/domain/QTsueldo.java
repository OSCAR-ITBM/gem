package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTsueldo is a Querydsl query type for Tsueldo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTsueldo extends EntityPathBase<Tsueldo> {

    private static final long serialVersionUID = 414957204L;

    public static final QTsueldo tsueldo = new QTsueldo("tsueldo");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Double> aguinaldo = createNumber("aguinaldo", Double.class);

    public final NumberPath<Double> aguinaldoeven = createNumber("aguinaldoeven", Double.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Double> compensacion = createNumber("compensacion", Double.class);

    public final StringPath cvepuesto = createString("cvepuesto");

    public final NumberPath<Double> dietas = createNumber("dietas", Double.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Double> gratificacion = createNumber("gratificacion", Double.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath idUser = createString("idUser");

    public final NumberPath<Integer> nconfianza = createNumber("nconfianza", Integer.class);

    public final NumberPath<Integer> neventual = createNumber("neventual", Integer.class);

    public final StringPath nivel = createString("nivel");

    public final StringPath nompuesto = createString("nompuesto");

    public final NumberPath<Integer> nsindicalizados = createNumber("nsindicalizados", Integer.class);

    public final NumberPath<Double> opercepciones = createNumber("opercepciones", Double.class);

    public final NumberPath<Double> pv = createNumber("pv", Double.class);

    public final NumberPath<Double> sbase = createNumber("sbase", Double.class);

    public final NumberPath<Double> sbaseeven = createNumber("sbaseeven", Double.class);

    public final NumberPath<Integer> sectorId = createNumber("sectorId", Integer.class);

    public QTsueldo(String variable) {
        super(Tsueldo.class, forVariable(variable));
    }

    public QTsueldo(Path<? extends Tsueldo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTsueldo(PathMetadata<?> metadata) {
        super(Tsueldo.class, metadata);
    }

}

