package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBienesinm is a Querydsl query type for Bienesinm
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QBienesinm extends EntityPathBase<Bienesinm> {

    private static final long serialVersionUID = 499574934L;

    public static final QBienesinm bienesinm = new QBienesinm("bienesinm");

    public final NumberPath<java.math.BigDecimal> campo1 = createNumber("campo1", java.math.BigDecimal.class);

    public final StringPath campo2 = createString("campo2");

    public final StringPath campo3 = createString("campo3");

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final NumberPath<Integer> consec = createNumber("consec", Integer.class);

    public final StringPath cuenta = createString("cuenta");

    public final StringPath cvecatas = createString("cvecatas");

    public final NumberPath<java.math.BigDecimal> depreacum = createNumber("depreacum", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> depreperiodo = createNumber("depreperiodo", java.math.BigDecimal.class);

    public final StringPath escritura = createString("escritura");

    public final DatePath<java.util.Date> fecadq = createDate("fecadq", java.util.Date.class);

    public final DatePath<java.util.Date> fecalta = createDate("fecalta", java.util.Date.class);

    public final DatePath<java.util.Date> fecbaja = createDate("fecbaja", java.util.Date.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final DatePath<java.util.Date> fecpol = createDate("fecpol", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath juridica = createString("juridica");

    public final StringPath localidad = createString("localidad");

    public final NumberPath<Integer> mes = createNumber("mes", Integer.class);

    public final StringPath modadqui = createString("modadqui");

    public final StringPath nnorte = createString("nnorte");

    public final StringPath nomcta = createString("nomcta");

    public final StringPath nommueble = createString("nommueble");

    public final StringPath noriente = createString("noriente");

    public final NumberPath<java.math.BigDecimal> norte = createNumber("norte", java.math.BigDecimal.class);

    public final StringPath nponiente = createString("nponiente");

    public final StringPath nsur = createString("nsur");

    public final StringPath obs = createString("obs");

    public final NumberPath<java.math.BigDecimal> oriente = createNumber("oriente", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> poniente = createNumber("poniente", java.math.BigDecimal.class);

    public final StringPath registro = createString("registro");

    public final StringPath scta = createString("scta");

    public final StringPath sscta = createString("sscta");

    public final StringPath ssscta = createString("ssscta");

    public final StringPath sssscta = createString("sssscta");

    public final NumberPath<java.math.BigDecimal> supconst = createNumber("supconst", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> superficie = createNumber("superficie", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sur = createNumber("sur", java.math.BigDecimal.class);

    public final StringPath tippol = createString("tippol");

    public final StringPath tvidautil = createString("tvidautil");

    public final StringPath ubicacion = createString("ubicacion");

    public final StringPath userid = createString("userid");

    public final StringPath uso = createString("uso");

    public final NumberPath<java.math.BigDecimal> valorcatas = createNumber("valorcatas", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> valorinm = createNumber("valorinm", java.math.BigDecimal.class);

    public final StringPath zona = createString("zona");

    public QBienesinm(String variable) {
        super(Bienesinm.class, forVariable(variable));
    }

    public QBienesinm(Path<? extends Bienesinm> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBienesinm(PathMetadata<?> metadata) {
        super(Bienesinm.class, metadata);
    }

}

