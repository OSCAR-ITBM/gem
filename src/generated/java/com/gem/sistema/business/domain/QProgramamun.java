package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QProgramamun is a Querydsl query type for Programamun
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProgramamun extends EntityPathBase<Programamun> {

    private static final long serialVersionUID = 125190805L;

    public static final QProgramamun programamun = new QProgramamun("programamun");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath cvedepa = createString("cvedepa");

    public final StringPath cvedepg = createString("cvedepg");

    public final StringPath descrip = createString("descrip");

    public final DatePath<java.util.Date> fecini = createDate("fecini", java.util.Date.class);

    public final DatePath<java.util.Date> fecter = createDate("fecter", java.util.Date.class);

    public final StringPath ffin = createString("ffin");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath locben = createString("locben");

    public final StringPath observa1 = createString("observa1");

    public final StringPath observa10 = createString("observa10");

    public final StringPath observa11 = createString("observa11");

    public final StringPath observa12 = createString("observa12");

    public final StringPath observa2 = createString("observa2");

    public final StringPath observa3 = createString("observa3");

    public final StringPath observa4 = createString("observa4");

    public final StringPath observa5 = createString("observa5");

    public final StringPath observa6 = createString("observa6");

    public final StringPath observa7 = createString("observa7");

    public final StringPath observa8 = createString("observa8");

    public final StringPath observa9 = createString("observa9");

    public final NumberPath<Integer> pobben = createNumber("pobben", Integer.class);

    public final StringPath programa = createString("programa");

    public final StringPath userid = createString("userid");

    public QProgramamun(String variable) {
        super(Programamun.class, forVariable(variable));
    }

    public QProgramamun(Path<? extends Programamun> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProgramamun(PathMetadata<?> metadata) {
        super(Programamun.class, metadata);
    }

}

