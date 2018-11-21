package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPredial is a Querydsl query type for Predial
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPredial extends EntityPathBase<Predial> {

    private static final long serialVersionUID = 1116471519L;

    public static final QPredial predial = new QPredial("predial");

    public final StringPath campo1 = createString("campo1");

    public final NumberPath<Integer> campo2 = createNumber("campo2", Integer.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> gastosejec = createNumber("gastosejec", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> gastosejeca = createNumber("gastosejeca", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> impuestos = createNumber("impuestos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> impuestosa = createNumber("impuestosa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> indemni = createNumber("indemni", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> indemnia = createNumber("indemnia", java.math.BigDecimal.class);

    public final StringPath mes = createString("mes");

    public final NumberPath<java.math.BigDecimal> multas = createNumber("multas", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> multasa = createNumber("multasa", java.math.BigDecimal.class);

    public final NumberPath<Integer> nctasanterior = createNumber("nctasanterior", Integer.class);

    public final NumberPath<Integer> nctasdmes = createNumber("nctasdmes", Integer.class);

    public final NumberPath<java.math.BigDecimal> otros = createNumber("otros", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otrosa = createNumber("otrosa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> racumaa = createNumber("racumaa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ralmes = createNumber("ralmes", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rdeaa = createNumber("rdeaa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rdelmes = createNumber("rdelmes", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> recargos = createNumber("recargos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> recargosa = createNumber("recargosa", java.math.BigDecimal.class);

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public QPredial(String variable) {
        super(Predial.class, forVariable(variable));
    }

    public QPredial(Path<? extends Predial> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPredial(PathMetadata<?> metadata) {
        super(Predial.class, metadata);
    }

}

