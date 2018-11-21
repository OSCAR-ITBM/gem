package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPolidu is a Querydsl query type for Polidu
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPolidu extends EntityPathBase<Polidu> {

    private static final long serialVersionUID = -1906204799L;

    public static final QPolidu polidu = new QPolidu("polidu");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anodeu = createNumber("anodeu", Integer.class);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final StringPath caopol = createString("caopol");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final StringPath cuenta = createString("cuenta");

    public final StringPath fecpag = createString("fecpag");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mesdeu = createNumber("mesdeu", Integer.class);

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<java.math.BigDecimal> mondeu = createNumber("mondeu", java.math.BigDecimal.class);

    public final NumberPath<Integer> numnpr = createNumber("numnpr", Integer.class);

    public final NumberPath<Integer> numpag = createNumber("numpag", Integer.class);

    public final NumberPath<java.math.BigDecimal> ordpol = createNumber("ordpol", java.math.BigDecimal.class);

    public final NumberPath<Integer> renpol = createNumber("renpol", Integer.class);

    public final StringPath scta = createString("scta");

    public final StringPath sscta = createString("sscta");

    public final StringPath ssscta = createString("ssscta");

    public final StringPath sssscta = createString("sssscta");

    public final NumberPath<Integer> tippag = createNumber("tippag", Integer.class);

    public final StringPath tippol = createString("tippol");

    public final StringPath userid = createString("userid");

    public final StringPath wtipo = createString("wtipo");

    public QPolidu(String variable) {
        super(Polidu.class, forVariable(variable));
    }

    public QPolidu(Path<? extends Polidu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPolidu(PathMetadata<?> metadata) {
        super(Polidu.class, metadata);
    }

}

