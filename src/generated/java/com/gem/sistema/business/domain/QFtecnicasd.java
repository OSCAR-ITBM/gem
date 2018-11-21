package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFtecnicasd is a Querydsl query type for Ftecnicasd
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFtecnicasd extends EntityPathBase<Ftecnicasd> {

    private static final long serialVersionUID = -1810632566L;

    public static final QFtecnicasd ftecnicasd = new QFtecnicasd("ftecnicasd");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> alcan1 = createNumber("alcan1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcan2 = createNumber("alcan2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcan3 = createNumber("alcan3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcan4 = createNumber("alcan4", java.math.BigDecimal.class);

    public final StringPath clvdep = createString("clvdep");

    public final StringPath clvfin = createString("clvfin");

    public final StringPath clvfun = createString("clvfun");

    public final NumberPath<Integer> codigo = createNumber("codigo", Integer.class);

    public final StringPath cveind = createString("cveind");

    public final StringPath cvetemas = createString("cvetemas");

    public final StringPath cvevar = createString("cvevar");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> metanual = createNumber("metanual", java.math.BigDecimal.class);

    public final StringPath nope = createString("nope");

    public final StringPath operacion = createString("operacion");

    public final StringPath pe = createString("pe");

    public final NumberPath<java.math.BigDecimal> porcalcan1 = createNumber("porcalcan1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcalcan2 = createNumber("porcalcan2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcalcan3 = createNumber("porcalcan3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcalcan4 = createNumber("porcalcan4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcprog1 = createNumber("porcprog1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcprog2 = createNumber("porcprog2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcprog3 = createNumber("porcprog3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcprog4 = createNumber("porcprog4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> prog1 = createNumber("prog1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> prog2 = createNumber("prog2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> prog3 = createNumber("prog3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> prog4 = createNumber("prog4", java.math.BigDecimal.class);

    public final StringPath tema = createString("tema");

    public final StringPath unimed = createString("unimed");

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public final StringPath variables = createString("variables");

    public QFtecnicasd(String variable) {
        super(Ftecnicasd.class, forVariable(variable));
    }

    public QFtecnicasd(Path<? extends Ftecnicasd> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFtecnicasd(PathMetadata<?> metadata) {
        super(Ftecnicasd.class, metadata);
    }

}

