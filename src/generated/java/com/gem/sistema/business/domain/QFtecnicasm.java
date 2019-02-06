package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QFtecnicasm is a Querydsl query type for Ftecnicasm
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QFtecnicasm extends EntityPathBase<Ftecnicasm> {

    private static final long serialVersionUID = -1810632557L;

    public static final QFtecnicasm ftecnicasm = new QFtecnicasm("ftecnicasm");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> alcane1 = createNumber("alcane1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcane2 = createNumber("alcane2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcane3 = createNumber("alcane3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcane4 = createNumber("alcane4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcporcen1 = createNumber("alcporcen1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcporcen2 = createNumber("alcporcen2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcporcen3 = createNumber("alcporcen3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> alcporcen4 = createNumber("alcporcen4", java.math.BigDecimal.class);

    public final StringPath ambito1 = createString("ambito1");

    public final NumberPath<Integer> ambito2 = createNumber("ambito2", Integer.class);

    public final StringPath clvdep = createString("clvdep");

    public final StringPath clvfin = createString("clvfin");

    public final StringPath clvfun = createString("clvfun");

    public final StringPath cobertura = createString("cobertura");

    public final StringPath cveind = createString("cveind");

    public final StringPath cvetemas = createString("cvetemas");

    public final StringPath descfac = createString("descfac");

    public final StringPath descripcion = createString("descripcion");

    public final StringPath dimension = createString("dimension");

    public final NumberPath<java.math.BigDecimal> ef1 = createNumber("ef1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ef2 = createNumber("ef2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ef3 = createNumber("ef3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> ef4 = createNumber("ef4", java.math.BigDecimal.class);

    public final StringPath elaboro = createString("elaboro");

    public final StringPath evaluacion = createString("evaluacion");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final StringPath formula = createString("formula");

    public final StringPath frecuencia = createString("frecuencia");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath interpretacion = createString("interpretacion");

    public final StringPath lineaBase = createString("lineaBase");

    public final NumberPath<java.math.BigDecimal> metanuale1 = createNumber("metanuale1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> metanuale2 = createNumber("metanuale2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> metanuale3 = createNumber("metanuale3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> metanuale4 = createNumber("metanuale4", java.math.BigDecimal.class);

    public final StringPath nomind = createString("nomind");

    public final StringPath nope = createString("nope");

    public final StringPath objetivo = createString("objetivo");

    public final StringPath pe = createString("pe");

    public final NumberPath<java.math.BigDecimal> porcalcane1 = createNumber("porcalcane1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcalcane2 = createNumber("porcalcane2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcalcane3 = createNumber("porcalcane3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcalcane4 = createNumber("porcalcane4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcproge1 = createNumber("porcproge1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcproge2 = createNumber("porcproge2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcproge3 = createNumber("porcproge3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> porcproge4 = createNumber("porcproge4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> proge1 = createNumber("proge1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> proge2 = createNumber("proge2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> proge3 = createNumber("proge3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> proge4 = createNumber("proge4", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> progporcen1 = createNumber("progporcen1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> progporcen2 = createNumber("progporcen2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> progporcen3 = createNumber("progporcen3", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> progporcen4 = createNumber("progporcen4", java.math.BigDecimal.class);

    public final StringPath semaforo11 = createString("semaforo11");

    public final StringPath semaforo12 = createString("semaforo12");

    public final StringPath semaforo13 = createString("semaforo13");

    public final StringPath semaforo14 = createString("semaforo14");

    public final StringPath semaforo21 = createString("semaforo21");

    public final StringPath semaforo22 = createString("semaforo22");

    public final StringPath semaforo23 = createString("semaforo23");

    public final StringPath semaforo24 = createString("semaforo24");

    public final StringPath tema = createString("tema");

    public final StringPath userid = createString("userid");

    public final StringPath usuario = createString("usuario");

    public final StringPath valido = createString("valido");

    public QFtecnicasm(String variable) {
        super(Ftecnicasm.class, forVariable(variable));
    }

    public QFtecnicasm(Path<? extends Ftecnicasm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFtecnicasm(PathMetadata<?> metadata) {
        super(Ftecnicasm.class, metadata);
    }

}

