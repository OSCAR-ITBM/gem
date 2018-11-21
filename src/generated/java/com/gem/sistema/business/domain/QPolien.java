package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPolien is a Querydsl query type for Polien
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPolien extends EntityPathBase<Polien> {

    private static final long serialVersionUID = -1906204775L;

    public static final QPolien polien = new QPolien("polien");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final StringPath cappol = createString("cappol");

    public final NumberPath<Integer> ccondu = createNumber("ccondu", Integer.class);

    public final NumberPath<Integer> cconfl = createNumber("cconfl", Integer.class);

    public final NumberPath<Integer> cconii = createNumber("cconii", Integer.class);

    public final NumberPath<Integer> cconin = createNumber("cconin", Integer.class);

    public final NumberPath<java.math.BigDecimal> ccontrol = createNumber("ccontrol", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ccuenta = createNumber("ccuenta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cdebe = createNumber("cdebe", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> chaber = createNumber("chaber", java.math.BigDecimal.class);

    public final StringPath clvpol = createString("clvpol");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final NumberPath<java.math.BigDecimal> cscta = createNumber("cscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> csscta = createNumber("csscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cssscta = createNumber("cssscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> csssscta = createNumber("csssscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> cta600 = createNumber("cta600", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ctc600 = createNumber("ctc600", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ctcuenta = createNumber("ctcuenta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ctscta = createNumber("ctscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ctsscta = createNumber("ctsscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ctssscta = createNumber("ctssscta", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ctsssscta = createNumber("ctsssscta", java.math.BigDecimal.class);

    public final StringPath esppol = createString("esppol");

    public final StringPath estatus = createString("estatus");

    public final DatePath<java.util.Date> fecafec = createDate("fecafec", java.util.Date.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final DatePath<java.util.Date> fecpol = createDate("fecpol", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<Integer> nivaut = createNumber("nivaut", Integer.class);

    public final NumberPath<java.math.BigDecimal> p1000 = createNumber("p1000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p2000 = createNumber("p2000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p3000 = createNumber("p3000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p4000 = createNumber("p4000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p5000 = createNumber("p5000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p6000 = createNumber("p6000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p7000 = createNumber("p7000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p8000 = createNumber("p8000", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> p9000 = createNumber("p9000", java.math.BigDecimal.class);

    public final StringPath polclv1 = createString("polclv1");

    public final StringPath polclv11 = createString("polclv11");

    public final StringPath polclv12 = createString("polclv12");

    public final StringPath polclv13 = createString("polclv13");

    public final StringPath polclv2 = createString("polclv2");

    public final StringPath polclv3 = createString("polclv3");

    public final NumberPath<java.math.BigDecimal> renpol = createNumber("renpol", java.math.BigDecimal.class);

    public final StringPath staafe = createString("staafe");

    public final StringPath staaut = createString("staaut");

    public final StringPath stades = createString("stades");

    public final StringPath stapol = createString("stapol");

    public final StringPath tippol = createString("tippol");

    public final StringPath userid = createString("userid");

    public QPolien(String variable) {
        super(Polien.class, forVariable(variable));
    }

    public QPolien(Path<? extends Polien> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolien(PathMetadata<?> metadata) {
        super(Polien.class, metadata);
    }

}

