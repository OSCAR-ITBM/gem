package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPolide is a Querydsl query type for Polide
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPolide extends EntityPathBase<Polide> {

    private static final long serialVersionUID = -1906204815L;

    public static final QPolide polide = new QPolide("polide");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final NumberPath<java.math.BigDecimal> antpag = createNumber("antpag", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> canpol = createNumber("canpol", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> canpolh = createNumber("canpolh", java.math.BigDecimal.class);

    public final StringPath caopol = createString("caopol");

    public final NumberPath<Integer> clvban = createNumber("clvban", Integer.class);

    public final StringPath clvcto = createString("clvcto");

    public final StringPath clvfuen = createString("clvfuen");

    public final StringPath concep = createString("concep");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final StringPath cuenta = createString("cuenta");

    public final StringPath cuenta1 = createString("cuenta1");

    public final StringPath dn = createString("dn");

    public final DatePath<java.util.Date> fecpol = createDate("fecpol", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<java.math.BigDecimal> numdet = createNumber("numdet", java.math.BigDecimal.class);

    public final StringPath recpol = createString("recpol");

    public final NumberPath<java.math.BigDecimal> refpol = createNumber("refpol", java.math.BigDecimal.class);

    public final NumberPath<Integer> rela = createNumber("rela", Integer.class);

    public final NumberPath<java.math.BigDecimal> renpol = createNumber("renpol", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> renpolr = createNumber("renpolr", java.math.BigDecimal.class);

    public final StringPath scta = createString("scta");

    public final StringPath sscta = createString("sscta");

    public final StringPath ssscta = createString("ssscta");

    public final StringPath sssscta = createString("sssscta");

    public final NumberPath<Integer> stacon = createNumber("stacon", Integer.class);

    public final NumberPath<Integer> tipcon = createNumber("tipcon", Integer.class);

    public final StringPath tippol = createString("tippol");

    public final StringPath tipr = createString("tipr");

    public final StringPath userid = createString("userid");

    public QPolide(String variable) {
        super(Polide.class, forVariable(variable));
    }

    public QPolide(Path<? extends Polide> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolide(PathMetadata<?> metadata) {
        super(Polide.class, metadata);
    }

}

