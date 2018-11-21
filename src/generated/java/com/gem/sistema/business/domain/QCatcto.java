package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCatcto is a Querydsl query type for Catcto
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCatcto extends EntityPathBase<Catcto> {

    private static final long serialVersionUID = 2003887292L;

    public static final QCatcto catcto = new QCatcto("catcto");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Float> antpag = createNumber("antpag", Float.class);

    public final StringPath capcto = createString("capcto");

    public final StringPath clvcto = createString("clvcto");

    public final NumberPath<Integer> clvobr = createNumber("clvobr", Integer.class);

    public final NumberPath<Integer> clvprv = createNumber("clvprv", Integer.class);

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final DateTimePath<java.util.Date> fecfin = createDateTime("fecfin", java.util.Date.class);

    public final DateTimePath<java.util.Date> fecini = createDateTime("fecini", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Float> monant = createNumber("monant", Float.class);

    public final NumberPath<Double> montot = createNumber("montot", Double.class);

    public final StringPath nomcto = createString("nomcto");

    public QCatcto(String variable) {
        super(Catcto.class, forVariable(variable));
    }

    public QCatcto(Path<? extends Catcto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCatcto(PathMetadata<?> metadata) {
        super(Catcto.class, metadata);
    }

}

