package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0111 is a Querydsl query type for Pm0111
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0111 extends EntityPathBase<Pm0111> {

    private static final long serialVersionUID = -1909894766L;

    public static final QPm0111 pm0111 = new QPm0111("pm0111");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> njma = createNumber("njma", Integer.class);

    public final NumberPath<Integer> njmc = createNumber("njmc", Integer.class);

    public final NumberPath<Integer> njmd = createNumber("njmd", Integer.class);

    public final NumberPath<Integer> njml = createNumber("njml", Integer.class);

    public final StringPath obsnjm = createString("obsnjm");

    public final StringPath obstji = createString("obstji");

    public final NumberPath<Integer> tjia = createNumber("tjia", Integer.class);

    public final NumberPath<Integer> tjic = createNumber("tjic", Integer.class);

    public final NumberPath<Integer> tjid = createNumber("tjid", Integer.class);

    public final NumberPath<Integer> tjil = createNumber("tjil", Integer.class);

    public final NumberPath<Integer> totjucon = createNumber("totjucon", Integer.class);

    public final NumberPath<Integer> totjuga = createNumber("totjuga", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0111(String variable) {
        super(Pm0111.class, forVariable(variable));
    }

    public QPm0111(Path<? extends Pm0111> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0111(PathMetadata<?> metadata) {
        super(Pm0111.class, metadata);
    }

}

