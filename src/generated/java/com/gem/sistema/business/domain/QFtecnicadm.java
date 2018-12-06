package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFtecnicadm is a Querydsl query type for Ftecnicadm
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFtecnicadm extends EntityPathBase<Ftecnicadm> {

    private static final long serialVersionUID = -1810633022L;

    public static final QFtecnicadm ftecnicadm = new QFtecnicadm("ftecnicadm");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clvdep = createString("clvdep");

    public final StringPath clvfin = createString("clvfin");

    public final StringPath clvfun = createString("clvfun");

    public final StringPath cveind = createString("cveind");

    public final StringPath cvetemas = createString("cvetemas");

    public final StringPath descanual = createString("descanual");

    public final StringPath descfac = createString("descfac");

    public final StringPath dimension = createString("dimension");

    public final StringPath elaboro = createString("elaboro");

    public final NumberPath<java.math.BigDecimal> factor = createNumber("factor", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final StringPath formula = createString("formula");

    public final StringPath frecuencia = createString("frecuencia");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath interpretacion = createString("interpretacion");

    public final StringPath medios = createString("medios");

    public final NumberPath<java.math.BigDecimal> metanuale = createNumber("metanuale", java.math.BigDecimal.class);

    public final StringPath metasact = createString("metasact");

    public final StringPath nomind = createString("nomind");

    public final StringPath nope = createString("nope");

    public final StringPath objetivo = createString("objetivo");

    public final StringPath pe = createString("pe");

    public final StringPath tema = createString("tema");

    public final StringPath tipo = createString("tipo");

    public final NumberPath<java.math.BigDecimal> trim1e = createNumber("trim1e", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trim2e = createNumber("trim2e", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trim3e = createNumber("trim3e", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> trim4e = createNumber("trim4e", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public final StringPath valido = createString("valido");

    public QFtecnicadm(String variable) {
        super(Ftecnicadm.class, forVariable(variable));
    }

    public QFtecnicadm(Path<? extends Ftecnicadm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFtecnicadm(PathMetadata<?> metadata) {
        super(Ftecnicadm.class, metadata);
    }

}

