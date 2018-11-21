package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3911 is a Querydsl query type for Pm3911
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3911 extends EntityPathBase<Pm3911> {

    private static final long serialVersionUID = -1909797705L;

    public static final QPm3911 pm3911 = new QPm3911("pm3911");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath manorgno = createString("manorgno");

    public final StringPath manorgpro = createString("manorgpro");

    public final StringPath manorgsi = createString("manorgsi");

    public final StringPath manorgvig = createString("manorgvig");

    public final StringPath manprono = createString("manprono");

    public final StringPath manpropro = createString("manpropro");

    public final StringPath manprosi = createString("manprosi");

    public final StringPath manprovig = createString("manprovig");

    public final StringPath observaciones = createString("observaciones");

    public final StringPath obsmanorg = createString("obsmanorg");

    public final StringPath obsmanpro = createString("obsmanpro");

    public final StringPath obsorg = createString("obsorg");

    public final StringPath obsreg = createString("obsreg");

    public final StringPath orgno = createString("orgno");

    public final StringPath orgpro = createString("orgpro");

    public final StringPath orgsi = createString("orgsi");

    public final StringPath orgvig = createString("orgvig");

    public final DatePath<java.util.Date> perfinman = createDate("perfinman", java.util.Date.class);

    public final DatePath<java.util.Date> perfinmo = createDate("perfinmo", java.util.Date.class);

    public final DatePath<java.util.Date> perfino = createDate("perfino", java.util.Date.class);

    public final DatePath<java.util.Date> perfinreg = createDate("perfinreg", java.util.Date.class);

    public final DatePath<java.util.Date> periniman = createDate("periniman", java.util.Date.class);

    public final DatePath<java.util.Date> perinimo = createDate("perinimo", java.util.Date.class);

    public final DatePath<java.util.Date> perinio = createDate("perinio", java.util.Date.class);

    public final DatePath<java.util.Date> preinireg = createDate("preinireg", java.util.Date.class);

    public final StringPath regno = createString("regno");

    public final StringPath regpro = createString("regpro");

    public final StringPath regsi = createString("regsi");

    public final StringPath regvig = createString("regvig");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm3911(String variable) {
        super(Pm3911.class, forVariable(variable));
    }

    public QPm3911(Path<? extends Pm3911> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3911(PathMetadata<?> metadata) {
        super(Pm3911.class, metadata);
    }

}

