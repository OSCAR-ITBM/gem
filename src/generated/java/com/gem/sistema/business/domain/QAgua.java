package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAgua is a Querydsl query type for Agua
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAgua extends EntityPathBase<Agua> {

    private static final long serialVersionUID = 176332902L;

    public static final QAgua agua = new QAgua("agua");

    public final NumberPath<java.math.BigDecimal> alcant = createNumber("alcant", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcanta = createNumber("alcanta", java.math.BigDecimal.class);

    public final StringPath campo1 = createString("campo1");

    public final NumberPath<Integer> campo2 = createNumber("campo2", Integer.class);

    public final NumberPath<java.math.BigDecimal> conexr = createNumber("conexr", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> conexra = createNumber("conexra", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> drenaje = createNumber("drenaje", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> drenajea = createNumber("drenajea", java.math.BigDecimal.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<java.math.BigDecimal> gtosejec = createNumber("gtosejec", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> gtosejeca = createNumber("gtosejeca", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<java.math.BigDecimal> indemni = createNumber("indemni", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> indemnia = createNumber("indemnia", java.math.BigDecimal.class);

    public final StringPath mes = createString("mes");

    public final NumberPath<java.math.BigDecimal> multas = createNumber("multas", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> multasa = createNumber("multasa", java.math.BigDecimal.class);

    public final NumberPath<Integer> ntomasmen = createNumber("ntomasmen", Integer.class);

    public final NumberPath<java.math.BigDecimal> otros = createNumber("otros", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> otrosa = createNumber("otrosa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> racumaa = createNumber("racumaa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ralmes = createNumber("ralmes", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rdeaa = createNumber("rdeaa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rdelmes = createNumber("rdelmes", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> recargos = createNumber("recargos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> recargosa = createNumber("recargosa", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sagua = createNumber("sagua", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> saguaa = createNumber("saguaa", java.math.BigDecimal.class);

    public final NumberPath<Integer> tomasantes = createNumber("tomasantes", Integer.class);

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public QAgua(String variable) {
        super(Agua.class, forVariable(variable));
    }

    public QAgua(Path<? extends Agua> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgua(PathMetadata<?> metadata) {
        super(Agua.class, metadata);
    }

}

