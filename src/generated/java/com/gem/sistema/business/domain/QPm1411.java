package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1411 is a Querydsl query type for Pm1411
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1411 extends EntityPathBase<Pm1411> {

    private static final long serialVersionUID = -1909862092L;

    public static final QPm1411 pm1411 = new QPm1411("pm1411");

    public final NumberPath<java.math.BigDecimal> aam = createNumber("aam", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acusp = createNumber("acusp", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acuspspa = createNumber("acuspspa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> acute = createNumber("acute", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aid = createNumber("aid", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> aj = createNumber("aj", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> als = createNumber("als", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ampg = createNumber("ampg", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> amse = createNumber("amse", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> apcd = createNumber("apcd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ape = createNumber("ape", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> avma = createNumber("avma", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<java.math.BigDecimal> dif = createNumber("dif", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ei = createNumber("ei", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> ied = createNumber("ied", java.math.BigDecimal.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final NumberPath<java.math.BigDecimal> oana = createNumber("oana", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> oapf = createNumber("oapf", java.math.BigDecimal.class);

    public final StringPath obssp = createString("obssp");

    public final StringPath obsspspa = createString("obsspspa");

    public final StringPath obste = createString("obste");

    public final NumberPath<java.math.BigDecimal> paam = createNumber("paam", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pafd = createNumber("pafd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paid = createNumber("paid", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> paj = createNumber("paj", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pals = createNumber("pals", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pampg = createNumber("pampg", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pamse = createNumber("pamse", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> papcd = createNumber("papcd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pape = createNumber("pape", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pavma = createNumber("pavma", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pdif = createNumber("pdif", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pei = createNumber("pei", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pied = createNumber("pied", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> poana = createNumber("poana", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> poapf = createNumber("poapf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ppafd = createNumber("ppafd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> psjaf = createNumber("psjaf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pspdc = createNumber("pspdc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sjaf = createNumber("sjaf", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sp = createNumber("sp", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> spdc = createNumber("spdc", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> spspa = createNumber("spspa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> te = createNumber("te", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public QPm1411(String variable) {
        super(Pm1411.class, forVariable(variable));
    }

    public QPm1411(Path<? extends Pm1411> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1411(PathMetadata<?> metadata) {
        super(Pm1411.class, metadata);
    }

}

