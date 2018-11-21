package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCuenta is a Querydsl query type for Cuenta
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCuenta extends EntityPathBase<Cuenta> {

    private static final long serialVersionUID = 2021921404L;

    public static final QCuenta cuenta1 = new QCuenta("cuenta1");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> abonos1 = createNumber("abonos1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos10 = createNumber("abonos10", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos11 = createNumber("abonos11", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos12 = createNumber("abonos12", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos13 = createNumber("abonos13", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos14 = createNumber("abonos14", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos15 = createNumber("abonos15", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos16 = createNumber("abonos16", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos2 = createNumber("abonos2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos3 = createNumber("abonos3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos4 = createNumber("abonos4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos5 = createNumber("abonos5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos6 = createNumber("abonos6", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos7 = createNumber("abonos7", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos8 = createNumber("abonos8", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> abonos9 = createNumber("abonos9", java.math.BigDecimal.class);

    public final StringPath capcta = createString("capcta");

    public final NumberPath<java.math.BigDecimal> cargos1 = createNumber("cargos1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos10 = createNumber("cargos10", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos11 = createNumber("cargos11", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos12 = createNumber("cargos12", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos13 = createNumber("cargos13", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos14 = createNumber("cargos14", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos15 = createNumber("cargos15", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos16 = createNumber("cargos16", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos2 = createNumber("cargos2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos3 = createNumber("cargos3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos4 = createNumber("cargos4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos5 = createNumber("cargos5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos6 = createNumber("cargos6", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos7 = createNumber("cargos7", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos8 = createNumber("cargos8", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cargos9 = createNumber("cargos9", java.math.BigDecimal.class);

    public final StringPath cuenta = createString("cuenta");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Long> idsector = createNumber("idsector", Long.class);

    public final StringPath nivcta = createString("nivcta");

    public final StringPath nomcta = createString("nomcta");

    public final NumberPath<Integer> notcta = createNumber("notcta", Integer.class);

    public final NumberPath<java.math.BigDecimal> salini = createNumber("salini", java.math.BigDecimal.class);

    public final StringPath scuenta = createString("scuenta");

    public final StringPath sscuenta = createString("sscuenta");

    public final StringPath ssscuenta = createString("ssscuenta");

    public final StringPath sssscuenta = createString("sssscuenta");

    public final StringPath stacta = createString("stacta");

    public final StringPath userid = createString("userid");

    public final NumberPath<Integer> xnicta = createNumber("xnicta", Integer.class);

    public QCuenta(String variable) {
        super(Cuenta.class, forVariable(variable));
    }

    public QCuenta(Path<? extends Cuenta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCuenta(PathMetadata<?> metadata) {
        super(Cuenta.class, metadata);
    }

}

