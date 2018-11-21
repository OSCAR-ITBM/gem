package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm3111 is a Querydsl query type for Pm3111
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm3111 extends EntityPathBase<Pm3111> {

    private static final long serialVersionUID = -1909805393L;

    public static final QPm3111 pm3111 = new QPm3111("pm3111");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> cpci = createNumber("cpci", Integer.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> ntloc = createNumber("ntloc", Integer.class);

    public final StringPath obscpci = createString("obscpci");

    public final StringPath obsntloc = createString("obsntloc");

    public final StringPath userid = createString("userid");

    public QPm3111(String variable) {
        super(Pm3111.class, forVariable(variable));
    }

    public QPm3111(Path<? extends Pm3111> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm3111(PathMetadata<?> metadata) {
        super(Pm3111.class, metadata);
    }

}

