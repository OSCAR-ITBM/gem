package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QObra is a Querydsl query type for Obra
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QObra extends EntityPathBase<Obra> {

    private static final long serialVersionUID = 176745078L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObra obra = new QObra("obra");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> auto1 = createNumber("auto1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto10 = createNumber("auto10", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto11 = createNumber("auto11", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto12 = createNumber("auto12", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto13 = createNumber("auto13", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto2 = createNumber("auto2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto3 = createNumber("auto3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto4 = createNumber("auto4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto5 = createNumber("auto5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto6 = createNumber("auto6", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto7 = createNumber("auto7", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto8 = createNumber("auto8", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> auto9 = createNumber("auto9", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> avancef = createNumber("avancef", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> avancefa = createNumber("avancefa", java.math.BigDecimal.class);

    public final StringPath capturo = createString("capturo");

    public final QCatsector catsector;

    public final StringPath clvdep = createString("clvdep");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final StringPath ffin = createString("ffin");

    public final StringPath fn = createString("fn");

    public final StringPath fun = createString("fun");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath justificacion = createString("justificacion");

    public final NumberPath<Integer> ncontrol = createNumber("ncontrol", Integer.class);

    public final StringPath nomobra = createString("nomobra");

    public final NumberPath<Integer> poblacion = createNumber("poblacion", Integer.class);

    public final StringPath prog = createString("prog");

    public final StringPath proy = createString("proy");

    public final StringPath subfun = createString("subfun");

    public final StringPath subprog = createString("subprog");

    public final StringPath tipoasig = createString("tipoasig");

    public final StringPath tipoejec = createString("tipoejec");

    public final NumberPath<java.math.BigDecimal> toeje1 = createNumber("toeje1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje10 = createNumber("toeje10", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje11 = createNumber("toeje11", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje12 = createNumber("toeje12", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje13 = createNumber("toeje13", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje2 = createNumber("toeje2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje3 = createNumber("toeje3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje4 = createNumber("toeje4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje5 = createNumber("toeje5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje6 = createNumber("toeje6", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje7 = createNumber("toeje7", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje8 = createNumber("toeje8", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> toeje9 = createNumber("toeje9", java.math.BigDecimal.class);

    public final StringPath ubicacion = createString("ubicacion");

    public final StringPath userid = createString("userid");

    public QObra(String variable) {
        this(Obra.class, forVariable(variable), INITS);
    }

    public QObra(Path<? extends Obra> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObra(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QObra(PathMetadata<?> metadata, PathInits inits) {
        this(Obra.class, metadata, inits);
    }

    public QObra(Class<? extends Obra> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.catsector = inits.isInitialized("catsector") ? new QCatsector(forProperty("catsector")) : null;
    }

}

